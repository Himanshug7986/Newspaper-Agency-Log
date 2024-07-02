package paperMaster;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnector {
	
	static Connection con;
	public static Connection doConnect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/NewspaperAgency","root","");
			System.out.println("connected..");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
	public static void main(String[] a)
	{
		doConnect();
	}

}
