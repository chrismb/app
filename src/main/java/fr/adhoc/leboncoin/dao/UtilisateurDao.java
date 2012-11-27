package fr.adhoc.leboncoin.dao;

import java.util.List;

import fr.adhoc.leboncoin.model.Utilisateur;

public interface UtilisateurDao {
	Utilisateur create(String nom, String mail);
	Utilisateur findById(int U_ID);
	List<Utilisateur> findAll();
}