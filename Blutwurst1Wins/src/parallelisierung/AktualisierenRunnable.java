package parallelisierung;

import spieldaten.DBObject;

public class AktualisierenRunnable implements Runnable{
	private DBObject objekt;
	
	public AktualisierenRunnable(DBObject objekt){
		this.objekt = objekt;
	}
	
	public void run(){
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		objekt.aktualisieren();
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
}
