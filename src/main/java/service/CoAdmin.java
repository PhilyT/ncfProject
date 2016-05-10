package main.java.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.*;
import main.java.moteur.ConnectionBD;

public class CoAdmin extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String mailAdmin = request.getParameter("email");
		String mdp = request.getParameter("mdp");
		try
		{
			setResponse(response, mailAdmin, mdp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	
	private void setResponse(HttpServletResponse response, String email, String mdp) throws JSONException, IOException, ClassNotFoundException, SQLException
	{
		System.out.println(email);
		System.out.println(mdp);
		JSONObject json = new JSONObject();
		ConnectionBD maco = new ConnectionBD();
		try
		{
			Admin admin = maco.getAdministrateur(email, mdp);
			if(admin == null)
			{
				json.put("etat", "Utilisateur inconue.");
				json.put("user", "");
				response.setStatus(200);
		        response.setContentType("application/json");
		        response.getWriter().write(json.toString());
			}
			else
			{
				JSONObject adminJson = new JSONObject();
				adminJson.put("id",admin.getId());
				adminJson.put("prenom",admin.getPrenom());
				adminJson.put("nom",admin.getNom());
				json.put("etat", "success");
				json.put("user", adminJson.toString());
				response.setStatus(200);
		        response.setContentType("application/json");
		        response.getWriter().write(json.toString());
			}
		}
		catch(Exception e)
		{
			json.put("etat", "Mauvais mot de passe.");
			json.put("user", "");
			response.setStatus(200);
	        response.setContentType("application/json");
	        response.getWriter().write(json.toString());
		}
	}
	
	
}
