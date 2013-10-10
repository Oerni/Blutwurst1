package runnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import model.spiel.HSQLConnection;
import model.spiel.Strings;

public class AnzahlSiegeCallable implements Callable<Number>{
	
	public Number call(){
		SemaphorManager.getInstance().lesezugriffAnmelden();
		ResultSet anzahlSiegeSQL = HSQLConnection.getInstance().executeQuery(Strings.ANZAHL_SIEGE);
		SemaphorManager.getInstance().lesezugriffAbmelden();
		try{
			anzahlSiegeSQL.next();
			return anzahlSiegeSQL.getInt("anzahlsiege");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return -1;
	}
}
