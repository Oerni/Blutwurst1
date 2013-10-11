package logik;

public class KnotenComparator {
	
//	Ist Knoten 2 ein Nachfolger von Knoten 1?
	public static int istKnoten2Nachbar(Knoten knoten1,Knoten knoten2) {
		if(knoten2.getZeile()==knoten1.getZeile()){
			if(knoten2.getSpalte()-knoten1.getSpalte()==1)
				return Kante.HORIZONTAL_RECHTS;
			else if(knoten2.getSpalte()-knoten1.getSpalte()==-1)
				return Kante.HORIZONTAL_LINKS;
		}
		if(knoten2.getZeile()-knoten1.getZeile()==1){
			if(knoten1.getSpalte()==knoten2.getSpalte())
				return Kante.VERTIKAL_OBEN;
			if(knoten2.getSpalte()-knoten1.getSpalte()==1)
				return Kante.DIAGONAL_RECHTS_OBEN;
			if(knoten2.getSpalte()-knoten1.getSpalte()==-1)
				return Kante.DIAGONAL_LINKS_OBEN;
		}
		if(knoten2.getZeile()-knoten1.getZeile()==-1){
			if(knoten1.getSpalte()==knoten2.getSpalte())
				return Kante.VERTIKAL_UNTEN;
			if(knoten2.getSpalte()-knoten1.getSpalte()==1)
				return Kante.DIAGONAL_RECHTS_UNTEN;
			if(knoten2.getSpalte()-knoten1.getSpalte()==-1)
				return Kante.DIAGONAL_UNTEN_LINKS;
		}
//		Keine Nachbarn
		return -1;
	}
}
