package main.java.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;

import main.java.models.*;

public class CoAdmin extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String mailAdmin = request.getParameter("email");
		
    }
	
	private void setResponse(HttpServletResponse response) throws JSONException, IOException
	{
		
	}
	
	
}
