package esir.dom12.nsoc.nfc;


import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 09/01/13
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */

public interface NFCTranslatorInterface {

    public String sendNumeroTagNFCFromGestionAccesToNfc (String numeroTagNfc) throws SQLException, ClassNotFoundException;

}
