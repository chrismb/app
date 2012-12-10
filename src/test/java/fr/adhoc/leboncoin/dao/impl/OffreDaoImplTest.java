package fr.adhoc.leboncoin.dao.impl;
import org.junit.*;

import static org.junit.Assert.*;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.model.Offre;
import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.OffreDao;
import fr.adhoc.leboncoin.dao.impl.OffreDaoImpl;

import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;

import fr.adhoc.leboncoin.dao.ProduitDao;
import fr.adhoc.leboncoin.dao.impl.ProduitDaoImpl;




public class OffreDaoImplTest{
	private static DbUtils myDbUtils;
	private static UtilisateurDao myUtDao;
	private static OffreDao myOfDao;
	private static ProduitDao myPrDao;
	private static List<Offre> listeOffres;
	private static List<Produit> listeProduits; 
	private static List<Utilisateur> listeUtilisateurs;

	//TODO Ajouter une liste pour garder la trace des Offres ajoutes pendant les tests
	

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		myUtDao = new UtilisateurDaoImpl();
		myOfDao = new OffreDaoImpl();
		myPrDao = new ProduitDaoImpl();
		listeOffres = new ArrayList<Offre>();
		listeUtilisateurs = new ArrayList<Utilisateur>();
		listeProduits = new ArrayList<Produit>();


	}

	
	@Test
	public void createTest() throws SQLException, Exception{
		//Creation d'un vendeur
		Utilisateur testutv = new Utilisateur("test1v","test1v@test.ts");
		testutv = myUtDao.create(testutv);
		listeUtilisateurs.add(testutv);
		//creation d'un acheteur
		Utilisateur testuta = new Utilisateur("test1a","test1a@test.ts");
		testuta = myUtDao.create(testuta);
		listeUtilisateurs.add(testuta);
		//Creation d'un produit
		Produit testprod = new Produit("test1",10,"this is a test",testutv);
		testprod = myPrDao.create(testprod);
		listeProduits.add(testprod);
		//creation d'une offre
		Offre testoffre = new Offre(11,testuta,testprod);
		Offre retOffre = myOfDao.create(testoffre);
		listeOffres.add(retOffre);

		// Test si l'Offre a bien ete ajoute a la bd (par findbyID) et si c'est la bonne (comparaison acheteur, produit et montant)
		assertEquals(
			testoffre.getMontant(),
			myOfDao.findById(retOffre.getId()).getMontant()
			,0);
		assertEquals(
			testoffre.getAcheteur().getId(),
			myOfDao.findById(retOffre.getId()).getAcheteur().getId()
			);
		assertEquals(
			testoffre.getProduit().getId(),
			myOfDao.findById(retOffre.getId()).getProduit().getId()
			);

	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		for(Offre of : listeOffres) {
			myOfDao.delete(of);
		}
		for(Produit prod : listeProduits) {
			myPrDao.delete(prod);
		}
		for(Utilisateur ut : listeUtilisateurs) {
			myUtDao.delete(ut);
		}

		myDbUtils.getConnection().close();

		 
	}

}
