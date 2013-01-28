package esir.dom12.nsoc.nfc;
/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 09/01/13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

@Provides(value = {
        @ProvidedPort(name = "entreeFromGaToNfc", type = PortType.SERVICE, className = NFCTranslatorInterface.class),
        @ProvidedPort(name = "entreeFromBddToNfc", type = PortType.MESSAGE)
})

@Requires(value = {
        @RequiredPort(name = "sortieFromNfcToGa", type = PortType.SERVICE, className = NFCTranslatorInterface.class, optional = false),
        @RequiredPort(name = "sortieFromNfcToBdd", type = PortType.MESSAGE, optional = true)
})

@DictionaryType({
        @DictionaryAttribute(name = "NfcTranslatorDelay", defaultValue = "2000", optional = true)
})

@ComponentType
public class NFCTranslator extends AbstractComponentType {

    int numeroTagNFC;
    String nomPrenomEtudiant;

    @Start
    public void startComponent() {
        System.out.println("NFCTranslator:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("NFCTranslator:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("NFCTranslator:: Update");
    }

    @Port(name = "entreeFromGaToNfc", method = "sendNumeroTagNFCFromGestionAccesToNfc")
    public void receiveNumeroTagNFCFromGestionAcces (Object o) {
        String data = new String((byte[]) o);
        String [] temp = data.split(";");
        numeroTagNFC = Integer.valueOf(temp[0]);

        sendRequestToConnexionBDD();
        receiveNameFromConnexionBDD(o);

        MessagePort portGA = getPortByName("sortieFromNfcToGa", MessagePort.class);
        portGA.process(nomPrenomEtudiant);
    }

 //   @Port(name = "entreeFromGaToNfc", method = "receiveNameFromNfcToGestionAcces")
 //   public void sendNameToGestionAcces(Object o) {

 //   }

    public void sendRequestToConnexionBDD () {
        String requeteSQL = "SELECT nom FROM listeEtudiants WHERE numeroTagNFC = " + numeroTagNFC + ";" + "SELECT prenom FROM listeEtudiants WHERE numeroTagNFC = " + numeroTagNFC;
        MessagePort portBDD = getPortByName("sortieFromNfcToBdd", MessagePort.class);
        portBDD.process(requeteSQL);

    }

    @Port(name = "entreeFromBddToNfc")
    public void receiveNameFromConnexionBDD (Object o) {
        nomPrenomEtudiant = o.toString();
    }

}