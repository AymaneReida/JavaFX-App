package daofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
	String db = "compte_exam";
	String user = "root";
	String pwd = "";
	String url = "jdbc:mysql://localhost:3306/" + db;
	private static Connection connection = null; // connection doit etre partagé : utiliser un seul objet (reference)
													// connection qui va servir à toutes les requetes

	private SingleConnection() {
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("instance créée!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			new SingleConnection();
		}

		return connection;
	}
}
