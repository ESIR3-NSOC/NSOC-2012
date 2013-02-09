package pc;

import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import org.kevoree.android.framework.helper.UIServiceHandler;
import org.kevoree.android.framework.service.KevoreeAndroidService;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;


@Library(name = "Android")
@ComponentType
public class AppliComponent extends AbstractComponentType {

    private KevoreeAndroidService uiService = null;
    private RelativeLayout layout = null;
    private FirstView firstView;

    @Start
    public void start () {


        uiService = UIServiceHandler.getUIService();

        firstView = new FirstView(uiService.getRootActivity());


        uiService.addToGroup("Appli", firstView);



    }

    @Stop
    public void stop () {
        uiService.remove(layout);
    }


    @Update
    public void update() {
        stop();
        start();
    }
}
