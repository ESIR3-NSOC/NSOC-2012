import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

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


@Requires({
        @RequiredPort(name = "type", type = PortType.MESSAGE, optional = false),
        @RequiredPort(name = "value", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "lightValue", type = PortType.MESSAGE, optional = true)
})
@Provides({
        @ProvidedPort(name = "getLightState", type = PortType.MESSAGE),
        @ProvidedPort(name = "setLight", type = PortType.MESSAGE)
})
public class GestionLum extends AbstractComponentType {

    /**
     * Global Variable
     */
    String ipadd; // Adresse de la machine
    Inet4Address ip;// Objet pour connaitre l'adresse ip de notre machine
    String ipPasserelle; // Adresse ip de la passerelle
    Logger log = Logger.getLogger(GestionVolImpl.class.getName());

    // Variables necessaire pour le getShutterState et le setVolet
    String type = null; // Correspond au type de technologie
    String add = null;  // Adresse de l'équipement que l'on veut commander
    float value = 0;    // Valeur a addresse au volet


    @Start
    public void startComponent() {
        System.out.println("Gestion Luminaire :: Start");

        // Get the value from the required port

    }

    /**
     * Methode qui appel le bon composant pour effectuer la modification sur l'équipement
     * @param add:   addresse de l'équipement a commander
     * @param type:  type d'équipement (KNX, Dali, Bacnet)
     * @param value: valeur a donner a l'équipement
     * @return
     */

    @Port(name = "setLight")
    public boolean setEquipement() {
        // Selon le type d'équipement, nous appelons le bon composant

        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            log.log(Level.INFO, "KNX selectionné");
            // Cas ou l'on veut commander des équipements KNX

            // Ecriture sur le port setDataKnx


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

    @Port(name = "getLightState")
    public float getLightState(){

        // Variables de la fonction
        float lightValue = 0;

        // Selon le type de technologie,
        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            log.log(Level.INFO, "KNX selectionné");
            // Cas ou l'on veut commander des équipements KNX

            // Ecriture sur le port getDataKnx


        } else if (type.equals("Dali")) {

            log.log(Level.INFO, "Dali selectionné");
            // Cas ou l'on veut commander des equipement Dali
            System.out.println("Les équipements dali ne sont pas encore commandables");

        } else if (type.equals("Bacnet")) {

            log.log(Level.INFO, "Bacnet selectionné");
            // Cas ou l'on veut commander des équipement Bacnet
            System.out.println("Les équipements Bacnet ne pas encore commandables");

        } else {

            log.log(Level.INFO, "appareil inconnu selectionné");
            // Cas ou le type d'équipement est inconnu
            System.out.println("Equipements non reconnu");

        }

        return shutterValue;
    }
}
