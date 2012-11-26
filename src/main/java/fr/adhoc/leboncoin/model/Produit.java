package model;
import java.util.Set;


public class Produit {

	private String P_ID;
	private double prixDepart;
	private String description;
	private Set<Offre> listeOffres;
	private Utilisateur vendeur;
	
	//------------------------------------------------------
	//	Constructeurs
	//------------------------------------------------------
	public Produit() {
		// TODO Auto-generated constructor stub
	}
	
	public Produit(Utilisateur vendeur) {
		super();
		this.vendeur = vendeur;
	}
	


	//------------------------------------------------------
	//	Get/Set
	//------------------------------------------------------
	
	





	public String getID() {
		return P_ID;
	}

	public void setID(String iD) {
		P_ID = iD;
	}

	public double getPrixDepart() {
		return prixDepart;
	}

	public void setPrixDepart(double prixDepart) {
		this.prixDepart = prixDepart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Offre> getListeOffres() {
		return listeOffres;
	}

	public void setListeOffres(Set<Offre> listeOffres) {
		this.listeOffres = listeOffres;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}


}