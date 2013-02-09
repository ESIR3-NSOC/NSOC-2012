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
    private Button btn;
    private TextView nfcText;

    public FirstView(Context context) {
        super(context);
        this.ctx = context;
        initUI();
    }

    private void initUI() {

     nfcText = new TextView(ctx);
     btn = new Button(ctx);


     nfcText.setText("Please Check Your NFC card");
     btn.setText("next");

     btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  Toast.makeText(ctx,"Test",Toast.LENGTH_LONG);

            }

        });

     RelativeLayout.LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


     addView(nfcText, params);
     addView(btn,params);



    }


}
