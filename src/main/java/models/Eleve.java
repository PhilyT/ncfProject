package main.java.models;

public class Eleve {
	
	private int id;
	private String prenom;
	private String nom;
	private String idCarte;
	
	public Eleve() {}
	public Eleve(int id, String prenom, String nom, String idCarte)
	{
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.idCarte = idCarte;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getIdCarte()
	{
		return idCarte;
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
	
	public void setIdCarte(String idCarte)
	{
		this.idCarte = idCarte;
	}
}
