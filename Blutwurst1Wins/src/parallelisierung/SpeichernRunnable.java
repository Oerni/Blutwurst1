package parallelisierung;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import spieldaten.DBObject;

public class SpeichernRunnable implements Runnable{
	private DBObject objekt;
	
	public SpeichernRunnable(DBObject objekt){
		this.objekt = objekt;
	}
	@Override
	public void run(){
		System.out.println("Klasse: " + objekt.getClass());
		
		try{
			SemaphorManager.getInstance().schreibzugriffAnmelden();
			objekt.speichern();
			SemaphorManager.getInstance().schreibzugriffAbmelden();
		}
		catch(SQLIntegrityConstraintViolationException ex){
			
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
