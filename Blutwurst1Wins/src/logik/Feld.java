package logik;

import java.util.ArrayList;

public class Feld {
	private ArrayList<Knoten> knotenListe = new ArrayList<Knoten>();
	private ArrayList<Kante> kantenListe = new ArrayList<Kante>();
	
	public Feld(){
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++){
				Knoten knoten = new Knoten(i,j);
				Knoten lastElement = knotenListe.get(knotenListe.size());
				knotenListe.add(knoten);
				if(lastElement!=null)
					if(KnotenComparator.istKnoten2Nachfolger(lastElement,knoten)!=-1)
						kantenListe.add(new Kante(lastElement,knoten));
			}
	}
	
	public static void main(String[] args){
		
	}
}