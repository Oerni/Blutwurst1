package model;

import java.sql.*;

public class HSQLConnection {
	private Connection connection;
	
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
		}catch(SQLException ex){
			
		}
	}
}