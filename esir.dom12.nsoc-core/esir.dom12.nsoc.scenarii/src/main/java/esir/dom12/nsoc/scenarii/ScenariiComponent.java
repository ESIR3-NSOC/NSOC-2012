package esir.dom12.nsoc.scenarii;

import java.util.StringTokenizer;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;


/**
 * Created with Eclipse
 * User: Yann Le Gall
 * Date: 17/12/12
 * Description : When the scenario is activated, it sends commands to the light regulation and video regulation. He can not do anything if forcage was previously enabled. And this scenario is desactivated automatically when he receives a message that it was not intended. That it supposes it is the activation of another scenario. 
 */

@Provides({
    @ProvidedPort(name = "commandScenarii", type = PortType.MESSAGE),
})

@Requires({
    @RequiredPort(name = "commandRegul", type = PortType.MESSAGE, optional = false),
    @RequiredPort(name = "isSettingON", type = PortType.MESSAGE, optional = true),
    @RequiredPort(name = "isSettingOFF", type = PortType.MESSAGE, optional = true)
})

@DictionaryType({
    @DictionaryAttribute(name = "scenarioName", optional = false),
    @DictionaryAttribute(name = "lightLevel", defaultValue = "530", optional = false),
    @DictionaryAttribute(name = "boardLightState", defaultValue = "ON", optional = false),
    @DictionaryAttribute(name = "videoState", defaultValue = "OFF", optional = false)    
})

@ComponentType
public class ScenariiComponent extends AbstractComponentType {

	private static String ON = "ON";
	private static String OFF = "OFF";
	private static String FORCE ="FORC";
	private static String LIGHT = "LIGHT";
	private static String VIDEO = "VIDEO";
	
    private boolean forcing;

    @Start
    public void startComponent() {
        // Do nothing at startup ...
    	// The component is setting OFF (initial values) 
    	this.set(OFF);
    	forcing = false;
    }

    @Stop
    public void stopComponent() {
    	// Desactivate Presentation Scenario
    	this.set(OFF);
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
    }
    
    /**
     * Receive message to activate/desactivate scenario
     */
    @Port(name = "commandScenarii")
    public void receiveMessage(Object o) {
    	
    	// Reception of message
    	System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: Received " + o.toString());
    	
    	// Analysis only if the message is a string
        if(o instanceof String) {
            String msg = (String)o;
            
            // Who is the recipient of the message ?
            // Extraction of the recipient
            StringTokenizer tokens = new StringTokenizer(msg,":,;-_|¦\n\t\b");
            String receiver_string = tokens.nextToken();
            
            // What is the command ? ON or OFF
            // Extraction of main command
            String action_string = tokens.nextToken();
            
            // The recipient of the message is the Forcing Component : stopping...
            if ((new String(receiver_string)).compareTo(FORCE) == 0){
            	
            	// Forcing Component is setting ON, desactivation of this scenario
            	if ((new String(action_string)).compareTo(ON) == 0){
            		this.set(OFF);
            		forcing = true;
            		System.out.println("forcing = true");
            	}
            	
            	// Forcing Component is setting OFF, this scenario do nothing
            	if ((new String(action_string)).compareTo(OFF) == 0){
            		forcing = false;
            		System.out.println("forcing = false");
            	}
            }
            
            // The recipient of the message is this scenario component : starting...
            else if ((new String(receiver_string)).compareTo((String) getDictionary().get("scenarioName")) == 0){
            	if (forcing == true); // if Forcing Component is ON, scenarii are unable to do anything
            	else { // else scenarii can send regulation commands
            		this.set(ON);
            		sendLightCommandRegulation(action_string);
            		sendVideoCommandRegulation(action_string);
            	}
            }
            
            // The recipient of the message is probably an other scenario component : stopping...
            else {
            	this.set(OFF);
            }
        }  
    }
    
    /**
     * Change the state of this scenario
     * @param state must be a "ON" or "OFF" command
     */
    private void set(String state){
    	MessagePort prodPort;
    	
    	// This scenario is setting ON
    	if ((new String(state)).compareTo(ON) == 0){
    		System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: ON");
    		prodPort = getPortByName("isSettingON",MessagePort.class);
    		prodPort.process("");

    	}
    	
    	// This scenario is setting OFF
    	else if ((new String(state)).compareTo(OFF) == 0){
    		System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: OFF");
    		prodPort = getPortByName("isSettingOFF",MessagePort.class);
    		prodPort.process("");
    	}
    	
    	// With a bad command, print a error message
    	else System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: The command can not be interpreted ::  " + state);
    }
    
    /**
     * Send command to light regulation
     * @param state must a "ON" or "OFF" to activate or desactivate the regulation
     */
    private void sendLightCommandRegulation(String state){
    	MessagePort prodPort = getPortByName("commandRegul",MessagePort.class);
    	String msg;
    	
        if(prodPort != null) {
        
        	// Activate Light Regulation with appropriate values
        	if ((new String(state)).compareTo(ON) == 0){
        		msg = LIGHT + ":" + ON + ":" + getDictionary().get("boardLightState") + ":" + getDictionary().get("lightLevel");
        		prodPort.process(msg);
        		System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: send " + msg);
        	}
        	
        	// Desactivate Light Regulation
        	if ((new String(state)).compareTo(OFF) == 0){
        		msg = LIGHT + ":" + OFF;
        		prodPort.process(msg);
        		System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: send " + msg);
        	}
        	
        }
    }
    
    /**
     * Send command to video regulation
     * @param state must a "ON" or "OFF" to activate or desactivate the regulation
     */
    private void sendVideoCommandRegulation(String state){
    	MessagePort prodPort = getPortByName("commandRegul",MessagePort.class);
    	String msg;
    	
        if(prodPort != null) {
        	
        	// Activate Video Regulation
        	if ((new String(state)).compareTo(ON) == 0){
        		msg = VIDEO + ":" + ON;
        		prodPort.process(msg);
        		System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: send " + msg);
        	}
        	
        	// Desactivate Video Regulation
        	if ((new String(state)).compareTo(OFF) == 0){
        		msg = VIDEO + ":" + OFF;
        		prodPort.process(msg);
        		System.out.println("Scenario " + getDictionary().get("scenarioName") + " :: send " + msg);
        	}
        }
    }

    
}
