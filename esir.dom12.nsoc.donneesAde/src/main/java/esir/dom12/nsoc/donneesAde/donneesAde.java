package esir.dom12.nsoc.donneesAde;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

//    public static void main(String[] args) throws IOException {
//    	fonctionsAde fa = new fonctionsAde();
//    	//init ("982",2012,12,18,"41","1");//ressource annee mois jour batiment salle
//       // String[] data = fonctionsAde.planningSalleParDate (2012,12,18, "41","1");
//    	//String[] data = fonctionsAde.planningSalleParDate (2013,01,22, "41","3");
//        /*for (int i=0; i<9; i++)
//        	System.out.println("n°"+i+" : "+data[i]);
//        System.out.println(fonctionsAde.coursActuelParEtudiant ("5881"));*/
//    	//recupererNoms("ESIR 1","Dom");
//    	System.out.println(recupererIdEtudiant("Thebault Antoine"));
//    }
    
    //attribue la ressource voulue et la plage de temps concernée
    public void init(String ressourceInit, int anneeInit, int moisInit, int jourInit, String batimentInit, String salleInit) {
        ressource = ressourceInit;//voir ent : ESIR1 997 / ESIR2 111 / ESIR3 982
        //dateDebut = anneeInit+"-"+moisInit+"-"+jourInit;
        //dateFin = anneeInit+"-"+moisInit+"-"+jourInit;
        String date = null;
        projectId = "31";//2012-2013 31/67
        batiment=batimentInit;
        salle=salleInit;
        annee=anneeInit;
        mois=moisInit;
        jour=jourInit+1;
        if (moisInit<10 && jourInit<10)
        	date=anneeInit+"-0"+moisInit+"-0"+jourInit;
        else if (moisInit<10 && jourInit>10)
        	date=anneeInit+"-0"+moisInit+"-"+jourInit;
        else if (moisInit>10 && jourInit<10)
        	date=anneeInit+"-"+moisInit+"-0"+jourInit;
        else
        	date=anneeInit+"-"+moisInit+"-"+jourInit;
        dateDebut = date;
        dateFin = date;
     }

    public String recupererAde() throws IOException {
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
	
	public String[] parseAde (String Ade){//découpes Ade en tranches horaires (la tranche d'indice 0 est invalide)
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
	
	public String[] parsePlageHoraire(String plageHoraire){//heure+1
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
	
	public int recupHeureCours(String[] elementsPlageHoraire){
		String element = elementsPlageHoraire[dtstart];
		element = element.substring(17, 21);
		return Integer.parseInt(element)+100;
	}
	
	public int recupFinHeureCours(String[] elementsPlageHoraire){
		String element = elementsPlageHoraire[dtend];
		element=element.substring(15,19);
		return Integer.parseInt(element)+100;
	}
	
	public String recupNomCours (String[] elementsPlageHoraire){
		return elementsPlageHoraire[summary].substring(8);
	}
	
	public String recupLocation(String[] elementsPlageHoraire){
		return elementsPlageHoraire[location].substring(10);
	}
	
	//date +- 1
	//batiment={41,42} salle={L,M,N,1,2,3,4,101,102,103,104}
	public String[] getPlanningSalle() throws IOException{
		//renvoie les heures 
		String[] infos=new String[15];
		String[] elements=new String[10];
		if (batiment=="41"){
			if (salle=="1")
				ressource="349";
			else if (salle=="2")
				ressource="359";
			else if (salle=="3")
				ressource="467";
			else if (salle=="4")
				ressource="524";
			else if (salle=="101")
				ressource="602";
			else if (salle=="102")
				ressource="694";
			else if (salle=="103")
				ressource="989";
			else if (salle=="104")
				ressource="1005";
			else
				ressource="";
		}
		else if (batiment=="42"){
			if (salle=="L")
				ressource="429";
			else if (salle=="M")
				ressource="430";
			else if (salle=="N")
				ressource="431";
		}
		infos[0] = recupererAde();//récupération d'ade
		infos = parseAde(infos[0]);//parse ade par cours
		infos[0]=null;
		int i=1;
		while (i<10){//récupère les informations voulues
			if (infos[i]!=null){
				elements = parsePlageHoraire(infos[i]);
				if (recupHeureCours(elements)<1000){
					infos[i-1]="0"+recupHeureCours(elements)+" "+recupNomCours(elements)+" "+recupLocation(elements);
				}
				else
					infos[i-1]=recupHeureCours(elements)+" "+recupNomCours(elements)+" "+recupLocation(elements);
				infos[i]=null;
			}
			i++;
		}
		return infos;
	}

	
	public String getPlanningEtudiant(String nom, int temps) throws IOException{
		//renvoie les heures 
		String[] infos=new String[15];
		String[] elements=new String[10];
		ressource = nom;
		
		infos[0] = recupererAde();//récupération d'ade
		infos = parseAde(infos[0]);//parse ade par cours
		infos[0]=null;
		int i=1;
		while (i<10){//récupère les informations voulues
			if (infos[i]!=null){
				elements = parsePlageHoraire(infos[i]);
				if (temps>recupHeureCours(elements)&& temps<recupFinHeureCours(elements)){
						infos[0]=recupNomCours(elements)+recupLocation(elements);
				}
			}
			i++;
		}
		return infos[0];
	}
    
    public String[] recupererNoms (String promo, String option) throws IOException{
    	//Option : {Info, R & T, Biom, Dom, Mat}
    	//Promo : {ESIR 1, ESIR 2, ESIR 3}
    	//
    	//
    	InputStream ips=new FileInputStream("tree"); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String ligne;
		String []resultat = new String[100];
		Boolean promoOk=false;
		Boolean optionOk=false;
		Boolean ok =false;
		int i=0;
		
		while ((ligne=br.readLine())!=null && !ok){
			if (ligne.contains(promo)){
				promoOk=true;
			}
			if (promoOk && ligne.contains(option)){
				promoOk=false;
				optionOk=true;
			}
			if (optionOk && !ok){
				resultat[i]=ligne;
				i++;
				if (ligne.contains("]),"))
					ok=true;
			}
		}
		resultat[0]=null;
		resultat[i-1]=null;
		for (i=1; resultat[i]!=null; i++){
			resultat[i]=resultat[i].substring(15);
			System.out.println(resultat[i]);
		}
		br.close(); 
    	return resultat;
    }
    public String recupererIdEtudiant (String nomEtudiant) throws IOException{
    	//(Nom Prenom)
		String idEtudiant=null;
		boolean ok=false;
		
		InputStream ips=new FileInputStream("tree"); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String ligne;
		
		while ((ligne=br.readLine())!=null && !ok){
			if (ligne.contains(nomEtudiant)){
				idEtudiant=ligne.substring(15,19);
				ok=true;
			}
		}
		
    	return idEtudiant;    	
    }
}
