package esir.dom12.nsoc.gateManager;


import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;


/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 10/01/13
 * Description : Security system of the door. If there is an authorization message, access will be approved for a short period to access to the room. If access is not denied, display a warning message indicating a refusal.
 */

@Provides({
    @ProvidedPort(name = "command", type = PortType.MESSAGE),
})

@Requires({
    @RequiredPort(name = "acquit", type = PortType.MESSAGE, optional = true),
})

@ComponentType
public class GateManagerComponent extends AbstractComponentType {

	SecurityOverview so;
	
    @Start
    public void startComponent() {
    	so = new SecurityOverview();
    	
    	// Create and show the GUI
    	so.createAndShowGUI();
    }

    @Stop
    public void stopComponent() {
    	// Dispose the GUI
    	so.killGUI(); //
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
    }
    
    /**
     * Receive message to activate/desactivate door
     */
    @Port(name = "command")
    public void receiveMessage(Object o) {
    	
    	// Reception of message
    	System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: Received " + o.toString());
    	
    	// Analysis only if the message is a Boolean
        if(o instanceof Boolean) {
            boolean msg = (Boolean)o;
            // Change the state of the secure door access
            if (((Boolean) msg).booleanValue() == true )so.securityIs("approved");
            if (((Boolean) msg).booleanValue() == false )so.securityIs("denied");  
            // Acquit by the received message
            answer(msg); 
        }  
    } 
    
    /**
     * Acquit by the received message
     * @param val is the boolean value received by the component
     */
    private void answer(boolean val){
    	MessagePort prodPort = getPortByName("acquit",MessagePort.class);
    	prodPort.process(val);
    }
}
