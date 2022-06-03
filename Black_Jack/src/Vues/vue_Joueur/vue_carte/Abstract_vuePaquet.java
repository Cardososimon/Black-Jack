package Vues.vue_Joueur.vue_carte;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Modele.Joueur.Joueur;
import Vue.Abstracte_Vue;
import Vues.vue_Joueur.Abstract_vue;

public abstract class Abstract_vuePaquet extends JPanel implements Observer{
	
		protected ArrayList<Abstracte_Vue>liste_Jcarte=new ArrayList<>();
		protected int width;
		protected Joueur j;
		protected Abstract_vue vue;
		/**
		 * permet de créer la vue des paquets des joueurs
		 * @param width permet de savoir la taille que va faire la vue
		 * @param j représente le joueur
		 * @param vue 
		 */
	public Abstract_vuePaquet(int width,Joueur j,Abstract_vue vue) {
		this.width=width;
		this.j=j;
		this.vue=vue;
	}
	public ArrayList<Abstracte_Vue>getListe_Jcarte(){
		return this.liste_Jcarte;
	}
	/**
	 * permet de bloquer le clic d'une carte
	 */
	public void desactiver_touteCarte() {
		for (int i=0;i<liste_Jcarte.size();i++) {
			liste_Jcarte.get(i).desactiver_Carte();
		}
	}
	/**
	 * permet de réactiver toutes les cartes du joueur
	 */
	public void activer_touteCarte() {
		for (int i=0;i<liste_Jcarte.size();i++) {
			liste_Jcarte.get(i).activer_Carte();
		}
	}
	
	public abstract JPanel creatPan();
	public abstract JPanel refresh();
	/**
	 * permet au changement du modèle de rafraichir Abstract_vuePaquet
	 */
	public void update(Observable arg0, Object arg1) {
	refresh();
	this.setLocation(0,60);
}
	
}
