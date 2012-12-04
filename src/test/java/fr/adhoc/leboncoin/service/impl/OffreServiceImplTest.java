package fr.adhoc.leboncoin.service.impl;

import org.junit.*;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import java.io.*;

import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.model.Offre;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.service.impl.UtilisateurServiceImpl;
import fr.adhoc.leboncoin.service.UtilisateurService;
import fr.adhoc.leboncoin.service.impl.ProduitServiceImpl;
import fr.adhoc.leboncoin.service.ProduitService;
import fr.adhoc.leboncoin.service.impl.OffreServiceImpl;
import fr.adhoc.leboncoin.service.OffreService;

import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.OffreDaoImpl;
import fr.adhoc.leboncoin.dao.OffreDao;
import fr.adhoc.leboncoin.dao.impl.ProduitDaoImpl;
import fr.adhoc.leboncoin.dao.ProduitDao;


public class OffreServiceImplTest {
	private static DbUtils myDbUtils;
	private static UtilisateurDao myUtDao;
	private static OffreDao myOfDao;
	private static ProduitDao myPrDao;
	private static ProduitService myPrService;
	private static UtilisateurService myUtService;
	private static OffreService myOfService;
	private static List<Offre> listeOffres;
	private static List<Produit> listeProduits; 
	private static List<Utilisateur> listeUtilisateurs;

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myUtService = new UtilisateurServiceImpl();
		myUtDao = new UtilisateurDaoImpl();
		listeUtilisateurs = new ArrayList<Utilisateur>();

		myPrService = new ProduitServiceImpl();
		myPrDao = new ProduitDaoImpl();
		listeProduits = new ArrayList<Produit>();

		myOfService = new OffreServiceImpl();
		myOfDao = new OffreDaoImpl();
		listeOffres = new ArrayList<Offre>();
	}

	@Test
	public void createOffreTest() throws SQLException, Exception{
		Utilisateur vendeur = myUtService.createUtilisateur("vendeurCreateOffreTest","mailvendeur@CreateOffreTest.ts");
		listeUtilisateurs.add( vendeur );

		Produit article = myPrService.createProduit("produitCreateOffreTest",10,"description test createOffretest", vendeur);
		listeProduits.add( article );

		Utilisateur acheteur = myUtService.createUtilisateur("acheteurCreateOffreTest","mailacheteur@CreateOffreTest.ts");
		listeUtilisateurs.add( acheteur );

		Offre offre = myOfService.createOffre((double)10, acheteur, article);
		listeOffres.add( offre );

		assertEquals( myOfDao.findById( offre.getID() ).getAcheteur().getID(),
					offre.getAcheteur().getID() );
	
	}
/*
	@Test
	public void findAllOffresTest() throws SQLException, Exception{

		assertEquals(myService.findAllUtilisateurs().size(), myDao.findAll().size());
	}


	@Test
	public void deleteOffreTest() throws SQLException, Exception{
		// Add user
		myService.createUtilisateur("test9","test9@test.ts");	
		// Retrieve Utilisateur
		Utilisateur testut2 = myService.findUtilisateurByNameAndMail("test9","test9@test.ts");
		// Delete it

		myService.deleteUtilisateur(testut2.getID());
		// Test
		assertEquals(myService.findUtilisateurById(testut2.getID()), null);


	}


	@Test
	public void findOffreByIdTest() throws SQLException, Exception{
		myService.createUtilisateur("test8","test8@test.ts");	
		listeUtil.add(myService.findUtilisateurByNameAndMail("test8","test8@test.ts"));
		int IDtest = myService.findUtilisateurByNameAndMail("test8","test8@test.ts").getID();
		assertEquals(myService.findUtilisateurById(IDtest).getMail(), myDao.findById(IDtest).getMail());

	}
*/
	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		for(Offre of : listeOffres) {
			myOfDao.delete(of);
		}
		for(Produit pr : listeProduits) {
			myPrDao.delete(pr);
		}
		for(Utilisateur ut : listeUtilisateurs) {
			myUtDao.delete(ut);
		}



		 
	}

}
