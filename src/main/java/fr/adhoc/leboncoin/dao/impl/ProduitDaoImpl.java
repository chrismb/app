package fr.adhoc.leboncoin.dao.impl;

import java.sql.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.ProduitDao;
import fr.adhoc.leboncoin.dao.impl.ProduitDaoImpl;
import fr.adhoc.leboncoin.model.Produit;



public class ProduitDaoImpl implements ProduitDao {
	private DbUtils myDbUtils;
	
	
	public ProduitDaoImpl() throws SQLException, Exception {
		 
		// TODO Auto-generated constructor stub
	}

	@Override
	public Produit create(Produit produit){

        try{		
		//Stockage dans la basede donnees
        Statement stmt = myDbUtils.getStatement();


        String str = "INSERT INTO Produit (NOM,PRIXDEPART,DESCRIPTION,V_ID) VALUES ('" + 
        								produit.getNom() + "'," + 
        								produit.getPrixDepart() + ",'" + 
        								produit.getDescription() + "'," +
        								produit.getVendeur().getId() + ")";
        stmt.execute(str);
        ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit WHERE DESCRIPTION='"+ produit.getDescription() + "' AND V_ID=" + produit.getVendeur().getId() );
        	if (rslt.next()){
        		produit.setId(rslt.getInt("P_ID"));
        		rslt.close();
				stmt.close();
        		return produit;
	        }else{
	        	rslt.close();
				stmt.close();
	        	return null;
	        }
        }
        catch (SQLException e) {
        	System.out.println(e);
        	return null;
        }
		
		
	}

	@Override
	public Produit findById(int P_ID) {
	        try {	
		//Recherche dans la basede donnees
        Statement stmt = myDbUtils.getStatement();
        
        ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit WHERE P_ID="+ P_ID );
        
        //Instantiation d'un produit
        
        //Instantiation du vendeur

        UtilisateurDao myVendeur = new UtilisateurDaoImpl();

        Produit myProduit = new Produit();
        myProduit.setId(P_ID);
			while  (rslt.next()){
				myProduit.setNom(rslt.getString("NOM"));
				myProduit.setPrixDepart(rslt.getDouble("PRIXDEPART"));
				myProduit.setDescription(rslt.getString("DESCRIPTION"));
				myProduit.setVendeur( myVendeur.findById( rslt.getInt("V_ID") ) );
				
			}
			rslt.close();
			stmt.close();
			return myProduit;
		} catch (SQLException e) {
				System.out.println(e);
				return null;

		} catch (Exception e) {
				System.out.println(e);
				return null;
		}
        
	}


	public List<Produit> findByName(String nom) {
	    List<Produit> liste = new ArrayList<Produit>();
	     try {	
		//Recherche dans la basede donnees
        Statement stmt = myDbUtils.getStatement();
        
        ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit WHERE NOM='"+ nom +"'");
        
        //Instantiation d'un produit
        
			while  (rslt.next()){
				Produit prod = new Produit();
				UtilisateurDao myVendeur = new UtilisateurDaoImpl();
				prod.setId(rslt.getInt("P_ID"));
				prod.setNom(rslt.getString("NOM"));
				prod.setPrixDepart(rslt.getDouble("PRIXDEPART"));
				prod.setDescription(rslt.getString("DESCRIPTION"));
				prod.setVendeur( myVendeur.findById( rslt.getInt("V_ID") ));
				liste.add(prod);
			}
			rslt.close();
			stmt.close();
			return liste;
		} catch (SQLException e) {

			System.out.println("Produit " + nom + " inconnu.");
				return null;
		} catch (Exception e) {
				System.out.println(e);
				return null;
		}
	}


	public List<Produit> findAll(){
		List<Produit> liste = new ArrayList<Produit>();
		try{
			Statement stmt = myDbUtils.getStatement();
			ResultSet rslt = stmt.executeQuery("SELECT * FROM PRODUIT");
			while(rslt.next()){
				Produit prod = new Produit();
				UtilisateurDao myVendeur = new UtilisateurDaoImpl();
				prod.setId(rslt.getInt("P_ID"));
				prod.setNom(rslt.getString("NOM"));
				prod.setPrixDepart(rslt.getDouble("PRIXDEPART"));
				prod.setDescription(rslt.getString("DESCRIPTION"));
				prod.setVendeur( myVendeur.findById( rslt.getInt("V_ID") ));
				liste.add(prod);
			}
			rslt.close();
			stmt.close();
		}catch(SQLException e){
			System.out.println(e);	
			return null;
		} catch (Exception e) {
				System.out.println(e);
				return null;
		}
		return liste;
	}

	public boolean delete(Produit produit){
		try{
			String str = "DELETE FROM Produit WHERE P_ID=" + produit.getId();
			Statement stmt = myDbUtils.getStatement();
       		stmt.execute(str);
       		stmt.close();
        	return true;
        } catch (SQLException e) {
			System.out.println("Produit n°" + produit.getId() + " non efface.");
				return false;
		}
	}

	public List<Produit> findByUtilisateur(Utilisateur utilisateur){
		List<Produit> listeproduits = new ArrayList<Produit>();
		ProduitDao myDao = null;
		try{
		 myDao = new ProduitDaoImpl();
		}
		catch (Exception e) {
        	System.out.println(e);
        	return null;
        }
   		for(Produit prod : myDao.findAll() ){

	        if( prod.getVendeur().getId() == utilisateur.getId() ){
	            listeproduits.add(prod);
	        }
    	}
    	return listeproduits;
	}
	public void setMyDbUtils(DbUtils dbUtils){
			this.myDbUtils = dbUtils;
	}


}
