
import java.util.*;

import org.kevoree.framework.MessagePort;
import org.kevoree.annotation.*;

@Provides({
        @ProvidedPort(name = "entreeADE", type = PortType.MESSAGE)
})

@Requires({
        @RequiredPort(name = "sortieAffichage", type = PortType.MESSAGE, optional = true)
})

@DictionaryType({
        @DictionaryAttribute(name = "ConnexionDelay", defaultValue = "2000", optional = true)
})

class donneesAAfficher {

    public void date (){

        // Lecture de la date et de l'heure
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int year = now.get(Calendar.YEAR);
        int second = now.get(Calendar.SECOND);

        TimeZone tz = TimeZone.getTimeZone("CST");
        TimeZone.setDefault(tz);

        //affiche la date et l'heure au complet
        System.out.print("Le " + day + "/" + month + "/" + year + ", " + hour + ":" + minute + ":" + second );

    }

    Boolean [] libre;

    @Port(name = "entreeADE")
    public void libre (String [] emploiDuTemps){

                libre = new Boolean[4];
        int i = 0;

        while (i <= 3) {
            if (emploiDuTemps[i] != null) {
                libre[i] = true;
                i++;
            }
        }

        MessagePort port = getPortByName("sortieAffichage",MessagePort.class);
        port.process(emploiDuTemps);
        port.process(libre);
    }
}







	/*
	}*/

        // Emploi du temps du jour en cours et du prochain jours de cours

        // Occupation de la salle ? : OCCUPEE ... LIBRE OCCUPATION ... LIBRE

	/*public void occupation(){

on a la date
on a l'heure
on a le numéro de salle
si l'heure actuelle comprise dans une plage horaire et qu'il y a un cours qui se déroule, afficher 'occupé'
si c'est null alors on est soit en "libre" soit en "libre occupation"

la salle n'est pas en "occupation"
si la salle a été taguée dans la plage horaire
alors on est en libre occupation

si ce n'est ni occupée ni en libre occupation
alors la salle est libre dans la plage horaire en cours

if (occupee = false & libreoccupation = false){
	System.out.print ("LIBRE");
}

// Cours actuel
// En ce moment : Cours : ; Promotion : ; Enseignant
 public void encours(){
	 getPlanningSalle
 }

// Prochain cours : Date - Plage horaire ; Cours : ; Promotion : ; Enseignant :.
	 */
