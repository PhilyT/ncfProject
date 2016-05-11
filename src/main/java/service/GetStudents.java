package main.java.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.Eleve;
import main.java.moteur.ConnectionBD;

public class GetStudents extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		try {
			setResponse(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response) throws JSONException, IOException, ClassNotFoundException
	{
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		try
		{
			ConnectionBD maco = new ConnectionBD();
			ArrayList<Eleve> eleves = maco.getEtudiants();
			for	(int i = 0; i < eleves.size(); i++)
			{
				JSONObject jsonEleve = new JSONObject();
				jsonEleve.put("id", eleves.get(i).getId());
				jsonEleve.put("prenom", eleves.get(i).getPrenom());
				jsonEleve.put("nom", eleves.get(i).getNom());
				jsonEleve.put("historisation", eleves.get(i).getHistorisation());
				jsonEleve.put("idCarte", eleves.get(i).getIdCarte());
				ja.put(jsonEleve);
			}
			json.put("etat", "success");
			json.put("Etudiants", ja);
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
		catch(SQLException e)
		{
			json.put("etat", "Erreur accès base de données !");
			json.put("Etudiants", "");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
	}
}
