package moteur;

import java.sql.SQLException;
import java.util.ArrayList;

import models.*;

public class main {

	public static void main(String[] args)
	{
		try
		{
			ConnectionBD macon = new ConnectionBD();
			ArrayList<Eleve> eleves = macon.getEtudiants();
		}
		catch(Exception e)
		{
			
		}
	}

}
