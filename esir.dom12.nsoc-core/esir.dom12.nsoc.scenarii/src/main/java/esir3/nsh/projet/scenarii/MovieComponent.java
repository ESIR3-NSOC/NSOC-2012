package esir3.nsh.projet.scenarii;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;


/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : The person wishes to use the projector to show a video. The projector and the screen are turned on to video projection. Scenario turns off lights and closes the blinds.
 */

@ComponentType
public class MovieComponent extends AbstractComponentType {

     private MovieThread movie;
     private boolean stopped = false;

    @Start
    public void startComponent() {
    	if (movie == null || movie.isStopped()) {
        	movie.start();
        }
    }

    @Stop
    public void stopComponent() {
        if (movie != null) {
        	movie.halt();
        }
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
    }
    
    /**
     * State of MovieComponent
     * @return true if the thread is stopped, elsif running
     */
    public boolean isStopped(){return stopped;}

}
