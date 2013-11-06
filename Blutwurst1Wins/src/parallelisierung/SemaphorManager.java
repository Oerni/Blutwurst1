package parallelisierung;

public class SemaphorManager {
//	L�sung des Leser-Schreiber-Problems ohne Priorit�t
	
	/**
	 * N�tig f�r zu speichernde Z�ge, da es hier vorkommen kann, dass in sehr kurzen Abst�nden Z�ge gespeichert werden.
	 * Beim Insert wird die letzte ID eines Zuges selektiert und in der Instanz gespeichert. Wird in der Zwischenzeit
	 * ein weiterer Zug eingef�gt, k�nnte es ohne Verwendung des Semaphors passieren, dass zwei Objekte derselben ID 
	 * entstehen
	 */
	
	private int aktiveLeser = 0;
	private boolean aktiveSchreiber = false;
	private static SemaphorManager instance;
	
	private SemaphorManager(){}
	
	public static SemaphorManager getInstance(){
		if(instance == null)
			instance = new SemaphorManager();
		return instance;
	}
	
	public synchronized void lesezugriffAnmelden(){
		while(aktiveSchreiber)
			try{
				wait();
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		aktiveLeser++;
	}
	
	public synchronized void lesezugriffAbmelden(){
		aktiveLeser--;
		notifyAll();
	}
	
	public synchronized void schreibzugriffAnmelden(){
//		System.out.println("SemaphorManager: Schreibzugriff angemeldet");
		while(aktiveSchreiber || aktiveLeser > 0)
			try{
				wait();
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		aktiveSchreiber = true;
	}
	
	public synchronized void schreibzugriffAbmelden(){
//		System.out.println("SemaphorManager: Schreibzugriff abgemeldet");
		aktiveSchreiber = false;
		notifyAll();
	}
}