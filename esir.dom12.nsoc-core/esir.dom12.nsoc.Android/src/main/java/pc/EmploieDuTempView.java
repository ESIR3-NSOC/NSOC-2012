package pc;

import android.content.Context;
import android.widget.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastien
 * Date: 13/02/13
 * Time: 08:28
 * To change this template use File | Settings | File Templates.
 */
public class EmploieDuTempView extends LinearLayout {

    private Context ctx;
    private Button btn;

    public EmploieDuTempView(Context context) {

        super(context);
        this.ctx = context;

    }

    public void affichEmploi (String st){

        TextView textView = new TextView(ctx);
        textView.setText(st);
    }


}
