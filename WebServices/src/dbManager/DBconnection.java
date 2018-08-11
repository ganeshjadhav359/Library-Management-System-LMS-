package dbManager;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBconnection {
	private static Connection	connection=null;


	private DBconnection() {
		
	}

	// DBConnection Method to connect to  Database
	public static Connection connect() throws SQLException {
		if (connection != null) {
			return connection;
		} else {

			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "test_1234");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return DBconnection.connection;
		}

	}
}
