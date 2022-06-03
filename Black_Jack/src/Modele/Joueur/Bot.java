package Modele.Joueur;

import java.util.ArrayList;
import Modele.Paquet;
import Modele.Joueur.strat.*;
/**
 * 
 * @author slurfy
 *
 */
public class Bot extends Joueur {
	Strategy ma_strate;
	/**
	 * permet de créer une instance de bot
	 * @param nom permet de donner un petit nom à son bot
	 * @param jeton permet de dire avec combien de jetons le bot commence
	 */
	public Bot (String nom,int jeton) {
		super(nom,jeton);
		this.ma_strate=new Strategy_random(this);
	}
	/**
	 * permet de savoir les mises que peut effectuer un bot
	 * @return la mise du bot
	 */
	public int mise_Possible() {
		ArrayList<Integer> liste_mise = new ArrayList<>();
		for (int i=100;i<=400;i+=100) {
			if (i<=jeton) {
				liste_mise.add(i);
			}
		}
		int choix=(int) (Math.random()*(liste_mise.size()-1));
		return liste_mise.get(choix);
	}
	/**
	 * permet de faire miser le bot et de mettre à jour les jetons et la mise
	 */
	@SuppressWarnings("deprecation")
	public void creat_mise(int pas_utile) {
		int mise2=mise_Possible();
		liste_mise.add(mise2);
		this.jeton-=mise2;
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * permet de créer une liste des assurances mises possibles
	 * et renvoi l'assurance mise choisi
	 */
	@SuppressWarnings("deprecation")
	public void creat_insuranceMise(int pas_utile) {
		if (jeton-liste_mise.get(0)/2>0) {
			ArrayList<Boolean>choix = new ArrayList<>();
			choix.add(true);
			choix.add(false);
			int bool=(int)(Math.random()*(choix.size()-1));
			if (choix.get(bool).equals(true)) { 
				ArrayList<Integer> liste_mise2=insuranceMise_Possible();
				int insmise=(int) (Math.random()*(liste_mise.size()-1));
				insmise=liste_mise2.get(insmise);
				insurance_mise=insmise;
				jeton-=insurance_mise;
				this.setChanged();
				this.notifyObservers();
			}
		}
	}
	/**
	 * permet de changer la stratégie d'un bot (sa façon de jouer)
	 * @param strat représente une stratégie 
	 */
	public void setStrategy(Strategy strat) {
		this.ma_strate=strat;
	}
	/**
	 * permet au bot de jouer un coup
	 * @param bq représente le joueur Banquier de la game
	 * @param pioche représente le paquet pioche de la game
	 */
	public void jouer(Banquier bq,Paquet pioche) {
		if (jeton<500) {
			setStrategy(new Strategy_noAssurance(this));
		}
		else {
			setStrategy(new Strategy_random(this));
		}
			ma_strate.jouer(bq, pioche);
	}
	public String type() {
		return "bot";
	}
}
