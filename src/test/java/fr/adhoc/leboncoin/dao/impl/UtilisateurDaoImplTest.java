package fr.adhoc.leboncoin.dao.impl;
import org.junit.*;

import static org.junit.Assert.*;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;






public class UtilisateurDaoImplTest{
	private static DbUtils myDbUtils;
	private static int lastID;
	

	@BeforeClass public static void runBeforeClass() throws Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		while  (rslt.next()){
			lastID = rslt.getInt("U_ID");
		}
	}

	
	@Test
	public void createTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test","test@test.ts");
		UtilisateurDao myDao = new UtilisateurDaoImpl();
		myDao.create(testut);

		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test'");
		String mailtest = "";
		while  (rslt.next()){
			//On recupere le mail du dernier utilisateur ajoute (normalement "test@test.ts")
			mailtest = rslt.getString("MAIL");
		}
		assertEquals("test@test.ts", mailtest);
	}

	@Test
	public void findByIdTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test","test@test.ts");
		UtilisateurDao myDao = new UtilisateurDaoImpl();
		myDao.create(testut);

		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test'");
		int IDtest = 0;
		while  (rslt.next()){
			//On recupere le mail du dernier utilisateur ajoute (normalement "test@test.ts")
			IDtest = rslt.getInt("U_ID");
		}
		assertEquals(myDao.findById(IDtest).getMail(), testut.getMail());
	}

	@Test
	public void findAllTest() throws SQLException, Exception{
		UtilisateurDao myDao = new UtilisateurDaoImpl();
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le mail du dernier utilisateur ajoute (normalement "test@test.ts")
			nbrTest ++;
		}
		assertEquals(myDao.findAll().size(), nbrTest);
	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		String str = "DELETE FROM Utilisateur WHERE U_ID>" + lastID;
		Statement stmt = myDbUtils.getStatement();
        stmt.execute(str);
		myDbUtils.getConnection().close();
	}

}
