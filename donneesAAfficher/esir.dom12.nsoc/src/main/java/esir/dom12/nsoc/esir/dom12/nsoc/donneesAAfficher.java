package esir.dom12.nsoc.esir.dom12.nsoc;

import java.io.IOException;
import java.util.*;

import org.kevoree.framework.AbstractComponentType;
import org.kevoree.annotation.*;

@Provides({
        @ProvidedPort(name = "entreeADE", type = PortType.MESSAGE),
        @ProvidedPort(name = "occupation", type = PortType.MESSAGE),
        @ProvidedPort(name = "recupDateHeure", type = PortType.MESSAGE)
})

@Requires({
        @RequiredPort(name = "sortieAffichage", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "comAde", type = PortType.SERVICE, optional = true)
})

@DictionaryType({
        @DictionaryAttribute(name = "ConnexionDelay", defaultValue = "2000", optional = true)
})

@ComponentType
class donneesAAfficher extends AbstractComponentType{
    public int[] date (){
    	
        // lecture de la date et de l'heure
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int year = now.get(Calendar.YEAR);
        int second = now.get(Calendar.SECOND);

        TimeZone tz = TimeZone.getTimeZone("CST");
        TimeZone.setDefault(tz);

        int [] output = {0,0,0,0,0};
        output[0]=day;
        output[1]=month;
        output[2]=year;
        output[3]=hour;
        output[4]=minute;
        output[5]=second;
        return output;
    }
    
    @Port(name = "recupDateHeure")
    //affiche la date et l'heure au complet
    public String recupDateHeure(){
    	int [] output = date();
    	return "Le " + output[0]+ "/" + output[1] + "/" + output[2] + ", " + output[3] + ":" + output[4] + ":" + output[5];
    }

    @Port(name = "occupation")
    //permet de donner l'état de la salle
    public String occupation () throws IOException{
    	int [] date = date();
    	int i=0;
    	String etat=null;
    	String [] emploiDuTemps=getPortByName("comAde",Ade.class).planningSalleParDate(date[2], date[1], date[0], "41", "3");
    	String heure = date[3]+""+date[4];  
    	
    	while (i<emploiDuTemps.length){
    		if(Integer.parseInt(heure)>Integer.parseInt (emploiDuTemps[i].substring(0, 3)) && 
    				Integer.parseInt(heure)<Integer.parseInt (emploiDuTemps[i].substring(0, 3))+2){
    			etat="OCCUPE";
    		}
    		i++;
    	}
    	
    	/*while (){
    		etat = "EN LIBRE OCCUPATION";
    	}*/
    	
    	if (etat != "EN LIBRE OCCUPATION" && etat != "OCCUPE"){
    		etat="LIBRE";
    	}
    	return etat;
    }
    
   /* @Port(name = "entreeADE")
    // renvoie l'emploi du temps du jour même et du jour suivant
    public String[] emploiDuTempsDeuxJours() throws IOException{
    	int [] date = date();
    	String [] emploiDuTemps=getPortByName("comAde",Ade.class).planningSalleParDate(date[2], date[1], date[0], "41", "3");
    	String [] emploiDuTempsSuivant=getPortByName("comAde",Ade.class).planningSalleParDate(date[2], date[1], date[0]+1, "41", "3");
		
    	return emploiDuTempsSuivant;
    }*/
}
