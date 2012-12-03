package fr.adhoc.leboncoin.service.impl;

import org.junit.*;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import java.io.*;

import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.service.impl.UtilisateurServiceImpl;
import fr.adhoc.leboncoin.service.UtilisateurService;

import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;
import fr.adhoc.leboncoin.dao.UtilisateurDao;




public class UtilisateurServiceImplTest {
	private static UtilisateurService myService;
	private static UtilisateurDao myDao;
	private static DbUtils myDbUtils;
	private static int lastID;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private  static List<Utilisateur> listeUtil;

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		myService = new UtilisateurServiceImpl();
		myDao = new UtilisateurDaoImpl();
		listeUtil = new ArrayList<Utilisateur>();
	}

	@Test
	public void createUtilisateurTest() throws SQLException, Exception{

		myService.createUtilisateur("test1","test@test.ts");
		listeUtil.add(myDao.findByMail("test@test.ts"));
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE MAIL='test@test.ts'");
		String	mailtest = "";
		while (rslt.next()) {

			//On recupere le mail du dernier utilisateur ajoute (normalement "test@test.ts")

		mailtest = rslt.getString("MAIL");
		}
		assertEquals("test@test.ts", mailtest);

		System.setOut(new PrintStream(outContent));

		myService.createUtilisateur("test2","test@test.ts");
    	assertEquals("Mail deja utilise ! \n", outContent.toString());
		stmt.close();
		rslt.close();	    	
	}

	@After
	public void cleanUpStreams() {
    	System.setOut(null);
	}

	@Test
	public void findAllUtilisateursTest() throws SQLException, Exception{
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'utilisateurs
			nbrTest ++;
		}
		assertEquals(myService.findAllUtilisateurs().size(), nbrTest);
		stmt.close();
		rslt.close();
	}

	@Test
	public void findUtilisateurByNameTest() throws SQLException, Exception{
		myService.createUtilisateur("test","test1@test.ts");
		myService.createUtilisateur("test","test2@test.ts");
		listeUtil.add(myDao.findByMail("test1@test.ts"));
		listeUtil.add(myDao.findByMail("test2@test.ts"));

		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test'");
		int nbrTest = 0;
		while  (rslt.next()){
			//On recupere le nombre d'utilisateurs dont le nom est test
			nbrTest ++;
		}
		assertNotNull(myService.findUtilisateurByName("test"));
		assertEquals(myService.findUtilisateurByName("test").size(), nbrTest);
		stmt.close();
		rslt.close();
	}

	@Test
	public void deleteUtilisateurTest() throws SQLException, Exception{
		// Add user
		myService.createUtilisateur("test5","test5@test.ts");	
		listeUtil.add(myService.findUtilisateurByNameAndMail("test5","test5@test.ts"));
		// Retrieve Utilisateur
		Utilisateur testut2 = myService.findUtilisateurByNameAndMail("test5","test5@test.ts");
		// Delete it

		myService.deleteUtilisateur(testut2.getID());
		// Test
		assertEquals(myService.findUtilisateurById(testut2.getID()), null);


	}


	@Test
	public void findUtilisateurByNameAndMailTest() throws SQLException, Exception{
		myService.createUtilisateur("test4","test@test.ts");	
		listeUtil.add(myDao.findByNameAndMail("test4","test@test.ts"));	
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test4' AND MAIL='test@test.ts'");

		
		rslt.next();
		
		
		assertNotNull(myService.findUtilisateurByNameAndMail("test4","test@test.ts"));
		assertEquals(myService.findUtilisateurByNameAndMail("test4","test@test.ts").getID(), rslt.getInt("U_ID"));
		rslt.close();
		stmt.close();
		
	}

	@Test
	public void findUtilisateurByIdTest() throws SQLException, Exception{
		myService.createUtilisateur("test8","test@test.ts");	
		listeUtil.add(myService.findUtilisateurByNameAndMail("test8","test@test.ts"));
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test8'");
		int IDtest = 0;
		while (rslt.next()){
			//On recupere l'ID' du dernier utilisateur ajoute 
			IDtest = rslt.getInt("U_ID");
		}
		assertEquals(myService.findUtilisateurById(IDtest).getMail(), myService.findUtilisateurByNameAndMail("test8","test@test.ts").getMail());

		rslt.close();
		stmt.close();
	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		for(Utilisateur ut : listeUtil) {
			myDao.delete(ut);
		}

		myDbUtils.getConnection().close();

		 
	}

}
