package fr.adhoc.leboncoin.dao.impl;

import java.sql.*;
import java.util.List;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

import fr.adhoc.leboncoin.model.Offre;
import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.OffreDao;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.ProduitDao;




public class OffreDaoImpl implements OffreDao {
	private DbUtils myOffreDbUtils;
	private UtilisateurDao utilisateurDaoOffreDao;
	private ProduitDao produitDaoOffreDao;
	

	@Override
	public Offre create(Offre offre){
		
        try{		
		//Stockage dans la basede donnees
        Statement stmt = myOffreDbUtils.getStatement();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        

        String str = "INSERT INTO Offre (MONTANT,DATE,STATUT,A_ID,P_ID) VALUES (" + 
        								offre.getMontant() + ",'" + 
        								ft.format(offre.getDate()) + "','" + 
        								offre.getStatut() + "'," +
        								offre.getAcheteur().getId() + "," +
        								offre.getProduit().getId() + ")";

        stmt.execute(str);
        ResultSet rslt = stmt.executeQuery("SELECT * FROM Offre WHERE MONTANT="+ offre.getMontant() + " AND A_ID=" + offre.getAcheteur().getId() + " AND P_ID=" + offre.getProduit().getId());
        	if (rslt.next()){
        		offre.setId(rslt.getInt("O_ID"));
        		rslt.close();
				stmt.close();
        		return offre;
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

		public Offre findById(int O_ID) {
	        try {	
		//Recherche dans la basede donnees
        Statement stmt = myOffreDbUtils.getStatement();
        
        ResultSet rslt = stmt.executeQuery("SELECT * FROM Offre WHERE O_ID="+ O_ID );
        
        //Instantiation d'une offre
        
        //Instantiation du acheteur


        Offre myOffre = new Offre();
			if  (rslt.next()){
				myOffre.setId(rslt.getInt("O_ID"));
				myOffre.setMontant(rslt.getDouble("MONTANT"));
				myOffre.setDate(rslt.getDate("DATE"));
				myOffre.setStatut(rslt.getString("STATUT"));
				myOffre.setAcheteur( utilisateurDaoOffreDao.findById( rslt.getInt("A_ID") ) );
				myOffre.setProduit( produitDaoOffreDao.findById( rslt.getInt("P_ID") ) );
			rslt.close();
			stmt.close();
			return myOffre;
			}else{
				rslt.close();
				stmt.close();
        		return null;
        	}
			
		} catch (SQLException e) {
				System.out.println(e);
				return null;

		} catch (Exception e) {
				System.out.println(e);
				return null;
		}
        
	}

	public List<Offre> findAll(){
		List<Offre> liste = new ArrayList<Offre>();
		try{
			Statement stmt = myOffreDbUtils.getStatement();
			ResultSet rslt = stmt.executeQuery("SELECT * FROM OFFRE");
			while(rslt.next()){
				Offre of = new Offre();
				of.setId(rslt.getInt("O_ID"));
				of.setMontant(rslt.getDouble("MONTANT"));
				of.setDate(rslt.getDate("DATE"));
				of.setStatut(rslt.getString("STATUT"));
				of.setAcheteur( utilisateurDaoOffreDao.findById( rslt.getInt("A_ID") ) );
				of.setProduit( produitDaoOffreDao.findById( rslt.getInt("P_ID") ) );

				liste.add(of);
			}
			rslt.close();
			stmt.close();
		}catch(SQLException e){
			System.out.println(e);
			return null;	
		}catch (Exception e) {
        	System.out.println(e);
        	return null;
        }
		return liste;
	}

		public boolean delete(Offre offre){
		try{
			String str = "DELETE FROM Offre WHERE O_ID=" + offre.getId();
			Statement stmt = myOffreDbUtils.getStatement();
       		stmt.execute(str);
       		stmt.close();
        	return true;
        } catch (SQLException e) {
			System.out.println("Offre n°" + offre.getId() + " non efface.");
				return false;
		}
	}
	public void setMyOffreDbUtils(DbUtils dbUtils){
			this.myOffreDbUtils = dbUtils;
	}
	public void setUtilisateurDaoOffreDao(UtilisateurDao produitDAO){
			this.utilisateurDaoOffreDao = produitDAO;
	}
	public void setProduitDaoOffreDao(ProduitDao produitDAO){
			this.produitDaoOffreDao = produitDAO;
	}

}
