package spieldaten;

import java.sql.SQLException;

public abstract class DBObject {
	/**
	 * Abstrakte Klasse zur Gewährleistung der Implementierung zweier essentieller
	 * Methoden: Speichern und das Laden der ID aus der Datenbank. Diese kann nicht
	 * bei Instanziieren eines Objekts übergeben werden, da sie auf Datenbankebene 
	 * automatisch generiert wird
	 */
	protected int id = -1;
//	Die ID des gespeicherten Objekts wird automatisch zurückgegeben
	public abstract void speichern() throws SQLException;
	public abstract void aktualisieren();
	public int getID(){
		return id;
	}
}
