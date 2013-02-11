package pc;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: max
 * Date: 10/07/12
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class FirstView extends LinearLayout {


    private Context ctx;
    private Button btn;
    private ImageView logoEsirIm;
    private TextView nfcText;
    public Boolean secondView;
    ApplicationComponent ac = new ApplicationComponent();

    public FirstView(Context context) {
        super(context);
        this.ctx = context;
        initUI();
        configUI();
    }

    private void initUI() {

        nfcText = new TextView(ctx);
        btn = new Button(ctx);
        logoEsirIm = new ImageView(ctx);
        secondView = false;

    }

    public void configUI() {

        LinearLayout.LayoutParams paramstv = new LayoutParams(
                 LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        paramstv.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;

        LinearLayout.LayoutParams paramsBtn = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        paramsBtn.gravity = (Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
        //String imageInSD = "/sdcard/logoESIR.PNG";
        //Bitmap image = BitmapFactory.decodeFile(imageInSD);





        nfcText.setText("Please Check Your NFC card 4");
        nfcText.setTextSize(40);
        nfcText.setLayoutParams(paramstv);
        //nfcText.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        //RelativeLayout.LayoutParams paramstv = new LayoutParams(
        //      LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);




        //logoEsirIm.setImageBitmap(image);
       // logoEsirIm.setMaxHeight(50);
        //logoEsirIm.setMaxWidth(200);


        //RelativeLayout.LayoutParams paramsIm = new LayoutParams(
        //        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //logoEsirIm.setLayoutParams(new FrameLayout.LayoutParams(val_highW,val_highH,(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL)));

        btn.setText("nfc");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ac.changeView();
            }

        });

        btn.setLayoutParams(paramsBtn);
        //RelativeLayout.LayoutParams paramsBn = new LayoutParams(
        //        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


        addView(nfcText);
       // addView(logoEsirIm);
        addView(btn);


    }


}
