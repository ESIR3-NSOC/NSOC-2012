package esir.dom12.nsoc.regulation;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.util.StringTokenizer;



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
	@RequiredPort(name = "lightCommandRegulation", type = PortType.MESSAGE, optional = false),
	@RequiredPort(name = "askDataEquipment", type = PortType.MESSAGE, optional = false)
})

@Library(name = "JavaSE")

@ComponentType
public class RegulationComponent extends AbstractComponentType {


	/**
	 * EN: DECLARATION OF VARIABLES
	 * FR: DECLARATION DES VARIABLES
	 */
	private static String LIGHT_ROOM = "ROOM_LIGHT";
	private static String LIGHT_BOARD = "BOARD_LIGHT";
	private static String SHUTTER = "SHUTTER";
	private static String ON = "ON";
	private static String OFF = "OFF";
	private static String UP = "UP";
	private static String DOWN = "DOWN";
	private static String VALUE;

	// Variables to store the data parser
	private static String regulationState; 	// ON or OFF
	private static String boardState;		// ON or OFF
	private static String roomValue;		// Lux Value in String
	private static int roomValueINT;		// Lux Value in Int

	// Variables to store the data of component KNX
	String [] temp1 = new String[4];        // init array with size of 4 (Array for lines)
	String [] temp2 = new String[2];        // init array with size of 2 (Array split lines)
	private static String roomValueKNX;		// Lux Value in String
	private static String boardStateKNX;	// ON or OFF
	private static String shutterStateKNX;	// UP or DOWN

	// Variables for parse data
	private static int parseValueKNX = -1;
	private static int parseOutsideValueKNX = -1;

	// News variables for send command
	private static String newRoomValueKNX;		// Lux Value in String
	private static String newBoardStateKNX;		// ON or OFF
	private static String newShutterStateKNX;	// UP or DOWN
	private static String outsideValueKNX;		// Lux Value in String

	// Variables for default operation
	private static String DEFAULT_ROOM_VALUE = "0";			// Default Value Room
	private static String DEFAULT_BOARD_VALUE = "OFF";		// Default Value Board
	private static String DEFAULT_SHUTTER_VALUE = "DOWN";	// Default Value Shutter
	private static String DAY = "400";						// Lux Value for default iteration



	@Start
	public void startComponent() {
		System.out.println("RegulationComponent :: Start");
	}


	@Stop
	public void stopComponent() {
		System.out.println("RegulationComponent :: Stop");
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
	@Port(name = "commandRegul")
	public void receiveCommandRegul(Object o) {

		// Received Message
		System.out.println("***********************************************************");
		System.out.println("***************** SCENARII COMPONENT *****************");
		System.out.println("RegulationComponent :: Received " + o.toString() + "\n");

		// Data Processing
		if(o instanceof String) {

			String msg = (String)o;
			System.out.println("The component 'RegulationComponent' received :: " + msg);

			// True data we'll get : LIGHT:ON:ON:500
			// False data we'll get : VIDEO:ON

			// Who is the recipient of the message ?
			// Extraction of the recipient
			StringTokenizer tokens = new StringTokenizer(msg,":,;-_|¦\n");
			String receiverRegulString = tokens.nextToken();
			String firstReference = "LIGHT";
			String secondReference = "OFF";

			// Count the terms of the message
			// LIGHT:regulationState:boardState:roomValue
			// LIGHT:ON:ON:700
			if(tokens.countTokens() > 4 || tokens.countTokens() < 4){

				// Extinction regulation
				// LIGHT:regulationState
				// LIGHT:OFF
				if((tokens.countTokens() == 2) && (receiverRegulString.equals(firstReference) == true) && (tokens.nextToken().equals(secondReference) == true)){
					
					// Regulation is OFF
					regulationState = secondReference;
				}
				else{
					
					// Display result
					System.out.println("The component scenario sent a false message !");
					System.out.println("The string received isn't 'LIGHT'.");
					System.out.println("\n");
				}
			}
			else{

				// Iteration to know the regulation
				if(receiverRegulString.equals(firstReference) == false){

					// Display result
					System.out.println("The string received isn't 'LIGHT'.");
					System.out.println("\n");
				}
				else{
					// What is the command ? ON or OFF
					// Extraction of main command of state regulation
					regulationState = tokens.nextToken();
					System.out.println("State of Regulation : " + regulationState);

					// What is the value of the board ? ON or OFF
					// Extraction of main command of value of the regulation of the board
					boardState = tokens.nextToken();
					System.out.println("State of Light Board : " + boardState);

					// What is the value of the room ? Value in LUX
					// Extraction of main command of value of the regulation of the room
					roomValue = tokens.nextToken();
					roomValueINT = Integer.parseInt(roomValue);
					System.out.println("Value of Light Room : " + roomValueINT + "\n");
				}
			}
			// Calling getData compoment
			receiveDataKNX();
		}
	}


	/**
	 * EN: COMPONENT "ORDER KNX" GIVE VALUES OF EQUIPMENT KNX
	 * FR: COMPOSANT "COMMANDE KNX" : DONNE LES VALEURS DES EQUIPEMENTS KNX
	 * @param o
	 */
	@Port(name = "commandKNX")
	public void receiveValueKNX(Object o) {

		// Received Message
		System.out.println("***********************************************************");
		System.out.println("******************** KNX COMPONENT *******************");
		System.out.println("RegulationComponent :: Received " + o.toString());

		// Data Processing
		if(o instanceof String) {

			String msg = (String)o;
			System.out.println("The component 'RegulationComponent' received :: " + msg);
			System.out.println("\n");

			// DataType We'll get :
			// BOARD_LIGHT:true;
			// ROOM_Light:400;
			// SHUTTER:TRUE;
			// BRIGHTNESS:500;


			/**
			 * EN: NEW PARSING FOR REVEIVE DATA KNX
			 * FR: NOUVEAU PARSE DES DONNEES KNX EN RECEPTION
			 */
			temp1 = msg.split(";"); // Split data by ;

			// Extract board state
			temp2 = temp1[0].split(":");            // temp2[0] = BOARD_LIGHT, temp2[1] = true
			if(temp2[1].equals("false")){

				boardStateKNX = "OFF";
			}
			else{

				boardStateKNX = "ON";
			}

			// Extract room value
			temp2 = new String[2];                  // initiate variable
			temp2 = temp1[1].split(":");            // temp2[0] = ROOM_LIGHT, temp2[1] = 400

			roomValue = temp2[1];
			parseValueKNX = Integer.parseInt(roomValue);

			// Extract Shutter value
			temp2 = new String[2];                  // initiate variable
			temp2 = temp1[2].split(":");            // temp2[0] = SHUTTER, temp2[1] = true

			if(temp2[1].equals("false")){

				shutterStateKNX = "DOWN";

			} else{

				shutterStateKNX = "UP";

			}

			// Extract Brightness Value
			temp2 = new String[2];                  // initiate variable
			temp2 = temp1[1].split(":");            // temp2[0] = BRIGHTNESS, temp2[1] = 500

			outsideValueKNX= temp2[1];
			parseOutsideValueKNX = Integer.parseInt(outsideValueKNX);


			/*********************************************************************************************
			 **************************** INTELLIGENCE OF THE REGULATION *********************************
			 *********************************************************************************************/


			// Regulation is ON
			if(regulationState.equals("ON")){

				// Regulation of board light for boardState
				if(boardState.isEmpty()){}

				else{

					newBoardStateKNX = boardState;
					sendLightCommandStateBoard(newBoardStateKNX);
				}

				// Regulation of room light if roomValueINT > roomValueKNX
				// If the user doesn't like -> FORCAGE
				if(roomValueINT > parseValueKNX){

					// Regulation of room if brightness >= roomValueINT
					if(parseOutsideValueKNX >= roomValueINT){

						// Regulation of room if shutter state is DOWN
						if(shutterStateKNX.equals("DOWN")){

							newShutterStateKNX = "UP";
							sendShutterCommandState(newShutterStateKNX);
						}
						// Regulation of room if shutter state is UP
						else{

							newRoomValueKNX = DEFAULT_ROOM_VALUE;
							sendLightCommandRegulationRoom(newRoomValueKNX);
						}
					}
					// Regulation of room if brightness < roomValueINT
					else{

						newRoomValueKNX = roomValue;
						sendLightCommandRegulationRoom(newRoomValueKNX);
						newShutterStateKNX = "UP";
						sendShutterCommandState(newShutterStateKNX);
					}
				}

				// Regulation of room light if roomValueINT < roomValueKNX
				else if(roomValueINT < parseValueKNX && shutterStateKNX.equals("UP")){

					// Regulation of room if brightness >= roomValueINT
					if(parseOutsideValueKNX >= roomValueINT){

						newRoomValueKNX = roomValue;
						sendLightCommandRegulationRoom(newRoomValueKNX);
						newShutterStateKNX = "DOWN";
						sendShutterCommandState(newShutterStateKNX);
					}

					// Regulation of room if brightness < roomValueINT
					else{

						newRoomValueKNX = roomValue;
						sendLightCommandRegulationRoom(newRoomValueKNX);
					}
				}

				// Regulation of room if roomValueINT == 0 (night)
				else if(roomValueINT == 0){

					newRoomValueKNX = roomValue;
					sendLightCommandRegulationRoom(newRoomValueKNX);
				}

				// Regulation of room light if roomValueINT = roomValueKNX
				else{

					newRoomValueKNX = roomValue;
					sendLightCommandRegulationRoom(newRoomValueKNX);
					newShutterStateKNX = "UP";
					sendShutterCommandState(newShutterStateKNX);
				}
			}

			// Regulation is OFF
			else if(regulationState.equals("OFF") && parseOutsideValueKNX > Integer.parseInt(DAY)){
				newRoomValueKNX = DEFAULT_ROOM_VALUE;
				newBoardStateKNX = DEFAULT_BOARD_VALUE;
				newShutterStateKNX = "UP";

				sendLightCommandRegulationRoom(newRoomValueKNX);
				sendLightCommandStateBoard(newBoardStateKNX);
				sendShutterCommandState(newShutterStateKNX);

			}

			// Regulation for FORCING
			else{
				newRoomValueKNX = roomValue;
				newBoardStateKNX = boardState;
				newShutterStateKNX = DEFAULT_SHUTTER_VALUE;

				sendLightCommandRegulationRoom(newRoomValueKNX);
				sendLightCommandStateBoard(newBoardStateKNX);
				sendShutterCommandState(newShutterStateKNX);
			}
		}
	}



	/**
	 * CONSTRUCTEUR VIDE
	 */
	public RegulationComponent () {}



	/*********************************************************************************************
	 ************************* FUNCTIONS SENDING TO COMPONENT KONNEX *****************************
	 *********************************************************************************************/


	/**
	 * EN: SEND ORDER "LUX" FOR THE MANAGEMENT OF THE ROOM LIGHTS
	 * FR: ENVOI DE LA COMMANDE "LUX" POUR LA GESTION DES LUMIERES DE LA SALLE
	 * @param
	 */
	public void sendLightCommandRegulationRoom(String newRoomValueKNX){
		MessagePort prodPort = getPortByName("lightCommandRegulation",MessagePort.class);
		String msg;
		VALUE = newRoomValueKNX;

		if(prodPort != null) {

			// Activate Konnex Equipment
			msg = LIGHT_ROOM + ":" + VALUE;
			prodPort.process(msg);
			System.out.println("Room light command send :: " + msg);
		}
	}


	/**
	 * EN: SEND ORDER "ON" OR "OFF" FOR THE MANAGEMENT OF LIGHTS OF TABLE
	 * FR: ENVOI DE LA COMMANDE "ON" OR "OFF" POUR LA GESTION DES LUMIERES DU TABLEAU
	 * @param newBoardStateKNX
	 */
	private void sendLightCommandStateBoard(String newBoardStateKNX){
		MessagePort prodPort = getPortByName("lightCommandRegulation",MessagePort.class);
		String msg;

		if(prodPort != null) {

			// Activate Konnex Board Equipment
			if ((new String(newBoardStateKNX)).compareTo(ON) == 0){
				msg = LIGHT_BOARD + ":" + ON;
				prodPort.process(msg);
				System.out.println("Board light command send :: " + msg);
			}

			// Desactivate Konnex Board Equipment
			if ((new String(newBoardStateKNX)).compareTo(OFF) == 0){
				msg = LIGHT_BOARD + ":" + OFF;
				prodPort.process(msg);
				System.out.println("Board light command send :: " + msg);
			}
		}
	}


	/**
	 * EN: SEND ORDER "UP" OR "DOWN" FOR THE MANAGEMENT OF COMPONENTS OF THE ROOM
	 * FR: ENVOI DE LA COMMANDE "UP" OR "DOWN" POUR LA GESTION DES VOLETS DE LA SALLE
	 *
	 */
	private void sendShutterCommandState(String newShutterStateKNX){
		MessagePort prodPort = getPortByName("lightCommandRegulation",MessagePort.class);
		String msg;

		if(prodPort != null) {

			// Activate Konnex Shutter Equipment
			if ((new String(newShutterStateKNX)).compareTo(UP) == 0){
				msg = SHUTTER + ":" + UP;
				prodPort.process(msg);
				System.out.println("Shutter command send :: " + msg);
			}

			// Desactivate Konnex Shutter Equipment
			if ((new String(newShutterStateKNX)).compareTo(DOWN) == 0){
				msg = SHUTTER + ":" + DOWN;
				prodPort.process(msg);
				System.out.println("Shutter command send :: " + msg);
			}
		}
	}



	/**
	 * EN: SENDING THE APPLICATION INFORMATION COMPONENT TO "GET DATA"
	 * FR: ENVOI DE LA DEMANDE D'INFORMATIONS AU COMPOSANT "GET DATA"
	 * @param
	 */
	private void receiveDataKNX(){

		MessagePort prodPort = getPortByName("askDataEquipment",MessagePort.class);
		String msg = "getData";
		prodPort.process(msg);

	}
}
