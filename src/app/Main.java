package app;

import java.sql.Connection;

import connection.ConnectionFactory;

public class Main {

	public static void main(String[] args) {
		Connection con = ConnectionFactory.getConnection();
		ConnectionFactory.closeConnection(con);
	}

}
