package Logik;

import java.util.Comparator;

public class KnotenComparator {
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int DIAGONAL = 2;
	
//	Ist Knoten 2 ein Nachfolger von Knoten 1?
	public static int istKnoten2Nachfolger(Knoten knoten1, Knoten knoten2) {
		if(knoten1.getZeile()==knoten1.getZeile() && (Math.sqrt(Math.pow(knoten1.getSpalte()-knoten2.getSpalte(),2))==1))
			return HORIZONTAL;
		if(Math.sqrt(Math.pow(knoten1.getZeile()-knoten2.getZeile(),2))==1 && knoten1.getSpalte()==knoten2.getSpalte())
			return VERTICAL;
		if((knoten1.getZeile()-knoten2.getZeile())==-1 && Math.sqrt(Math.pow(knoten1.getSpalte()-knoten2.getSpalte(),2))==1)
			return DIAGONAL;
		return -1;
	}

}
