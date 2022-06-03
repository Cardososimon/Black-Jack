package Modele.Joueur.strat;

import Modele.Paquet;
import Modele.Joueur.Banquier;

public interface Strategy {
	void jouer(Banquier bq,Paquet pioche);
}
