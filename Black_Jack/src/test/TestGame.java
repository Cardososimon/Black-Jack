package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import Modele.Factory_Paquet;
import Modele.Game;
import Modele.Paquet;
import Modele.Joueur.FactoryJoueur;
import Modele.Joueur.Groupe_Joueur;

class TestGame {
Groupe_Joueur gp;
Paquet pioche;
Game game;
	
	@BeforeClass
	void testGive2Carte() {
		int r=52-(5*2);
		game.give_deuxCarte();
		assertEquals(r, pioche.size());
	}
	@BeforeClass
	void testRendreTouteslesCartes() {
		game.rendre_CartetoPioche();
		assertEquals(52,pioche.size());
	}
	@Test
	void test() {
		new FactoryJoueur(5,"bob");
		gp = FactoryJoueur.getGroupe_Joueur();
		new Factory_Paquet(52);
		pioche=Factory_Paquet.getPaquet();
		game = new Game(gp,pioche);
		testGive2Carte();
		testRendreTouteslesCartes();
	}

}
