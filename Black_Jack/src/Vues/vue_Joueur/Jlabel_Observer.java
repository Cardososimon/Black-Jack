package Vues.vue_Joueur;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

import Modele.Joueur.Joueur;

public class Jlabel_Observer extends JLabel implements Observer{
	/**
	 * class permettant de tenir à jour les jetons et les différentes mises des joueurs
	 * @param texte
	 */
	public Jlabel_Observer(String texte) {
		super(texte);
	}

	@Override
	/**
	 * permet au changement du modèle de venir dans cette fonction qui va permettre de mettre à jour le texte 
	 * selon ce que renvoi la fonction something_havechanged() du modèle
	 */
	public void update(Observable arg0, Object arg1) {
		Joueur j=(Joueur)arg0;
		this.setText(j.something_havechanged());
	}
	
}
