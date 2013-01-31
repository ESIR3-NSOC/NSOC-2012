package esir.dom12.nsoc.nfc;
/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 09/01/13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */

import esir.dom12.nsoc.bdd.ConnexionBDDInterface;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

@Provides(value = {
        @ProvidedPort(name = "entreeFromGaToNfc", type = PortType.SERVICE, className = NFCTranslatorInterface.class)
})

@Requires(value = {
        @RequiredPort(name = "sortieFromNfcToBdd", type = PortType.SERVICE, className = ConnexionBDDInterface.class, optional = true)
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
    public String receiveNumeroTagNFCFromGestionAcces (String numeroTagNfc) {
        String [] temp = numeroTagNfc.split(";");
        numeroTagNFC = Integer.valueOf(temp[0]);
        ConnexionBDDInterface cbi = getPortByName("sortieFromNfcToBdd", ConnexionBDDInterface.class);

        String requeteSQL = "SELECT nom FROM listeEtudiants WHERE numeroTagNFC = " + numeroTagNFC + ";" + "SELECT prenom FROM listeEtudiants WHERE numeroTagNFC = " + numeroTagNFC;
        nomPrenomEtudiant = cbi.sendRequestFromNfcToBdd(requeteSQL);

        return nomPrenomEtudiant;
    }
}