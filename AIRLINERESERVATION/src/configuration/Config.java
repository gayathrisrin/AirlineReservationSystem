package configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public interface Config {

	String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/5622db?useSSL=false";
	String UNAME = "root";
String PASS = "root";
}

