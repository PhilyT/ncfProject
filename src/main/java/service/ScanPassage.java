package main.java.service;

import main.java.moteur.ReadCard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class ScanPassage extends HttpServlet
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
	
	private void setResponse(HttpServletResponse response) throws JSONException, IOException{
		
		JSONObject json = new JSONObject();
		ReadCard reader = new ReadCard();
		String idCard = "";
		try
		{
			reader.read();
			idCard = reader.getIdResult();
			if(idCard.equals("No card detected!"))
			{
				json.put("etat", idCard);
				json.put("user", "");
			}
			else
			{
				json.put("etat", "success");
				json.put("user", idCard);
			}
		}
		catch(Exception e)
		{
			idCard = "Erreur lecture Card";
			json.put("etat", idCard);
			json.put("user", "");
		}
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
    }
}
