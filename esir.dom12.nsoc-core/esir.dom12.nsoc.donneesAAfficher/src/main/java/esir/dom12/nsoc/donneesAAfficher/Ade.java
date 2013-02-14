package esir.dom12.nsoc.donneesAAfficher;

import java.io.IOException;

public interface Ade {
	
	public String[] planningSalleParDate (int annee, int mois, int jour, String batiment, String salle) throws IOException;
	/*
	 * exemple : String[] data = fonctionsAde.planningSalleParDate (2013,01,22, "41","3");
	 * renvoie :
	 * 1615 MNGT9-TDRHM1 41-003
	 * 1015 LCOM-S8CM/CC 41-003
     * 0800 VO-CM/CC 41-003
     * 1400 RC-S9CM/CC 41-003	 * 
	 */
	public String coursActuelParEtudiant(String etudiant) throws IOException;
	/*
	 * prend en compte l'heure actuelle
	 * exemple : fonctionsAde.coursActuelParEtudiant ("5881"))
	 * renvoie : 
	 * -le nom du cours et la salle : NSOCTP5-Salle925VerteBDomo
	 * - ou null si l'étudiant n'a pas cours
	 */
	public String[] planningEtudiantParDate(int annee, int mois, int jour, String etudiant) throws IOException;
	
	public boolean autorisation (String nom) throws IOException;
}
