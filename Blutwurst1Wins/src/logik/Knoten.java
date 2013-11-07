package logik;

import java.util.Stack;

import spieldaten.Spieler;

public class Knoten {
	private Spieler besetztVon;
	private int zeile;
	private int spalte;
//	Bewertung des eigenen Wurfs
	private int max;
//	Bewertung des gegnerischen Wurfs
	private int min;
	private Stack<Kante> nachbarn = new Stack<Kante>();
	private boolean spielGewonnen = false;
	public static final int GEWONNEN = Integer.MAX_VALUE;
	
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
	
	public void bewertungZuruecksetzen(){
		this.min = 0;
		this.max = 0;
	}
	
	public void minZuruecksetzen(){
		this.min = 0;
	}
	
	public void spielerEntfernen(){
		this.besetztVon = null;
	}
	
	public void maxZuruecksetzen(){
		this.max = 0;
	}
	
	public int getMin(){
		return min;
	}
	public int getMax(){
		return max;
	}
	
	public int getBewertungFinal(){
		return max - min;
	}
	
	public void bewerten(boolean eigenerWurf,Spieler spieler){
		if(eigenerWurf)
			max = this.getBewertung(spieler);
		else
			min = this.getBewertung(spieler);
	}
	
//	public void bewerten(Spieler spieler){
//		int horizontalLinks = zaehleFreieFelder(Kante.HORIZONTAL_LINKS,spieler);
////		System.out.println("Links: " + horizontalLinks);
//		int horizontalRechts = zaehleFreieFelder(Kante.HORIZONTAL_RECHTS,spieler);
////		System.out.println("Rechts: " + horizontalRechts);
//		int diagonalLinksOben = zaehleFreieFelder(Kante.DIAGONAL_LINKS_OBEN,spieler);
////		System.out.println("links oben: " + diagonalLinksOben);
//		int diagonalRechtsUnten = zaehleFreieFelder(Kante.DIAGONAL_RECHTS_UNTEN, spieler);
////		System.out.println("rechts unten: " + diagonalRechtsUnten);
//		int diagonalRechtsOben = zaehleFreieFelder(Kante.DIAGONAL_RECHTS_OBEN, spieler);
////		System.out.println("rechts oben: " + diagonalRechtsOben);
//		int diagonalLinksUnten = zaehleFreieFelder(Kante.DIAGONAL_UNTEN_LINKS, spieler);
////		System.out.println("links unten: " + diagonalLinksUnten);
//		int vertikalOben = zaehleFreieFelder(Kante.VERTIKAL_OBEN,spieler);
////		System.out.println("oben: " + vertikalOben);
//		int vertikalUnten = zaehleFreieFelder(Kante.VERTIKAL_UNTEN,spieler);
////		System.out.println("unten: " + vertikalUnten);
//		
//		bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalLinks);
//		bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalRechts);
//		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksOben);
//		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsUnten);
//		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsOben);
//		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksUnten);
//		bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalOben);
//		bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalUnten);			
//		
//		if(horizontalLinks >= 3){
//			int horizontalLinksWert = wertDurchEigeneSteine(Kante.HORIZONTAL_LINKS,spieler,1);
//			bewertung += horizontalLinksWert;
//		}
//		if(horizontalRechts >= 3){
//			int horizontalRechtsWert = wertDurchEigeneSteine(Kante.HORIZONTAL_RECHTS,spieler,1);	
//			bewertung += horizontalRechtsWert;
//		}
//		if(diagonalLinksOben >= 3){
//			int diagonalLinksObenWert = wertDurchEigeneSteine(Kante.DIAGONAL_LINKS_OBEN,spieler,1);
//			bewertung += diagonalLinksObenWert;
//		}
//		if(diagonalRechtsUnten >= 3){
//			int diagonalRechtsUntenWert = wertDurchEigeneSteine(Kante.DIAGONAL_RECHTS_UNTEN, spieler,1);
//			bewertung += diagonalRechtsUntenWert;
//		}
//		if(diagonalRechtsOben >= 3){
//			int diagonalRechtsObenWert = wertDurchEigeneSteine(Kante.DIAGONAL_RECHTS_OBEN, spieler,1);
//			bewertung += diagonalRechtsObenWert;
//		}
//		if(diagonalLinksUnten >= 3){
//			int diagonalLinksUntenWert = wertDurchEigeneSteine(Kante.DIAGONAL_UNTEN_LINKS, spieler,1);
//			bewertung += diagonalLinksUntenWert;
//		}
//		if(vertikalOben >= 3){
//			int vertikalObenWert = wertDurchEigeneSteine(Kante.VERTIKAL_OBEN, spieler,1);
//			bewertung += vertikalObenWert;
//		}
//		
//		if(vertikalUnten >= 3){
//			int vertikalUntenWert = wertDurchEigeneSteine(Kante.VERTIKAL_UNTEN, spieler,1);
//			bewertung += vertikalUntenWert;
//		}
//		
////		bewertung += erhoeheBewertungDurchEigeneSteine(horizontalLinks,horizontalLinksWert);
////		bewertung += erhoeheBewertungDurchEigeneSteine(horizontalRechts,horizontalRechtsWert);
////		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksOben,diagonalLinksObenWert);
////		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsUnten,diagonalRechtsUntenWert);
////		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsOben,diagonalRechtsObenWert);
////		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksUnten,diagonalLinksUntenWert);
////		bewertung += erhoeheBewertungDurchEigeneSteine(vertikalOben,vertikalObenWert);
////		bewertung += erhoeheBewertungDurchEigeneSteine(vertikalUnten,vertikalUntenWert);
//		
////		if(horizontalLinks<=3&&horizontalRechts<=3)
////			bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalLinks+horizontalRechts);
////		if(diagonalLinksOben<=3&&diagonalRechtsUnten<=3)
////			bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksOben+diagonalRechtsUnten);
////		if(diagonalRechtsOben<=3&&diagonalLinksUnten<=3)
////			bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsOben+diagonalLinksUnten);
////		if(vertikalOben<=3&&vertikalUnten<=3)
////			bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalOben+vertikalUnten);
////		
////		if(horizontalLinks<=3&&horizontalRechts<=3)
////			bewertung += erhoeheBewertungDurchEigeneSteine(horizontalLinks+horizontalRechts,horizontalLinksWert+horizontalRechtsWert);
////		if(diagonalLinksOben<=3&&diagonalRechtsUnten<=3)
////			bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksOben+diagonalRechtsUnten,diagonalLinksObenWert+diagonalRechtsUntenWert);
////		if(diagonalRechtsOben<=3&&diagonalLinksUnten<=3)
////			bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsOben+diagonalLinksUnten,diagonalRechtsObenWert+diagonalLinksUntenWert);
////		if(vertikalOben<=3&&vertikalUnten<=3)
////			bewertung += erhoeheBewertungDurchEigeneSteine(vertikalOben+vertikalUnten,vertikalObenWert+vertikalUntenWert);
//	}
	
	public int getBewertung(Spieler spieler){
		int bewertung = 0;
		
		int horizontalLinks = zaehleFreieFelder(Kante.HORIZONTAL_LINKS,spieler);
//		System.out.println("Links: " + horizontalLinks);
		int horizontalRechts = zaehleFreieFelder(Kante.HORIZONTAL_RECHTS,spieler);
//		System.out.println("Rechts: " + horizontalRechts);
		int diagonalLinksOben = zaehleFreieFelder(Kante.DIAGONAL_LINKS_OBEN,spieler);
//		System.out.println("links oben: " + diagonalLinksOben);
		int diagonalRechtsUnten = zaehleFreieFelder(Kante.DIAGONAL_RECHTS_UNTEN, spieler);
//		System.out.println("rechts unten: " + diagonalRechtsUnten);
		int diagonalRechtsOben = zaehleFreieFelder(Kante.DIAGONAL_RECHTS_OBEN, spieler);
//		System.out.println("rechts oben: " + diagonalRechtsOben);
		int diagonalLinksUnten = zaehleFreieFelder(Kante.DIAGONAL_UNTEN_LINKS, spieler);
//		System.out.println("links unten: " + diagonalLinksUnten);
		int vertikalOben = zaehleFreieFelder(Kante.VERTIKAL_OBEN,spieler);
//		System.out.println("oben: " + vertikalOben);
		int vertikalUnten = zaehleFreieFelder(Kante.VERTIKAL_UNTEN,spieler);
//		System.out.println("unten: " + vertikalUnten);
		
		bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalLinks);
		bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalRechts);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksOben);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsUnten);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsOben);
		bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksUnten);
		bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalOben);
		bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalUnten);			
		
		if(horizontalLinks >= 3){
			int horizontalLinksWert = wertDurchEigeneSteine(Kante.HORIZONTAL_LINKS,spieler,1);
			bewertung += horizontalLinksWert;
		}
		if(horizontalRechts >= 3){
			int horizontalRechtsWert = wertDurchEigeneSteine(Kante.HORIZONTAL_RECHTS,spieler,1);	
			bewertung += horizontalRechtsWert;
		}
		if(diagonalLinksOben >= 3){
			int diagonalLinksObenWert = wertDurchEigeneSteine(Kante.DIAGONAL_LINKS_OBEN,spieler,1);
			bewertung += diagonalLinksObenWert;
		}
		if(diagonalRechtsUnten >= 3){
			int diagonalRechtsUntenWert = wertDurchEigeneSteine(Kante.DIAGONAL_RECHTS_UNTEN, spieler,1);
			bewertung += diagonalRechtsUntenWert;
		}
		if(diagonalRechtsOben >= 3){
			int diagonalRechtsObenWert = wertDurchEigeneSteine(Kante.DIAGONAL_RECHTS_OBEN, spieler,1);
			bewertung += diagonalRechtsObenWert;
		}
		if(diagonalLinksUnten >= 3){
			int diagonalLinksUntenWert = wertDurchEigeneSteine(Kante.DIAGONAL_UNTEN_LINKS, spieler,1);
			bewertung += diagonalLinksUntenWert;
		}
		if(vertikalOben >= 3){
			int vertikalObenWert = wertDurchEigeneSteine(Kante.VERTIKAL_OBEN, spieler,1);
			bewertung += vertikalObenWert;
		}
		
		if(vertikalUnten >= 3){
			int vertikalUntenWert = wertDurchEigeneSteine(Kante.VERTIKAL_UNTEN, spieler,1);
			bewertung += vertikalUntenWert;
		}
		
//		bewertung += erhoeheBewertungDurchEigeneSteine(horizontalLinks,horizontalLinksWert);
//		bewertung += erhoeheBewertungDurchEigeneSteine(horizontalRechts,horizontalRechtsWert);
//		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksOben,diagonalLinksObenWert);
//		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsUnten,diagonalRechtsUntenWert);
//		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsOben,diagonalRechtsObenWert);
//		bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksUnten,diagonalLinksUntenWert);
//		bewertung += erhoeheBewertungDurchEigeneSteine(vertikalOben,vertikalObenWert);
//		bewertung += erhoeheBewertungDurchEigeneSteine(vertikalUnten,vertikalUntenWert);
		
//		if(horizontalLinks<=3&&horizontalRechts<=3)
//			bewertung += erhoeheBewertungDurchAnzahlFelder(horizontalLinks+horizontalRechts);
//		if(diagonalLinksOben<=3&&diagonalRechtsUnten<=3)
//			bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalLinksOben+diagonalRechtsUnten);
//		if(diagonalRechtsOben<=3&&diagonalLinksUnten<=3)
//			bewertung += erhoeheBewertungDurchAnzahlFelder(diagonalRechtsOben+diagonalLinksUnten);
//		if(vertikalOben<=3&&vertikalUnten<=3)
//			bewertung += erhoeheBewertungDurchAnzahlFelder(vertikalOben+vertikalUnten);
//		
//		if(horizontalLinks<=3&&horizontalRechts<=3)
//			bewertung += erhoeheBewertungDurchEigeneSteine(horizontalLinks+horizontalRechts,horizontalLinksWert+horizontalRechtsWert);
//		if(diagonalLinksOben<=3&&diagonalRechtsUnten<=3)
//			bewertung += erhoeheBewertungDurchEigeneSteine(diagonalLinksOben+diagonalRechtsUnten,diagonalLinksObenWert+diagonalRechtsUntenWert);
//		if(diagonalRechtsOben<=3&&diagonalLinksUnten<=3)
//			bewertung += erhoeheBewertungDurchEigeneSteine(diagonalRechtsOben+diagonalLinksUnten,diagonalRechtsObenWert+diagonalLinksUntenWert);
//		if(vertikalOben<=3&&vertikalUnten<=3)
//			bewertung += erhoeheBewertungDurchEigeneSteine(vertikalOben+vertikalUnten,vertikalObenWert+vertikalUntenWert);
//		
		if(this.spielGewonnen)
			return GEWONNEN;
		return bewertung;
	}
	
	public boolean spielGewonnen(){
		return spielGewonnen;
	}
	
	public int erhoeheBewertungDurchAnzahlFelder(int anzahlFelder){
		return anzahlFelder >= 3 ? 1 : 0;
	}	
	
	public int wertDurchEigeneSteine(int richtung,Spieler spieler,int zaehler){		
		if(zaehler == 3)
			this.spielGewonnen = true;
		for(Kante k : nachbarn){
			if(k.getRichtung() == richtung){
				if(k.getNachbarn().getBesetztVon() == spieler){
					return (zaehler-1)*2 + 1 + k.getNachbarn().wertDurchEigeneSteine(richtung, spieler, zaehler+1);
				}
			}
		}
		
		if(besetztVon == spieler)
			return (zaehler-1)*2+1;
		else
			return 0;
	}
	
	public int zaehleFreieFelder(int richtung,Spieler spieler){
		for(Kante k : nachbarn){
			if(k.getRichtung() == richtung){
				if(k.getNachbarn().getBesetztVon() == spieler || k.getNachbarn().getBesetztVon() == null){
					return 1 + k.getNachbarn().zaehleFreieFelder(richtung,spieler);
				}
			}
		}	
		
		return 0;
	}
	
	public Spieler getBesetztVon(){
		return besetztVon;
	}
	public void besetzen(Spieler spieler){
		this.besetztVon = spieler;
	}
	public int getZeile(){
		return zeile;
	}
	public int getSpalte(){
		return spalte;
	}
}