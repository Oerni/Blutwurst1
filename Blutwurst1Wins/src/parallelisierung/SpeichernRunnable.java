package parallelisierung;

import datenhaltung.DBObject;

public class SpeichernRunnable implements Runnable{
	private DBObject objekt;
	
	public SpeichernRunnable(DBObject objekt){
		this.objekt = objekt;
	}
	@Override
	public void run() {
		objekt.speichern();
	}

}
