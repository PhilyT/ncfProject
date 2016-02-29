package moteur;

import java.sql.*;
import java.util.ArrayList;

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
	
	public ArrayList<Presence> getPresenceEtudiant(Cours cour, Date date)
	{
		ArrayList<Presence> res = new ArrayList<Presence>();
		return res;
	}
	
	public ArrayList<Eleve> getEtudiants()
	{
		ArrayList<Eleve> res = new ArrayList<Eleve>();
		
		return res;
	}
}

