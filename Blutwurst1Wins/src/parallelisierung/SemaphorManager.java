package parallelisierung;

public class SemaphorManager {
//	Lösung des Leser-Schreiber-Problems ohne Priorität
	
	/**
	 * Nötig für zu speichernde Züge, da es hier vorkommen kann, dass in sehr kurzen Abständen Züge gespeichert werden.
	 * Beim Insert wird die letzte ID eines Zuges selektiert und in der Instanz gespeichert. Wird in der Zwischenzeit
	 * ein weiterer Zug eingefügt, könnte es ohne Verwendung des Semaphors passieren, dass zwei Objekte derselben ID 
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