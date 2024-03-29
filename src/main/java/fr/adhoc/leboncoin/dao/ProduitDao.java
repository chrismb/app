package fr.adhoc.leboncoin.dao;

import java.util.List;

import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;

public interface ProduitDao {
	Produit create(Produit produit);
	Produit findById(int U_ID);
	List<Produit> findByName(String nom);
	List<Produit> findAll();
	boolean delete(Produit produit);
	List<Produit> findByUtilisateur(Utilisateur utilisateur);
}