package esir.dom12.nsoc.module_gestion_dacces;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

/**
 * Created by IntelliJ IDEA.
 * User: gnain
 * Date: 27/10/11
 * Time: 13:43
 */

// provider port de sortie
// requires port d'entrée


@Provides(value = {

        @ProvidedPort(name = "identification_sortie", type = PortType.MESSAGE),
        @ProvidedPort(name = "ADE_communication_sortie", type = PortType.SERVICE),
        @ProvidedPort(name = "BDD_communication_sortie", type = PortType.SERVICE),
        @ProvidedPort(name = "Gestionnaire_Porte", type = PortType.SERVICE)
})

@Requires({

        @RequiredPort(name = "identification_entree", type = PortType.MESSAGE),
        @RequiredPort(name = "ADE_communication_entree", type = PortType.SERVICE,className = interfaceName.class  ,optional = false),
        @RequiredPort(name = "BDD_communication_entree", type = PortType.SERVICE, optional = true)

})

@DictionaryType({
        @DictionaryAttribute(name = "tag", optional = false)
})

@ComponentType
public class ModuleGestionDacces extends AbstractComponentType {


    static String id;
    static String tag_recu;
    static int cpt =0;


    @Start
    public void startComponent() {
        System.out.println("Consumer:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Consumer:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Consumer:: Update");
    }

    @Port(name = "identification_entree")
    public void traitement_message(Object o) {


        tag_recu=o.toString();


    }


    @Port(name = "Gestionnaire_Porte")
    public void setAutorization(){

        String NomPrenom="";
        Boolean autorization=false;



        getPortByName("");
        SendNumeroTagNFC(tag_recu);
        NomPrenom = receiveName();
        SendName(NomPrenom);
        autorization = GetAuto();
        if(autorization = true){
            cpt=cpt+1;
        }
        SendAuto(autorization);

        if()





    }





    /*




    @Port(name= "identification_entree")
    public void buzz(int duree){
        try
        {
            Thread.sleep(duree);

        }
        catch (InterruptedException ie)
        {
            System.out.println(ie.getMessage());
        }

    }

    */
}
