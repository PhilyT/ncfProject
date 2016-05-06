package main.java.models;
import java.sql.Date;

public class Presence {
	
	private int idEtud;
	private int idCours;
	private PresenceEnum presence;
	private Date date;
	
	public Presence(int idEtud, int idCours, PresenceEnum presence, Date date )
	{
		this.idEtud = idEtud;
		this.idCours = idCours;
		this.presence = presence;
		this.date = date;
	}
	
	public Presence(int idEtud, int idCours, Date date )
	{
		this.idEtud = idEtud;
		this.idCours = idCours;
		this.date = date;
	}
	
	public Presence()
	{
		
	}
	
	public int getIdEtud()
	{
		return idEtud;
	}
	
	public int getIdCours()
	{
		return idCours;
	}
	
	public PresenceEnum getPresence()
	{
		return presence;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void setIdEtud(int idEtud)
	{
		this.idEtud = idEtud;
	}
	
	public void setIdCours(int idCours)
	{
		this.idCours = idCours;
	}
	
	public void setPresence(PresenceEnum presence)
	{
		this.presence = presence;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}

}
