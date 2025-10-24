package database_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadDataFromDatabase {

	public static void main(String[] args) throws SQLException {
		
		//1.Connect to the database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NINZA_CRM", "root", "root");
		
		//2.Create A statement
		Statement stat = conn.createStatement();
		
		//3. execute query
		ResultSet set = stat.executeQuery("select contactName from contact where contactId=7612;");
		
		while (set.next()) {
			String contactName = set.getString(1);
			System.out.println(contactName);
		}
			conn.close();
		}
	}

