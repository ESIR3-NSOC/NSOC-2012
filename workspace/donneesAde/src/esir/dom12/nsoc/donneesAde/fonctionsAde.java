package esir.dom12.nsoc.donneesAde;

import java.io.IOException;

public class fonctionsAde {
	public static String[] planningDeuxJours (int annee, int mois, int jour, String batiment, String salle) throws IOException{
		//ex : init("982",2012,12,18,"41","1") 
		//ESIR1 997 / ESIR2 111 / ESIR3 982
		int i=0; int j=0;
		String[] planning = new String[20];
		
		donneesAde.init ("982",annee,mois,jour,batiment,salle);
		String[] data = donneesAde.getPlanningSalle();
		planning=data;
		
		donneesAde.init ("111",annee,mois,jour,batiment,salle);
		data = donneesAde.getPlanningSalle();
		while (j<data.length){
			planning[i+j]=data[j];
			j++;
			i++;
		}
		
		donneesAde.init ("997",annee,mois,jour,batiment,salle);
		data = donneesAde.getPlanningSalle();
		j=0;
		while (j<data.length){
			planning[i+j]=data[j];
			j++;
			i++;
		}
		
		return planning;
	}
}
