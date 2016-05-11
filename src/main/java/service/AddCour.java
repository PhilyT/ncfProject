package main.java.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.Cours;
import main.java.moteur.ConnectionBD;

public class AddCour extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String libelle = request.getParameter("libelle");
		String heureDebut = request.getParameter("heureDebut");
		String heureFin = request.getParameter("heureFin");
		try {
			setResponse(response, libelle, heureDebut, heureFin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response, String libelle, String heureDebut, String heureFin) throws JSONException, IOException, ClassNotFoundException
	{
		JSONObject json = new JSONObject();
		try
		{
			ConnectionBD maco = new ConnectionBD();
			Time hDebut = new Time(Date.parse(heureDebut));
			Time hFin = new Time(Date.parse(heureFin));
			Cours cour = new Cours();
			cour.setHeureDebut(hDebut);
			cour.setHeureFin(hFin);
			cour.setLibelle(libelle);
			maco.insertCours(cour);
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
		}
	}
}
