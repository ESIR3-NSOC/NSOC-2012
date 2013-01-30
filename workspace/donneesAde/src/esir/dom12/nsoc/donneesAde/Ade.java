package esir.dom12.nsoc.donneesAde;

public interface Ade {
	String[] planningSalleParDate (int annee, int mois, int jour, String batiment, String salle);
	/*
	 * exemple : String[] data = fonctionsAde.planningSalleParDate (2013,01,22, "41","3");
	 * renvoie :
	 * 1615 MNGT9-TDRHM1 41-003
	 * 1015 LCOM-S8CM/CC 41-003
     * 0800 VO-CM/CC 41-003
     * 1400 RC-S9CM/CC 41-003	 * 
	 * 
	 */
	String coursActuelParEtudiant(String etudiant);
	/*
	 * prend en compte l'heure actuelle
	 * exemple : fonctionsAde.coursActuelParEtudiant ("5881"))
	 * renvoie : 
	 * -le nom du cours et la salle : NSOCTP5-Salle925VerteBDomo
	 * - ou null si l'étudiant n'a pas cours
	 */
}
