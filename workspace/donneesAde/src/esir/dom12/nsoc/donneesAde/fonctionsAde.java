package esir.dom12.nsoc.donneesAde;

import java.io.IOException;

public class fonctionsAde {
	public static String[] planningSalleParDate (int annee, int mois, int jour, String batiment, String salle) throws IOException{
		//ex : init("982",2012,12,18,"41","1") 
		
		donneesAde.init ("5238",annee,mois,jour,batiment,salle);
		String[] planning = donneesAde.getPlanningSalle();
		
		return planning;
	}
}
