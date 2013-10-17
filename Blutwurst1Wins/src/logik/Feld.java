package logik;

import java.util.Stack;

import datenhaltung.Spieler;
import datenhaltung.Zug;

public class Feld {
	private Knoten[][] spielfeld = new Knoten[7][6];
	
	public Feld(){
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
	
	public int zugBerechnen(Spieler spieler){
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
		int bewertung = 0;
		Knoten unserZug = null;
		for(Knoten k : zuUeberpruefen){
			int temp;
			if((temp = k.getBewertung(spieler))>=bewertung){
				bewertung = temp;
				unserZug = k;
			}
			System.out.println("("+k.getSpalte()+","+k.getZeile()+"): "+temp);
		}
		if(unserZug!=null)
			return unserZug.getSpalte();
		return -1;
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