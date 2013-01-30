package esir.dom12.nsoc.regulation;

import java.util.ArrayList;




/**
 * Created by Eclipse.
 * User: LEDRU Vincent
 * Date: 09/01/13
 */
public class RegulationThread extends Thread {

	/**
	 * VARIABLES
	 */
	private ArrayList<RegulationListener> listeners = new ArrayList<RegulationListener>();

	private boolean stopped = false;
	private long delay = 2000;
    private int time = 0;



	/**
	 * CONSTRUCTEUR VIDE
	 */
	public RegulationThread() {}


	public RegulationThread(long delay) {
		this.delay = delay;
	}


	public void halt() {
		stopped = true;
	}


	public boolean isStopped(){
		return stopped;
	}


	public void run() {
		while(!stopped) {
			lightThreadRegulation();
            time ++;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	
	/**
	 * Parse la valeur luxLevel qui est un String en Int
	 * @param luxLevel
	 * @return
	 */
	 public static int parseLuxLevel(String luxLevel) {
		 int luxLevelINT = Integer.parseInt(luxLevel);
		 return luxLevelINT;
	 }

	 
	 
	 /**
	  * LIGHT REGULATION
	  */
	 private void lightThreadRegulation() {
		 for(final RegulationListener listener : listeners) {
			 new Thread(new Runnable(){
				 public void run() {listener.helloProduced("Somethings message for the regulation ?");}
			 }).start();
		 }
	 }
}