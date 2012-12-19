package esir3.nsh.projet.scenarii;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;


/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : The person wishes to make use of the projector. The projector and the screen being of use to the video projection are set up. A scenario turns on the lights and the blinds at predetermined levels.
 */

@Provides({
    @ProvidedPort(name = "activate", type = PortType.MESSAGE),
	@ProvidedPort(name = "desactivate", type = PortType.MESSAGE),
    @ProvidedPort(name = "isStopped", type = PortType.SERVICE, className =  esir3.nsh.projet.scenarii.PresentationComponent.class)

})

@Requires({
    @RequiredPort(name = "lightR", type = PortType.MESSAGE, optional = false),
    @RequiredPort(name = "videoR", type = PortType.MESSAGE, optional = false),
    @RequiredPort(name = "scenarii", type = PortType.MESSAGE, optional = true),
    @RequiredPort(name = "isStopped", type = PortType.MESSAGE, optional = true)
})

@ComponentType
public class PresentationComponent extends AbstractComponentType {

    private boolean stopped = true;

    @Start
    public void startComponent() {
        // Do nothing at startup ...
    }

    @Stop
    public void stopComponent() {
    	// Desactivate Presentation Scenario
    	this.desactivatePresentation();
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
    }
    
    /**
     * Receipt Message to activate Presentation Scenario
     */
    @Port(name = "activate")
    public void activatePresentation() {
        System.out.println("Presentation Scenario :: Activate");
        this.lightRegulation(level);
        this.videoRegulation(state);
        
        stopped = false;
        this.isStopped();
    }
    
    /**
     * Receipt Message to desactivate Presentation Scenario
     */
    @Port(name = "desactivate")
    public void desactivatePresentation() {
        System.out.println("Presentation Scenario :: Desactivate");
        
        stopped = true;
        this.isStopped();
    }
    
    /**
     * Send message to light regulation
     * @param level
     */
    public void lightRegulation(int level) {
        MessagePort prodPort = getPortByName("lightR",MessagePort.class);
        if(prodPort != null) {
            prodPort.process(level);
        }
    }
    
    /**
     * Send message to video regulation
     * @param state must be a string : "ON" or OFF"
     */
    public void videoRegulation(String state) {
        MessagePort prodPort = getPortByName("videoR",MessagePort.class);
        if(prodPort != null) {
            prodPort.process(state);
        }
    }
    
    /**
     * Change state of Presentation Scénario
     * And send message to provide the state of Presentation Scenario
     * @param state must be a boolean value
     */
    public void isStopped() {
        MessagePort prodPort = getPortByName("videoR",MessagePort.class);
        if(prodPort != null) {
            prodPort.process(stopped);
        }
    }

}
