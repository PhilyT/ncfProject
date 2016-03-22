package moteur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Time;

import models.*;

public class main 
{
	public static void main(String[] args)
	{
		try
		{
			ConnectionBD maco = new ConnectionBD();
			maco.insertCours(new Cours (4,new Time(9,00,00),new Time(14,00,00),"JEE","202"));
			//ArrayList<Eleve> eleves = maco.getEtudiants();
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
