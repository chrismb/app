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
	private static List<Utilisateur> listeUtil;


	//TODO Ajouter une liste pour garder la trace des utilisateurs ajoutes pendant les tests
	

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		myDao = new UtilisateurDaoImpl();
		listeUtil = new ArrayList<Utilisateur>();

	}

	
	@Test
	public void createTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test7","test@test.ts");
		Utilisateur retUt = myDao.create(testut);
		listeUtil.add(myDao.findByNameAndMail("test7","test@test.ts"));
		// Test si l'utilisateur a bien ete ajoute a la bd (par findbyID) et si c'est le bon (comparaison mail et nom)
		assertEquals(
			testut.getNom(),
			myDao.findById(retUt.getID()).getNom()
			);
		assertEquals(
			testut.getMail(),
			myDao.findById(retUt.getID()).getMail()
			);

	}

	@Test
	public void findByIdTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test8","test@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByNameAndMail("test8","test@test.ts"));
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test8'");
		int IDtest = 0;
		while (rslt.next()){
			//On recupere l'ID' du dernier utilisateur ajoute 
			IDtest = rslt.getInt("U_ID");
		}
		assertEquals(myDao.findById(IDtest).getMail(), testut.getMail());

		rslt.close();
		stmt.close();
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
		rslt.close();
		stmt.close();
	}

	@Test
	public void findByNameTest() throws SQLException, Exception{
		Utilisateur testut1 = new Utilisateur("test","test1@test.ts");	
		Utilisateur testut2 = new Utilisateur("test","test2@test.ts");
		myDao.create(testut1);	
		myDao.create(testut2);
		listeUtil.add(myDao.findByMail("test1@test.ts"));
		listeUtil.add(myDao.findByMail("test2@test.ts"));
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test'");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'utilisateurs dont le nom est test
			nbrTest ++;
		}
		assertEquals(myDao.findByName("test").size(), nbrTest);
		rslt.close();
		stmt.close();
	}

	@Test
	public void findByMailTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test3","test@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByNameAndMail("test3","test@test.ts"));	
		//Statement stmt = myDbUtils.getStatement();
		//ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE MAIL='test@test.ts'");
		assertEquals(myDao.findByMail("test@test.ts").getMail(), testut.getMail());
		//rslt.close();
		//stmt.close();
	}


		@Test
	public void findByNameAndMailTest() throws SQLException, Exception{
		Utilisateur testut = new Utilisateur("test4","test@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByNameAndMail("test4","test@test.ts"));	
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test4' AND MAIL='test@test.ts'");

		
		rslt.next();
		
		
		assertNotNull(myDao.findByNameAndMail("test4","test@test.ts"));
		assertEquals(myDao.findByNameAndMail("test4","test@test.ts").getID(), rslt.getInt("U_ID"));
		rslt.close();
		stmt.close();
		
	}

	@Test
	public void deleteTest() throws SQLException, Exception{
		// Add user
		Utilisateur testut = new Utilisateur("test5","test5@test.ts");	
		myDao.create(testut);
		listeUtil.add(myDao.findByMail("test@test.ts"));
		// Retrieve Utilisateur
		Utilisateur testut2 = myDao.findByMail("test5@test.ts");
		// Delete it

		myDao.delete(testut2);
		// Test
		assertEquals(myDao.findById(testut2.getID()), null);


	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		for(Utilisateur ut : listeUtil) {
			myDao.delete(ut);
		}

		myDbUtils.getConnection().close();

		 
	}

}
