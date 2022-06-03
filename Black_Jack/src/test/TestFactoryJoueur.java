package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import Modele.Joueur.FactoryJoueur;
import Modele.Joueur.Groupe_Joueur;

class TestFactoryJoueur {
	Groupe_Joueur gp;
	
	@BeforeClass
	void testNumberofPlayer() {
		new FactoryJoueur(0,"bob");
		gp = FactoryJoueur.getGroupe_Joueur();
		assertNull(gp);
		
		new FactoryJoueur(-50,"bob");
		gp = FactoryJoueur.getGroupe_Joueur();
		assertNull(gp);
		
		
		new FactoryJoueur(6,"bob");
		gp = FactoryJoueur.getGroupe_Joueur();
		assertNull(gp);
		
		new FactoryJoueur(5,"bob");
		gp = FactoryJoueur.getGroupe_Joueur();
		assertEquals(6, gp.size());
	}
	@AfterClass
	void testTypeofPlayer() {
		
		assertEquals("banquier",gp.getJoueur(0).type());
		assertEquals("humain",gp.getJoueur(1).type());
		assertEquals("bot",gp.getJoueur(2).type());
		assertEquals("bot",gp.getJoueur(3).type());
		assertEquals("bot",gp.getJoueur(4).type());
		assertEquals("bot",gp.getJoueur(5).type());
	}
	@Test
	void testall() {
		testNumberofPlayer();
		testTypeofPlayer();
	}
}
