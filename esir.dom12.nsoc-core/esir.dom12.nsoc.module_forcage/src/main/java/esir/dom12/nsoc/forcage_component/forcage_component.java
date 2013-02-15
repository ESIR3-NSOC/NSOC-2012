package esir.dom12.nsoc.forcage_component; /**
 * Created with IntelliJ IDEA.
 * User: travail
 * Date: 13/02/13
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.util.StringTokenizer;







@Provides({
        @ProvidedPort(name = "commandForcage", type = PortType.MESSAGE)
})

@Requires({
        @RequiredPort(name = "commandRegul", type = PortType.MESSAGE, optional = false),
        @RequiredPort(name = "isSettingON", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "isSettingOFF", type = PortType.MESSAGE, optional = true)

})

@DictionaryType({

        @DictionaryAttribute(name = "light", defaultValue = "450", optional = true),
        @DictionaryAttribute(name = "video", defaultValue = "OFF", optional = true),
        @DictionaryAttribute(name = "boardLightState", defaultValue = "OFF", optional = true),
        @DictionaryAttribute(name = "scenarioName", defaultValue = "FORC",optional = true)


})

@ComponentType

public class forcage_component extends AbstractComponentType {

    private static String ON = "ON";
    private static String OFF = "OFF";
    private static String LIGHT = "LIGHT";
    private static String VIDEO = "VIDEO";




    @Start
      public void startComponent() {
        this.set(OFF);
    }

    @Stop
    public void stopComponent() {
        this.set(OFF);
    }

    @Update
    public void updateComponent() {
        this.stopComponent();
        this.startComponent();
    }

    /**
     * Receive message to activate/desactivate Forcage
     */
    @Port(name = "commandForcage")
    public void receiveMessage(Object o) {


        // Reception of message
        System.out.println("mode_Forcage " + getDictionary().get("scenarioName") + " :: Received " + o.toString());


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

            // The recipient of the message is this forcing component : command readingÉ
            if ((new String(receiver_string)).compareTo((String) getDictionary().get("scenarioName")) == 0){

                // What is the forced device ? VIDEO or LIGHT
                // Extraction of device
                String device_string = tokens.nextToken();

                // What is the forced command? ON or OFF
                // Extraction of main command
                String command_string = tokens.nextToken();

                // Action is ON, we have to force a device...
                if ((new String(action_string)).compareTo((String) ON) == 0){
                    this.set(ON);

                    if ((new String(device_string)).compareTo((String) VIDEO) == 0){
                        sendVideoCommandRegulation(ON);
                    }
                    else if ((new String(device_string)).compareTo((String) LIGHT) == 0){
                        sendLightCommandRegulation(ON);
                    }

                // Action is OFF, we have to set OFF the forcing component, we do not change device state
                else if ((new String(action_string)).compareTo((String) OFF) == 0){
                    this.set(OFF);

                        if ((new String(device_string)).compareTo((String) VIDEO) == 0){
                            sendVideoCommandRegulation(OFF);
                        }
                        else if ((new String(device_string)).compareTo((String) LIGHT) == 0){
                            sendLightCommandRegulation(OFF);
                        }
                }

                }

            }
        }


    }










    /**
     * Change the state of forcage
     * @param state must be a "ON" or "OFF" command
     */
    private void set(String state){


        // Forcage is setting ON
        if ((new String(state)).compareTo(ON) == 0){
            System.out.println("Forcage "  + " :: ON");
            getPortByName("isSettingON",MessagePort.class).process(true);


        }

        // Forcage is setting OFF
        else if ((new String(state)).compareTo(OFF) == 0){
            System.out.println("Forcage "  + " :: OFF");
            getPortByName("isSettingOFF",MessagePort.class).process(true);

        }

        // With a bad command, print a error message
        else System.out.println("Forcage " +  " :: The command can not be interpreted ::  " + state);
    }






    private void sendLightCommandRegulation(String state){
        MessagePort prodPort = getPortByName("commandRegul",MessagePort.class);
        String msg;

        if(prodPort != null) {

            // Activate Light Regulation with appropriate values
            if ((new String(state)).compareTo(ON) == 0){
                msg = LIGHT + ":" + ON + ":" + getDictionary().get("boardLightState") + ":" + getDictionary().get("lightLevel");
                prodPort.process(msg);
                System.out.println("Forcage "+ " :: send " + msg);
            }

            // Desactivate Light Regulation
            if ((new String(state)).compareTo(OFF) == 0){
                msg = LIGHT + ":" + OFF;
                prodPort.process(msg);
                System.out.println("Forcage "+ " :: send " + msg);
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
                System.out.println("Forcage "+ " :: send " + msg);
            }

            // Desactivate Video Regulation
            if ((new String(state)).compareTo(OFF) == 0){
                msg = VIDEO + ":" + OFF;
                prodPort.process(msg);
                System.out.println("Forcage "+ " :: send " + msg);
            }
        }
    }


}

