package models;
import java.sql.Date;

public class Presence {
	
	private Eleve idEtud;
	private Cours idCours;
	private PresenceEnum presence;
	private Date date;
	private Passage idPassage;
	
	public Presence(Eleve idEtud, Cours idCours, PresenceEnum presence, Date date, Passage idPassage )
	{
		this.idEtud = idEtud;
		this.idCours = idCours;
		this.presence = presence;
		this.date = date;
		this.idPassage = idPassage;
	}
	
	public Eleve getIdEtud()
	{
		return idEtud;
	}
	
	public Cours getIdCours()
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
	
	public Passage getIdPassage()
	{
		return idPassage;
	}
	
	public void setIdEtud()
	{
		this.idEtud = idEtud;
	}
	
	public void setIdCours()
	{
		this.idCours = idCours;
	}
	
	public void setPresence()
	{
		this.presence = presence;
	}
	
	public void setDate()
	{
		this.date = date;
	}
	
	public void setIdPassage()
	{
		this.idPassage = idPassage;
	}

}
