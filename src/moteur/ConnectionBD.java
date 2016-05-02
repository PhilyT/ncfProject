package moteur;

import java.sql.*;
import java.util.ArrayList;

import models.*;


public class ConnectionBD
{
	private Connection connection;
	private String url;
	

	public ConnectionBD() throws SQLException, ClassNotFoundException
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver O.K.");
		
		url = "jdbc:mysql://localhost:3306/rfid_badgeuse";
		connection = DriverManager.getConnection(url,"root","jullyetgege");
		System.out.println("Connection fonctionnelle !");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Presence> getPresenceEtudiant(Cours cour, Date date)
	{
		ArrayList<Presence> res = new ArrayList<Presence>();
		
		ResultSet résultats = null;

		
		return res;
	}
	
	public ArrayList<Eleve> getEtudiants()
	{
		ArrayList<Eleve> res = new ArrayList<Eleve>();
		ResultSet result;
		String requete = "SELECT * FROM eleve";
		
		try 
		{
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			
			
		}
		catch (SQLException e)
		{
			
		}
		
		
		
		return res;
	}
	
	public void insertCours( Cours cours) throws SQLException
	{
		Statement statement = connection.createStatement();
		
		statement.executeUpdate("INSERT INTO cours "
				+  "VALUES ('"+cours.getId()+"','"+cours.getHeureDebut()+"','"+cours.getHeureFin()+"', '"+cours.getLibelle()+"','" +cours.getSalle()+"')");
	}
	
	public void insertEtudiants(Eleve eleve) throws SQLException
	{
		Statement statement = connection.createStatement();
		
		statement.executeUpdate("INSERT INTO eleve " 
				+ "VALUES ('"+eleve.getId()+"','"+eleve.getPrenom()+"', '"+eleve.getNom()+"','" +eleve.getIdCarte()+"')");
	}
	public void postPassage(String idCard)
	{
		
	}
}

