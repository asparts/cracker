package cracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public  class DatabaseConnection {

	
	String jdbcURL = "jdbc:derby:crackerDB;create=true";
	String shutdownURL = "jdbc:derby:;shutdown=true";
	String sql = "";
	
	Connection DBconnection;
	
	public void establishConnection() throws SQLException {
		System.out.println("jee");
		Connection DBconnection = DriverManager.getConnection(jdbcURL);
		String sqlTemp = "Create Table passwords (password varchar(128), hash varchar(128))";	
		Statement sqlStatement = DBconnection.createStatement();
		//sqlStatement.execute(sqlTemp); //=> These not needed after first time since the table already exists. 
	}
	public void closeConnection() throws SQLException{
		DBconnection = DriverManager.getConnection(shutdownURL);
	}
	
	
}
