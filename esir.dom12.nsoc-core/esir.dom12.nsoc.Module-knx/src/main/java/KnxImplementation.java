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

/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 12/23/12
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */

public class KnxImplementation implements InterfaceKnx {

    /**
     * Attributs
     */
    String ipPasserelle = null;
    Boolean connect = false;
    KNXNetworkLinkIP netLinkIp;
    ProcessCommunicator pc;

    /**
     * Constructeur
     */
    public KnxImplementation(String ip) {
        ipPasserelle = ip;
    }

    /**
     * Implémentation de la methode setComposant
     *
     * @param addComposant: adresse de l'équipement a controler
     * @param value:        Valeur a donner a l'equipement
     * @return
     */
    public boolean setComposant(String addComposant, float value) {

        // Connexion à la passerelle KNX (si l'on est pas deja connecté)
        if (connect == false) {
            connexionKnx(ipPasserelle);
        }

        // Action sur l'équipement
        try {
            pc.write(new GroupAddress(addComposant), value);
        } catch (KNXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

            // Si il y a un problème on returne false et on se déconnecte
            deconnexionKNX(netLinkIp);
            return false;
        }

        // Sinon on retourne true et on se déconnect
        deconnexionKNX(netLinkIp);
        return true;
    }

    /**
     * Connexion via calimero a la passerelle KNX
     *
     * @param ip: adresse IP de la passerelle
     */
    public void connexionKnx(String ip) {

        try {
            netLinkIp = new KNXNetworkLinkIP(KNXNetworkLinkIP.TUNNEL, new InetSocketAddress(InetAddress.getLocalHost(), 0), new InetSocketAddress(
                    InetAddress.getByName(ip),
                    KNXnetIPConnection.IP_PORT), false, new TPSettings(
                    false));
            pc = new ProcessCommunicatorImpl(netLinkIp);
        } catch (KNXException e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
                System.out.println("Connexion closed");
                connect = false; // Indique que l'on est déconnecté de la passerelle
            }
        }

    }
}
