package fr.adhoc.leboncoin.service.impl;

import java.sql.*;


import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.ProduitDao;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.ProduitDaoImpl;
import fr.adhoc.leboncoin.service.ProduitService;
import fr.adhoc.leboncoin.service.UtilisateurService;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;
import fr.adhoc.leboncoin.model.Utilisateur;
import java.util.List;
import java.util.ArrayList;




public class ProduitServiceImpl implements ProduitService {
	private ProduitDao produitDaoProduitService;
	private UtilisateurDao utilisateurDaoProduitService;
	
	public ProduitServiceImpl() throws SQLException, Exception {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Produit createProduit(String nom, double prixDepart, String description, Utilisateur vendeur){

			//Creation de l'objet Produit
			Produit myPro = new Produit(nom,prixDepart,description, vendeur);
			
			//Stockage de myUt dans la base de donnees
			produitDaoProduitService.create(myPro);
	     
	     return myPro;
	        
	    
		
		
	}
	public List<Produit> findProduitByName(String nom){
		return produitDaoProduitService.findByName(nom);
	}
	public List<Produit> findAllProduits(){
		return produitDaoProduitService.findAll();
	}
		public boolean deleteProduit(int id){
		Produit myProd = new Produit("",0,"",new Utilisateur());
		myProd.setId(id);
		return produitDaoProduitService.delete(myProd);
	}
	public List<Produit> findProduitsByUtilisateur(int idutilisateur) {
		
		return produitDaoProduitService.findByUtilisateur( utilisateurDaoProduitService.findById(idutilisateur) );
	}

	public void setProduitDaoProduitService(ProduitDao produitDAO){
			this.produitDaoProduitService = produitDAO;
	}
	public void setUtilisateurDaoProduitService(UtilisateurDao utilisateurDAO){
			this.utilisateurDaoProduitService = utilisateurDAO;
	}

}
