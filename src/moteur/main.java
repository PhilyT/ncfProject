package moteur;

import java.sql.Date;
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
			//maco.connect();
			//maco.insertCours(new Cours (4,new Time(9,00,00),new Time(14,00,00),"JEE","202"));
			ArrayList<Eleve> eleves = maco.getEtudiants();
			System.out.println("Eleve 1 = "+eleves.get(0).getPrenom());
			System.out.println("Eleve 2 = "+eleves.get(1).getPrenom());
			System.out.println("Eleve 3 = "+eleves.get(2).getPrenom());
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
