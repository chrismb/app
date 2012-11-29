package fr.adhoc.leboncoin.dao.impl;

import java.sql.*;
import java.util.List;

import java.util.ArrayList;


import fr.adhoc.leboncoin.model.Offre;
import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.OffreDao;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.ProduitDao;




public class OffreDaoImpl implements OffreDao {
	private DbUtils myDbUtils;
	
	
	public OffreDaoImpl() throws SQLException, Exception {
		 this.myDbUtils = new DbUtils();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Offre offre){
		
        try{		
		//Stockage dans la basede donnees
        Statement stmt = myDbUtils.getStatement();


        String str = "INSERT INTO Offre (MONTANT,DATE,STATUT,A_ID,P_ID) VALUES (" + 
        								offre.getMontant() + ",'" + 
        								offre.getDate() + "','" + 
        								offre.getStatut() + "'," +
        								offre.getAcheteur().getID() + "," +
        								offre.getProduit().getID() + ")";

        stmt.execute(str);
        return true;
        }
        catch (SQLException e) {
        	System.out.println(e);
        	return false;
        }
	}

	

	public List<Offre> findAll(){
		List<Offre> liste = new ArrayList<Offre>();
		try{
			Statement stmt = myDbUtils.getStatement();
			ResultSet rslt = stmt.executeQuery("SELECT * FROM OFFRE");
			while(rslt.next()){
				Offre of = new Offre();
				UtilisateurDao acht = new UtilisateurDaoImpl();
				ProduitDao prod = new ProduitDaoImpl();
				of.setID(rslt.getInt("O_ID"));
				of.setMontant(rslt.getDouble("MONTANT"));
				of.setDate(rslt.getDate("DATE"));
				of.setStatut(rslt.getString("STATUT"));
				of.setAcheteur( acht.findById( rslt.getInt("A_ID") ) );
				of.setProduit( prod.findById( rslt.getInt("P_ID") ) );

				liste.add(of);
			}
		}catch(SQLException e){
			System.out.println(e);
			return null;	
		}catch (Exception e) {
        	System.out.println(e);
        	return null;
        }
		return liste;
	}

}
