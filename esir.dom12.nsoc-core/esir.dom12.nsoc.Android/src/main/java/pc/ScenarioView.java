package pc;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.kevoree.android.framework.service.KevoreeAndroidService;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastien
 * Date: 09/02/13
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
public class ScenarioView extends LinearLayout
{
    private Context ctx;
    public Button btnSCNB;
    public Button btnSCNP;
    public Button btnSCNM;
    public Button btnSCNE;
   // private KevoreeAndroidService uiService = null;

    public ScenarioView(Context context) {
        super(context);
        this.ctx = context;
        initUI();
        configUI();
    }


    private void initUI() {
        btnSCNB = new Button(ctx);
        btnSCNP = new Button(ctx);
        btnSCNM = new Button(ctx);
        btnSCNE = new Button(ctx);

}

    private void configUI() {

       btnSCNP.setText("MODE PRESENTATION");
       btnSCNB.setText("MODE FILM");
       btnSCNM.setText("MODE TABLEAU");
       btnSCNE.setText("MODE EXTINCTION");

       addView(btnSCNP);
       addView(btnSCNB);
       addView(btnSCNM);
       addView(btnSCNE);





 }


}