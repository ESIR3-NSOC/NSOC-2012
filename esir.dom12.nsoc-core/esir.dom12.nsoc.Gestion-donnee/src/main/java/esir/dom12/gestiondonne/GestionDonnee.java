package esir.dom12.gestiondonne;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 2/8/13
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */

@Library(name = "JavaSE")
@Requires({
        @RequiredPort(name = "getDataToKnx", type = PortType.MESSAGE, optional = true)
})
@Provides({
        @ProvidedPort(name = "getDataFromVincent", type = PortType.MESSAGE)
})
@DictionaryType({
        @DictionaryAttribute(name = "BOARD_LIGHT", defaultValue = "3/0/1", optional = true),
        @DictionaryAttribute(name = "ROOM_LIGHT", defaultValue = "3/0/2", optional = true),
        @DictionaryAttribute(name = "SHUTTER", defaultValue = "2/0/1", optional = true),
        @DictionaryAttribute(name = "BRIGHTNESS", defaultValue = "4/0/1", optional = true)
})
@ComponentType
public class GestionDonnee extends AbstractComponentType {

    /**
     * Variables Globale
     */
    Logger log = Logger.getLogger(GestionDonnee.class.getName());

    @Start
    public void startComponent() {
        System.out.println("Gestion Donnee :: Start");

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
     * Méthode qui appel le module KNX
     */
     @Port(name = "getDataFromVincent")
     public void getData(Object o){

         // Variable de la classe
         String data = null;        // Variable qui va regrouper toutes les addresses de groupes

         // On lit les valeur du Dictionnaire et on crée le string a envoyé au module KNX
         data = "BOARD_LIGHT:" + (String)getDictionary().get("BOARD_LIGHT");
         data = data + ";ROOM_LIGHT:" + (String)getDictionary().get("ROOM_LIGHT");
         data = data + ";SHUTTER:" + (String)getDictionary().get("SHUTTER");
         data = data + ";BRIGHTNESS:" + (String)getDictionary().get("BRIGHTNESS") + ";";

         log.log(Level.INFO, data);

         // Une fois le String de donnée créé, on écrit sur le port qui sera lu par le module KNX
         // Variable pour écrire sur le port lu par le module KNX
         MessagePort portKnx = getPortByName("getDataToKnx", MessagePort.class);
         if(portKnx != null){
            portKnx.process(data);      // Ecrit les données sur le port
         }
         log.log(Level.INFO, "Data sent from GestionDonnée to Module-KNX");

     }

}
