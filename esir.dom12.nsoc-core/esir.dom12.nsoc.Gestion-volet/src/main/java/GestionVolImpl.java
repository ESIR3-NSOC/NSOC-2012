import java.net.Inet4Address;

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
    String ipadd;
    Inet4Address ip;

    /**
     * Constructeur
     */
    public GestionVolImpl() {
        ipadd = ip.getHostAddress();
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

            // Cas ou l'on veut commander des équipements KNX


        } else if (type.equals("Dali")) {

            // Cas ou l'on veut commander des equipement Dali
            System.out.println("Les équipements dali ne sont pas encore commandables");
            return false;
        } else if (type.equals("Bacnet")) {

            // Cas ou l'on veut commander des équipement Bacnet
            System.out.println("Les équipements Bacnet ne pas encore commandables");
            return false;
        } else {

            // Cas ou le type d'équipement est inconnu
            System.out.println("Equipements non reconnu");
            return false;
        }
        return false;
    }
}
