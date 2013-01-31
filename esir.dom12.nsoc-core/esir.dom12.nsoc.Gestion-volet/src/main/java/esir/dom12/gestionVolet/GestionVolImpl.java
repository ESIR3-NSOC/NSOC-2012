package esir.dom12.gestionVolet;

import org.kevoree.annotation.Provides;

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
        @DictionaryAttribute(name = "volet", defaultValue = "2/0/1", optional = true)
})
@ComponentType
public class GestionVolImpl extends AbstractComponentType {

    /**
     * Global Variable
     */
    Logger log = Logger.getLogger(GestionVolImpl.class.getName());

    // Variables necessaire pour le getShutterState et le setVolet
    String type = null; // Correspond au type de technologie
    String add = null;  // Adresse de l'équipement que l'on veut commander
    float value = 0;    // Valeur a addresse au volet

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

        // Selon le type d'équipement, nous appelons le bon composant
        String data = null;
        System.out.println("Message Recu");
        // On recécupere les données
        if (o instanceof String) {
            data = (String) o;

            StringTokenizer tokens = new StringTokenizer(data,":");
            type = tokens.nextElement().toString();
            value = Float.valueOf(tokens.nextElement().toString());

            // Switch case impossible sur une variable String...
            if (type.equals("KNX")) {

                log.log(Level.INFO, "KNX selectionné");
                // Cas ou l'on veut commander des équipements KNX

               // Ecriture sur le port setDataKnx
                MessagePort portKnx = getPortByName("setshutter", MessagePort.class);
                if (portKnx != null) {
                    // Donnée à envoyer au composant KNX
                    String knxData = (String) getDictionary().get("volet"); // Récupere l'adresse KNX du volet
                    knxData = knxData + ":" + value;
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
