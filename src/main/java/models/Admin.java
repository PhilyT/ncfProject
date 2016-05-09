package main.java.models;

public class Admin 
{
	String nom;
	String prenom;
	String mdp;
	String email;
	int id;
	
	public Admin()
	{
		
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getMdp()
	{
		return mdp;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setMdp(String mdp)
	{
		this.mdp = mdp;
	}
}
