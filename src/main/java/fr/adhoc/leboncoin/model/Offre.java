package model;
import java.util.Date;


public class Offre {
	
	private String O_ID;
	private double montant;
	private Date date;
	private enum Statut {Attente, Acceptee, Refusee};
	private Statut statut;
	private Utilisateur acheteur;
	private Produit produit;
	
	
	
	//------------------------------------------------------
	//	Constructeurs
	//------------------------------------------------------

	
	public Offre() {
		// TODO Auto-generated constructor stub
	}

	public Offre(double montant, Utilisateur acheteur, Produit produit) {
		super();
		this.montant = montant;
		this.acheteur = acheteur;
		this.produit = produit;
	}


	//------------------------------------------------------
	//	Get/Set
	//------------------------------------------------------





	public String getID() {
		return O_ID;
	}



	public void setID(String iD) {
		O_ID = iD;
	}



	public double getMontant() {
		return montant;
	}



	public void setMontant(double montant) {
		this.montant = montant;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Statut getStatut() {
		return statut;
	}



	public void setStatut(Statut statut) {
		this.statut = statut;
	}



	public Utilisateur getAcheteur() {
		return acheteur;
	}



	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}



	public Produit getProduit() {
		return produit;
	}



	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	


	
	
	

}
