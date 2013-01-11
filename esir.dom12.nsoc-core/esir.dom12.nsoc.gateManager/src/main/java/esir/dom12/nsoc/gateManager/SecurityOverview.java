package esir.dom12.nsoc.gateManager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

/*
 * SecurityOverview.java requires 3 image files in the images/security
 * directory: 
 * security_approved.png, security_denied.png, security_default.png.
 */
public class SecurityOverview extends JPanel {

	private static final long serialVersionUID = 1L;
	private static String soundDirectory = "sounds/buzzer.wav";

    JLabel pictureLabel;
    JFrame frame;

    public SecurityOverview() {
        super(new BorderLayout());

        //Set up the picture label
        pictureLabel = new JLabel();
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        updatePicture("default");

        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Secure Acces");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new SecurityOverview();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * The Window and its subcomponents can be made displayable
     */
    public void killGUI(){
    	frame.dispose();
    }

    /**
     * Displays and controls security access
     * @param command is a string "approved" or "denied"
     */
    public void securityIs(String command){
    	updatePicture(command);
    	playSound(soundDirectory);
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	updatePicture("default");
    }    
    
    /**
     * Refresh image on the GUI
     * @param choices : image between "approved", "default" and "denied"
     */
    private void updatePicture(String choices) {
        //Get the icon corresponding to the image.
        ImageIcon icon = createImageIcon(
                                    "images/security/security_"
                                    + choices.toString()
                                    + ".png");
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText(choices.toString());
        if (icon == null) {
            pictureLabel.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
        }
    }

    /** 
     * Returns an ImageIcon, or null if the path was invalid.
     * */
    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SecurityOverview.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }    
    
    /**
     * Play wav, au or aiff sounds, no mp3
     * @param path : directory of sound
     */
    private static void playSound(String path){
    	URL url = SecurityOverview.class.getResource(path);
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
    }
}
