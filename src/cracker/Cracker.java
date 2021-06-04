package cracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Cracker {

	Scanner scanner = new Scanner(System.in);
	
	static Scanner fileScanner = null;
	DatabaseConnection databaseConnection = new DatabaseConnection();
	ResultSet resultSet;
	String sql = "";
	
	
	public Cracker() throws SQLException {
		databaseConnection.DBconnection = DriverManager.getConnection(databaseConnection.jdbcURL);
	}
	

	public String queryDBwHash(String inputHash) throws SQLException {
		
		String outputString = "Password not found from database";
		
		Statement sqlStatement = databaseConnection.DBconnection.createStatement();
		sql ="Select password from passwords WHERE hash = '" + inputHash +"'";
		resultSet = sqlStatement.executeQuery(sql);
		
		//outputString = resultSet.toString(); //TODO: Check that resultSet is null if hash not found
		if(resultSet.next()) {
		outputString = resultSet.getString(1);
		System.out.println(outputString);}
		return outputString;
		
	}
	
	private String queryDBwPassword(String inputString) throws SQLException {
		
		String outputString = null;
		
		Statement sqlStatement = databaseConnection.DBconnection.createStatement();
		sql ="Select password from crackerDB WHERE password = " + inputString;
		resultSet = sqlStatement.executeQuery(sql);
		
		//outputString = resultSet.toString(); //TODO: Check that resultSet is null if hash not found
		outputString = resultSet.getString("password");
		System.out.println(outputString);
		return outputString;
		
	}
	public void savePasswordToDB(String password, String hash) {
		//TODO: Do I need two of these methods??
	}
	
	public void savePasswordToDBFromFile(String textFile) throws SQLException {
		//TODO: Do I need two of these methods??
		databaseConnection.establishConnection();
		try {
			fileScanner = new Scanner(new File(textFile));
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String hash = HashUtils.sha256Hash(line);
				Statement sqlStatement = databaseConnection.DBconnection.createStatement(); // TODO: MAKE SOME KIND OF LOADING SCREEN FOR USER TO HAVE INFORMATION 
				sql ="Insert into passwords (password, hash) values ('" + line + "','" + hash + "')";
				sqlStatement.executeUpdate(sql);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
