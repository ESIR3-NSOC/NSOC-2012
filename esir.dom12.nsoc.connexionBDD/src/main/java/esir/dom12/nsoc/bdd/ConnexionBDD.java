package esir.dom12.nsoc.bdd; /**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 09/01/13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.sql.*;

@Provides({
        @ProvidedPort(name = "entreeFromNfcToBdd", type = PortType.MESSAGE)
})
@Requires({
        @RequiredPort(name = "sortieFromBddToNfc", type = PortType.MESSAGE, optional = true)
})
@DictionaryType({
        @DictionaryAttribute(name = "ConnexionDelay", defaultValue = "2000", optional = true)
})

@ComponentType
public class ConnexionBDD extends AbstractComponentType {
    String url;
    String user;
    String password;
    String requeteNom;
    String requetePrenom;
    String nomPrenomEtudiant;
    ConnexionBDD c = new ConnexionBDD();

    @Start
    public void startComponent() {
        System.out.println("Connexion Base de Données:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Connexion Base de Données:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Connexion Base de Données:: Update");
    }

    public ConnexionBDD () {
        this.url = "nsoc";
        this.user = "root";
        this.password = "";
    }

    /**
     * execute la connexion avec le SGBD (MySQL)
     */
    public Connection execute () throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver OK !");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + url + user + password);
        System.out.println("Connection effective");

        return connection;
    }

    @Port(name = "entreeFromNfcToBdd")
    public void receiveRequestFromNFCTranslator (Object o) throws SQLException, ClassNotFoundException {

        String data = new String ((byte[]) o);
        String [] temp = data.split(";");
        requeteNom = temp[0];
        requetePrenom = temp[1];

        Statement state = c.execute().createStatement();

        ResultSet nom = state.executeQuery(requeteNom);
        nom.next();
        nom.getString(1);

        ResultSet prenom = state.executeQuery(requetePrenom);
        prenom.next();
        prenom.getString(1);

        nomPrenomEtudiant = nom + " " + prenom;

        MessagePort portNFC = getPortByName("sortieFromBddToNfc", MessagePort.class);
        portNFC.process(nomPrenomEtudiant);
    }
}