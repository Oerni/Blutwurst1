package datenhaltung;

public class Strings {
	/**
	 * Zentrale Klasse zur Verwaltung von Strings wie Abfragen, Namen, ...
	 */
	public static final int STANDARD_ZUGZEIT_S = 1;
	public static final String SPIELDATEN = "SELECT sp.name,s.spielnr,s.punkteheim,s.punktegegner FROM spiel s JOIN spieler sp on s.gegner = sp.id";
	public static final String ANZAHL_SIEGE = "SELECT COUNT(spielnr) AS anzahlsiege FROM spiel WHERE punkteheim > punktegegner;";
	public static final String ANZAHL_NIEDERLAGEN = "SELECT COUNT(spielnr) AS anzahlniederlagen FROM spiel WHERE punkteheim < punktegegner;";
	public static final String INSERT = "INSERT INTO %s(%s) VALUES(%s);";
	public static final String LADE_ID = "SELECT id FROM %s WHERE %s = %s;";
	public static final String GEGNER_ID = "SELECT id FROM spieler WHERE name='%s';";
	public static final String SPIEL = "SELECT sp.name,s.punkteheim,s.punktegegner FROM spiel s JOIN spieler sp ON sp.id = s.gegner WHERE spielnr=%s;";
	public static final String SAETZE_EINES_SPIELS = "SELECT sp.id,s.satznr FROM satz s JOIN spieler sp ON s.beginner = sp.id WHERE spielnr = %s;";
	public static final String ZUEGE_EINES_SATZES = "SELECT zugnr,zeile,spalte,spieler FROM zug WHERE satznr = %s AND spielnr = %s;";
	public static final String SPIELER_ID = "SELECT id FROM spieler WHERE name='%s';";
	public static final String LETZTE_SATZ_NR = "SELECT MAX(id) AS id FROM satz WHERE spielnr = %s;";
	public static final String LETZTES_SPIEL_NR = "SELECT MAX(id) AS id FROM spiel WHERE gegner = %s;";
	public static final String LETZTER_ZUG_NR = "SELECT MAX(id) AS id FROM zug WHERE satznr = %s AND spielnr = %s;";
	public static final String ZUG_AKTUALISIEREN = "UPDATE zug SET satznr=%s,spielnr=%s,spalte=%s,zeile=%s,spieler='%s' WHERE id=%s;";
	public static final String SATZ_AKTUALISIEREN = "UPDATE satz SET spielnr=%s,beginner='%s',gewinner='%s' WHERE id=%s;";
	public static final String SPIEL_AKTUALISIEREN = "UPDATE spiel SET gegner='%s',punkteheim=%s,punktegegner=%s,gewinner='%s' WHERE id=%s;";
	public static final String SPIELER_AKTUALISIEREN = "UPDATE spieler SET name='%s' WHERE id=%s;";
	public static final String NAME = "blutwurst1";
}