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
	/*public Response sendResponse(int status, String entity, String methode)
	{
		return Response
				.status(status)
				.entity(entity)
				.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With")
                .header("Access-Control-Allow-Methods", methode)
                .build();	
	}*/
	
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
		return "";
	}
}
