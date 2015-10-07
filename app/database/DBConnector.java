package database;

import java.sql.*;

public class DBConnector {

	// Diese Klasse dient nur zur einfachen überprüfung der DB Verbindung
	
	Statement stmt;
	ResultSet rs;
	Connection con;

	public DBConnector() {
		System.out.println("War im DB Connector");
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/staj_db", "root", "root");
			//System.out.println("alles in Ordnung");

		} catch (Exception ex) {
			System.out.println("Dieser Fehler ist aufgetreten: "
					+ ex.getMessage());

		}

	}
}