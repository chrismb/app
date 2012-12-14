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
	private ProduitDao myDao;
	private UtilisateurDao myUtDao;
	
	public ProduitServiceImpl() throws SQLException, Exception {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Produit createProduit(String nom, double prixDepart, String description, Utilisateur vendeur){

			//Creation de l'objet Produit
			Produit myPro = new Produit(nom,prixDepart,description, vendeur);
			
			//Stockage de myUt dans la base de donnees
			myDao.create(myPro);
	     
	     return myPro;
	        
	    
		
		
	}
	public List<Produit> findProduitByName(String nom){
		return myDao.findByName(nom);
	}
	public List<Produit> findAllProduits(){
		return myDao.findAll();
	}
		public boolean deleteProduit(int id){
		Produit myProd = new Produit("",0,"",new Utilisateur());
		myProd.setId(id);
		return myDao.delete(myProd);
	}
	public List<Produit> findProduitsByUtilisateur(int idutilisateur) {
		
		return myDao.findByUtilisateur( myUtDao.findById(idutilisateur) );
	}

	public void setMyDao(ProduitDao produitDAO){
			this.myDao = produitDAO;
	}
	public void setMyUtDao(UtilisateurDao utilisateurDAO){
			this.myUtDao = utilisateurDAO;
	}

}
