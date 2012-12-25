/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 12/23/12
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GestionLumInterface{


    /**
     * Cette methode va appeler le bon module pour geré l'éclairage
     * @param add: addresse de l'équipement a commander
     * @param value: valeur a donner a l'équipement
     * @param type: type d'équipement (KNX, Dali, Bacnet)
     * @return
     */
    public boolean commandEquipement(String add, String type, float value);


}