import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;


public class donneesAde {
	static int dtstamp = 0, dtstart = 1, dtend = 2, summary = 3, location = 4, description = 5;
	
	public static String ressource;//voir ent : ESIR1 997 / ESIR2 111 / ESIR3 982 plusieurs ressources peuvent être sélectionnées, séparées par des virgules
	public static String dateDebut;
	public static String dateFin;
	public static String projectId;
	
	public static void main (String[] args) throws IOException{
		init ("5858","2012-12-18","2012-12-18");
		String[] chaine = getPlanningSalle("41", "102", 2012,12,18);
		for (int i=0; i<10;i++){
			System.out.println("cours "+i+" : "+chaine[i]);
		}
	}
	
	static void init (String ressourceInit, String dateDebutInit, String dateFinInit){
		ressource=ressourceInit;//voir ent : ESIR1 997 / ESIR2 111 / ESIR3 982
		dateDebut=dateDebutInit;
		dateFin=dateFinInit;
		projectId="31";
	}
	
	static String recupererAde () throws IOException{
		String ade = null;
		URL url = new URL("http://plannings.univ-rennes1.fr/ade/custom/modules/plannings/direct_cal.jsp?calType=ical&login=cal&password=visu&resources="+ressource+"&firstDate="+dateDebut+"&lastDate="+dateFin+"&showTree=non&projectId="+projectId);
		URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
        	ade=ade+"\n"+inputLine;
        in.close();
		return ade;		
	}
	
	static String[] parseAde (String Ade){//découpes Ade en tranches horaires (la tranche d'indice 0 est invalide)
		StringTokenizer filtreLignes = new StringTokenizer(Ade);
		String lignes = null;
		String[] plagesHoraires = new String [10];
		int i=0;
		while (filtreLignes.hasMoreTokens()){
			lignes=filtreLignes.nextToken();
			plagesHoraires[i]+=" "+lignes;
			if (lignes.compareTo("BEGIN:VEVENT")==0){//une nouvelle plage horaire arrive
				i++;
			}
		}
		return plagesHoraires;
	}
	
	static String[] parsePlageHoraire(String plageHoraire){//heure+1
		StringTokenizer filtreElements = new StringTokenizer(plageHoraire);
		String[] elementsPlageHoraire = new String [8];
		String element;
		int i=0;
		while (filtreElements.hasMoreTokens()){
			element=filtreElements.nextToken();
			if (element.startsWith("DTSTAMP")){
				elementsPlageHoraire[0]=element;}
			else if (element.startsWith("DTSTART")){
				elementsPlageHoraire[1]=element;i=1;}
			else if (element.startsWith("DTEND")){
				elementsPlageHoraire[2]=element;i=2;}
			else if (element.startsWith("SUMMARY")){
				elementsPlageHoraire[3]=element;i=3;}
			else if (element.startsWith("LOCATION")){
				elementsPlageHoraire[4]=element;i=4;}
			else if (element.startsWith("DESCRIPTION")){
				elementsPlageHoraire[5]=element;i=5;}
			else if (element.startsWith("UID")){
				elementsPlageHoraire[6]=element;i=6;}
			else
				elementsPlageHoraire[i]+=element;
		}
		for (i=0; i<8; i++)
			System.out.println(elementsPlageHoraire[i]);
		return elementsPlageHoraire;
	}
	
	static int recupHeureCours(String[] elementsPlageHoraire){
		String element = elementsPlageHoraire[dtstart];
		element = element.substring(17, 21);
		return Integer.parseInt(element)+100;
	}
	
	static String recupNomCours (String[] elementsPlageHoraire){
		return elementsPlageHoraire[summary].substring(8);
	}
	

	String getCours(String personne, String heure, String date){
		String infos=null;
		
		return infos;
	}
	
	//date +- 1
	//batiment={41,42} salle={L,M,N,1,2,3,4,101,102,103,104}
	static String[] getPlanningSalle(String batiment, String salle,  int annee, int mois, int jour) throws IOException{
		//renvoie les heures 
		String[] infos=new String[15];
		String[] elements=new String[10];
		jour = jour +1;//correction ade
		String date = annee+"-"+mois+"-"+jour;
		dateDebut=date;
		dateFin=date;
		if (batiment=="41"){
			switch (salle)
			{
			case "1": ressource="349"; break; 
			case "2": ressource="359"; break; 
			case "3": ressource="467"; break; 
			case "4": ressource="524"; break; 
			case "101": ressource="602"; break; 
			case "102": ressource="694"; break;
			case "103": ressource="989"; break; 
			case "104": ressource="1005"; break; 
			default : ressource=""; break;
			}
		}
		else if (batiment=="42"){
			switch (salle)
			{
			case "L":ressource="429";break;
			case "M":ressource="430";break;
			case "N":ressource="431";break;
			}
		}
		infos[0] = recupererAde();//récupération d'ade
		infos = parseAde(infos[0]);//parse ade par cours
		infos[0]=null;
		int i=1;
		while (i<10){//récupère les informations voulues
			if (infos[i]!=null){
				elements = parsePlageHoraire(infos[i]);
				if (recupHeureCours(elements)<1000){
					infos[i-1]="0"+recupHeureCours(elements)+" "+recupNomCours(elements);
				}
				else
					infos[i-1]=recupHeureCours(elements)+" "+recupNomCours(elements);
				infos[i]=null;
			}
			i++;
		}
		return infos;
	}
}
