package models;
import java.sql.Time;


public class Cours 
{
	private int id_c;
	private Time heureDebut;
	private Time heureFin;
	private String libelle;
	private String salle;
	
	public Cours(int id_c, Time heureDebut, Time heureFin, String libelle, String salle)
	{
		this.id_c = id_c;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.libelle = libelle;
		this.salle = salle;
	}
	
	public int getId()
	{
		return id_c;
	}
	
	public Time getHeureDebut()
	{
		return heureDebut;
	}
	
	public Time getHeureFin()
	{
		return heureFin;
	}
	
	public String getLibelle()
	{
		return libelle;
	}
	
	public String getSalle()
	{
		return salle;
	}
	
	public void setId(int id_c)
	{
		id_c = id_c;
	}
	
	public void setHeureDebut(Time heureDebut)
	{
		heureDebut = heureDebut;
	}
	
	public void setHeureFin(Time heureFin)
	{
		heureFin = heureFin;
	}
	
	public void setLibelle(String libelle)
	{
		libelle = libelle;
	}
	
	public void setSalle(String salle)
	{
		salle = salle;
	}
}
