package connexion.base.donnees;

import java.sql.*;

public class Connexion {
	String url;
	String user;
	String passwd;
	
	public Connexion () {
		this.url = "nsoc";
		this.user = "root";
		this.passwd = "";
	}
	
	/**
	 * execute la connexion avec le SGBD (MySQL)
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Connection execute () throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("DRIVER OK ! ");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + url, user, passwd);
		System.out.println("Connection effective !");
		
		return connection;
	}
}


