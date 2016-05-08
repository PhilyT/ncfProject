package main.java.moteur;

import java.sql.*;
import java.util.ArrayList;

import main.java.models.*;


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
		connection = DriverManager.getConnection(url,"rfid","rfid");
		System.out.println("Connection fonctionnelle !");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Presence> getPresenceEtudiant(Cours cour, Date date)
	{
		ArrayList<Presence> res = new ArrayList<Presence>();
		
		ResultSet resultats = null;

		
		return res;
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
				unCours.setSalle(result.getString("salle"));
				
		 
				res.add(unCours); 
			}
		}
		catch (SQLException e)
		{
			
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
		        
				unEleve.setPrenom(result.getString("prenom")); 
				unEleve.setNom(result.getString("nom")); 
				unEleve.setIdCarte(result.getInt("idCarte"));
		 
				res.add(unEleve); 
			}
		}
		catch (SQLException e)
		{
			
		} finally {
			stmt.close();
		}
		return res;
	}
	
	
	public ArrayList<Passage> getPassage() throws SQLException
	{
		ArrayList<Passage> res = new ArrayList<Passage>();
		ResultSet result;
		String requete = "SELECT * FROM passage";
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			
			while(result.next()){
				Passage unPassage = new Passage(); // Nouvelle instance
		        
				unPassage.setId_p(result.getInt("id_p"));
				unPassage.setHeureArrivee(result.getTimestamp("heureArrivee"));
				unPassage.setHeureDepart(result.getDate("heureDepart")); 
				
		 
				res.add(unPassage); 
			}
		}
		catch (SQLException e)
		{
			
		} finally {
			stmt.close();
		}
		return res;
	}
	
	public ArrayList<Presence> getPresence() throws SQLException
	{
		ArrayList<Presence> res = new ArrayList<Presence>();
		ResultSet result;
		String requete = "SELECT * FROM presence";
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
				unePresence.setIdPassage(result.getInt("idPassage"));
				
				
		 
				res.add(unePresence); 
			}
		}
		catch (SQLException e)
		{
			
		} finally {
			stmt.close();
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
	
	public void insertPassage(Passage passage) throws SQLException
	{
		
		Statement statement = connection.createStatement();
		
		statement.executeUpdate("INSERT INTO passage (id_p, heureDepart)"
				+ "VALUES ('"+passage.getId_p()+"', '"+passage.getHeureDepart()+"')");
		
	}
	
	public void insertPresence(Presence presence) throws SQLException
	{
		Statement statement = connection.createStatement();
		
		statement.executeUpdate("INSERT INTO presence "
				+ "VALUES ('"+presence.getIdEtud()+"','"+presence.getIdCours()+"','"+presence.getPresence()+"','"+presence.getDate()+"','"+presence.getIdPassage()+"')");
	}
}

