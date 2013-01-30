package esir.dom12.moduleKnx;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 1/21/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
class KnxThread extends Thread{

    /**
     * Variable global
     */
    private boolean stopped = false;
    private long delay = 200000;
    private ArrayList<KnxListener> listeners = new ArrayList<KnxListener>();

    /**
     * Fonction appélé lors du démarrage du thread
     */
    public void run() {
        while(!stopped) {
            getData();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addKnxListener(KnxListener lst) {
        listeners.add(lst);
    }

    /**
     * Stop l'ecriture des données sur le port provided par le module KNX
     */
    public void halt() {
        stopped = true;
    }

    /**
     * Donne l'état du thread
     * @return
     */
    public boolean isStopped(){
        return stopped;
    }

    /**
     * Appel la fonction get State de la classe KnxImplementation et écrit le port
     */
    public void getData(){
        for(final KnxListener listener : listeners) {
            new Thread(new Runnable(){
                public void run() {
                    String data = "SHUTTER:" + listener.getState("2/0/1") + ";";      // Get Shutter value
                    data = data + "BOARD_LIGHT:" + listener.getState("3/0/1") + ";";  // Get Board light value
                    data = data + "ROOM_LIGHT:" + listener.getState("3/0/2") + ";";   // Get Room light value
                    data = data + "BRIGHTNESS:" + listener.getState("4/0/1") + ";";   // Get Brightness value

                    listener.sendData(data); // Write the data on the getState port

                    System.out.println("Data sent from the KNX Component");
                }
            }).start();
        }
    }
}
