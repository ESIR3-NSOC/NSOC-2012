package esir.dom12.nsoc.bdd; /**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 09/01/13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

import javax.swing.*;
import java.sql.*;

@Provides({
        @ProvidedPort(name = "entreeBdd", type = PortType.SERVICE, className = ConnexionBDDInterface.class)
})

@DictionaryType({
        @DictionaryAttribute(name = "ConnexionDelay", defaultValue = "2000", optional = true)
})

@ComponentType
public class ConnexionBDD extends AbstractComponentType implements ConnexionBDDInterface {

    String url;
    String user;
    String password;
    String requeteNomPrenom;
    String requeteTrombi;
    String nomPrenomEtudiant;
    ImageIcon trombiEtudiant;
    Connection connection;

    public ConnexionBDD () {
        this.url = "nsoc";
        this.user = "root";
        this.password = "";
    }

    @Start
    public void startComponent() throws ClassNotFoundException, SQLException {
        System.out.println("Connexion Base de Donnees:: Start");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver OK !");

        connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + url, user, password);
        System.out.println("Connexion reussie !");
    }

    @Stop
    public void stopComponent() throws SQLException {
        System.out.println("Connexion Base de Donnees:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Connexion Base de Donnees:: Update");
    }


    @Port(name = "entreeBdd", method = "sendRequestFromNfcToBdd")
     public String sendRequestFromNfcToBdd (String req) throws SQLException, ClassNotFoundException {
        System.out.println("SendRequestFromNfcToBdd");
        requeteNomPrenom = req;

        Statement state = connection.createStatement();

        ResultSet nomPrenom = state.executeQuery(requeteNomPrenom);
        nomPrenom.next();

        nomPrenomEtudiant = nomPrenom.getString(1) + " " + nomPrenom.getString(2);
        System.out.println(nomPrenomEtudiant);

        return nomPrenomEtudiant;
    }

    /**
     *
     * @param req : numero de l'etudiant (String)
     * @return  ImageIcon trombiEtudiant
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Port(name = "entreeBdd", method = "sendRequestFromTrombiToBdd")
    public ImageIcon sendRequestFromTrombiToBdd (String req) throws SQLException, ClassNotFoundException {

        requeteTrombi = "SELECT trombi FROM listeEtudiants WHERE numeroEtudiant = " + req;

        Statement state = connection.createStatement();

        ResultSet trombi = state.executeQuery(requeteTrombi);
        trombi.next();
        ImageIcon trombiImage = new ImageIcon("../BDD/" + trombi.getString(1));


        trombiEtudiant = trombiImage;

        return trombiEtudiant;
    }
}
