import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.exception.KNXException;
import tuwien.auto.calimero.knxnetip.KNXnetIPConnection;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 12/23/12
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Definition des ports
 */

@Requires({
        @RequiredPort(name = "getState", type = PortType.MESSAGE, optional = true)
})
@Provides({
        @ProvidedPort(name = "getEquipementState", type = PortType.MESSAGE),
        @ProvidedPort(name = "setEquipementState", type = PortType.MESSAGE)
})
@DictionaryType({
        @DictionaryAttribute(name = "ipMaquette", defaultValue = "192.168.1.193", optional = true)
})
@ComponentType
public class KnxImplementation extends AbstractComponentType implements KnxListener {

    /**
     * Attributs
     */
    String ipPasserelle = (String) getDictionary().get("ipMaquette");
    Boolean connect = false;
    KNXNetworkLinkIP netLinkIp;
    ProcessCommunicator pc;
    Logger log = Logger.getLogger(KnxImplementation.class.getName());

    // Variables neccessaire pour agir sur les équipements
    String addComposant; // Adresse de l'équipement
    float value ;        // Prochaine valeur du composant

    // Variable pour le Thread
    KnxThread threadKnx = new KnxThread();

    @Start
    public void startComponent() {

        System.out.println("Module KNX :: Start");
        // Si le thread n'est pas démarré
        if(threadKnx == null || threadKnx.isStopped()){
            threadKnx.addKnxListener(this);
            threadKnx.start(); // Démarre le thread
        }

    }

    @Stop
    public void stopComponent() {
        System.out.println("Module KNX :: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Module KNX :: Update");
    }

    /**
     * Implémentation de la methode setComposant
     */

    @Port(name = "setEquipementState")
    public void setComposant(Object o) {

        // On lit les paramètres ecrit sur le port
        String data = new String(o.toString());
        String [] temp = data.split(":"); // Split les parametres
        addComposant = temp[0]; // Récupere l'adresse de l'équipement
        String value = temp[1]; // Get the value
        boolean isFloat = false;
        float fvalue = 0;

        // Cas ou la valeur est un float
        if(value != "ON" || value != "OFF" || value != "UP" || value != "DOWN"){
            fvalue = Float.parseFloat(value);
            isFloat = true;
        }

        // Connexion à la passerelle KNX (si l'on est pas deja connecté)
        if (connect == false) {
            connexionKnx(ipPasserelle);
        }

        // Action sur l'équipement
        try {
            /* Cas ou l'on utilise la valeur float */
            if(isFloat == true){
                pc.write(new GroupAddress(addComposant), fvalue);
                log.log(Level.INFO, String.format("Valeur équipement: %s modifié: %s", addComposant, fvalue));
            }
            else{
                pc.write(new GroupAddress(addComposant), value);
                log.log(Level.INFO, String.format("Valeur équipement: %s modifié: %s", addComposant, value));
            }


        } catch (KNXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            log.log(Level.WARNING, "KNX Exception lors de l'ecriture sur le composant");

            // Si il y a un problème on returne false et on se déconnecte
            deconnexionKNX(netLinkIp);

            log.log(Level.INFO, "Déconnexion");
        }

        // Sinon on retourne true et on se déconnect
        deconnexionKNX(netLinkIp);

        log.log(Level.INFO,"Déconnexion");
    }

    /**
     * Récupère la valeur d'un équipement KNX
     */
    @Port(name = "getEquipementState")
    public String getState(String addComposant){

        float value = 0; // Variable pour la valeur de Ligth room
        boolean valueBool = false; // Variable utilisé pour la valeur des volets et de la lumiere du tableau

        // Connexion à la passerelle KNX (si l'on est pas deja connecté)
        if (connect == false) {
            connexionKnx(ipPasserelle);
        }

        // Action sur l'équipement
        try {
            // Cas ou on lit un float (Room_Light)
            if(addComposant.equals("3/0/2")){
                value = pc.readFloat(new GroupAddress(addComposant));
                log.log(Level.INFO, String.format("Valeur équipement: %s: %s", addComposant, value));
                return addComposant + ":" + String.valueOf(value);
            }
            // Sinon on lit un boolean true ou false
            else{
                valueBool = pc.readBool(new GroupAddress(addComposant));
                log.log(Level.INFO, String.format("Valeur équipement: %s: %s", addComposant, valueBool));
                return addComposant + ":" + String.valueOf(valueBool);
            }

        } catch (KNXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            log.log(Level.WARNING, "KNX Exception lors de l'ecriture sur le composant");
            // Si il y a un problème on returne false et on se déconnecte
            deconnexionKNX(netLinkIp);

            log.log(Level.INFO, "Déconnexion");


        }

        // Sinon on retourne true et on se déconnect
        deconnexionKNX(netLinkIp);

        log.log(Level.INFO,"Déconnexion");

        // Cas on lit un boolean
        if(value == 0){
            return addComposant + ":" + String.valueOf(valueBool);
        }
        // Cas on lit un float
        else{
            return addComposant + ":" + String.valueOf(value);
        }

    }

    /**
     * Connexion via calimero a la passerelle KNX
     *
     * @param ip: adresse IP de la passerelle
     */
    public void connexionKnx(String ip) {

        log.log(Level.INFO, "Connexion a la passerelle");
        try {
            netLinkIp = new KNXNetworkLinkIP(KNXNetworkLinkIP.TUNNEL, new InetSocketAddress(InetAddress.getLocalHost(), 0), new InetSocketAddress(
                    InetAddress.getByName(ip),
                    KNXnetIPConnection.IP_PORT), false, new TPSettings(
                    false));
            log.log(Level.INFO, "KNX netLinkIp créé");

            pc = new ProcessCommunicatorImpl(netLinkIp);

            log.log(Level.INFO, "Process Communicator créé");
        } catch (KNXException e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            log.log(Level.WARNING, "KNX Exception " + e);
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            log.log(Level.WARNING, "UnKnownHostException " + e);
        }

        connect = true; // Indique que l'on est connecté a la passerelle KNX
    }


    /**
     * Deconnexion a la passerelle KNX
     *
     * @param linkIp
     */
    public void deconnexionKNX(KNXNetworkLinkIP linkIp) {

        try {
            linkIp.close();
        } finally {
            if (linkIp != null) {
                linkIp.close();

                log.log(Level.INFO, "Connection closed");
                connect = false; // Indique que l'on est déconnecté de la passerelle
            }
        }

    }


    /**
     * Cette fonction écrit les données sur le port getState du module KNX
     * Les données correspondent aux différentes valeur des capteurs
     */
    public void sendData(String data){
        MessagePort dataPort = getPortByName("getState",MessagePort.class);
        if(dataPort != null) {
            dataPort.process(data);
        }
    }
}
