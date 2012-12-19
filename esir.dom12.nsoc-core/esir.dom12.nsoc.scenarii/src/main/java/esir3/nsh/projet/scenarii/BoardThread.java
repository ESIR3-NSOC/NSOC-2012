package esir3.nsh.projet.scenarii;

/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : A person wants to write on the blackboard, the room brightness adjusts according to the brightness outside and azimuth of the sun, finally illuminate the room without dazzling the teacher and the students, acting on fixtures and blinds.
 */
public class BoardThread extends Thread {

    private boolean stopped = false;
    private long delay = 2000;
    private int time = 0;

    /**
     * A person wants to write on the blackboard, the room brightness adjusts according to the brightness outside and azimuth of the sun.
     * Period of repetition of commands : 2000 ms
     */
    public BoardThread() {}
    
    /**
     * A person wants to write on the blackboard, the room brightness adjusts according to the brightness outside and azimuth of the sun.
     * @param delay period of repetition of commands in ms
     */
    public BoardThread(long delay) {
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
    	// Send a predermined level for lights (e.g. 600lx)
    	// Send a OFF level for projector control
    }

}
