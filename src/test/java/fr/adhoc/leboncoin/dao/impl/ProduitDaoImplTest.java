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
	}

	
	@Test
	public void createTest() throws SQLException, Exception{
		UtilisateurDao myUtilisateurDao = new UtilisateurDaoImpl();
		Produit testProd = new Produit("Ptest",10 ,"This product is a test", myUtilisateurDao.findByMail(testUt.getMail()));
		myDao.create(testProd);
		Statement stmt = myDbUtils.getStatement();

		ResultSet rslt = stmt.executeQuery("SELECT * FROM Produit WHERE NOM='Ptest'");
		String descriptionTest = "";
		while  (rslt.next()){
			//On recupere la description du dernier produit ajoute (normalement "This product is a test")
			descriptionTest = rslt.getString("DESCRIPTION");
		}

		assertEquals("This product is a test", descriptionTest);
	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		String str = "DELETE FROM Produit WHERE P_ID>" + lastIDProd;
		Statement stmt = myDbUtils.getStatement();
        stmt.execute(str);
        str = "DELETE FROM Utilisateur WHERE U_ID>" + lastIDUt;
        stmt.execute(str);
		myDbUtils.getConnection().close();
	}

}
