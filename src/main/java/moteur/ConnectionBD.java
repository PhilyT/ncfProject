package main.java.moteur;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	public Cours getCourActuel(java.util.Date date) throws SQLException
	{
		Cours res = new Cours();
		ResultSet result;
		String requete = "SELECT * FROM cours WHERE heureDebut <= '" + new Time(date.getTime()) + "' AND heureFin >= '" + new Time(date.getTime()) + "';";
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
				unEleve.setHistorisation(result.getString("id_historisation"));
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
	
	/*public ArrayList<Presence> getPresence(int idCour) throws SQLException
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
	}*/
	
	public Admin getAdministrateur(String email, String mdp) throws Exception
	{
		Admin res = null;
		ResultSet result;
		String requete = "SELECT * FROM administration WHERE mailAdmin = '" + email + "';";
		Statement stmt = null;
		try
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			while(result.next())
			{
				final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
		        sha512.update(mdp.getBytes());
		        
		        String mdpencrypt = convertByteToHex(sha512.digest()).toUpperCase();
				System.out.println(mdpencrypt);
				System.out.println(result.getString("mp"));
				if(mdpencrypt.equals(result.getString("mp")))
				{
					res = new Admin();
					res.setEmail(result.getString("mailAdmin"));
					res.setId(result.getInt("idAdmin"));
					res.setMdp(result.getString("mp"));
					res.setNom(result.getString("nomAdmin"));
					res.setPrenom(result.getString("prenomAdmin"));
				}
				else
				{
					throw new Exception("Mauvais mot de passe.");
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
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
	public void updateHeuretCours (Cours cours) throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE cours set heureDebut ='"
		+ cours.getHeureDebut()+"', heureFin='"+cours.getHeureFin()+"' where id_c ="+cours.getId()+";");
	}
	public void  deleteCours (Cours cours) throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM cours where id_c ='" + cours.getId()+"';");
	}
	public void insertEtudiants(Eleve eleve) throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("INSERT INTO eleve (prenom, nom, idCarte) " 
				+ "VALUES ('"+eleve.getPrenom()+"', '"+eleve.getNom()+"','" +eleve.getIdCarte()+"')");
	}
	public void updateEtudiant (Eleve eleve) throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE eleve set nom = '"+eleve.getNom()+"', prenom = '"+eleve.getPrenom()+"', id_historisation = idCarte , idCarte ='"+eleve.getIdCarte()+"'" +
		"WHERE id ='"+eleve.getId()+"';");
	}
	public void deleteEtudiant (Eleve eleve) throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE RROM eleve WHERE id = '"+eleve.getId()+"';");
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
		statement.executeUpdate("UPDATE presence SET presence.presence = 'p'" 
				+ " WHERE idEtud = "+presence.getIdEtud()+" AND idCours = "+presence.getIdCours()+" AND Date = '"+presence.getDate()+"';");
	}

	public static String convertByteToHex(byte data[])
    {
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
        
        return hexData.toString();
    }
	
	public ArrayList<Presence>getPresence(java.sql.Date date, int idCours)throws SQLException
	{
		
		ArrayList<Presence> res = new ArrayList<Presence>();
		ResultSet result;
		String requete = "SELECT c.libelle, e.nom, e.prenom , p.date , p.presence FROM cours c, eleve e, presence p WHERE p.idEtud = e.id AND p.idCours =c.id_c AND c.id_c = " + idCours +" AND  p.Date = '"+ date+"';";
		Statement stmt = null;
		try 
		{
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			while(result.next()){
				PresenceEnum penum = PresenceEnum.absent;
				Presence unePresence = new Presence(); // Nouvelle instance
		        unePresence.setLibelle(result.getString("libelle"));
				unePresence.setNomEtud(result.getString("nom"));
				unePresence.setPrenomEtud(result.getString("prenom"));
				unePresence.setDate(result.getDate("date"));
				penum.setPresence(result.getString("presence"));
				unePresence.setPresence(penum);
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
}

