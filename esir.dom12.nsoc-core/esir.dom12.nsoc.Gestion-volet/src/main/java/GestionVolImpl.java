import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 12/23/12
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class GestionVolImpl implements GestionVolInterface {

    /**
     * Global Variable
     */
    String ipadd; // Adresse de la machine
    Inet4Address ip;// Objet pour connaitre l'adresse ip de notre machine
    String ipPasserelle; // Adresse ip de la passerelle
    Logger log = Logger.getLogger(GestionVolImpl.class.getName());


    /**
     * Constructeur
     */
    public GestionVolImpl() {
        ipadd = ip.getHostAddress();
        log.log(Level.INFO, "Instance créée");
    }

    /**
     * Methode qui appel le bon composant pour effectuer la modification sur l'équipement
     * @param add:   addresse de l'équipement a commander
     * @param type:  type d'équipement (KNX, Dali, Bacnet)
     * @param value: valeur a donner a l'équipement
     * @return
     */


    public boolean commandEquipement(String add, String type, float value) {
        // Selon le type d'équipement, nous appelons le bon composant

        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            log.log(Level.INFO, "KNX selectionné");
            // Cas ou l'on veut commander des équipements KNX


        } else if (type.equals("Dali")) {

            log.log(Level.INFO, "Dali selectionné");
            // Cas ou l'on veut commander des equipement Dali
            System.out.println("Les équipements dali ne sont pas encore commandables");
            return false;
        } else if (type.equals("Bacnet")) {

            log.log(Level.INFO, "Bacnet selectionné");
            // Cas ou l'on veut commander des équipement Bacnet
            System.out.println("Les équipements Bacnet ne pas encore commandables");
            return false;
        } else {

            log.log(Level.INFO, "appareil inconnu selectionné");
            // Cas ou le type d'équipement est inconnu
            System.out.println("Equipements non reconnu");
            return false;
        }
        return false;
    }
}
