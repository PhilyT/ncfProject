package models;
import java.sql.Date;
import java.sql.Timestamp;

public class Passage {
	
	private  int id_p;
	private Timestamp heureArrivee;
	private Date heureDepart;
	
	public Passage(int id_p, Timestamp heureArrivee, Date heureDepart)
	{
		this.id_p = id_p;
		this.heureArrivee = heureArrivee;
		this.heureDepart = heureDepart;
	}
	
	public int getId_p()
	{
		return id_p;
	}
	
	public Timestamp getHeureArrivee()
	{
		return heureArrivee;
	}
	
	public Date getHeureDepart()
	{
		return heureDepart;
	}
	
	public void setId_p(int id_p)
	{
		this.id_p = id_p;
	}
	
	public void setHeureArrivee(Timestamp heureArrivee)
	{
		this.heureArrivee = heureArrivee;
	}
	
	public void setHeureDepart(Date heureDepart)
	{
		this.heureDepart = heureDepart;
	}
	
}
