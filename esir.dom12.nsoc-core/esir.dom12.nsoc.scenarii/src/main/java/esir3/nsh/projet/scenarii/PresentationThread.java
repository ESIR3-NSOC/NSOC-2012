package esir3.nsh.projet.scenarii;

/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : The person wishes to make use of the projector. The projector and the screen being of use to the video projection are set up. A scenario turns on the lights and the blinds at predetermined levels.
 */
public class PresentationThread {

    private boolean stopped = true;
    private long delay = 2000;
    private int time = 0;
    
    /**
     * To start the scenario
     */
    public void start() {
        stopped = false;
        
    }
    
    /**
     * To stop temporarily the thread
     */
    public void halt() {
        stopped = true;
    }
    
    /**
     * State of thread
     * @return true if the thread is stopped, elsif running
     */
    public boolean isStopped(){return stopped;}
    
    /**
     * Time for the presentation works
     * @return time in ms
     */
    public int time(){return time;}

    /**
     * To Send commands periodically
     */
    public void run() {
        while(!stopped) {
        	this.sendCommand();
            time ++;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendCommand() {
    	// Send a predermined level for lights (e.g. 600lx)
    	// Send a ON level for projector control
    }
    
}
