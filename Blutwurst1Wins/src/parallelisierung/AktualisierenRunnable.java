package parallelisierung;

import datenhaltung.DBObject;

public class AktualisierenRunnable implements Runnable{
	private DBObject objekt;
	
	public AktualisierenRunnable(DBObject objekt){
		this.objekt = objekt;
	}
	
	public void run(){
		objekt.aktualisieren();
	}
}
