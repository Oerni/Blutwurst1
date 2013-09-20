package database.connection;

import java.sql.*;

public class HSQLConnection {
	private Connection con = null;
	private static HSQLConnection instance = null;
	private Statement statement = null;
	private HSQLConnection(){
		try{
			Class.forName("org.hsqldb.jdbcDriver");
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}		
		try{
			con = DriverManager.getConnection("jdbc:hsqldb:file:home/blutwurst/trash/hsql; shutdown=true", "root", "blutwurst1");
			statement = con.createStatement();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public static HSQLConnection getInstance(){
		if(instance == null)
			instance = new HSQLConnection();
		return instance;
	}
	
	public boolean reset(String table){
		String query = "DELETE FROM " + table;
		try{
			return statement.execute(query);
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}
	}
	public boolean update(String table,String[] value){
		return false;
	}
	
	public void executeQuery(String query){
		
	}
	
	public static void main(String[] args){
		new HSQLConnection();
	}
}
