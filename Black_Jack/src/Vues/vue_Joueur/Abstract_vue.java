package Vues.vue_Joueur;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Modele.Joueur.Banquier;
import Modele.Joueur.Groupe_Joueur;
import Modele.Joueur.Joueur;
import Modele.Paquet;
import Vue.Abstracte_Vue;
import Vues.vue_Joueur.vue_carte.Abstract_vuePaquet;

public abstract class Abstract_vue extends JPanel {

	protected int width,height,i;
	protected ArrayList<Abstracte_Vue>liste_Jcarte=new ArrayList<>();
	protected Abstract_vuePaquet vuePaquet;
	protected Joueur joueur;
	protected Jlabel_Observer info;
	public Abstract_vue(int width,int height,int i,Joueur joueur) {
		this.width=width;
		this.height=height;
		this.i=i;
		this.joueur=joueur;
	}
	
	public abstract JPanel mise( Paquet pioche);
	public abstract JPanel choix(Groupe_Joueur gp_Joueur, Paquet pioche, Banquier pbanq);
	public abstract JPanel fin_choix();
	public Jlabel_Observer getInfo() {
		return info;
	}
	public Abstract_vuePaquet getVuePaquet(){
		return vuePaquet;
	}
	public ArrayList<Abstracte_Vue>getListe_Jcarte(){
		return this.liste_Jcarte;
	}
	
}
