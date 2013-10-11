package logik;

import datenhaltung.Spieler;

public class Spielfeld {
	private char spielfeld[][] = new char[7][6];
	
	public Spielfeld(){
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				spielfeld[i][j] = ' ';
	}
	
	public int einfuegen(int spalte,Spieler spieler){
		for(int i=0;i<6;i++)
			if(spielfeld[spalte][i] == ' '){
				spielfeld[spalte][i] = spieler.getKennzeichnung();
//				Spalte �berpr�fen
				return i;
			}
//		Spalte voll
		return -1;
	}
}