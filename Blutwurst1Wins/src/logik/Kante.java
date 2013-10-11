package logik;

public class Kante {
	private Knoten nachbarKnoten;
	private int richtung;
	public static final int HORIZONTAL_LINKS = 0;
	public static final int DIAGONAL_LINKS_OBEN = 1;
	public static final int VERTIKAL_OBEN = 2;
	public static final int DIAGONAL_RECHTS_OBEN = 3;
	public static final int HORIZONTAL_RECHTS = 4;	
	public static final int DIAGONAL_RECHTS_UNTEN = 5;
	public static final int VERTIKAL_UNTEN = 6;
	public static final int DIAGONAL_UNTEN_LINKS = 7;
	
	public Kante(Knoten nachbarKnoten,int richtung){
		this.nachbarKnoten = nachbarKnoten;
		this.richtung = richtung;
	}
	
	public Knoten getNachbarn(){
		return nachbarKnoten;
	}
	public int getRichtung(){
		return richtung;
	}
}
