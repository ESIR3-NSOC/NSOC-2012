package esir.dom12.gestionLum;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

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
@Library(name = "JavaSE")
@Requires({
        @RequiredPort(name = "setLight", type = PortType.MESSAGE, optional = true)
})
@Provides({
        @ProvidedPort(name = "setLightState", type = PortType.MESSAGE)
})
@DictionaryType({
        @DictionaryAttribute(name = "Board_light", defaultValue = "3/0/1", optional = true),
        @DictionaryAttribute(name = "Room_light", defaultValue = "3/0/2", optional = true)
})
@ComponentType
public class GestionLumImpl extends AbstractComponentType {

    /**
     * Global Variable
     */
    String ipadd;           // Adresse de la machine
    Inet4Address ip;        // Objet pour connaitre l'adresse ip de notre machine
    String ipPasserelle;    // Adresse ip de la passerelle
    Logger log = Logger.getLogger(GestionLumImpl.class.getName());

    /* Variables necessaire pour le getShutterState et le setVolet */
    String type = null;                                     // Correspond au type de technologie
    String add = null;                                      // Adresse de l'équipement que l'on veut commander
    float value = 0;                                        // Valeur a addresse au volet
    String knxData = null;                                  // Valeur ecrite sur le port

    /* Variable pour la comparaison */
    private static String on = "ON";
    private static String off = "OFF";
    private static String BoardLight = "BOARD_LIGHT";
    private static String RoomLight = "ROOM_LIGHT";

    @Start
    public void startComponent() {
        System.out.println("Gestion Luminaire :: Start");

        // Get the value from the required port

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
     * @return
     */

    @Port(name = "setLightState")
    public void setEquipement(Object o) {

        /**
         * Variable de la function
         */
        String equipementAdd = null;        // Equipement a controler (Room_light, Board_light)
        Boolean valLight = false;           // Boolean qui sera transmi au module KNX
        int valRoomLightTemp = 0;           // Valeur passer en parametre
        String valBoardLightTemp = null;    // Addresse de l'équipement lu sur le port
        String data = null;                 // Données récupérées sur le port
        String [] temp;                     // Tableau temporaire qui stocke les données lu

        // Variable pour écrire sur le port lu par le module KNX
        MessagePort portKnx = getPortByName("setLight", MessagePort.class);

        // On recécupere les données
        data = new String(o.toString());    // Récupere les données lu sur le port
        temp = data.split(":");             // Crée un tableau a partir des donnée

        // Le Format des donnée lu sur le port est de la forme suivante:
        // ROOM_LIGHT:400
        // BOARD_LIGHT:ON

        type = "KNX";                       // Toujours égal a KNX car les autres techno ne sont pas géré

        log.log(Level.INFO, "Equipement sélectionné :: " + temp[0]);

        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            if(portKnx != null){

                // On récupere la valeur et les équipements a modifié
                // Dans le cas de la lumiere du tableau, la valeur est de type ON/OFF
                if((new String(temp[0])).equals(BoardLight)){
                    equipementAdd = (String) getDictionary().get("Board_light");
                    valBoardLightTemp = temp[1];
                    log.log(Level.INFO, "Valeur :: " + valBoardLightTemp);

                    // Une fois les paramètres récupérer, nous traitons l'inforamtions
                    // Pour savoir si il faut allumer ou éteindre la lumiere du tableau

                    log.log(Level.INFO, "Difference between on :: " + valBoardLightTemp.compareTo(on));
                    log.log(Level.INFO, "Diiference betwwen off :: " + valBoardLightTemp.compareTo(off));

                    if((new String(valBoardLightTemp)).compareTo(on) == 1){
                        // Cas on l'on doit allumer la lampe
                        valLight = true;
                    } else if(valBoardLightTemp.compareTo(off) == 1){
                        // Sinon on l'éteint
                        valLight = false;
                    } else {
                        log.log(Level.WARNING, "An error occured parsing the request");
                    }

                    knxData = equipementAdd + ":" + valLight;     // Ecrit sur le port lu par le module KNX
                }
                else if(temp[0].equals(RoomLight)){

                    // Récupere l'adresse des équipements
                    equipementAdd = (String) getDictionary().get("Room_light");
                    //valRoomLightTemp = Integer.parseInt(temp[1]); // Lit la valeur passer en paramètre
                    log.log(Level.INFO, "Valeur :: " + valRoomLightTemp);

                    // On ne peut pas faire de régulation de lumiere donc si la valeur
                    // est égale a 0 on éteint sinon on allume
                    if(temp[1].equals("0")){
                        // On passe le parametre false pour éteindre la lampe
                        valLight = false;
                    }  else {
                        // Sinon on allume la lumiere
                        valLight = true;
                    }
                    knxData = equipementAdd + ":" + valLight;     // Ecrit sur le port lu par le module KNX
                }

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
}