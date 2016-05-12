package main.java.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.smartcardio.CardException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.Eleve;
import main.java.moteur.ConnectionBD;
import main.java.moteur.ReadCard;

public class UpdStudent extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String ide = request.getParameter("ide");
		try {
			setResponse(response, nom, prenom, ide);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response, String nom, String prenom, String ide) throws JSONException, IOException, ClassNotFoundException
	{
		JSONObject json = new JSONObject();
		try
		{
			ReadCard reader = new ReadCard();
			int idEtud = Integer.parseInt(ide);
			reader.read();
			String idCard = reader.getIdResult();
			Eleve etudiant = new Eleve();
			etudiant.setId(idEtud);
			etudiant.setIdCarte(idCard);
			etudiant.setNom(nom);
			etudiant.setPrenom(prenom);
			ConnectionBD maco = new ConnectionBD();
			maco.updateEtudiant(etudiant);
			json.put("etat", "success");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
		catch(SQLException e)
		{
			json.put("etat", "Erreur accès base de données !");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		} catch (CardException e) {
			// TODO Auto-generated catch block
			json.put("etat", "Erreur lecture carte !");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.put("etat", "Erreur mise à jour !");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
	}
}
