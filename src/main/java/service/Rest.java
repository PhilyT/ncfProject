package main.java.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.moteur.ReadCard;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/badgeuse")
public class Rest extends HttpServlet
{	
	@GET
    @Path("/scan")
    @Produces(MediaType.APPLICATION_JSON)
	public Response scanCard()
	{
		/*ReadCard readeur = new ReadCard();
		try
		{
			String reponse = readeur.read();
			return reponse;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		return sendResponse(200,"{'test' : 'salut'}","GET");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		
    }
	
	private Response sendResponse(int status, String entity, String methode){
        return Response
                .status(status)
                .entity(entity)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With")
                .header("Access-Control-Allow-Methods", methode)
                .build();
    }
}
