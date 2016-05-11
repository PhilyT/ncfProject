package main.java.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.smartcardio.CardException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.Eleve;
import main.java.moteur.ConnectionBD;
import main.java.moteur.ReadCard;

public class AddStudent extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		try {
			setResponse(response, nom, prenom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response, String nom, String prenom) throws JSONException, IOException, ClassNotFoundException
	{
		JSONObject json = new JSONObject();
		ReadCard reader = new ReadCard();
		try
		{
			reader.read();
			String idCard = reader.getIdResult();
			Eleve eleve = new Eleve();
			eleve.setIdCarte(idCard);
			eleve.setNom(nom);
			eleve.setPrenom(prenom);
			ConnectionBD maco = new ConnectionBD();
			maco.insertEtudiants(eleve);
			json.put("etat", "success");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
		catch(SQLException e)
		{
			json.put("etat", "Erreur accès base de données !");
			System.out.println(e);
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		} catch (CardException e) {
			// TODO Auto-generated catch block
			json.put("etat", "Erreur lecture Carte !");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.put("etat", "Pas de carte détecter !");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
	}
}
