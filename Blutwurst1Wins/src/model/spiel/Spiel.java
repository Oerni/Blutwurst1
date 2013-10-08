package model.spiel;

public class Spiel {
	private int spielNr;
	private String gegner;
	private int punkteHeim;
	private int punkteGegner;
	private String gewinner;
	private String spielstand;
	
	public Spiel(int spielNr,String gegner,int punkteHeim,int punkteGegner){
		this.spielNr = spielNr;
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		if(punkteHeim>punkteGegner)
			this.gewinner = "blutwurst1";
		else
			this.gewinner = gegner;
	}
	
	public int getSpielNr(){
		return spielNr;
	}
	public String getGegner(){
		return gegner;
	}
	public int getPunkteHeim(){
		return punkteHeim;
	}
	public int getPunkteGegner(){
		return punkteGegner;
	}
	public String getGewinner(){
		return gewinner;
	}
	public String getSpielstand(){
		return spielstand;
	}
	public void speichern(){
		
	}
}
