package fr.adhoc.leboncoin.dao;

import java.util.List;

import fr.adhoc.leboncoin.model.Utilisateur;

public interface UtilisateurDao {
	boolean create(Utilisateur utilisateur);
	Utilisateur findById(int U_ID);
	Utilisateur findByName(String nom);
	Utilisateur findByMail(String mail);
	List<Utilisateur> findAll();
}