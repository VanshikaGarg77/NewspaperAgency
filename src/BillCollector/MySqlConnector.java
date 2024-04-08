package BillCollector;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {

	static Connection con;
	public static Connection doConnect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/newspaper","root","Vishu@9177");
			System.out.println("connected successfully...");
		} catch (Exception exp) {
			System.out.println(exp);
		}
		return con;
	}
	public static void main(String args[])
	{
		doConnect();
	}
}