package moteur;

import java.sql.SQLException;
import java.util.ArrayList;

import models.*;

public class main 
{
	public static void main(String[] args)
	{
		try
		{
			ConnectionBD maco = new ConnectionBD();
			maco.insertEtudiants(new Eleve (3,"Stéphanie","Carrié",123456));
			ArrayList<Eleve> eleves = maco.getEtudiants();
			//ReadCard readercard = new ReadCard();
			//String message = readercard.read();
			//System.out.println(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
