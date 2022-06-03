package Modele.Joueur.strat;
import Modele.Paquet;
import Modele.Joueur.Joueur;
public abstract class Abstract_Strategy implements Strategy{
	protected Joueur joueur;
	/**
	 * cette class permet de changer la façon de jouer d'un bot 
	 * @param joueur prend en paramètre un joueur mais plus particulièrement bot
	 */
	public Abstract_Strategy(Joueur joueur) {
		this.joueur=joueur;
	}
	/**
	 * permet de jouer un coup pour un paquet donné du joueur
	 * @param paquet représente l'un des paquets du joueur
	 * @param mon_choix donne la valeur en string de ce que le joueur va faire (passe ,double , ...)
	 * @param pioche c'est le paquet pioche du jeux
	 * @param i permet de savoir dans quel paquet on doit effectuer des modifications
	 **/
	public void Play(Paquet paquet ,String mon_choix,Paquet pioche,int i) {
		switch (mon_choix) {
		case "Blackjack":
			joueur.winmise_BlackJack();
			joueur.change_Etat();
			break;
		case "Double":
			joueur.Double(pioche);
			joueur.change_Etat();
			break;
		case "Split":
			joueur.split_Paquet(pioche);
			break;
		case "AddCarte":
			//System.out.println("nb carte paquet avant : "+liste_paquet.get(i).size());
			joueur.getPaquet_Index(i).Add_Carte_debut(pioche.getCarteIndex(0));
			pioche.Del_Carte(0);
			//System.out.println("nb carte paquet apres : "+liste_paquet.get(i).size());
			if(joueur.Valeur_Paquet(paquet)>21) {
				joueur.change_Etat();
			}
			break;
		case "Pass":
			joueur.change_Etat();
			break;
		}
	}
}
