package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import datenhaltung.HSQLConnection;
import datenhaltung.Spieler;
import datenhaltung.Strings;

public class SpeichereSpielerCallable implements Callable<Number> {
	private Spieler spieler;
	public SpeichereSpielerCallable(Spieler spieler){
		this.spieler = spieler;
	}
	
	public Number call(){
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		HSQLConnection.getInstance().insert(String.format(Strings.INSERT, "spieler", "name", spieler.getName()));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
		SemaphorManager.getInstance().lesezugriffAnmelden();
		ResultSet spielerIDSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.SPIELER_ID,spieler.getName()));
		SemaphorManager.getInstance().lesezugriffAbmelden();
		
		try{
			spielerIDSQL.next();
			return spielerIDSQL.getInt("id");
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
}
