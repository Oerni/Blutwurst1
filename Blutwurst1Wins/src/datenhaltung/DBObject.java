package datenhaltung;

public abstract class DBObject {
	/**
	 * Abstrakte Klasse zur Gew�hrleistung der Implementierung zweier essentieller
	 * Methoden: Speichern und das Laden der ID aus der Datenbank. Diese kann nicht
	 * bei Instanziieren eines Objekts �bergeben werden, da sie auf Datenbankebene 
	 * automatisch generiert wird
	 */
	public abstract void speichern();
//	aufgrund der Statistik-Funktion
	public abstract int ladeIDausDB();
}