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
		try{
//			System.out.println(objekt +" speichern");
			objekt.speichern();
//			System.out.println(objekt +" gespeichert");
		}
		catch(SQLIntegrityConstraintViolationException ex){
//			ex.printStackTrace();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
