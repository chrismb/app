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
	private static int lastID;
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
			myOfDao.findById(retOffre.getID()).getMontant()
			,0);
		assertEquals(
			testoffre.getAcheteur().getID(),
			myOfDao.findById(retOffre.getID()).getAcheteur().getID()
			);
		assertEquals(
			testoffre.getProduit().getID(),
			myOfDao.findById(retOffre.getID()).getProduit().getID()
			);

	}
/*
	@Test
	public void findByIdTest() throws SQLException, Exception{
		Offre testut = new Offre("test8","test@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByNameAndMail("test8","test@test.ts"));
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Offre WHERE NOM='test8'");
		int IDtest = 0;
		while (rslt.next()){
			//On recupere l'ID' du dernier Offre ajoute 
			IDtest = rslt.getInt("U_ID");
		}
		assertEquals(myDao.findById(IDtest).getMail(), testut.getMail());

		rslt.close();
		stmt.close();
	}

	@Test
	public void findAllTest() throws SQLException, Exception{
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Offre");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'Offres
			nbrTest ++;
		}
		assertEquals(myDao.findAll().size(), nbrTest);
		rslt.close();
		stmt.close();
	}

	@Test
	public void findByNameTest() throws SQLException, Exception{
		Offre testut1 = new Offre("test","test1@test.ts");	
		Offre testut2 = new Offre("test","test2@test.ts");
		myDao.create(testut1);	
		myDao.create(testut2);
		listeUtil.add(myDao.findByMail("test1@test.ts"));
		listeUtil.add(myDao.findByMail("test2@test.ts"));
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Offre WHERE NOM='test'");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'Offres dont le nom est test
			nbrTest ++;
		}
		assertEquals(myDao.findByName("test").size(), nbrTest);
		rslt.close();
		stmt.close();
	}

	@Test
	public void findByMailTest() throws SQLException, Exception{
		Offre testut = new Offre("test3","test@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByNameAndMail("test3","test@test.ts"));	
		assertEquals(myDao.findByMail("test@test.ts").getMail(), testut.getMail());

	}


		@Test
	public void findByNameAndMailTest() throws SQLException, Exception{
		Offre testut = new Offre("test4","test@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByNameAndMail("test4","test@test.ts"));	
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Offre WHERE NOM='test4' AND MAIL='test@test.ts'");

		
		rslt.next();
		
		
		assertNotNull(myDao.findByNameAndMail("test4","test@test.ts"));
		assertEquals(myDao.findByNameAndMail("test4","test@test.ts").getID(), rslt.getInt("U_ID"));
		rslt.close();
		stmt.close();
		
	}

	@Test
	public void deleteTest() throws SQLException, Exception{
		// Add user
		Offre testut = new Offre("test5","test5@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByMail("test@test.ts"));
		// Retrieve Offre
		Offre testut2 = myDao.findByMail("test5@test.ts");
		// Delete it

		myDao.delete(testut2);
		// Test
		assertEquals(myDao.findById(testut2.getID()), null);


	}
*/
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
