package fr.adhoc.leboncoin.service;

import java.util.List;

import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.model.Utilisateur;

public interface ProduitService {
	Produit createProduit(String nom, double prixDepart, String description, Utilisateur vendeur);

}