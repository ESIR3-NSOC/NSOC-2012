package pc;

import android.view.View;
import android.widget.Button;
import org.kevoree.MessagePortType;
import org.kevoree.android.framework.helper.UIServiceHandler;
import org.kevoree.android.framework.service.KevoreeAndroidService;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastien
 * Date: 11/02/13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */

@Requires({
        @RequiredPort(name = "SCN", type = PortType.MESSAGE, needCheckDependency = false , optional = true)
})

@Library(name = "Android")
@ComponentType
public class ApplicationComponent extends AbstractComponentType {


    private KevoreeAndroidService uiService = null;
    private FirstView firstView ;
    private ScenarioView scenarioView;
    private Boolean theBoolean = false;
    private Boolean theBoolean1 = false;
    private Boolean theBoolean2 = false;
    private Boolean theBoolean3 = false;

    @Start
    public void start () {


        uiService = UIServiceHandler.getUIService();
        uiService.getRootActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.

                firstView = new FirstView (uiService.getRootActivity());
                scenarioView =new ScenarioView(uiService.getRootActivity());
                uiService.addToGroup("NFC", firstView);
                uiService.addToGroup("SCENARIO", scenarioView);


                scenarioView.btnSCNP.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        getPortByName("SCN", MessagePort.class).process("SCNP:"+onOff(theBoolean));
                        theBoolean = !theBoolean;
                    }
                });

                scenarioView.btnSCNB.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        getPortByName("SCN", MessagePort.class).process("SCNB:"+onOff(theBoolean1));
                        theBoolean1 = !theBoolean1;
                    }
                });

                scenarioView.btnSCNE.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        getPortByName("SCN", MessagePort.class).process("SCNE:"+onOff(theBoolean2));
                        theBoolean2 = !theBoolean2;
                    }
                });

                scenarioView.btnSCNM.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        getPortByName("SCN", MessagePort.class).process("SCNM:"+onOff(theBoolean3));
                        theBoolean3 = !theBoolean3;
                    }
                });

            }
        });
        //uiService.getRootActivity().setContentView(firstView);
    }

    @Stop
    public void stop () {
        //uiService.remove(firstView);
    }


    @Update
    public void update() {
        stop();
        start();
    }

    private String onOff(Boolean bn) {

    String sendBool= new String ();
    if(bn.equals(true)){sendBool ="ON";}
    else{sendBool="OFF";}
    return(sendBool);
    }

}










