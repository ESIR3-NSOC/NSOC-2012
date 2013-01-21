package esir.dom12.nsoc.donneesAde;

import java.io.IOException;
import java.util.Calendar;



public class fonctionsAde {
	public static String[] planningSalleParDate (int annee, int mois, int jour, String batiment, String salle) throws IOException{
		//ex : init("982",2012,12,18,"41","1") 
		
		donneesAde.init ("5238",annee,mois,jour,batiment,salle);
		String[] planning = donneesAde.getPlanningSalle();
		
		return planning;
	}
	public static String coursActuelParEtudiant(String etudiant) throws IOException{
		//retourne cours!=null si l'etudiant a cours
		String ressource =etudiant;//recuperer par le nom de l'étudiant
		//recup heure et date
		Calendar calendar = Calendar.getInstance();	
		int annee = calendar.get(Calendar.YEAR);
		int mois = calendar.get(Calendar.MONTH)+1;
		int jour = calendar.get(Calendar.DAY_OF_MONTH)+1;
		int heures = calendar.get(Calendar.HOUR);
		int partie=calendar.get(Calendar.AM_PM);
		
		if (partie ==1)
			heures+=12;
		int minutes = calendar.get(Calendar.MINUTE);	
		int temps=heures*100+minutes;
		System.out.println("le "+jour+"-0"+mois+"-"+annee+" à "+temps);
		
		donneesAde.init (ressource,annee,mois,jour,"","");
		String cours = donneesAde.getPlanningEtudiant(etudiant,temps);//ressource
		return cours;
	}
}
