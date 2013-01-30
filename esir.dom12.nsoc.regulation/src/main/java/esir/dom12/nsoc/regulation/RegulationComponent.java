package esir.dom12.nsoc.regulation;

import java.util.StringTokenizer;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;



/**
 * Created with Eclipse
 * User: LEDRU Vincent
 * Date: 17/12/12
 * Description : When the scenario is activated, it sends commands to the light regulation. 
 */

@Provides({
	@ProvidedPort(name = "commandRegul", type = PortType.MESSAGE),
	@ProvidedPort(name = "commandKNX", type = PortType.MESSAGE)
})

@Requires({
	@RequiredPort(name = "lightCommandRegulationKonnex", type = PortType.MESSAGE, optional = false)
})


@DictionaryType({
	@DictionaryAttribute(name = "roomLightLevel", defaultValue = "530", optional = false),
	@DictionaryAttribute(name = "boardLightState", defaultValue = "OFF", optional = false),
	@DictionaryAttribute(name = "roomShutterState", defaultValue = "UP", optional = false)
})

@ComponentType
public class RegulationComponent extends AbstractComponentType {


	/**
	 * DECLARATION DES VARIABLES
	 */
	private static RegulationThread lightThread;
	private static String ON = "ON";
	private static String OFF = "OFF";
	private static String UP = "UP";
	private static String DOWN = "DOWN";
	private static String VALUE = "VALUE";

	// Variables to store the data parser
	private static String regulationState; 	// ON or OFF
	private static String boardState;		// ON or OFF
	private static String roomValue;		// Lux Value in String
	private static int roomValueINT;		// Lux Value in Int

	// Variables to store the data of component KNX
	private static String roomValueKNX;		// Lux Value in String
	private static String boardStateKNX;	// ON or OFF
	private static String shutterStateKNX;	// UP or DOWN

	// News variables for send command
	private static String newRoomValueKNX;		// Lux Value in String
	private static String newBoardStateKNX;		// ON or OFF
	private static String newShutterStateKNX;	// UP or DOWN
	private static String outsideValueKNX;		// Lux Value in String

	// Variables for default operation
	private static String DEFAULT_VALUE_ROOM = "0";			// Default Value Room
	private static String DEFAULT_VALUE_BOARD = "OFF";		// Default Value Board
	private static String DEFAULT_VALUE_SHUTTER = "DOWN";	// Default Value Shutter
	private static String DAY = "400";						// Lux Value for default iteration

	
	/**
	 * GESTION THREAD
	 */
	@Start
	public void startComponent() {
		if (lightThread == null || lightThread.isStopped()) {
			lightThread = new RegulationThread(Long.valueOf((String) getDictionary().get("lightLevel")));
			lightThread.start();
		}
		System.out.println("RegulationComponent :: Start");
	}


	@Stop
	public void stopComponent() {
		if (lightThread != null) {
			lightThread.halt();
		}
		System.out.println("RegulationComponent :: Stop");
	}


	@Update
	public void updateComponent() {
		this.stopComponent();
		this.startComponent();
	}


	/**
	 * COMPOSANT SCENARII : DONNE LES ORDRES POUR ETABLIR LA REGULATION
	 * @param o
	 */
	@Port(name = "commandRegul")
	public void receiveCommandRegul(Object o) {

		// Received Message
		System.out.println("RegulationComponent :: Received " + o.toString());

		// Data Processing
		if(o instanceof String) {

			String msg = (String)o;
			System.out.println("The component 'RegulationComponent' received :: " + msg);

			// Who is the recipient of the message ?
			// Extraction of the recipient
			StringTokenizer tokens = new StringTokenizer(msg,":,;-_|¦\n");
			String receiverRegulString = tokens.nextToken();

			// Iteration to know the regulation
			if(new String(receiverRegulString) != "LIGHT"){

				// Display result
				System.out.println("The string received isn't 'LIGHT'.");
			}
			else{
				// What is the command ? ON or OFF
				// Extraction of main command of state regulation
				regulationState = tokens.nextToken();

				// What is the value of the board ? ON or OFF
				// Extraction of main command of value of the regulation of the board
				boardState = tokens.nextToken();

				// What is the value of the room ? Value in LUX
				// Extraction of main command of value of the regulation of the room
				roomValue = tokens.nextToken();
				roomValueINT = Integer.parseInt(roomValue);
			}
		}
	}


	/**
	 * COMPOSANT "COMMANDE KNX" : DONNE LES VALEURSQ DES EQUIPEMENTS KNX
	 * @param o
	 */
	@Port(name = "commandKNX")
	public void receiveValueKNX(Object o) {

		// Received Message
		System.out.println("RegulationComponent :: Received " + o.toString());

		// Data Processing
		if(o instanceof String) {

			String msg = (String)o;
			System.out.println("The component 'RegulationComponent' received :: " + msg);

			// Who is the recipient of the message ?
			// Extraction of the recipient
			StringTokenizer tokens = new StringTokenizer(msg,";");
			String receiverKNXString = tokens.nextToken();

			// Iteration to know the regulation
			if(new String(receiverKNXString) == "LIGHT_ROOM"){

				// What is the value of the room ? Value in LUX
				// Extraction of main command of value of the regulation of the room
				roomValueKNX = tokens.nextToken();
				System.out.println("Information of light room equipment.");
			}
			else if(new String(receiverKNXString) == "LIGHT_BOARD"){

				// What is the value of the board ? ON or OFF
				// Extraction of main command of value of the regulation of the board
				boardStateKNX = tokens.nextToken();
				System.out.println("Information of light board equipment.");
			}
			else if(new String(receiverKNXString) == "SHUTTER"){

				// What is the value of the room ? Value in LUX
				// Extraction of main command of value of the regulation of the room
				shutterStateKNX = tokens.nextToken();
				System.out.println("Information of shutter equipment.");
			}
			else if(new String(receiverKNXString) == "LIGHT_OUTSIDE"){

				// What is the value of the room ? Value in LUX
				// Extraction of main command of value of the regulation of the room
				outsideValueKNX = tokens.nextToken();
				System.out.println("Information of shutter equipment.");
			}
			else{
				System.out.println("Error Information");
			}

			// Intelligence of the regulation
			// Regulation is ON
			if(regulationState == "ON"){

				// Regulation of board light for boardState
				if(boardState.isEmpty()){
					newBoardStateKNX = boardState;
					sendLightCommandStateBoard(newBoardStateKNX);
				}

				// Regulation of room light if roomValueINT > roomValueKNX
				else if(Integer.parseInt(roomValueKNX) < roomValueINT){

					// Regulation of room if shutter state is UP
					if(shutterStateKNX == "UP"){

						newRoomValueKNX = roomValue;
						sendLightCommandRegulationRoom(newRoomValueKNX);
					}
					
					// Regulation of room if outside light > command light and shutter state is DOWN
					else if(Integer.parseInt(outsideValueKNX) > roomValueINT && shutterStateKNX == "DOWN"){

						newShutterStateKNX = "UP";
						sendShutterCommandState(newShutterStateKNX);
					}
					
					// Regulation of room if there are no indications
					else{
						newRoomValueKNX = roomValue;
						sendLightCommandRegulationRoom(newRoomValueKNX);
					}
				}
			}
			
			// Regulation is OFF
			else if(regulationState == "OFF" && Integer.parseInt(outsideValueKNX) > Integer.parseInt(DAY)){
				newRoomValueKNX = "DEFAULT_VALUE_ROOM";
				newBoardStateKNX = "DEFAULT_VALUE_BOARD";
				newShutterStateKNX = "UP";

				sendLightCommandRegulationRoom(newRoomValueKNX);
				sendLightCommandStateBoard(newBoardStateKNX);
				sendShutterCommandState(newShutterStateKNX);

			}
			
			// Regulation for FORCING
			else{
				newRoomValueKNX = roomValue;
				newBoardStateKNX = boardState;
				newShutterStateKNX = "DEFAULT_VALUE_SHUTTER";

				sendLightCommandRegulationRoom(newRoomValueKNX);
				sendLightCommandStateBoard(newBoardStateKNX);
				sendShutterCommandState(newShutterStateKNX);
			}
		}
	}

	public void main (String[] args){
		
	}
	
	/**
	 * CONSTRUCTEUR VIDE
	 */
	public RegulationComponent () {}



	/*********************************************************************************************
	 ********************* LES FONCTIONS D'ETAT ISSUES DU COMPOSANT SCENARII *********************
	 *********************************************************************************************/

	/**
	 * FONCTION POUR CONNAITRE L'ETAT DE LA REGULATION
	 * @param state_regulation
	 */
	public static void actionPerformedForRegulation(String regulationState) {

		if(regulationState == "ON"){
			lightThread.start();
			System.out.println("The state of regulation is ON.");
		}
		else if (regulationState == "OFF"){
			lightThread.halt();
			System.out.println("The state of regulation is OFF.");
		}
		else{
			lightThread.halt();
			System.out.println("Error message, the state of regulation is OFF.");
		}
	}


	/**
	 * FONCTION POUR CONNAITRE L'ETAT DE LA LUMIERE DU TABLEAU
	 * @param board_state
	 */
	public void actionPerformedForBoard(String boardState) {

		if(boardState == "ON"){
			lightThread.start();
			System.out.println("The light of the board is ON.");
		}
		else{
			lightThread.halt();
			System.out.println("The light oh the board is OFF.");
		}
	}



	/*********************************************************************************************
	 *********************** LES FONCTIONS D'ENVOI AU COMPOSANT KONNEX ***************************
	 *********************************************************************************************/

	/**
	 * ENVOI DE LA COMMANDE "LUX" POUR LA GESTION DES LUMIERES DE LA SALLE
	 * @param luxLevel
	 */
	public void sendLightCommandRegulationRoom(String newRoomValueKNX){
		MessagePort prodPort = getPortByName("lightCommandRegulationKonnex",MessagePort.class);
		String msg;
		VALUE = newRoomValueKNX;
		
		if(prodPort != null) {

			// Activate Konnex Equipment
			msg = "LIGHT_ROOM:" + VALUE + getDictionary().get("roomLightLevel");
			prodPort.process(msg);
		}
	}


	/**
	 * ENVOI DE LA COMMANDE "ON" OR "OFF" POUR LA GESTION DES LUMIERES DU TABLEAU
	 * @param stateBoad
	 */
	private void sendLightCommandStateBoard(String newBoardStateKNX){
		MessagePort prodPort = getPortByName("lightCommandRegulationKonnex",MessagePort.class);
		String msg;

		if(prodPort != null) {

			// Activate Konnex Board Equipment
			if ((new String(newBoardStateKNX)).compareTo(ON) == 0){
				msg = "LIGHT_BOARD:" + ON + ":" + getDictionary().get("boardLightState");
				prodPort.process(msg);
			}

			// Desactivate Konnex Board Equipment
			if ((new String(newBoardStateKNX)).compareTo(OFF) == 0){
				msg = "LIGHT_BOARD:" + OFF;
				prodPort.process(msg);
			}
		}
	}


	/**
	 * ENVOI DE LA COMMANDE "UP" OR "DOWN" POUR LA GESTION DES VOLETS DE LA SALLE
	 * @param stateShutter
	 */
	private void sendShutterCommandState(String newShutterStateKNX){
		MessagePort prodPort = getPortByName("lightCommandRegulationKonnex",MessagePort.class);
		String msg;

		if(prodPort != null) {

			// Activate Konnex Shutter Equipment
			if ((new String(newShutterStateKNX)).compareTo(UP) == 0){
				msg = "SHUTTER:" + UP;
				prodPort.process(msg);
			}

			// Desactivate Konnex Shutter Equipment
			if ((new String(newShutterStateKNX)).compareTo(DOWN) == 0){
				msg = "SHUTTER:" + DOWN;
				prodPort.process(msg);
			}
		}
	}
}
