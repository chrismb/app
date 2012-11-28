package fr.adhoc.leboncoin.service;

import java.util.List;

import fr.adhoc.leboncoin.model.Utilisateur;

public interface UtilisateurDao {
	Utilisateur createUtilisateur(String nom, String mail);
}