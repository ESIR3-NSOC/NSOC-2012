package nsoc.nfc.translator;

import java.sql.*;
import connexion.base.donnees.Connexion;

public class NfcTranslator {

	Connexion c = new Connexion();

	/**
	 * rend le numero d'etudiant associe au tag numeroTagNFC
	 * @param numeroTagNFC
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public int numeroEtudiant (int numeroTagNFC) throws SQLException, ClassNotFoundException {
		// Connexion a la base de donnees et creation d'un objet Statement
		Statement state = c.execute().createStatement();
		// L'objet ResultSet contient le resultat de la requete SQL
		ResultSet result = state.executeQuery("SELECT numeroEtudiant FROM listeEtudiants WHERE numeroTagNFC = " + numeroTagNFC);
		
		result.next();
		return result.getInt(1);
	}

	public static void main (String [] args) {
		NfcTranslator nfcT = new NfcTranslator();
		int numNFC = 15;
		
		try {
			System.out.println(nfcT.numeroEtudiant(numNFC));;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
