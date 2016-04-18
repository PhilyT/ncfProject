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
			//maco.insertCours(new Cours (5,new Time(9,00,00),new Time(14,00,00),"JEE","202"));
			maco.insertPassage(new Passage(5, new Date(2016, 04, 19)));
			//maco.insertPresence(new Presence, cours, PresenceEnum.present, new Date(2016,04,18),2));
			//ArrayList<Eleve> eleves = maco.getEtudiants();
			//System.out.println(eleves);
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
