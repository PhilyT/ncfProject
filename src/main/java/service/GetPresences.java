package main.java.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.Cours;
import main.java.models.Presence;
import main.java.moteur.ConnectionBD;

public class GetPresences extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String id_c = request.getParameter("idc");
		String date = request.getParameter("date");
		try {
			setResponse(response, id_c, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response, String id_c, String date) throws JSONException, IOException, ClassNotFoundException
	{
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		try
		{
			int idcour = Integer.parseInt(id_c);
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date date1 = sdf1.parse(date);
			Date sqlStartDate = new Date(date1.getTime());
			ConnectionBD maco = new ConnectionBD();
			ArrayList<Presence> cours = maco.getPresence(sqlStartDate, idcour);
			for	(int i = 0; i < cours.size(); i++)
			{
				JSONObject jsonCour = new JSONObject();
				jsonCour.put("libelle", cours.get(i).getLibelle());
				jsonCour.put("nomE", cours.get(i).getNomEtud());
				jsonCour.put("prenomE", cours.get(i).getPrenomEtud());
				jsonCour.put("date", cours.get(i).getDate());
				jsonCour.put("presence", cours.get(i).getPresence());
				ja.put(jsonCour);
			}
			json.put("etat", "success");
			json.put("Presences", ja);
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
