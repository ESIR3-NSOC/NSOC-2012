package pc;

import android.content.Context;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastien
 * Date: 09/02/13
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
public class WelcomeView extends RelativeLayout
{
    private Context ctx;
    private TextView welcomeTv;

    public WelcomeView(Context context) {
        super(context);
        this.ctx = context;
        initUI();
        configUI();
    }

    private void initUI() {
       welcomeTv = new TextView(ctx);
    }

    private void configUI() {

      welcomeTv.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_HORIZONTAL);
      welcomeTv.setText("Welcome Mister Professor");
      welcomeTv.setTextSize(40);
      addView(welcomeTv);

    }


}
