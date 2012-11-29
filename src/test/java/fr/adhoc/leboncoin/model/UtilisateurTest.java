package fr.adhoc.leboncoin.model;


import org.junit.Test;
import static org.junit.Assert.*;

import fr.adhoc.leboncoin.model.Utilisateur;

public class UtilisateurTest {

	@Test
	public void testCreationUtilisateur(){
		Utilisateur user = new Utilisateur("ivan", "ivan@adhoc.com");
		assertEquals("ivan", user.getNom());
	}
}