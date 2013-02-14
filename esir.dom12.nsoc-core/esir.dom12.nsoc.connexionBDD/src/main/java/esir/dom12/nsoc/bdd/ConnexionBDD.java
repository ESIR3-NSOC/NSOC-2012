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
        @ProvidedPort(name = "entreeBdd", type = PortType.SERVICE, className = ConnexionBDDInterface.class),
        @ProvidedPort(name = "Trombi", type = PortType.MESSAGE)
})

@Requires({
        @RequiredPort(name = "trombiSortie", type = PortType.MESSAGE, optional = true)
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
    String trombiEtudiant;
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
     * @param o : numero de l'etudiant (String)
     * @return  ImageIcon trombiEtudiant
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Port(name = "Trombi")
    public void sendRequestFromTrombiToBdd (Object o) throws SQLException, ClassNotFoundException {
        String req = new String(o.toString());
        String [] temp = req.split(" ");
        String nom = temp[0];
        String prenom = temp[1];

        requeteTrombi = "SELECT trombinoscope FROM listeEtudiants WHERE nom = " + nom + " AND prenom = " + prenom;

        Statement state = connection.createStatement();

        ResultSet trombi = state.executeQuery(requeteTrombi);
        trombi.next();

        trombiEtudiant = "/sdcard/ressource/" + trombi.getString(1);

        MessagePort messagePort = getPortByName("trombiSortie", MessagePort.class);
        if (messagePort != null) {
            messagePort.process(trombiEtudiant);
        }
    }
}
