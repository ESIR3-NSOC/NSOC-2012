import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

import java.lang.Object;
import java.lang.String;
import java.lang.System;
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
        @RequiredPort(name = "getLight", type = PortType.MESSAGE, optional = false),
        @RequiredPort(name = "setLight", type = PortType.MESSAGE, optional = false)
})
@Provides({
        @ProvidedPort(name = "getLightState", type = PortType.MESSAGE),
        @ProvidedPort(name = "setLightState", type = PortType.MESSAGE)
})
public class GestionLum extends AbstractComponentType {

    /**
     * Global Variable
     */
    String ipadd; // Adresse de la machine
    InetAddress ip;// Objet pour connaitre l'adresse ip de notre machine
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
     *
     * @param add:   addresse de l'équipement a commander
     * @param type:  type d'équipement (KNX, Dali, Bacnet)
     * @param value: valeur a donner a l'équipement
     * @return
     */

    @Port(name = "setLightState")
    public boolean setEquipement(Object o) {

        // On recupere les parametres contenu dans l'objet o
        String data = new String(o);
        String [] temp = data.split(data,";");

        type = temp[0];
        value = float.valueOf(temp[1]);
        // Selon le type d'équipement, nous appelons le bon composant

        // Switch case impossible sur une variable String...
        if (type.equals("KNX")) {

            log.log(Level.INFO, "KNX selectionné");
            // Cas ou l'on veut commander des équipements KNX

            // Ecriture sur le port setDataKnx
            MessagePort prodPort = getPortByName("setLight",MessagePort.class);
            if(prodPort != null) {

                // Donnée à envoyer au composant KNX
                String knxData = value + ";" + add; //
                prodPort.process(knxData); // Ecrit sur le port setLight

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
    public float getLightState(Object o) {

        // Récuperation des données sur le port
        String data = new String(o);
        String [] temp = data.split(data,";");

        // Affichage des données
        System.out.println("Donnée::getLigthState : " + data);

        type = temp[0];

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
