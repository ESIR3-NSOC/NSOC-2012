package esir.dom12.nsoc.bdd;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 21/01/13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public interface ConnexionBDDInterface {

    public String sendRequestFromNfcToBdd (String req);

    public String sendRequestFromTrombiToBdd (String req);
}
