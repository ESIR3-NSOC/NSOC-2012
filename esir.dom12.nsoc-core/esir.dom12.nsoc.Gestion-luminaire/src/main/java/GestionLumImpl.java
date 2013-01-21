import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.lang.Boolean;
import java.lang.Float;
import java.lang.String;
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

/**
 * Défniniton des ports
 */

@Requires({
        @RequiredPort(name = "getLight", type = PortType.MESSAGE, optional = false),
        @RequiredPort(name = "setLight", type = PortType.MESSAGE, optional = false)
})
@Provides({
        @ProvidedPort(name = "getLightState", type = PortType.MESSAGE),
        @ProvidedPort(name = "setLightState", type = PortType.MESSAGE)
})
@DictionaryType({
        @DictionaryAttribute(name = "Board_light", defaultValue = "3/0/1", optional = true),
        @DictionaryAttribute(name = "Room_light", defaultValue = "3/0/2", optional = true)
})
public class GestionVolImpl extends AbstractComponentType {

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
        System.out.println("Consumer:: Start");

        // Get the value from the required port

    }

    /**
     * Methode qui appel le bon composant pour effectuer la modification sur l'équipement
     * @return
     */

    @Port(name = "setLight")
    public boolean setEquipement(Object o) {

        // Selon le type d'équipement, nous appelons le bon composant

        // On recécupere les données
        String data = new String((byte[]) o);
        String [] temp = data.split(":");
        String equipementAdd = null;  // Equipement a controler (Room_light, Board_light)
        Boolean valBoardLight;

        type = "KNX"; // Toujours égal a KNX
        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            log.log(Level.INFO, "KNX selectionné");
            // Cas ou l'on veut commander des équipements KNX

            // Ecriture sur le port setDataKnx
            MessagePort portKnx = getPortByName("setLight", MessagePort.class);
            if(portKnx != null){
                // On récupere la valeur et les équipements a modifié
                // Dans le cas de la lumiere du tableau, la valeur est de type ON/OFF
                if(temp[0].equals("LIGHT_BOARD")){
                    equipementAdd = temp[0];
                    valBoardLight = temp[1];
                    String knxData = equipementAdd + ":" + valBoardLight;
                }
                else if(temp[0].equals("LIGHT_ROOM")){
                    equipementAdd = temp[0];
                    value = Float.parseFloat(temp[1]);
                    String knxData = equipementAdd + ":" + value;

                }

                portKnx.process(knxData); // Ecrit sur le port setLight
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
        return false;
    }

    @Port(name = "getLightState")
    public void getShutterState(Object o){

        // Variables de la fonction

        String data = new String((byte[]) o);
        String [] temp = data.split(";");

        type = temp[0];

        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            log.log(Level.INFO, "KNX selectionné");
            // Cas ou l'on veut commander des équipements KNX

            // Ecriture sur le port setDataKnx
            MessagePort portKnx = getPortByName("setLight", MessagePort.class);
            if(portKnx != null){
                // Donnée à envoyer au composant KNX

                String knxData = (String) getDictionary().get("Room_light"); //
                portKnx.process(knxData); // Ecrit sur le port setLight
            }

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
    }

    /**
     * retourne l'adresse de l'équipement
     */
    public String getEquipement(String portValue){

        // Selon le nom de l'équipement on retourne l'adresse qui se trouve dans le dictionnnaire
        if(portValue.equals("LIGHT_BOARD")){
            return (String) getDictionary().get("Board_light");
        }
        if(portValue.equals("LIGHT_ROOM")){
            return (String) getDictionary().get("Room_light");
        }
        else {
            return "Equipement non connu";
        }
    }


}
