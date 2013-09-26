package model;

public class Spielfeld {
	private char spielfeld[][] = new char[7][6];
	
	public Spielfeld(){
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				spielfeld[i][j] = ' ';
	}
	
	public int einfuegen(int spalte,char spieler){
		for(int i=0;i<6;i++)
			if(spielfeld[spalte][i] == ' '){
				spielfeld[spalte][i] = spieler;
//				Spalte überprüfen
				return i;
			}
//		Spalte voll
		return 0;
	}
	
//	public boolean hatGewonnen(char spieler,int aktuellerSpalte,int aktuelleReihe){
//		boolean temp = false;
//		
//	}
}
