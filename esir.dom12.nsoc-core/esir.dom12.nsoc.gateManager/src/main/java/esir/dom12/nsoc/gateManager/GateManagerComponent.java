package esir.dom12.nsoc.gateManager;


import java.awt.*;
import javax.swing.*;

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
    @ProvidedPort(name = "door", type = PortType.MESSAGE),
})

@Requires({
    @RequiredPort(name = "acquit", type = PortType.MESSAGE, optional = true),
})

@ComponentType
public class GateManagerComponent extends AbstractComponentType {
	
	private static String soundDirectory = "sounds/buzzer.wav";
	private static String imageRepository= "images/security/";
	
	private static String DEFAULT = "security_default.png"; // Name of default image
	private static String DENIED = "security_denied.png"; // Name of denied image
	private static String APPROVED = "security_approved.png"; // Name of approved image
	
	
    private JLabel pictureLabel;
	private JFrame frame;

	private boolean isAlreadyInitialized;

	@Start
    public void startComponent() {    	
		isAlreadyInitialized = false;
    }

    @Stop
    public void stopComponent() {
    	if (isAlreadyInitialized) {
			frame.dispose();
			frame.setVisible(false);
		}
    	
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
    }
    
    private void init () {
    	//Create and set up the window.
        frame = new JFrame(this.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        //Create and set up the picture label
        pictureLabel = new JLabel(Utils.loadIcon(imageRepository + DEFAULT));
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        pictureLabel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        //Create and set up the content pane.
        frame.add(pictureLabel, BorderLayout.CENTER);
        //frame.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        //newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
		isAlreadyInitialized = true;
	}
    
    /**
     * Receive message to activate/desactivate door
     */
    @Port(name = "door")
    public void receiveMessage(Object o) {
    	
    	// Reception of message
    	System.out.println("GateManager " + " :: Received " + o.toString());
    	
    	// Create, set up and display the window if necessary.
    	if (!isAlreadyInitialized) {
			init();
		}
    	
    	// Analysis only if the message is a Boolean
        if(o instanceof Boolean) {
            boolean msg = (Boolean) o;
            
            // Change the state of the secure door access
            if (msg){
            	triggerPicture(APPROVED);
            	answer(true);
            }
            else {
            	triggerPicture(DENIED);
            	answer(true);
            }
        }  
        else {
        	answer(false);
        }
    }
    
    protected void triggerPicture(String choices) {
    	ImageIcon icon = Utils.loadIcon(imageRepository + choices);
    	
        // Get the icon corresponding to the image.
        pictureLabel.setIcon(Utils.loadIcon(imageRepository + choices));
        pictureLabel.setToolTipText(choices.toString());
        
        if (icon == null) {
            pictureLabel.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
        }
        
        // Play an AudioClip
        Utils.loadAudioClip(soundDirectory);
        
        // Wait 3s before...
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException("Could not sleep : ", e);
		}
        
        // Set up the initial and default picture label
        pictureLabel.setIcon(Utils.loadIcon(imageRepository + DEFAULT));
        pictureLabel.setToolTipText(choices.toString());    
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
