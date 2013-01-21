package esir.dom12.nsoc.donneesAde;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class donneesAde {
	//index :
	static int dtstamp = 0, dtstart = 1, dtend = 2, summary = 3, location = 4, description = 5;
	

    public static String ressource;//voir ent : ESIR1 997 / ESIR2 111 / ESIR3 982
    public static String dateDebut;//forme : AAAA-MM-JJ
    public static String dateFin;//forme : AAAA-MM-JJ
    public static int annee;//AAAA
    public static int mois;//MM
    public static int jour;//JJ
    public static String projectId;//défault = 31
    public static String batiment;//41 ou 42
    public static String salle;//1,2,3,4,101,102,103,104,L,M,N

    public static void main(String[] args) throws IOException {
    	//init ("982",2012,12,18,"41","1");//ressource annee mois jour batiment salle
        String[] data = fonctionsAde.planningSalleParDate (2012,12,18, "41","1");
        for (int i=0; i<9; i++)
        	System.out.println("n°"+i+" : "+data[i]);
        
    }
    
    //attribue la ressource voulue et la plage de temps concernée
    static void init(String ressourceInit, int anneeInit, int moisInit, int jourInit, String batimentInit, String salleInit) {
        ressource = ressourceInit;//voir ent : ESIR1 997 / ESIR2 111 / ESIR3 982
        //dateDebut = anneeInit+"-"+moisInit+"-"+jourInit;
        //dateFin = anneeInit+"-"+moisInit+"-"+jourInit;
        projectId = "31";
        batiment=batimentInit;
        salle=salleInit;
        annee=anneeInit;
        mois=moisInit;
        jour=jourInit;
    }

    static String recupererAde() throws IOException {
        String ade = null;
        URL url = new URL("http://plannings.univ-rennes1.fr/ade/custom/modules/plannings/direct_cal.jsp?calType=ical&login=cal&password=visu&resources=" + ressource + "&firstDate=" + dateDebut + "&lastDate=" + dateFin + "&projectId=" + projectId);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            ade = ade + "\n" + inputLine;
        in.close();
        System.out.println(url.toString());
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
	
	//date +- 1
	//batiment={41,42} salle={L,M,N,1,2,3,4,101,102,103,104}
	static String[] getPlanningSalle() throws IOException{
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


    String getCours(String personne, String heure) {
        String infos = null;

        return infos;
    }

    String getSalle(String salle, String heure) {
        String infos = null;

        return infos;
    }
}
