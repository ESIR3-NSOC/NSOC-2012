package esir.dom12.gestionVolet;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 12/23/12
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Défniniton des ports
 */


@Library(name = "JavaSE")
@Provides({
        @ProvidedPort(name = "setshutterstate", type = PortType.MESSAGE)
})
@Requires({
        @RequiredPort(name = "setshutter", type = PortType.MESSAGE, optional = true)
})
@DictionaryType({
        @DictionaryAttribute(name = "voletUp", defaultValue = "2/0/1", optional = true),
        @DictionaryAttribute(name = "voletDown", defaultValue = "2/0/2", optional = true)
})
@ComponentType
public class GestionVolImpl extends AbstractComponentType {

    /**
     * Global Variable
     */
    Logger log = Logger.getLogger(GestionVolImpl.class.getName());

    // Variables necessaire pour le getShutterState et le setVolet
    private static String shutter = "SHUTTER";          // Nom des équipement
    private static String down = "DOWN";                // Valeur lu dans la trame
    private static String up = "UP";                    // Valeur lu dans la trame
    String value = null;                                // Correspond a la valeur a donner au volet
    String add = null;                                  // Adresse de l'équipement que l'on veut commander
    String equipement = "SHUTTER";                      // Nom de l'équipement
    String type = "KNX";                                // Type de technologie utilisé
    Boolean shutterValue = false;                       // Boolean Transmi au module KNX
    String data = null;                                 // Variable pour stocker informations lu sur le port
    String [] temp;                                     // Tableau temporaire pour stocker les données
    String knxData = null;                              // Donnée sui seront écrite sur le port du module KNX

    @Start
    public void startComponent() {
        System.out.println("Gestion Volet :: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Gestion Volet:: Stop");
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
        System.out.println("Gestion Volet:: Update");
    }

    /**
     * Methode qui appel le bon composant pour effectuer la modification sur l'équipement
     */

    @Port(name = "setshutterstate")
    public boolean setEquipement(Object o) {

        // On recécupere les données
        if (o instanceof String) {

            data = (String) o;                                         // Stocke les données dans la variable data
            StringTokenizer tokens = new StringTokenizer(data,":");
            equipement = tokens.nextToken();
            value = tokens.nextToken();

            // Type d'informations Recu:
            // SHUTTER:UP
            // SHUTTER:DOWN

            log.log(Level.INFO, "Equipement :: " + equipement + " Valeur :: " + value);

            // Switch case impossible sur une variable String...
            if (type.equals("KNX")) {

                log.log(Level.INFO, "KNX selectionné, Valeur :: " + value);

                log.log(Level.INFO, "Differnce: " + value.compareTo(up));

                // Determine si l'on doit monter ou descendre les volets
                if(value.compareTo(up) == 1 || value.compareTo(up) == 0 ){
                    // Dans la cas ou l'on recoit UP, le volet doit monter
                    shutterValue = true;                               // Convertie les données en Boolean
                    add = (String) getDictionary().get("voletUp");     // Définie l'addresse sur laquelle écrire
                } else if(value.compareTo(down) == 1 || value.compareTo(down) == 0){
                    // Dans l'autre cas, on baisse le volet en lui envoyant FALSE
                    shutterValue = false;                              // Convertie les données en Boolean
                    add = (String) getDictionary().get("voletDown");   // Définie l'addresse sur laquelle écrire
                }  else {
                    log.log(Level.WARNING, "Data received have not been parsed");
                }



                // Ecriture sur le port setDataKnx
                MessagePort portKnx = getPortByName("setshutter", MessagePort.class);

                if (portKnx != null) {

                    // Donnée à envoyer au composant KNX
                    knxData = add + ":" + shutterValue;
                    portKnx.process(knxData); // Ecrit sur le port setLight
                    return true;
                }


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
        }
        return false;
    }


}
