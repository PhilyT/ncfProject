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


public class Rest extends HttpServlet
{	
	/*@GET
    @Produces(MediaType.APPLICATION_JSON)
	public HttpServletResponse scanCard() throws JSONException
	{
		ReadCard readeur = new ReadCard();
		try
		{
			String reponse = readeur.read();
			return reponse;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}*/
	
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
        json.put("test", "salut");
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
    }
}
