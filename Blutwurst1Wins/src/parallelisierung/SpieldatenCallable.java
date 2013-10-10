package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import datenhaltung.HSQLConnection;
import datenhaltung.Spiel;
import datenhaltung.Spieler;
import datenhaltung.Strings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpieldatenCallable implements Callable<ObservableList<Spiel>>{
	
	@Override
	public ObservableList<Spiel> call(){
		SemaphorManager.getInstance().lesezugriffAnmelden();
		ObservableList<Spiel> spieldaten = FXCollections.observableArrayList();
		SemaphorManager.getInstance().lesezugriffAbmelden();
		ResultSet spieldatenSQL = HSQLConnection.getInstance().executeQuery(Strings.SPIELDATEN);
		try{
			while(spieldatenSQL.next()){
				spieldaten.add(new Spiel(spieldatenSQL.getInt("spielnr"),new Spieler(spieldatenSQL.getString("name"),'X'),spieldatenSQL.getInt("punkteheim"),spieldatenSQL.getInt("punktegegner")));
			}
			return spieldaten;
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
}
