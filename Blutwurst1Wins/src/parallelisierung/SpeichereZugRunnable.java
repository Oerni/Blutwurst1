package parallelisierung;

import datenhaltung.Zug;

public class SpeichereZugRunnable implements Runnable {
	private Zug zug;

	public SpeichereZugRunnable(Zug zug){
		this.zug = zug;
	}
	
	@Override
	public void run() {
		zug.speichern();
	}
}