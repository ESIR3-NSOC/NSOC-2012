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

import java.sql.SQLException;


@Provides(value = {
        @ProvidedPort(name = "entreeFromGaToNfc", type = PortType.SERVICE, className = NFCTranslatorInterface.class)
})

@Requires(value = {
        @RequiredPort(name = "sortieFromNfcToBdd", type = PortType.SERVICE, className = ConnexionBDDInterface.class, optional = true)
})
@DictionaryType({
        @DictionaryAttribute(name = "ConnexionDelay", defaultValue = "2000", optional = true)
})


@ComponentType
public class NFCTranslator extends AbstractComponentType implements NFCTranslatorInterface {

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
    public String sendNumeroTagNFCFromGestionAccesToNfc (String numeroTagNfc) throws SQLException, ClassNotFoundException {
        // Recupere la valeur de numeroTagNfc
        numeroTagNFC = Integer.valueOf(numeroTagNfc);
        // Etablie la connexion via le port sortieFromNfcToBdd
        ConnexionBDDInterface cbi = getPortByName("sortieFromNfcToBdd", ConnexionBDDInterface.class);
        // Ecrit la requete dans une chaine de caracteres
        String requeteSQL = "SELECT nom, prenom FROM listeEtudiants WHERE numeroTagNFC = " + numeroTagNFC;
        //  & Envoie cette requete a travers ce port afin de recuperer nomPrenomEtudiant
        nomPrenomEtudiant = cbi.sendRequestFromNfcToBdd(requeteSQL);
        // Rend la variable nomPrenomEtudiant
        return nomPrenomEtudiant;
    }
}