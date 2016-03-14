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
			//ConnectionBD macon = new ConnectionBD();
			//ArrayList<Eleve> eleves = macon.getEtudiants();
			ReadCard readercard = new ReadCard();
			String message = readercard.read();
			System.out.print(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
