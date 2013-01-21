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

@Requires({
        @RequiredPort(name = "setShutter", type = PortType.MESSAGE, optional = false)
})
@Provides({
        @ProvidedPort(name = "setShutterState", type = PortType.MESSAGE)
})
@DictionaryType({
        @DictionaryAttribute(name = "Volet", defaultValue = "2/0/1", optional = true)
})
public class GestionVolImpl extends AbstractComponentType{

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
        System.out.println("Gestion volet :: Start");

        // Get the value from the required port

    }

    /**
     * Methode qui appel le bon composant pour effectuer la modification sur l'équipement
     * @return
     */

    @Port(name = "setVolet")
    public boolean setEquipement(Object o) {

        // Selon le type d'équipement, nous appelons le bon composant

        // On recécupere les données
        String data = new String(o.toString());
        String [] temp = data.split(";");

        type = temp[0];
        value = Float.parseFloat(temp[1]);

        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            log.log(Level.INFO, "KNX selectionné");
            // Cas ou l'on veut commander des équipements KNX

            // Ecriture sur le port setDataKnx
            MessagePort portKnx = getPortByName("setShutter", MessagePort.class);
            if(portKnx != null){
                // Donnée à envoyer au composant KNX
                String knxData = (String) getDictionary().get("Volet"); // Récupere l'adresse KNX du volet
                knxData = knxData + ":" + temp[1];
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


}
