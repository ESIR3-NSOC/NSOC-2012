package pc;

import org.kevoree.android.framework.helper.UIServiceHandler;
import org.kevoree.android.framework.service.KevoreeAndroidService;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastien
 * Date: 11/02/13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */

@Library(name = "Android")
@ComponentType
public class ApplicationComponent extends AbstractComponentType {


    private KevoreeAndroidService uiService = null;
    private FirstView firstView ;
    private WelcomeView welcomeView ;

    @Start
    public void start () {


        uiService = UIServiceHandler.getUIService();
        uiService.getRootActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.

                firstView = new FirstView (uiService.getRootActivity());
                welcomeView =new WelcomeView(uiService.getRootActivity());
                uiService.addToGroup("First", firstView);
                uiService.addToGroup("Welcome",welcomeView);


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


    public void changeView(){
        /*uiService.getRootActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                uiService.remove(firstView);
                welcomeView = new WelcomeView (uiService.getRootActivity());
                uiService.addToGroup("Appli", welcomeView);

            }
            //uiService.remove(firstView);


    });*/
}
}










