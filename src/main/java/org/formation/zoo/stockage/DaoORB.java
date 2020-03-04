package org.formation.zoo.stockage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoORB {
	private Connection conn;
	private Properties prop;
	public DaoORB() {
		try {
			prop = new Properties();
			charger();
//			conn = DriverManager.getConnection(prop.getProperty("URL"),prop.getProperty("LOGIN"),prop.getProperty("PSWD"));
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void charger() {
		try {
			prop.load(new FileInputStream("mysql.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConn() {
		return conn;
	}
	
	public static void main(String[] args) {
		DaoORB dao = new DaoORB();
	}
}
