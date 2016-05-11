package main.java.models;
import java.sql.Date;

public class Presence {
	
	private int idEtud;
	private int idCours;
	private String libelle;
	private String nomEtud;
	private String prenomEtud;
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
	
	public String getLibelle()
	{
		return libelle;
	}
	
	public String getNomEtud()
	{
		return nomEtud;
	}
	
	public String getPrenomEtud()
	{
		return prenomEtud;
	}
	
	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}
	
	public void setNomEtud(String nomEtud)
	{
		this.nomEtud = nomEtud;
	}
	
	public void setPrenomEtud(String prenomEtud)
	{
		this.prenomEtud = prenomEtud;
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
