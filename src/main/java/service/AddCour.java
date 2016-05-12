package main.java.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		String heureDebut = request.getParameter("dateDebut");
		String heureFin = request.getParameter("dateFin");
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
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		    java.util.Date d1 =(java.util.Date)format.parse(heureDebut+":00");
		    java.util.Date d2 =(java.util.Date)format.parse(heureFin+":00");
		    Time hDebut = new Time(d1.getTime());
		    Time hFin = new Time(d1.getTime());
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			json.put("etat", "Erreur de parsing de date !");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
	}
}
