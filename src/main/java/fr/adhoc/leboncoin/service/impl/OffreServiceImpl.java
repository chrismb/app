package fr.adhoc.leboncoin.service.impl;

import java.sql.*;


import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.model.Offre;
import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.ProduitDao;
import fr.adhoc.leboncoin.dao.impl.ProduitDaoImpl;
import fr.adhoc.leboncoin.dao.OffreDao;
import fr.adhoc.leboncoin.dao.impl.OffreDaoImpl;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;
import fr.adhoc.leboncoin.service.OffreService;

import java.util.List;
import java.util.ArrayList;




public class OffreServiceImpl implements OffreService {
	private OffreDao offreDaoOffreService;
	private UtilisateurDao utilisateurDaoOffreService;
	private ProduitDao produitDaoOffreService;

	@Override
	public Offre createOffre(Double montant, Utilisateur acheteur,Produit produit){

		//Business rules
		/*
		*	montant>0 montant peut etre inferieur au prix de depart

		*	L'acheteur ne peux pas etre le proprietaire du produit
		*	L'acheteur est bien dans la base de données
		*	Le produit est bien dans la base de données
		*/
		boolean busrulesOK = true;


		//	montant>0 ,montant peut etre inferieur au prix de depart
			if(montant <= 0){
				busrulesOK = false;
				System.out.println("Montant invalide");
			}
		//	L'acheteur ne peux pas etre le proprietaire du produit
			if(acheteur.getId() == produit.getVendeur().getId()){
				busrulesOK = false;
				System.out.println("Proprietaire = Acheteur");
			}
		//	L'acheteur est bien dans la base de données
			 
			if(acheteur.getId() == 0 || utilisateurDaoOffreService.findById(acheteur.getId()) == null){
				busrulesOK = false;
				System.out.println("Acheteur inconnu");
			}
		//	Le produit est bien dans la base de données
			
			if(produit.getId() == 0 || produitDaoOffreService.findById(produit.getId()) == null){
				busrulesOK = false;
				System.out.println("Produit inconnu");
			}


		if (busrulesOK){
			//Creation de l'objet Offre
			Offre myOffre = new Offre(montant,acheteur,produit);
			
			//Stockage de myUt dans la base de donnees
			offreDaoOffreService.create(myOffre);
	     
	     	return myOffre;
	     }
	     else{
	     	System.out.println("Probleme rencontre");
	     	return null;
	     }	
	}

	public List<Offre> findAllOffres(){
		return offreDaoOffreService.findAll();
	}

	public boolean deleteOffre(int id){
		return offreDaoOffreService.delete(new Offre(id));
	}

	public Offre findOffreById(int id){
		return offreDaoOffreService.findById(id);
	}

	public void setOffreDaoOffreService(OffreDao offreDAO){
			this.offreDaoOffreService = offreDAO;
	}

	public void setUtilisateurDaoOffreService(UtilisateurDao utilisateurDAO){
			this.utilisateurDaoOffreService = utilisateurDAO;
	}
	public void setProduitDaoOffreService(ProduitDao produitDAO){
			this.produitDaoOffreService = produitDAO;
	}

}
