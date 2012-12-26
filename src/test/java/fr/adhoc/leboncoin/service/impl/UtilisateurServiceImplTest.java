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
/*
	private static UtilisateurService myService;
	private static UtilisateurDao myDao;
	private static int lastID;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private  static List<Utilisateur> listeUtil;

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myService = new UtilisateurServiceImpl();
		myDao = new UtilisateurDaoImpl();
		listeUtil = new ArrayList<Utilisateur>();
	}

	@Test
	public void createUtilisateurTest() throws SQLException, Exception{

		myService.createUtilisateur("test1","test@test.ts");
		listeUtil.add(myDao.findByMail("test@test.ts"));

		assertEquals("test@test.ts", myDao.findByNameAndMail("test1","test@test.ts").getMail());

		System.setOut(new PrintStream(outContent));

		myService.createUtilisateur("test2","test@test.ts");
    	assertEquals("Mail deja utilise ! \n", outContent.toString());
	
	}

	@After
	public void cleanUpStreams() {
    	System.setOut(null);
	}

	@Test
	public void findAllUtilisateursTest() throws SQLException, Exception{

		assertEquals(myService.findAllUtilisateurs().size(), myDao.findAll().size());
	}

	@Test
	public void findUtilisateurByNameTest() throws SQLException, Exception{
		myService.createUtilisateur("test","test1@test.ts");
		myService.createUtilisateur("test","test2@test.ts");
		listeUtil.add(myDao.findByMail("test1@test.ts"));
		listeUtil.add(myDao.findByMail("test2@test.ts"));

		assertEquals(myService.findUtilisateurByName("test").size(), myDao.findByName("test").size());

	}

	@Test
	public void deleteUtilisateurTest() throws SQLException, Exception{
		// Add user
		myService.createUtilisateur("test9","test9@test.ts");	
		// Retrieve Utilisateur
		Utilisateur testut2 = myService.findUtilisateurByNameAndMail("test9","test9@test.ts");
		// Delete it

		myService.deleteUtilisateur(testut2.getId());
		// Test
		assertEquals(myService.findUtilisateurById(testut2.getId()), null);


	}


	@Test
	public void findUtilisateurByNameAndMailTest() throws SQLException, Exception{
		myService.createUtilisateur("test7","test7@test.ts");	
		listeUtil.add(myDao.findByNameAndMail("test7","test7@test.ts"));	
		assertEquals(myService.findUtilisateurByNameAndMail("test7","test7@test.ts").getId(), myDao.findByNameAndMail("test7","test7@test.ts").getId());
		
	}

	@Test
	public void findUtilisateurByIdTest() throws SQLException, Exception{
		myService.createUtilisateur("test8","test8@test.ts");	
		listeUtil.add(myService.findUtilisateurByNameAndMail("test8","test8@test.ts"));
		int IDtest = myService.findUtilisateurByNameAndMail("test8","test8@test.ts").getId();
		assertEquals(myService.findUtilisateurById(IDtest).getMail(), myDao.findById(IDtest).getMail());

	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		for(Utilisateur ut : listeUtil) {
			myDao.delete(ut);
		}


		 
	}
	*/

}
