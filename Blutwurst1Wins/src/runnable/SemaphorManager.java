package runnable;

public class SemaphorManager {
//	Lösung des Leser-Schreiber-Problems ohne Priorität
	
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
		while(aktiveSchreiber || aktiveLeser > 0)
			try{
				wait();
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		aktiveSchreiber = true;
	}
	
	public synchronized void schreibzugriffAbmelden(){
		aktiveSchreiber = false;
		notifyAll();
	}
}