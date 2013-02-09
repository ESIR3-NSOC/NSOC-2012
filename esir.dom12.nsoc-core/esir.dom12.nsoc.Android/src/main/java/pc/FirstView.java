package pc;

import android.content.Context;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created with IntelliJ IDEA.
 * User: max
 * Date: 10/07/12
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class FirstView extends RelativeLayout {


    private Context ctx;
    private Button btn_connect;
    private OnClickListener listener;

    public FirstView(Context context) {
        super(context);
        this.ctx = context;
        initUI();
    }

    private void initUI() {

     TextView nfcText = new TextView(ctx);
     nfcText.setText("Please Check Your NFC card");


     RelativeLayout.LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
     nfcText.setLayoutParams(params);

     addView(nfcText, params);

    }





}
