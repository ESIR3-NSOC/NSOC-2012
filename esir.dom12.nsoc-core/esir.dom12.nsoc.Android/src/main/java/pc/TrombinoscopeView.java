package pc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.LinearLayout;
import android.widget.ImageView;
import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastien
 * Date: 13/02/13
 * Time: 08:26
 * To change this template use File | Settings | File Templates.
 */
public class TrombinoscopeView extends LinearLayout {

    private Context ctx;
    private int i;

    public TrombinoscopeView(Context context) {

            super(context);
            this.ctx = context;

    }

    public void viewImgIcon (Bitmap imBm){

    ImageView iV = new ImageView(ctx);
    iV.setImageBitmap(imBm);
    addView(iV);
    }

}
