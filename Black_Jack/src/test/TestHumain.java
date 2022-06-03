package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Modele.Carte;
import Modele.Paquet;
import Modele.Joueur.Humain;

class TestHumain {
	
	@Test
	void testActionPossible() {
		ArrayList<Paquet>liste=new ArrayList<>();
		Humain h = new Humain("bob",200);
		Carte carte1=new Carte("as", "coeur");
		Carte carte2=new Carte("as", "pique");
		Paquet paquet = new Paquet();
		paquet.Add_Carte_debut(carte1);
		paquet.Add_Carte_fin(carte2);
		liste.add(paquet);
		h.setListe_Paquet(liste);
		h.creat_mise(100);
		assertEquals(false,h.BlackJack());
		assertEquals(true,h.CanSplit_Paquet());
		assertEquals(true,h.canDouble());
		
		ArrayList<Paquet>liste2=new ArrayList<>();
		Humain h2 = new Humain("bob",100);
		Carte carte12=new Carte("10", "coeur");
		Carte carte22=new Carte("as", "pique");
		Paquet paquet2 = new Paquet();
		paquet2.Add_Carte_debut(carte12);
		paquet2.Add_Carte_fin(carte22);
		liste2.add(paquet2);
		h2.setListe_Paquet(liste2);
		h2.creat_mise(100);
		assertEquals(false,h2.canDouble());
		assertEquals(true,h2.BlackJack());
		assertEquals(false,h2.CanSplit_Paquet());;
		assertEquals(true,h2.canDouble());
		
		ArrayList<Paquet>liste3=new ArrayList<>();
		Humain h3 = new Humain("bob",150);
		Carte carte13=new Carte("10", "coeur");
		Carte carte23=new Carte("9", "pique");
		Paquet paquet3 = new Paquet();
		paquet3.Add_Carte_debut(carte13);
		paquet3.Add_Carte_fin(carte23);
		liste3.add(paquet3);
		h3.setListe_Paquet(liste3);
		h3.creat_mise(100);
		assertEquals(false,h3.canDouble());
		assertEquals(false,h3.BlackJack());
		assertEquals(false,h3.CanSplit_Paquet());;
	}
	
	@Test
	void test() {
		
	}

}
