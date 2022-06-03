package Modele.Joueur.strat;

import java.util.ArrayList;

import Modele.Paquet;
import Modele.Joueur.Banquier;
import Modele.Joueur.Joueur;

public class Strategy_random extends Abstract_Strategy  {
	/**
	 * 
	 * @param joueur reprit de sa class mère
	 */
	public Strategy_random(Joueur joueur) {
		super(joueur);
		System.out.println("strat_random");
	}
	/**
	 * @param paquet représente le paquet en cours du joueur
	 * @return la liste des choix que peut effectuer le bot 
	 */
	public ArrayList<String> all_choix(Paquet paquet){
		ArrayList<String>liste_choixPossible=new ArrayList<>();
		if(joueur.BlackJack()) {
			liste_choixPossible.add("Blackjack");
		}
		else {
			if (joueur.canDouble()) {
				liste_choixPossible.add("Double");
			}
			if (joueur.CanSplit_Paquet()) {
				liste_choixPossible.add("Split");
			}
			if (joueur.cantake_Carte(paquet)) {
				liste_choixPossible.add("AddCarte");
			}
			liste_choixPossible.add("Pass");
		}
		return liste_choixPossible;
	}
	/**
	 * permet à un joueur de type bot de choisir pour chaque paquet qui lui appartient ce qu'il en fait
	 */
	public void jouer(Banquier bq,Paquet pioche) {
		if (joueur.canAssurance(bq)) {
			joueur.creat_insuranceMise(0);
		}
		for(int i=0;i<joueur.getListe_Paquet().size();i++) {
			ArrayList<String>liste_choixPossible=all_choix(joueur.getPaquet_Index(i));
			int choix=(int) (Math.random()*(liste_choixPossible.size()-1));
			String mon_choix=liste_choixPossible.get(choix);
			Play(joueur.getPaquet_Index(i),mon_choix,pioche,i);
			System.out.println(mon_choix+" choix pour "+i);
		}
	}
	
}
