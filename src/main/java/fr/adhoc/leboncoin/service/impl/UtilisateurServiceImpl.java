package fr.adhoc.leboncoin.service.impl;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;
import fr.adhoc.leboncoin.service.UtilisateurService;




public class UtilisateurServiceImpl implements UtilisateurService {
	private UtilisateurDao utilisateurDaoUtilisateurService;

	@Override
	public Utilisateur createUtilisateur(String nom, String mail){
		
		//Verification de l'unicite du mail
		
		if (utilisateurDaoUtilisateurService.findByMail(mail) != null){
			System.out.println("Mail deja utilise ! ");
			return null;
		}else{
			//Creation de l'objet utilisateur
			Utilisateur myUt = new Utilisateur(nom,mail);
			
			//Stockage de myUt dans la base de donnees
			utilisateurDaoUtilisateurService.create(myUt);
			//On le retire de la base de donnees pour avoi rsonID mis a jour.
			myUt = utilisateurDaoUtilisateurService.findByMail(myUt.getMail());
	     
	     return myUt;
	        
	    }		
	}

	public List<Utilisateur> findAllUtilisateurs(){
		return utilisateurDaoUtilisateurService.findAll();
	}

	public List<Utilisateur> findUtilisateurByName(String nom){
		return utilisateurDaoUtilisateurService.findByName(nom);
	}
	public Utilisateur findUtilisateurById(int id){

		return utilisateurDaoUtilisateurService.findById(id);
	}
	public Utilisateur findUtilisateurByNameAndMail(String nom, String mail){
		return utilisateurDaoUtilisateurService.findByNameAndMail(nom,mail);
	}
	public boolean deleteUtilisateur(int id){
		return utilisateurDaoUtilisateurService.delete(new Utilisateur(id));
	}

	public void setUtilisateurDaoUtilisateurService(UtilisateurDao utilisateurDAO){
			this.utilisateurDaoUtilisateurService = utilisateurDAO;
	}

}
