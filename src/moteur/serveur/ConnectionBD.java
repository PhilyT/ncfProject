package moteur.serveur;
import java.sql.*;

public class ConnectionBD
{
	Connection con;
	String url;
	

	public ConnectionBD() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		url = "jdbc:mysql://localhost:3306/test";
		con = DriverManager.getConnection(url,"root","");
	}
	
	public void getPresenceEtudiant()
	{
		
	}
}

