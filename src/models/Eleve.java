package models;

public class Eleve {
	
	private int id;
	private String prenom;
	private String nom;
	private int idCarte;
	
	public Eleve(int id, String prenom, String nom, int idCarte)
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
	
	public int getIdCarte()
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
	
	public void setIdCarte(int idCarte)
	{
		this.idCarte = idCarte;
	}
}
