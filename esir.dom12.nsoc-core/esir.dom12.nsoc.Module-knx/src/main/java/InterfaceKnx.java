/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 12/23/12
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public interface InterfaceKnx {


    /**
     * L'appel a cette fonction permet de
     * @param addComposant: adresse de l'équipement a controler
     * @param value: Valeur a donner a l'equipement
     * @return: retourne true si tout c'est bien passé false sinon
     */
     public boolean setComposant(String addComposant, float value );



}
