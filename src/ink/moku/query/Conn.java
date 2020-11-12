package ink.moku.query;

import java.sql.*;

public class Conn {
	Connection conn;

	public void getDriver() throws ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Load MySQL Driver");
	}
	public Connection getConnection(String sHost, String sPort, String sData, String sUser, String sPass) throws SQLException {
			conn=DriverManager.getConnection("jdbc:mysql://"+sHost+":"+sPort+"/"+sData+"?serverTimezone=UTC",sUser,sPass);
			System.out.println("Connection To MySQL Server "+sHost+" and getConnection");
			return conn;
	}
	/*
	public void closeConnection(Connection con) {
		try {
			con.close();
			System.out.println("Close Connection");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	 */

}
