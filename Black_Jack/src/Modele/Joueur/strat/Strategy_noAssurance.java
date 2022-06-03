package Modele.Joueur.strat;

import java.util.ArrayList;
import Modele.Paquet;
import Modele.Joueur.Banquier;
import Modele.Joueur.Joueur;

public class Strategy_noAssurance extends Abstract_Strategy{
	
	public Strategy_noAssurance(Joueur joueur) {
		super(joueur);
		System.out.println("super bot");
	}
	/**
	 * permet à un joueur de type bot de choisir pour chaque paquet qui lui appartient ce qu'il en fait
	 */
	public void jouer(Banquier bq, Paquet pioche) {
		for(int i=0;i<joueur.getListe_Paquet().size();i++) {
			ArrayList<String>liste_choixPossible=all_choix(joueur.getPaquet_Index(i));
			int choix=(int) (Math.random()*(liste_choixPossible.size()-1));
			String mon_choix=liste_choixPossible.get(choix);
			Play(joueur.getPaquet_Index(i),mon_choix,pioche,i);
			System.out.println(mon_choix+" choix pour "+i);
		}	
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
				if(joueur.Valeur_Paquet(paquet)<15) {
				liste_choixPossible.add("Double");
				}
			}
			if (joueur.CanSplit_Paquet()) {
				if(joueur.Valeur_Paquet(paquet)<16) {
				liste_choixPossible.add("Split");
				}
			}
			if (joueur.cantake_Carte(paquet)) {
				if(joueur.Valeur_Paquet(paquet)<15) {
				liste_choixPossible.add("AddCarte");
				}
			}
			if(joueur.Valeur_Paquet(paquet)>14) {
			liste_choixPossible.add("Pass");
			}
		}
		return liste_choixPossible;
	}
	
}
