package test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;

import org.junit.jupiter.api.Test;

import Modele.Joueur.FactoryJoueur;
import Modele.Joueur.Groupe_Joueur;

class TestGroupeJoueur {
	Groupe_Joueur gp;
	@AfterClass
	void testDell() {
		new FactoryJoueur(5,"bob");
		gp = FactoryJoueur.getGroupe_Joueur();
		gp.remove_Player();
		assertEquals(5, gp.size());
		gp.remove_Player();
		assertEquals(4, gp.size());
	}
	@AfterClass
	void testAdd() {
		
		gp.add_Player();
		assertEquals(5,gp.size());
		gp.add_Player();
		assertEquals(6,gp.size());
	}
	@AfterClass
	void testDellBanq() {
		assertEquals("banquier",gp.getJoueur(0).type());
		gp.remove_Banquier();
		assertEquals("humain",gp.getJoueur(0).type());
		assertEquals(5,gp.size());
	}
	@AfterClass
	void testRemoveAll() {
		gp.removeAll();
		assertEquals(0, gp.size());
	}
	@Test
	void test() {
		testDell();
		testAdd();
		testDellBanq();
		testRemoveAll();
	}

}
