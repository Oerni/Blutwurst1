package datenhaltung;

import java.sql.*;

public class HSQLConnection {
	/**
	 * Singleton zur zentralen Haltung der Datenbankverbindung
	 */
	private Connection connection;
	private Statement statement;
	private static HSQLConnection instance;
	
	private HSQLConnection(){
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
	
	public static HSQLConnection getInstance(){
		if(instance==null)
			instance = new HSQLConnection();
		return instance;
	}
	
	public Statement getStatement(){
		return statement;
	}
	
	public int insert(String insert,String fetchID) throws SQLException{
		statement.execute(insert);
		if(!fetchID.equals("ohne_id")){
			ResultSet idSet = statement.executeQuery(fetchID);
			idSet.next();
			return idSet.getInt("id");
		}else
			return -1;
	}
	
	public void update(String update){
		try{
			statement.executeUpdate(update);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query){
		try{
			return statement.executeQuery(query);
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
}