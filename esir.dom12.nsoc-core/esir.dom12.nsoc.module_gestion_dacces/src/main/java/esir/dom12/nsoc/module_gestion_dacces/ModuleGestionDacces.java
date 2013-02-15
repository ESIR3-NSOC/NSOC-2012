package esir.dom12.nsoc.module_gestion_dacces;

import esir.dom12.nsoc.donneesAde.Ade;
import esir.dom12.nsoc.nfc.NFCTranslatorInterface;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: gnain
 * Date: 27/10/11
 * Time: 13:43
 */

// provider port de sortie
// requires port d'entrée


@Provides(value = {
        @ProvidedPort(name = "entree_test", type =PortType.MESSAGE ) ,
        @ProvidedPort(name = "identification_sortie", type = PortType.MESSAGE)
})

@Requires({

        @RequiredPort(name = "identification_entree", type = PortType.MESSAGE),
        @RequiredPort(name = "identification_personne", type = PortType.MESSAGE),
        @RequiredPort(name = "ADE_communication_entree", type = PortType.SERVICE,className = Ade.class  ,optional = false),
        @RequiredPort(name = "BDD_communication_entree", type = PortType.SERVICE,className = NFCTranslatorInterface.class, optional = false),
        @RequiredPort(name = "occupation",type = PortType.MESSAGE)

})



@ComponentType
public class ModuleGestionDacces extends AbstractComponentType {


    String id;
    String tag_recu;
    int cpt =0;


    @Start
    public void startComponent() {
        System.out.println("Module_gestion_dacces:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Module_gestion_dacces:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Gestion_gestion_dacces:: Update");
    }

    @Port(name = "identification_sortie")
    public void traitement_message(Object o) {


        tag_recu=((String)o).toString();
        // TODO Appel methode de Marc
        NFCTranslatorInterface nfci = getPortByName("BDD_communication_entree", NFCTranslatorInterface.class);
        String nomPrenom = null;
        try {
            nomPrenom = nfci.sendNumeroTagNFCFromGestionAccesToNfc(tag_recu);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //  TODO envoie pour seb
        getPortByName("identification_personne",MessagePort.class).process(nomPrenom);
        // TODO appel methode Antoine avec Resultat de Marc
        Ade ADEI = getPortByName("ADE_communication_entree",Ade.class);
        try {
            boolean autorization= ADEI.autorisation(nomPrenom);
            System.out.println("resultat"+ autorization);
            // TODO Envoie information à Yann
            getPortByName("identification_entree", MessagePort.class).process(autorization);

            if(autorization = true){
                cpt=cpt+1;
                getPortByName("occupation",MessagePort.class).process(cpt);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
    @Port(name= "entree_test")
    public void test(Object o){
        traitement_message("3");
    }


}
