package esir.dom12.nsoc.donneesAde;

import java.io.IOException;

import esir.dom12.nsoc.donneesAde.Ade;
import esir.dom12.nsoc.donneesAde.fonctionsAde;

public class Test {

	public static void main(String[] args) throws IOException{
		Ade a = new fonctionsAde();
		String[] planning = a.planningSalleParDate(2013, 2,12, "41", "101");
		for (int i = 0; i < planning.length; i++) {
			String string = planning[i];
			System.out.println(string);
		}
	}
}

