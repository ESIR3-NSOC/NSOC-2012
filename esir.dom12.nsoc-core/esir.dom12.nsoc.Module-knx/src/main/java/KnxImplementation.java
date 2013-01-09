import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
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
        @RequiredPort(name = "ip", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "addEquipement", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "value", type = PortType.MESSAGE, optional = false)
})
@Provides({
        @ProvidedPort(name = "getData", type = PortType.MESSAGE),
        @ProvidedPort(name = "setData", type = PortType.MESSAGE),
        @ProvidedPort(name = "connect", type = PortType.MESSAGE),
        @ProvidedPort(name = "deconnect", type = PortType.MESSAGE)
})


public class KnxImplementation extends AbstractComponentType {

    /**
     * Attributs
     */
    String ipPasserelle = null;
    Boolean connect = false;
    KNXNetworkLinkIP netLinkIp;
    ProcessCommunicator pc;
    Logger log = Logger.getLogger(KnxImplementation.class.getName());

    /**
     * Implémentation de la methode setComposant
     *
     * @param addComposant: adresse de l'équipement a controler
     * @param value:        Valeur a donner a l'equipement
     * @return
     */

    @Port(name = "setData")
    public boolean setComposant(String addComposant, float value) {

        // Connexion à la passerelle KNX (si l'on est pas deja connecté)
        if (connect == false) {
            connexionKnx(ipPasserelle);
        }

        // Action sur l'équipement
        try {
            pc.write(new GroupAddress(addComposant), value);
            log.log(Level.INFO, String.format("Valeur équipement: %s modifié: %s", addComposant, value));

        } catch (KNXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            log.log(Level.WARNING, "KNX Exception lors de l'ecriture sur le composant");
            // Si il y a un problème on returne false et on se déconnecte
            deconnexionKNX(netLinkIp);

            log.log(Level.INFO, "Déconnexion");
            return false; // Retourne false car la valeur du composant n'a pas été modifié
        }

        // Sinon on retourne true et on se déconnect
        deconnexionKNX(netLinkIp);

        log.log(Level.INFO,"Déconnexion");
        return true;
    }

    /**
     * Récupère la valeur d'un équipement KNX
     */
    @Port(name = "getData")
    public float getData(){
        float value = 0;

        return value;
    }

    /**
     * Connexion via calimero a la passerelle KNX
     *
     * @param ip: adresse IP de la passerelle
     */
    @Port(name = "connect")
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
    @Port(name = "deconnect")
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
}
