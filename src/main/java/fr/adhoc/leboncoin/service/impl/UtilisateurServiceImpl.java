package fr.adhoc.leboncoin.service.impl;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;
import fr.adhoc.leboncoin.service.UtilisateurService;




public class UtilisateurServiceImpl implements UtilisateurService {
	private UtilisateurDao myDao;
	
	
	public UtilisateurServiceImpl() throws SQLException, Exception {
		 this.myDao = new UtilisateurDaoImpl();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Utilisateur createUtilisateur(String nom, String mail){
		
		//Verification de l'unicite du mail
		
		if (myDao.findByMail(mail) != null){
			System.out.println("Mail deja utilisé ! ");
			return null;
		}else{
			//Creation de l'objet utilisateur
			Utilisateur myUt = new Utilisateur(nom,mail);
			
			//Stockage de myUt dans la base de donnees
			myDao.create(myUt);
	     
	     return myUt;
	        
	    }
		
		
	}

		public List<Utilisateur> findAllUtilisateurs(){
		return myDao.findAll();
	}



}
