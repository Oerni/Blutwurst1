package model.spiel;

public abstract class DBObject {
	protected int id;
	public abstract void speichern();
//	aufgrund der Statistik-Funktion
	public abstract int ladeIDausDB();
}
