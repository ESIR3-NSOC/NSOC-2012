import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class donneesAde {
    public static String ressource;//voir ent : ESIR1 997 / ESIR2 111 / ESIR3 982
    public static String dateDebut;
    public static String dateFin;
    public static String projectId;

    public static void main(String[] args) throws IOException {
        init("982", "2012-12-18", "2012-12-18");
        System.out.println(recupererAde());
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
        return ade;
    }

    static void init(String ressourceInit, String dateDebutInit, String dateFinInit) {
        ressource = ressourceInit;//voir ent : ESIR1 997 / ESIR2 111 / ESIR3 982
        dateDebut = dateDebutInit;
        dateFin = dateFinInit;
        projectId = "31";
    }

    String getCours(String personne, String heure, String date) {
        String infos = null;

        return infos;
    }

    String getSalle(String salle, String heure, String date) {
        String infos = null;

        return infos;
    }
}
