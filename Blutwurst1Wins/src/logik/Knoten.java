package logik;

import java.util.Stack;

import datenhaltung.Spieler;

public class Knoten {
	private Spieler besetztVon;
	private int zeile;
	private int spalte;
	private Stack<Kante> nachbarn = new Stack<Kante>();
	
	public Knoten(int spalte,int zeile){
		this.zeile = zeile;
		this.spalte = spalte;
		besetztVon = null;
	}
	
	public Kante getNachbarKante(int i){
		return nachbarn.get(i);
	}
	public Knoten getNachbarKnoten(int i){
		return nachbarn.get(i).getNachbarn();
	}
	public Stack<Kante> getNachbarn(){
		return nachbarn;
	}
	public void nachbarnHinzufuegen(Knoten knoten,int richtung){
		nachbarn.add(new Kante(knoten,richtung));
	}
	
	public int getBewertung(Spieler selbst){
		int bewertung = 0;
		int horizontalLinks = zaehleFreieFelder(Kante.HORIZONTAL_LINKS,selbst);
		int horizontalRechts = zaehleFreieFelder(Kante.HORIZONTAL_RECHTS,selbst);
		int diagonalLinksOben = zaehleFreieFelder(Kante.DIAGONAL_LINKS_OBEN,selbst);
		int diagonalRechtsUnten = zaehleFreieFelder(Kante.DIAGONAL_RECHTS_UNTEN, selbst);
		int diagonalRechtsOben = zaehleFreieFelder(Kante.DIAGONAL_RECHTS_OBEN, selbst);
		int diagonalLinksUnten = zaehleFreieFelder(Kante.DIAGONAL_UNTEN_LINKS, selbst);
		int vertikalOben = zaehleFreieFelder(Kante.VERTIKAL_OBEN, selbst);
		int vertikalUnten = zaehleFreieFelder(Kante.VERTIKAL_UNTEN, selbst);
		
//		System.out.println("Knoten: ("+spalte+","+zeile+")");
//		System.out.println(Feld.getRichtung(Kante.HORIZONTAL_LINKS)+": "+horizontalLinks);
//		System.out.println(Feld.getRichtung(Kante.HORIZONTAL_RECHTS)+": "+horizontalRechts);
//		System.out.println(Feld.getRichtung(Kante.DIAGONAL_LINKS_OBEN)+": "+diagonalLinksOben);
//		System.out.println(Feld.getRichtung(Kante.DIAGONAL_RECHTS_UNTEN)+": "+diagonalRechtsUnten);
//		System.out.println(Feld.getRichtung(Kante.DIAGONAL_RECHTS_OBEN)+": "+diagonalRechtsOben);
//		System.out.println(Feld.getRichtung(Kante.DIAGONAL_UNTEN_LINKS)+": "+diagonalLinksUnten);
//		System.out.println(Feld.getRichtung(Kante.VERTIKAL_OBEN)+": "+vertikalOben);
//		System.out.println(Feld.getRichtung(Kante.VERTIKAL_UNTEN)+": "+vertikalUnten);
		
		int horizontalLinksWert = zaehleEigeneSteine(Kante.HORIZONTAL_LINKS,selbst,0);
		int horizontalRechtsWert = zaehleEigeneSteine(Kante.HORIZONTAL_RECHTS,selbst,0);
		int diagonalLinksObenWert = zaehleEigeneSteine(Kante.DIAGONAL_LINKS_OBEN,selbst,0);
		int diagonalRechtsUntenWert = zaehleEigeneSteine(Kante.DIAGONAL_RECHTS_UNTEN, selbst,0);
		int diagonalRechtsObenWert = zaehleEigeneSteine(Kante.DIAGONAL_RECHTS_OBEN, selbst,0);
		int diagonalLinksUntenWert = zaehleEigeneSteine(Kante.DIAGONAL_UNTEN_LINKS, selbst,0);
		int vertikalObenWert = zaehleEigeneSteine(Kante.VERTIKAL_OBEN, selbst,0);
		int vertikalUntenWert = zaehleEigeneSteine(Kante.VERTIKAL_UNTEN, selbst,0);
		
		bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalLinks);
		bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalRechts);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksOben);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsUnten);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsOben);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksUnten);
		bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalOben);
		bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalUnten);
		
		bewertung += erhoeheBewertungDurchEigeneSteine(horizontalLinks,horizontalLinksWert);
		bewertung += erhoeheBewertungDurchEigeneSteine(horizontalRechts,horizontalRechtsWert);
		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksOben,diagonalLinksObenWert);
		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsUnten,diagonalRechtsUntenWert);
		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsOben,diagonalRechtsObenWert);
		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksUnten,diagonalLinksUntenWert);
		bewertung += erhoeheBewertungDurchEigeneSteine(vertikalOben,vertikalObenWert);
		bewertung += erhoeheBewertungDurchEigeneSteine(vertikalUnten,vertikalUntenWert);
		
		if(horizontalLinks<=3&&horizontalRechts<=3)
			bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalLinks+horizontalRechts);
		if(diagonalLinksOben<=3&&diagonalRechtsUnten<=3)
			bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksOben+diagonalRechtsUnten);
		if(diagonalRechtsOben<=3&&diagonalLinksUnten<=3)
			bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsOben+diagonalLinksUnten);
		if(vertikalOben<=3&&vertikalUnten<=3)
			bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalOben+vertikalUnten);
		
		if(horizontalLinks<=3&&horizontalRechts<=3)
			bewertung += erhoeheBewertungDurchEigeneSteine(horizontalLinks+horizontalRechts,horizontalLinksWert+horizontalRechtsWert);
		if(diagonalLinksOben<=3&&diagonalRechtsUnten<=3)
			bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksOben+diagonalRechtsUnten,diagonalLinksObenWert+diagonalRechtsUntenWert);
		if(diagonalRechtsOben<=3&&diagonalLinksUnten<=3)
			bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsOben+diagonalLinksUnten,diagonalRechtsObenWert+diagonalLinksUntenWert);
		if(vertikalOben<=3&&vertikalUnten<=3)
			bewertung += erhoeheBewertungDurchEigeneSteine(vertikalOben+vertikalUnten,vertikalObenWert+vertikalUntenWert);
		
		return bewertung;
	}
	
	public int erhoeheBewertungDurchAnzahlFelder(int anzahlFelder){
		return anzahlFelder >= 3 ? 1 : 0;
	}
	public int erhoeheBewertungDurchEigeneSteine(int anzahlFelder,int wert){
		return anzahlFelder>= 3 ? wert : 0;
	}
	
	
	public int zaehleEigeneSteine(int richtung,Spieler selbst,int zaehler){
		int eigenerWert;
		if(besetztVon==selbst)
			eigenerWert=2;
		else
			eigenerWert=0;
		
		if(zaehler>2)
			return 0;
		
		for(Kante k : nachbarn){
			if(k.getRichtung() == richtung){
				if(k.getNachbarn().getBesetztVon() == selbst || k.getNachbarn().getBesetztVon() == null){
					return eigenerWert + k.getNachbarn().zaehleEigeneSteine(richtung, selbst, zaehler+1);
				}else
					return eigenerWert;
			}
//			keine Weitere Kante mehr
			return eigenerWert;
			}	
		
		return 0;
	}
	public int zaehleFreieFelder(int richtung,Spieler selbst){
		for(Kante k : nachbarn){
			if(k.getRichtung() == richtung){
				if(k.getNachbarn().getBesetztVon() == selbst || k.getNachbarn().getBesetztVon() == null){
					return 1 + k.getNachbarn().zaehleFreieFelder(richtung,selbst);
				}else
					return 1;
			}
		}	
		
		return 0;
	}
	
	public Spieler getBesetztVon(){
		return besetztVon;
	}
	public void besetzen(Spieler spieler) throws Exception{
		this.besetztVon = spieler;
	}
	public int getZeile(){
		return zeile;
	}
	public int getSpalte(){
		return spalte;
	}
}