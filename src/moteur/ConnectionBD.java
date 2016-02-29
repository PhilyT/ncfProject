package moteur;
import java.sql.*;

import models.*;


public class ConnectionBD
{
	Connection con;
	String url;
	

	public ConnectionBD() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		url = "jdbc:mysql://localhost:3306/rfid_project";
		con = DriverManager.getConnection(url,"root","");
	}
	
	public void getPresenceEtudiant(Cours cour, Date date)
	{
		
	}
}

