package pc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import org.kevoree.MessagePortType;
import org.kevoree.android.framework.helper.UIServiceHandler;
import org.kevoree.android.framework.service.KevoreeAndroidService;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastien
 * Date: 11/02/13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */

@Requires({
        @RequiredPort(name = "SCN", type = PortType.MESSAGE, needCheckDependency = false , optional = true),
        @RequiredPort(name = "trombiRequest", type = PortType.SERVICE, needCheckDependency = false)
        //@RequiredPort(name = "Empl", type = PortType.SERVICE, needCheckDependency = false, className = Ade.class)
})
@Provides({
        @ProvidedPort(name = "Trombi", type = PortType.MESSAGE)
})



@Library(name = "Android")
@ComponentType
public class ApplicationComponent extends AbstractComponentType {


    private KevoreeAndroidService uiService = null;
    private FirstView firstView ;
    private ScenarioView scenarioView;
    private TrombinoscopeView trombinoscopeView;
    private Boolean theBoolean = true;
    private Boolean theBoolean1 = true;
    private Boolean theBoolean2 = true;
    private Boolean theBoolean3 = true;

    @Start
    public void start () {


        uiService = UIServiceHandler.getUIService();
        uiService.getRootActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.

                firstView = new FirstView (uiService.getRootActivity());
                scenarioView =new ScenarioView(uiService.getRootActivity());
                trombinoscopeView =new TrombinoscopeView(uiService.getRootActivity());

                uiService.addToGroup("NFC", firstView);
                uiService.addToGroup("SCENARIO", scenarioView);
                uiService.addToGroup("TROMBI",trombinoscopeView);

                /**** BOUTON SCENARIO ****/

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

    String sendBool;
    if(bn.equals(true)){sendBool ="ON";}
    else{sendBool="OFF";}
    return(sendBool);
    }

    @Port(name= "Trombi")
    public void trombi(Object oId) {

        String URL = oId.toString();
        Bitmap bp = BitmapFactory.decodeFile(URL)  ;
        trombinoscopeView.viewBitmap(bp);

    }

    @Port(name = "NFC_Tager")
    public void nfc_check(Object oId) {

    String id = oId.toString();
    trombi(id);

    }


    public void trombi(String st){

       getPortByName("trombiRequest", MessagePort.class).process(st);

    }

}









