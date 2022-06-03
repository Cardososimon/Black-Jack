package Vues.vue_Joueur;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JPanel;

import Modele.Joueur.Banquier;
import Modele.Joueur.Groupe_Joueur;
import Modele.Joueur.Joueur;
import Modele.Paquet;
import Vue.Abstracte_Vue;
import Vues.vue_Joueur.vue_carte.Vue_Paquet_Player;

public class Vue_Bot extends Abstract_vue {
	
	JPanel panPlayer;
	ArrayList<JPanel>liste_Jcarte=new ArrayList<>();
	Abstracte_Vue vuepioche;
	public Vue_Bot(int width,int height,int i,Joueur joueur,Abstracte_Vue vuepioche) {
		super(width,height,i,joueur);
		this.vuepioche=vuepioche;
	}
	/**
	 * permet de créer un jpanel pour l'affichage de la création des mises
	 */
	@SuppressWarnings("deprecation")
	public JPanel mise(Paquet pioche) {
		info =new Jlabel_Observer(joueur.getNom()+" a :"+joueur.getJeton()+" jetons");
		joueur.addObserver(info);
		this.setLayout(null);
		this.setSize(width, height);
		this.setBackground(new Color(132,72,8));
		info.setSize(300, 50);
		info.setLocation(this.getWidth()/2-100, 10);
		info.setForeground(new Color(255,255,255));
		this.add(info);
		return this;
	}
	/**
	 * permet de créer un jpanel montrant les cartes du joueur
	 */
	@SuppressWarnings("deprecation")
	public JPanel choix(Groupe_Joueur gp_Joueur,Paquet pioche,Banquier pbanq) {
		vuePaquet=new Vue_Paquet_Player(width,gp_Joueur.getJoueur(i),this, vuepioche);
		vuePaquet.creatPan();
		vuePaquet.setBackground(new Color(132,72,0));
		vuePaquet.setLocation(10,60);
		joueur.getPaquet_Index(0).addObserver(vuePaquet);
		this.add(vuePaquet);
		return this;
	}

	@Override
	public JPanel fin_choix() {
		// TODO Auto-generated method stub
		return this;
	}
}
