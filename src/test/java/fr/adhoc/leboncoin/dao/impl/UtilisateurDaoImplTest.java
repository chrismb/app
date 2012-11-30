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
	private static UtilisateurDao myDao;
	

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		myDao = new UtilisateurDaoImpl();
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		while  (rslt.next()){
			lastID = rslt.getInt("U_ID");
		}
	}

	
	@Test
	public void createTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test","test@test.ts");
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
		myDao.create(testut);

		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test'");
		int IDtest = 0;
		while  (rslt.next()){
			//On recupere l'ID' du dernier utilisateur ajoute 
			IDtest = rslt.getInt("U_ID");
		}
		assertEquals(myDao.findById(IDtest).getMail(), testut.getMail());
	}

	@Test
	public void findAllTest() throws SQLException, Exception{
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'utilisateurs
			nbrTest ++;
		}
		assertEquals(myDao.findAll().size(), nbrTest);
	}

	@Test
	public void findByNameTest() throws SQLException, Exception{
		Utilisateur testut1 = new Utilisateur("test","test1@test.ts");	
		Utilisateur testut2 = new Utilisateur("test","test2@test.ts");
		myDao.create(testut1);	
		myDao.create(testut2);
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test'");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'utilisateurs dont le nom est test
			nbrTest ++;
		}
		assertEquals(myDao.findByName("test").size(), nbrTest);
	}

	@Test
	public void findByMailTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test","test@test.ts");	
		myDao.create(testut);	
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE MAIL='test@test.ts'");
		assertEquals(myDao.findByMail("test@test.ts").getMail(), testut.getMail());
	}

	@Test
	public void deleteTest() throws SQLException, Exception{
		// Add user
		Utilisateur testut = new Utilisateur("test","test@test.ts");	
		myDao.create(testut);
		// Retrieve ID
		int myID = myDao.findByMail("test@test.ts").getID();
		// Delete it

		myDao.delete(myID);
		// Test
		assertEquals(myDao.findById(myID), null);
	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		String str = "DELETE FROM Utilisateur WHERE U_ID>" + lastID;
		Statement stmt = myDbUtils.getStatement();
        stmt.execute(str);
		myDbUtils.getConnection().close();
	}

}
