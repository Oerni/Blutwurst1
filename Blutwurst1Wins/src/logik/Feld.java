package logik;

import java.util.Stack;

import spieldaten.SpielModel;
import spieldaten.Zug;

public class Feld {
	private Knoten[][] spielfeld = new Knoten[7][6];
	private boolean spielGewonnen = false;
	private SpielModel model;
	
	public Feld(SpielModel model){
		this.model = model;
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++){
				Knoten knoten = new Knoten(i,j);
				spielfeld[i][j] = knoten;
				this.verknuepfeNachbarn(knoten);
			}
	}
	
	public void verknuepfeNachbarn(Knoten knoten){
		for (int l=0;l<=6;l++) 
			for(int m=0;m<=5;m++){
				if(spielfeld[l][m]==null)
					break;
				int richtung = KnotenComparator.istKnoten2Nachbar(spielfeld[l][m],knoten);
				if(richtung!=-1)
					spielfeld[l][m].nachbarnHinzufuegen(knoten,richtung);
				richtung = KnotenComparator.istKnoten2Nachbar(knoten,spielfeld[l][m]);
				if(richtung!=-1)
					knoten.nachbarnHinzufuegen(spielfeld[l][m],richtung);
		}
	}
	
	public boolean spielGewonnen(){
		return spielGewonnen;
	}
	
	public Knoten[][] getSpielfeld(){
		return spielfeld;
	}
	
	public int einfuegen(Zug zug){
		int zeile=0;
		while(zeile<6){
			if(spielfeld[zug.getSpalte()][zeile].getBesetztVon() == null){
				spielfeld[zug.getSpalte()][zeile].besetzen(zug.getSpieler());
				return zeile;
			}
			zeile++;
		}
		return -1;
	}
	
	public int zugBerechnen(){
		int[] bewertungen = new int[7];
		int ueberprueft = 0;
		for(Knoten k : this.zuUeberpruefendeKnoten()){
//			eigene Bewertung
			k.bewerten(true,model.getSpiel().getSelbst());
			bewertungen[ueberprueft] = k.getMax();
			k.besetzen(model.getSpiel().getSelbst());
			
	
			Knoten gegnerZug = null;
			int bewertungGegner = 0;
//			Gegnerzug überprüfen
			for(Knoten j : this.zuUeberpruefendeKnoten()){
				j.bewerten(false,model.getSpiel().getGegner());
				if(j.getMin() > bewertungGegner){
					if(gegnerZug!=null)
						gegnerZug.minZuruecksetzen();
					gegnerZug = j;
				}else{
					j.minZuruecksetzen();
				}
			}
			gegnerZug.besetzen(model.getSpiel().getGegner());
			bewertungen[ueberprueft] -= gegnerZug.getMin();
			gegnerZug.minZuruecksetzen();
			
			Knoten eigenerZug = null;
			int eigeneBewertung = 0;
			for(Knoten l : this.zuUeberpruefendeKnoten()){
				l.bewerten(true,model.getSpiel().getSelbst());
				if(l.getMax() > eigeneBewertung){
					if(eigenerZug!=null)
						eigenerZug.maxZuruecksetzen();
					eigenerZug = l;
				}
			}
			bewertungen[ueberprueft] += eigenerZug.getMax();
			
			k.spielerEntfernen();
			gegnerZug.spielerEntfernen();
			ueberprueft++;
		}
		
		int unserZug = 0;
		int maxBewertung = Integer.MIN_VALUE;
		
		for(int i=0;i<bewertungen.length;i++){
			if(bewertungen[i] >= maxBewertung){
				unserZug = i;
				maxBewertung = bewertungen[i];	
			}
			System.out.println("Bewertung der Spalte " + i + ": " + bewertungen[i]);
		}
		return unserZug;
		
//		if(unserZug!=null){
//			this.spielGewonnen = unserZug.spielGewonnen();
//			return unserZug.getSpalte();
//		}
//		return -1;
	}
	
	private Stack<Knoten> zuUeberpruefendeKnoten(){
		Stack<Knoten> zuUeberpruefen = new Stack<Knoten>();
		int zeile=0;
		for(int i=0;i<7;i++){
			zeile=0;
			while(zeile<6){
				if(spielfeld[i][zeile].getBesetztVon()==null){
					zuUeberpruefen.add(spielfeld[i][zeile]);
					zeile = 6;
				}
				zeile++;
			}
		}
		return zuUeberpruefen;
	}

	public static String getRichtung(int richtung){
		switch(richtung){
		case Kante.DIAGONAL_LINKS_OBEN:
			return "Links oben";
		case Kante.DIAGONAL_RECHTS_OBEN:
			return "Rechts oben";
		case Kante.DIAGONAL_RECHTS_UNTEN:
			return "Rechts unten";
		case Kante.DIAGONAL_UNTEN_LINKS:
			return "Links unten";
		case Kante.HORIZONTAL_LINKS:
			return "Links";
		case Kante.HORIZONTAL_RECHTS:
			return "Rechts";
		case Kante.VERTIKAL_OBEN:
			return "Oben";
		case Kante.VERTIKAL_UNTEN:
			return "Unten";
		}
		return "";
	}
}