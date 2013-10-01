package model.spiel;

import java.sql.*;

public class HSQLConnection {
	private Connection connection;
	private Statement statement;
	
	public HSQLConnection(){
		try{
			Class.forName("org.hsqldb.jdbcDriver");
		}catch(ClassNotFoundException ex){
			System.out.println("Treiber nicht gefunden!");
			return;
		}
		
		connection = null;
		
		try{
			connection = DriverManager.getConnection("jdbc:hsqldb:file:home/blutwurst/trash/hsql", "root", "blutwurst1");
			statement = connection.createStatement();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
//	public 
//	public DBObject executeQuery(String query,String table){
//		if(!query.contains("timestamp")){
//			
//		}
//		try{
//			ResultSet rs = statement.executeQuery(query);
//			
//		}catch(SQLException ex){
//			ex.printStackTrace();
//		}
//	}
}