package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import datenhaltung.HSQLConnection;
import datenhaltung.Spiel;
import datenhaltung.Strings;

public class SpeichereSpielCallable implements Callable<Number>{
	private Spiel spiel;
	
	public SpeichereSpielCallable(Spiel spiel){
		this.spiel = spiel;
	}
	
	public Number call(){
		if(spiel.getSpielNr() == -1){
			SemaphorManager.getInstance().schreibzugriffAnmelden();
			HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"spiel","gegner,punkteheim,punktegegner",spiel.getGegner().getID()+","+spiel.getPunkteHeim()+","+spiel.getPunkteGegner()));
			SemaphorManager.getInstance().schreibzugriffAbmelden();
			SemaphorManager.getInstance().lesezugriffAnmelden();
			ResultSet spielNrSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.LETZTES_SPIEL_NR,spiel.getGegner().getID()));
			SemaphorManager.getInstance().lesezugriffAbmelden();
			try{
				spielNrSQL.next();
				return spielNrSQL.getInt("spielnr");
			}catch(SQLException ex){
				ex.printStackTrace();
				return -1;
			}
		}
		return -1;
	}
}
