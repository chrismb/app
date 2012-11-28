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
import fr.adhoc.leboncoin.model.Produit;



public class ProduitDaoImpl implements ProduitDao {
	private DbUtils myDbUtils;
	
	
	public ProduitDaoImpl() throws SQLException, Exception {
		 this.myDbUtils = new DbUtils();
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
        								produit.getVendeur() + ")";
        stmt.execute(str);
        return produit;
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
        myProduit.setID(P_ID);
			while  (rslt.next()){
				myProduit.setNom(rslt.getString("NOM"));
				myProduit.setPrixDepart(rslt.getDouble("PRIXDEPART"));
				myProduit.setDescription(rslt.getString("DESCRIPTION"));
				myProduit.setVendeur( myVendeur.findById( rslt.getInt("V_ID") ) );
				
			}
			return myProduit;
		} catch (SQLException e) {
				System.out.println(e);
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
				Produit ut = new Produit();
				ut.setID(rslt.getInt("U_ID"));
				ut.setNom(rslt.getString("NOM"));
				ut.setPrixDepart(rslt.getDouble("PRIXDEPART"));
				ut.setDescription(rslt.getString("DESCRIPTION"));
				liste.add(ut);
			}
		}catch(SQLException e){
			System.out.println(e);	
		}
		return liste;
	}

}