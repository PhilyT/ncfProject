package main.java.service;

import main.java.models.*;
import main.java.moteur.ConnectionBD;
import main.java.moteur.ReadCard;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			System.out.println(idCard);
			if(idCard.equals("No card detected!"))
			{
				json.put("etat", idCard);
				json.put("user", "");
			}
			else
			{
				ConnectionBD maco = new ConnectionBD();
				Eleve eleve = maco.getEtudiant(idCard);
				java.util.Date date = new java.util.Date();
				Date dateActuel = new Date(date.getTime());
				Cours cour = maco.getCourActuel(date);
				if(eleve != null)
				{
					JSONObject eleveJson = new JSONObject();
					eleveJson.put("id",eleve.getId());
					eleveJson.put("prenom",eleve.getPrenom());
					eleveJson.put("nom",eleve.getNom());
					eleveJson.put("idCarte",eleve.getIdCarte());
					json.put("etat", "success");
					json.put("user", eleveJson.toString()); 
					System.out.println(cour.getId());
					System.out.println(dateActuel);
					System.out.println(eleve.getId());
					System.out.println(eleve.getIdCarte());
					maco.updatePresence(new Presence(eleve.getId(), cour.getId(), PresenceEnum.present, dateActuel));;
				}
				else
				{
					json.put("etat", "Carte inconnue !");
					json.put("user", "");
				}
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
