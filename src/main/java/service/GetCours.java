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

import main.java.models.Cours;
import main.java.moteur.ConnectionBD;

public class GetCours extends HttpServlet
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
			ArrayList<Cours> cours = maco.getCours();
			for	(int i = 0; i < cours.size(); i++)
			{
				JSONObject jsonCour = new JSONObject();
				jsonCour.put("id_c", cours.get(i).getId());
				jsonCour.put("heureDebut", cours.get(i).getHeureDebut());
				jsonCour.put("heureFin", cours.get(i).getHeureFin());
				jsonCour.put("libelle", cours.get(i).getLibelle());
				ja.put(jsonCour);
			}
			json.put("etat", "success");
			json.put("Cours", ja);
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
		catch(SQLException e)
		{
			json.put("etat", "Erreur accès base de données !");
			json.put("Cours", "");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
	}
}
