package main.java.service;

import main.java.moteur.ReadCard;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class GetCours extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		try {
			setResponse(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response) throws JSONException, IOException
	{
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject cour1 = new JSONObject();
		cour1.put("id_c", "1");
		cour1.put("libelle", "Cour1");
		JSONObject cour2 = new JSONObject();
		cour2.put("id_c", "2");
		cour2.put("libelle", "Cour2");
		ja.put(cour1);
		ja.put(cour2);
		json.put("Cours", ja);
		response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
	}
}
