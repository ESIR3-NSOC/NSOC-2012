package esir.dom12.nsoc.bdd;

import android.graphics.Bitmap;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 21/01/13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public interface ConnexionBDDInterface {

    public String sendRequestFromNfcToBdd (String req) throws SQLException, ClassNotFoundException;

    public Bitmap sendRequestFromTrombiToBdd (String req)  throws SQLException, ClassNotFoundException;
}
