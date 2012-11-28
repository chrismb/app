package fr.adhoc.leboncoin.service.impl;

import java.sql.*;


import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.ProduitDao;
import fr.adhoc.leboncoin.dao.impl.ProduitDaoImpl;
import fr.adhoc.leboncoin.service.ProduitService;
import fr.adhoc.leboncoin.model.Utilisateur;




public class ProduitServiceImpl implements ProduitService {
	private ProduitDao myDao;
	
	public ProduitServiceImpl() throws SQLException, Exception {
		 this.myDao = new ProduitDaoImpl();
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



}
