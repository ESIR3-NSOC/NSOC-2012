package esir3.nsh.projet.scenarii;

/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : A person wants to leave the room. A timer is activated allowing time for people to leave the room.
 */
public class ShutRoom {

    private long delay = 18000000; // 5mn

    /**
     * A person wants to leave the room. A timer is activated allowing time for people to leave the room.
     * Timer : 5 mn
     */
    public ShutRoom() {
    }
    
    /**
     * A person wants to leave the room. A timer is activated allowing time for people to leave the room.
     * @param timer after which the piece is turned off
     */
    public ShutRoom(long delay) {
        this.delay = delay;
    }

    /**
     * To Send commands periodically
     */
    public void run() {
    	try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	this.sendCommand();
    }

    private void sendCommand() {
    	// Send a predermined level for lights (e.g. 600lx)
    	// Send a OFF level for projector control
    }

}
