package model.spiel;

public class Queries {
	public static final String SPIELDATEN = "SELECT sp.name,s.spielnr,s.punkteheim,s.punktegegner FROM spiel s JOIN spieler sp on s.gegner = sp.id";
	public static final String ANZAHL_SIEGE = "SELECT COUNT(spielnr) AS anzahlsiege FROM spiel WHERE punkteheim > punktegegner;";
	public static final String ANZAHL_NIEDERLAGEN = "SELECT COUNT(spielnr) AS anzahlniederlagen FROM spiel WHERE punkteheim < punktegegner;";
	public static final String INSERT = "INSERT INTO %s(%s) VALUES(%s);";
	public static final String LADE_ID = "SELECT id FROM %s WHERE %s = %s;";
	public static final String GEGNER_ID = "SELECT id FROM spieler WHERE name='%s'";
}
