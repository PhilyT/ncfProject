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
			ArrayList<Cours> cours = maco.getCours();
			System.out.println("Cours 1 = "+cours.get(0).getLibelle());
			System.out.println("Cours 2 = "+cours.get(1).getLibelle());
			System.out.println("Cours 3 = "+cours.get(2).getLibelle());
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
