package esir.dom12.nsoc.donneesAde;

import java.io.IOException;
import java.util.Calendar;

public class fonctionsAde implements Ade{
    
	private donneesAde da;

	public fonctionsAde (){
		da = new donneesAde();
	}
	public boolean autorisation (String nom) throws IOException{
		if (coursActuelParEtudiant(nom)==null)
				return false;
		return true;
	}
	
	public String[] planningSalleParDate (int annee, int mois, int jour, String batiment, String salle) throws IOException {
		//ex : init("982",2012,12,18,"41","1") 
		
		da.init ("5238",annee,mois,jour+1,batiment,salle);
		String[] planning = da.getPlanningSalle();
		
		return planning;
	}
	public String[] planningEtudiantParDate(int annee, int mois, int jour, String etudiant) throws IOException{
		da.init(da.recupererIdEtudiant(etudiant),annee,mois,jour+1,"","");		
		String[] infos=new String[15];
		String[] elements=new String[10];
		
		infos[0] = da.recupererAde();//récupération d'ade
		infos = da.parseAde(infos[0]);//parse ade par cours
		
		infos[0]=null;
		int i=1;
		while (i<10){//récupère les informations voulues
			if (infos[i]!=null){
				elements = da.parsePlageHoraire(infos[i]);
				infos[i]=da.recupHeureCours(elements)+" / "+da.recupNomCours(elements)+" / "+da.recupLocation(elements);
				}
			i++;
		}
		for (i=1; infos[i]!=null; i++){
			System.out.println(infos[i]);
		}
		return infos;
	}
	public String coursActuelParEtudiant(String etudiant) throws IOException{
		//String etudiant = "Nom Prenom";
		//retourne cours!=null si l'etudiant a cours
		String ressource =da.recupererIdEtudiant(etudiant);//recuperer par le nom de l'étudiant
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
		da.init (ressource,annee,mois,jour,"","");
		etudiant=ressource;
		String cours = da.getPlanningEtudiant(etudiant,temps);//ressource
		return cours;
	}
}
