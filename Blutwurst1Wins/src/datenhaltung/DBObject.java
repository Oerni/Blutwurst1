package datenhaltung;

public abstract class DBObject {
	/**
	 * Abstrakte Klasse zur Gewährleistung der Implementierung zweier essentieller
	 * Methoden: Speichern und das Laden der ID aus der Datenbank. Diese kann nicht
	 * bei Instanziieren eines Objekts übergeben werden, da sie auf Datenbankebene 
	 * automatisch generiert wird
	 */
	
//	Die ID des gespeicherten Objekts wird automatisch zurückgegeben
	public abstract int speichern();
//	aufgrund der Statistik-Funktion
	public abstract int ladeIDausDB();
}
