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
/*

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

		assertEquals( myOfDao.findById( offre.getId() ).getAcheteur().getId(),
					offre.getAcheteur().getId() );


		//test montant negatif
		assertEquals( myOfService.createOffre((double) -3, acheteur, article) , null);
		//test L'acheteur ne peux pas etre le proprietaire du produit
		assertEquals( myOfService.createOffre((double) 10, vendeur, article) , null);
		//test L'acheteur est bien dans la base de données
		Utilisateur utinconnu = new Utilisateur("inconnu", "inconnu@test.ts");
		assertEquals( myOfService.createOffre((double) 10, utinconnu, article) , null);
		// testLe produit est bien dans la base de données
		Produit princonnu = new Produit("produitinconnu",10,"description produit inconnu",vendeur);
		assertEquals( myOfService.createOffre((double) 10, acheteur, princonnu) , null);




	
	}

	@Test
	public void findAllOffresTest() throws SQLException, Exception{

		assertEquals(myOfService.findAllOffres().size(), myOfDao.findAll().size());
	}


	@Test
	public void deleteOffreTest() throws SQLException, Exception{
		Utilisateur vendeur = myUtService.createUtilisateur("vendeurDeleteOffreTest","mailvendeur@DeleteOffreTest.ts");
		listeUtilisateurs.add( vendeur );

		Produit article = myPrService.createProduit("produitDeleteOffreTest",10,"description test deleteOffretest", vendeur);
		listeProduits.add( article );

		Utilisateur acheteur = myUtService.createUtilisateur("acheteurDeleteOffreTest","mailacheteur@DeleteOffreTest.ts");
		listeUtilisateurs.add( acheteur );

		Offre offre = myOfService.createOffre((double)10, acheteur, article);
		listeOffres.add( offre );
		// Delete it

		myOfService.deleteOffre(offre.getId());
		// Test
		assertEquals(myOfService.findOffreById(offre.getId()), null);


	}


	@Test
	public void findOffreByIdTest() throws SQLException, Exception{
		myService.createUtilisateur("test8","test8@test.ts");	
		listeUtil.add(myService.findUtilisateurByNameAndMail("test8","test8@test.ts"));
		int IDtest = myService.findUtilisateurByNameAndMail("test8","test8@test.ts").getID();
		assertEquals(myService.findUtilisateurById(IDtest).getMail(), myDao.findById(IDtest).getMail());

	}

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
*/

}
