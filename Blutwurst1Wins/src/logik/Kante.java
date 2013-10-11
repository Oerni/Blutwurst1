package logik;

public class Kante {
	private Knoten von;
	private Knoten nach;
	private boolean aktiv;
	
	public Kante(Knoten von,Knoten nach){
		this.von = von;
		this.nach = nach;
		aktiv = false;
	}
	public Kante(boolean aktiv,Knoten von,Knoten nach){
		this.von = von;
		this.nach = nach;
		this.aktiv = aktiv;
	}
	
	public Knoten getNachfolger(Knoten knoten){
		if(knoten.getZeile() == von.getZeile() && knoten.getSpalte() == von.getSpalte())
			return nach;
		return null;
	}
	
	public boolean istAktiv(){
		return aktiv;
	}
	public void aktiviere(){
		this.aktiv = true;
	}
}
