package connection;

import static service.UtilsService.getProp;
import static service.UtilsService.getConnectionProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	
	private static Properties prop = getProp();
	private static Properties conProps = getConnectionProperties();
	
	private static final String DRIVER = conProps.getProperty("Connection.DRIVER");
	private static final String URL = conProps.getProperty("Connection.URL");
	private static final String USER = conProps.getProperty("Connection.USER");
	private static final String PASS = conProps.getProperty("Connection.PASS");

	public static Connection getConnection() {

		try {
			Class.forName(DRIVER);
			System.out.println(prop.getProperty("Connection.Message.Conectando"));
			return DriverManager.getConnection(URL, USER, PASS);

		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException(prop.getProperty("Connection.Message.Connection.Fail"), ex);
		}
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
				System.out.println(prop.getProperty("Connection.Message.Desconectando"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		closeConnection(con, stmt);

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
