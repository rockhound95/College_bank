package model;
import java.sql.*;
public class Dbconn {
	public static Connection connection=null;
	public static String URL="jdbc:mysql://localhost:3306/bank";
	public static String USERNAME="root";
	public static String PASSCODE="";
	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(URL, USERNAME, PASSCODE);
			System.out.println("Database Connected...");
		}catch(Exception ex) {
			ex.printStackTrace();			
		}
		return connection;
	}

}
