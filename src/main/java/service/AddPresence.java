package main.java.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.Presence;
import main.java.moteur.ConnectionBD;

public class AddPresence extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String id_c = request.getParameter("idc");
		String idEtud = request.getParameter("ide");
		String date = request.getParameter("date");
		try {
			setResponse(response, id_c, idEtud, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response, String id_c, String idEtud, String date) throws JSONException, IOException, ClassNotFoundException
	{
		JSONObject json = new JSONObject();
		try
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			int idcour = Integer.parseInt(id_c);
			int idetud = Integer.parseInt(idEtud);
			java.util.Date date1 = sdf1.parse(date);
			Date sqlStartDate = new Date(date1.getTime());
			ConnectionBD maco = new ConnectionBD();
			Presence presence = new Presence();
			presence.setDate(sqlStartDate);
			presence.setIdCours(idcour);
			presence.setIdEtud(idetud);
			maco.insertPresence(presence);
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
