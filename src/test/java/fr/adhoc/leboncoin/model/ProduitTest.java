package fr.adhoc.leboncoin.model;

import java.sql.*;
import org.junit.*;
import static org.junit.Assert.*;

import fr.adhoc.leboncoin.model.Produit;
import fr.adhoc.leboncoin.model.Utilisateur;

import fr.adhoc.leboncoin.model.Utilisateur;
import fr.adhoc.leboncoin.utils.DbUtils;


public class ProduitTest {
	private static DbUtils myDbUtils;
	private static int lastID;
	private static Utilisateur utTest;

	@BeforeClass public static void runBeforeClass() throws SQLException, Exception{
	// run for one time before all test cases
		myDbUtils = new DbUtils();
		Statement stmt = myDbUtils.getStatement();
		ResultSet rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
		while  (rslt.next()){
			lastID = rslt.getInt("U_ID");
		}
		utTest = new Utilisateur("test","test@test.ts");
	}
	@Test
	public void testCreationProduit(){
		Produit prod = new Produit("Ptest", 10, "This product is a test", utTest);
		assertEquals(prod.getVendeur().getMail(), "test@test.ts");
		assertEquals(prod.getNom(), "Ptest");
	}

	@AfterClass public static void runAfterClass() throws SQLException, Exception {
		// run for one time after all test cases
		String str = "DELETE FROM Utilisateur WHERE U_ID>" + lastID;
		Statement stmt = myDbUtils.getStatement();
        stmt.execute(str);
		myDbUtils.getConnection().close();
	}

}