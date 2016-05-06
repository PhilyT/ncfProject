package main.java.moteur;

import java.sql.*;
import java.util.ArrayList;
import main.java.models.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionBD
{
	private Connection connection;
	private String url;
	
	public ConnectionBD() throws SQLException, ClassNotFoundException
	{
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.println("Driver O.K.");
		url = "jdbc:mysql://localhost:3306/rfid_badgeuse";
		connection = DriverManager.getConnection(url,"rfid","rfid");
		System.out.println("Connection fonctionnelle !");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cours> getCours() throws SQLException
	{
		ArrayList<Cours> res = new ArrayList<Cours>();
		ResultSet result;
		String requete = "SELECT * FROM cours";
		Statement stmt = null;
		try 
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			
			while(result.next()){
				Cours unCours = new Cours(); // Nouvelle instance
				unCours.setId(result.getInt("id_c"));
				unCours.setHeureDebut(result.getTime("heureDebut"));
				unCours.setHeureFin(result.getTime("heureFin"));
				unCours.setLibelle(result.getString("libelle"));		 
				res.add(unCours); 
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		} finally {
			stmt.close();
		}
		return res;
	}
	
	public Cours getCourActuel(Date date) throws SQLException
	{
		Cours res = new Cours();
		ResultSet result;
		String requete = "SELECT * FROM cours WHERE heureDebut <= '" + date.getTime() + "' AND heureFin >= '" + date.getTime() + "';";
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			while(result.next()){
				Cours unCours = new Cours(); // Nouvelle instance
				unCours.setId(result.getInt("id_c"));
				unCours.setHeureDebut(result.getTime("heureDebut"));
				unCours.setHeureFin(result.getTime("heureFin"));
				unCours.setLibelle(result.getString("libelle")); 
				res = unCours; 
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		} finally {
			stmt.close();
		}
		return res;
	}
	
	public ArrayList<Eleve> getEtudiants() throws SQLException
	{
		ArrayList<Eleve> res = new ArrayList<Eleve>();
		ResultSet result;
		String requete = "SELECT * FROM eleve";
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			
			while(result.next()){
				Eleve unEleve = new Eleve(); // Nouvelle instance
		        unEleve.setId(result.getInt("id"));
				unEleve.setPrenom(result.getString("prenom")); 
				unEleve.setNom(result.getString("nom")); 
				unEleve.setIdCarte(result.getString("idCarte"));
				res.add(unEleve); 
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		} finally {
			stmt.close();
		}
		return res;
	}
	
	public Eleve getEtudiant(String idCard) throws SQLException
	{
		Eleve res = null;
		ResultSet result;
		String requete = "SELECT * FROM eleve WHERE idCarte = '" + idCard + "';";
		Statement stmt = null;
		try 
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			System.out.println(result.toString());
			while(result.next()){
				Eleve unEleve = new Eleve(); // Nouvelle instance
				unEleve.setId(result.getInt("id"));
				unEleve.setPrenom(result.getString("prenom")); 
				unEleve.setNom(result.getString("nom")); 
				unEleve.setIdCarte(result.getString("idCarte"));
				res = unEleve; 
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		} finally {
			stmt.close();
		}
		return res;
	}
	
	public ArrayList<Presence> getPresence(int idCour) throws SQLException
	{
		ArrayList<Presence> res = new ArrayList<Presence>();
		ResultSet result;
		String requete = "SELECT * FROM presence WHERE idCours = '" + idCour + "';";
		Statement stmt = null;
		try 
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			while(result.next()){
				Presence unePresence = new Presence(); // Nouvelle instance
		        unePresence.setIdEtud(result.getInt("idEtud"));
				unePresence.setIdCours(result.getInt("idCours"));
				unePresence.setPresence(PresenceEnum.valueOf(result.getString("presence")));
				unePresence.setDate(result.getDate("date"));		 
				res.add(unePresence); 
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		} finally {
			stmt.close();
		}
		return res;
	}
	
	public void insertCours( Cours cours) throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("INSERT INTO cours (heureDebut, heureFin, libelle) "
				+  "VALUES ('"+cours.getHeureDebut()+"','"+cours.getHeureFin()+"', '"+cours.getLibelle()+"');");
	}
	
	public void insertEtudiants(Eleve eleve) throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("INSERT INTO eleve (prenom, nom, idCarte) " 
				+ "VALUES ('"+eleve.getPrenom()+"', '"+eleve.getNom()+"','" +eleve.getIdCarte()+"')");
	}
	
	public void insertPresence(Presence presence) throws SQLException
	{
		Statement statement = connection.createStatement();	
		statement.executeUpdate("INSERT INTO presence (idEtud, idCours, Date) "
				+ "VALUES ('"+presence.getIdEtud()+"','"+presence.getIdCours()+"','"+presence.getDate()+"');");
	}
	
	public void updatePresence(Presence presence)throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE presence SET presence = '" + presence.getPresence()
				+ "' WHERE idEtud = '"+presence.getIdEtud()+"' AND idCours = '"+presence.getIdCours()+"' AND Date = '"+presence.getDate()+"';");
	}
}

