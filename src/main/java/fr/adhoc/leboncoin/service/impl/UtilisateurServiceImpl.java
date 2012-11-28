package fr.adhoc.leboncoin.dao.impl;

import java.sql.*;
import java.util.List;

import java.util.ArrayList;


import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.UtilisateurDao;




public class UtilisateurDaoImpl implements UtilisateurDao {
	private DbUtils myDbUtils;
	
	
	public UtilisateurDaoImpl() throws SQLException, Exception {
		 this.myDbUtils = new DbUtils();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Utilisateur create(Utilisateur utilisateur){
		
        try{		
		//Stockage dans la basede donnees
        Statement stmt = myDbUtils.getStatement();


        String str = "INSERT INTO Utilisateur (NOM,MAIL,NOTE) VALUES ('" + 
        								utilisateur.getNom() + "','" + 
        								utilisateur.getMail() + "'," + 
        								utilisateur.getNote() + ")";
        stmt.execute(str);
        return utilisateur;
        }
        catch (SQLException e) {
        	System.out.println(e);
        	return null;
        }
		
		
	}

	@Override
	public Utilisateur findById(int U_ID) {
	        try {	
		//Recherche dans la basede donnees
        Statement stmt = myDbUtils.getStatement();
        
        ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE U_ID="+ U_ID );
        
        //Instantiation d'un utilisateur
        Utilisateur myUtilisateur = new Utilisateur();
        myUtilisateur.setID(U_ID);
			while  (rslt.next()){
				myUtilisateur.setNom(rslt.getString("NOM"));
				myUtilisateur.setMail(rslt.getString("MAIL"));
				myUtilisateur.setNote( (float) rslt.getString("NOM").charAt( 0 - 'A' +1) );
				
			}
			return myUtilisateur;
		} catch (SQLException e) {
			System.out.println(e);
				return null;
		}
        
	}

	public List<Utilisateur> findAll(){
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		try{
			Statement stmt = myDbUtils.getStatement();
			ResultSet rslt = stmt.executeQuery("SELECT * FROM UTILISATEUR");
			while(rslt.next()){
				Utilisateur ut = new Utilisateur();
				ut.setID(rslt.getInt("U_ID"));
				ut.setNom(rslt.getString("NOM"));
				ut.setMail(rslt.getString("MAIL"));
				ut.setNote(rslt.getFloat("NOTE"));
				liste.add(ut);
			}
		}catch(SQLException e){
			System.out.println(e);	
		}
		return liste;
	}

}
