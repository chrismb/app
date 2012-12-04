package fr.adhoc.leboncoin.dao.impl;
import org.junit.*;

import static org.junit.Assert.*;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.ProduitDao;
import fr.adhoc.leboncoin.dao.impl.ProduitDaoImpl;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;


import fr.adhoc.leboncoin.service.UtilisateurService;
import fr.adhoc.leboncoin.service.impl.UtilisateurServiceImpl;



public class ProduitDaoImplTest{
	private static DbUtils myDbUtils;
	private static int lastIDUt;
	private static int lastIDProd;
	private static ProduitDao myDao;
	private static Utilisateur testUt;
	

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		myDao = new ProduitDaoImpl();
		//ajout d'un utilisateur pour les tests
		testUt = new Utilisateur("test","test@test.ts");
		Statement stmt = myDbUtils.getStatement();

		ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit");
		while  (rslt.next()){
			lastIDProd = rslt.getInt("P_ID");
		}

		rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		while  (rslt.next()){
			lastIDUt = rslt.getInt("U_ID");
		}
		UtilisateurDao myUtilisateurDao = new UtilisateurDaoImpl();
		myUtilisateurDao.create(testUt);
		testUt = myUtilisateurDao.findByMail(testUt.getMail());
		rslt.close();
		stmt.close();
	}

	
	@Test
	public void createTest() throws SQLException, Exception{
		
		Produit testProd = new Produit("Ptest",10 ,"This product is a test", testUt);
		myDao.create(testProd);
		Statement stmt = myDbUtils.getStatement();

		ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit WHERE NOM='Ptest'");
		String descriptionTest = "";
		while  (rslt.next()){
			//On recupere la description du dernier produit ajoute (normalement "This product is a test")
			descriptionTest = rslt.getString("DESCRIPTION");
		}

		assertEquals("This product is a test", descriptionTest);
		rslt.close();
		stmt.close();
	}

@Test
	public void findByIdTest() throws SQLException, Exception{
		Produit testProd = new Produit("Ptest",10 ,"This product is a test", testUt);	
		myDao.create(testProd);

		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit WHERE NOM='Ptest'");
		int IDtest = 0;
		while  (rslt.next()){
			//On recupere l'ID' du dernier utilisateur ajoute 
			IDtest = rslt.getInt("P_ID");
		}
		assertEquals(myDao.findById(IDtest).getDescription(), testProd.getDescription());
		rslt.close();
		stmt.close();
	}

	@Test
	public void findAll() throws SQLException, Exception{
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'utilisateurs
			nbrTest ++;
		}
		assertEquals(myDao.findAll().size(), nbrTest);
		rslt.close();
		stmt.close();
	}

	@Test
	public void findByName() throws SQLException, Exception{
		Produit testProd1 = new Produit("Ptest",10 ,"This product is a test1", testUt);	
		Produit testProd2 = new Produit("Ptest",10 ,"This product is a test2", testUt);
		myDao.create(testProd1);	
		myDao.create(testProd2);
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit WHERE NOM='Ptest'");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'utilisateurs dont le nom est test
			nbrTest ++;
		}
		assertEquals(myDao.findByName("Ptest").size(), nbrTest);
		rslt.close();
		stmt.close();
	}


	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		String str = "DELETE FROM Produit WHERE P_ID>" + lastIDProd;
		Statement stmt = myDbUtils.getStatement();
        stmt.execute(str);
        str = "DELETE FROM Utilisateur WHERE U_ID>" + lastIDUt;
        stmt.execute(str);
        stmt.close();
		myDbUtils.getConnection().close();
	}

}
