package fr.adhoc.leboncoin.app;

import fr.adhoc.leboncoin.model.Utilisateur;

public interface UtilisateurDao {
	Utilisateur create();
	Utilisateur findById(int U_ID);
}