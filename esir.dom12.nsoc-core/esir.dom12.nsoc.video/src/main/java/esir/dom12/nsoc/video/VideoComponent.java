package esir.dom12.nsoc.video;

import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;




/**
 * Created with Eclipse
 * User: LEDRU Vincent
 * Date: 17/12/12
 * Description : When the video is activated, it sends commands to the video. 
 */

@Provides({
	@ProvidedPort(name = "commandVideo", type = PortType.MESSAGE)
})

@Requires({
    @RequiredPort(name = "setshutter", type = PortType.MESSAGE, optional = true)
})

@DictionaryType({
	@DictionaryAttribute(name = "downDisplay", defaultValue = "6/3/25", optional = true),
	@DictionaryAttribute(name = "upDisplay", defaultValue = "6/3/25", optional = true)
})


@Library(name = "JavaSE")

@ComponentType
public class VideoComponent extends AbstractComponentType {


	/**
	 * EN: DECLARATION OF VARIABLES
	 * FR: DECLARATION DES VARIABLES
	 */
	private static String offVideo = "OFF";
	private static String onVideo = "ON";                  

	// Variables to store the data parser
	private static String value = null;     			// Correspond a la valeur a donner du display
	private static Boolean displayValue = false;       	// Boolean send at KNX controller
	public static String data = null;                  	// Variable pour stocker informations lu sur le port
	static String knxData = null;						// Donnée sui seront écrite sur le port du module KNX

	private static String videoState; 					// ON or OFF
	private static String displayState; 				// UP or DOWN
	private static String soundDirectory = "sounds/trompette.wav";
	private static String imageDirectory= "images/kevoree.png";


	/**
	 * SWING
	 */
	JFrame frame = new JFrame("ESIR TV");
	JButton setupButton = new JButton("Setup");
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");
	JButton tearButton = new JButton("Teardown");

	JLabel pictureLabelKevoree;
	JPanel mainPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLabel iconLabel = new JLabel();
	ImageIcon icon;



	/**
	 * KEVOREE
	 */
	@Start
	public void startComponent() {
		System.out.println("VideoComponent :: Start");
	}


	@Stop
	public void stopComponent() {
		System.out.println("VideoComponent :: Stop");
	}


	@Update
	public void updateComponent() {
		this.stopComponent();
		this.startComponent();
	}


	/**
	 * EN: COMPONENT SCENARIOS: GIVE THE ORDERS TO ESTABLISH THE REGULATION
	 * FR: COMPOSANT SCENARII : DONNE LES ORDRES POUR ETABLIR LA REGULATION
	 * @param o
	 */
	@Port(name = "commandVideo")
	public void receiveCommandVideo(Object o) {

		// Received Message
		System.out.println("***********************************************************");
		System.out.println("***************** SCENARII COMPONENT *****************");
		System.out.println("VideoComponent :: Received " + o.toString() + "\n");

		// Data Processing
		if(o instanceof String) {

			String msg = (String)o;
			System.out.println("The component 'VideoComponent' received :: " + msg);

			// Who is the recipient of the message ?
			// Extraction of the recipient
			StringTokenizer tokens = new StringTokenizer(msg,":,;-_|¦\n");
			String receiverVideoString = tokens.nextToken();
			String reference = "VIDEO";

			// Count the terms of the message
			if(tokens.countTokens() > 2){
				System.out.println("The component scenario sent a false message !");
			}
			else{

				// Iteration to know the video
				if(receiverVideoString.equals(reference) == false){

					// Display result
					System.out.println("The string received isn't 'VIDEO'.");
					System.out.println("\n");
				}
				else{
					// What is the command ? ON or OFF
					// Extraction of main command of state video
					videoState = tokens.nextToken();
					// Duplicate value
					displayState = videoState;
					System.out.println("State of Video : " + videoState);

					if(videoState.equals("ON") || videoState.equals("OFF")){
						commandVideo(videoState);
						commandDisplay(displayState);
						launchSound(soundDirectory);
					}
				}
			}
		}
	}



	/*********************************************************************************************
	 **************************** FUNCTION START TO COMPONENT VIDEO ******************************
	 *********************************************************************************************/


	/**
	 * EN: START OR STOP VIDEO
	 * FR: MARCHE OU ARRET VIDEO
	 */
	private void commandVideo(String requeteForVideo){

		System.out.println("The request sent :: " + requeteForVideo);

		if(requeteForVideo.equals("ON") == true){
			//build GUI
			//--------------------------

			//Frame
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			// Buttons
			buttonPanel.setLayout(new GridLayout(1,0));
			buttonPanel.add(setupButton);
			buttonPanel.add(playButton);
			buttonPanel.add(pauseButton);
			buttonPanel.add(tearButton);

			// Image display label
			iconLabel.setIcon(null);

			// Frame layout
			mainPanel.setLayout(null);
			mainPanel.add(iconLabel);
			mainPanel.add(buttonPanel);
			iconLabel.setBounds(0,0,380,280);
			buttonPanel.setBounds(0,280,380,50);

			// Frame
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.setSize(new Dimension(390,370));
			frame.setVisible(true);

			// Background Image KEVOREE
			pictureLabelKevoree = new JLabel(new ImageIcon(imageDirectory)); 
			pictureLabelKevoree.setBounds(0, 0, 270, 230);

		}
		else{
			frame.dispose();
			frame.setVisible(false);
		}
	}



	/**
	 * EN: START OR STOP VIDEO
	 * FR: MARCHE OU ARRET VIDEO
	 */
	private void commandDisplay(String requeteForDisplay){

		String add = null; // Variable pour stocker informations lu sur le port
		
		System.out.println("The request sent :: " + requeteForDisplay);

		if(requeteForDisplay.compareTo(offVideo) == 1 || requeteForDisplay.compareTo(onVideo) == 0 ){

			// Dans la cas ou l'on recoit UP, l'ecran doit monter
			// Convertie les donnees en Boolean
			displayValue = true;         

			// Définie l'addresse sur laquelle ecrire
			add = (String) getDictionary().get("upDisplay");    

		}
		else if(requeteForDisplay.compareTo(onVideo) == 1 || requeteForDisplay.compareTo(offVideo) == 0){

			// Dans l'autre cas, on baisse l'ecran en lui envoyant FALSE
			// Convertie les donnees en Boolean
			displayValue = false;

			// Définie l'addresse sur laquelle ecrire
			add = (String) getDictionary().get("downDisplay");   
		}
		else{
			System.out.println("Data received have not been parsed");
		}

		// Ecriture sur le port setDataKnx
		MessagePort portKnx = getPortByName("setshutter", MessagePort.class);

		if (portKnx != null) {

			// Donnée à envoyer au composant KNX
			knxData = add + ":" + displayValue;
			portKnx.process(knxData); // Ecrit sur le port setLight
			System.out.println(knxData);
			return;
		}
	}




	/**
	 * START SOUND
	 * @param pathDirectorySound
	 */
	public static void launchSound(String pathDirectorySound){
		try
		{
			URL url = VideoComponent.class.getClassLoader().getResource(pathDirectorySound);

			// Open audio clip and load from the audio input stream.
			AudioClip ac = Applet.newAudioClip(url);
			ac.play();
		}
		catch (Exception e)
		{
			throw new RuntimeException("Could not load clip with path: " + pathDirectorySound, e);
		}
	}
}
