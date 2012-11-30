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




public class UtilisateurServiceImplTest {
	private static UtilisateurService myService;

	private static DbUtils myDbUtils;
	private static int lastID;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		myService = new UtilisateurServiceImpl();
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		while  (rslt.next()){
			lastID = rslt.getInt("U_ID");
		}
	}

	@Test
	public void createUtilisateurTest() throws SQLException, Exception{

		myService.createUtilisateur("test","test@test.ts");
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE NOM='test'");
		String mailtest = "";
		while  (rslt.next()){
			//On recupere le mail du dernier utilisateur ajoute (normalement "test@test.ts")
			mailtest = rslt.getString("MAIL");
		}
		assertEquals("test@test.ts", mailtest);

		System.setOut(new PrintStream(outContent));

		myService.createUtilisateur("test","test@test.ts");

    	assertEquals("Mail deja utilise ! \n", outContent.toString());
	
	    	
	}

	@After
	public void cleanUpStreams() {
    	System.setOut(null);
	}
/*
	public List<Utilisateur> findAllUtilisateurs(){
		return myDao.findAll();
	}

	public List<Utilisateur> findUtilisateurByName(String nom){
		return myDao.findByName(nom);
	}
*/
	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		String str = "DELETE FROM Utilisateur WHERE U_ID>" + lastID;
		Statement stmt = myDbUtils.getStatement();
        stmt.execute(str);
		myDbUtils.getConnection().close();
	}

}