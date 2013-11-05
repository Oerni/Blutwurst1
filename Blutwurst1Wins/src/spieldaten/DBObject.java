package spieldaten;

import java.sql.SQLException;

public abstract class DBObject {
	/**
	 * Abstrakte Klasse zur Gew�hrleistung der Implementierung zweier essentieller
	 * Methoden: Speichern und das Laden der ID aus der Datenbank. Diese kann nicht
	 * bei Instanziieren eines Objekts �bergeben werden, da sie auf Datenbankebene 
	 * automatisch generiert wird
	 */
	protected int id = -1;
//	Die ID des gespeicherten Objekts wird automatisch zur�ckgegeben
	public abstract void speichern() throws SQLException;
	public abstract void aktualisieren();
	public int getID(){
		return id;
	}
}
