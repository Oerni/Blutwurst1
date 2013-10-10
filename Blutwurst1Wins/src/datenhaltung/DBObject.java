package datenhaltung;

public abstract class DBObject {
	/**
	 * Abstrakte Klasse zur Gewährleistung der Implementierung zweier essentieller
	 * Methoden: Speichern und das Laden der ID aus der Datenbank. Diese kann nicht
	 * bei Instanziieren eines Objekts übergeben werden, da sie auf Datenbankebene 
	 * automatisch generiert wird
	 */
	public abstract void speichern();
//	aufgrund der Statistik-Funktion
	public abstract int ladeIDausDB();
}
