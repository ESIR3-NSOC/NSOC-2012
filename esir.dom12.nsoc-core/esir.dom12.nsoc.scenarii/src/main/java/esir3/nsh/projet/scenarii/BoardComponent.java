package esir3.nsh.projet.scenarii;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;


/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : A person wants to write on the blackboard, the room brightness adjusts according to the brightness outside and azimuth of the sun, finally illuminate the room without dazzling the teacher and the students, acting on fixtures and blinds.
 */

@ComponentType
public class BoardComponent extends AbstractComponentType {

	private BoardThread board;
    private boolean stopped = false;


    @Start
    public void startComponent() {
        if (board == null || board.isStopped()) {
        	board.start();
        	this.stopped = false;
        }
    }

    @Stop
    public void stopComponent() {
        if (board != null) {
        	board.halt();
        	this.stopped = true;
        }
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
    }

    /**
     * State of BoardComponent
     * @return true if the thread is stopped, elsif running
     */
    public boolean isStopped(){return stopped;}
    
}
