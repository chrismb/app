package fr.adhoc.leboncoin.app;

import fr.adhoc.leboncoin.model.Utilisateur;

public interface UtilisateurDao {
	Utilisateur create(String nom, String mail);
	Utilisateur findById(int U_ID);
}