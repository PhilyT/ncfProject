package service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import moteur.ReadCard;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/")
public class Rest 
{	
	@GET
    @Path("/scan")
    @Produces(MediaType.APPLICATION_JSON)
	public String scanCard()
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
		return "salut";
	}
	
	public static void main(String[] args)
	{
		
	}
}
