package esir3.nsh.projet.scenarii;

/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : The person wishes to use the projector to show a video. The projector and the screen are turned on to video projection. Scenario turns off lights and closes the blinds.
 */
public class MovieThread extends Thread {

    private boolean stopped = false;
    private long delay = 2000;
    private int time = 0;

    /**
     * The person wishes to use the projector to show a video.
     * Period of repetition of commands : 2000 ms
     */
    public MovieThread() {}
    
    /**
     * The person wishes to use the projector to show a video.
     * @param delay period of repetition of commands in ms
     */
    public MovieThread(long delay) {
        this.delay = delay;
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
        	sendCommand();
            time ++;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendCommand() {
    	// Send a OFF level for lights
    	// Send a ON level for projector control
    }

}
