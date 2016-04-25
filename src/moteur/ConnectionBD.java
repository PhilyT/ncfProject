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
		
		url = "jdbc:mysql://localhost:3306/rfid_actuel";
		connection = DriverManager.getConnection(url,"root","");
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

