package fr.adhoc.leboncoin.model;
import java.util.Set;


public class Utilisateur {
	private String U_ID;
	private String mail;
	private float note;
	private Set<Offre> listeOffres;
	private Set<Produit> listeProduits;
	
	//------------------------------------------------------
	//	Constructeurs
	//------------------------------------------------------
	
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}
	
	public Utilisateur(String iD, String mail) {
		super();
		U_ID = iD;
		this.mail = mail;
	}	
	
	//------------------------------------------------------
	//	Get/Set
	//------------------------------------------------------


	public String getID() {
		return U_ID;
	}

	public void setID(String iD) {
		U_ID = iD;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public Set<Offre> getListeOffres() {
		return listeOffres;
	}

	public void setListeOffres(Set<Offre> listeOffres) {
		this.listeOffres = listeOffres;
	}

	public Set<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(Set<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}





	
	
}
