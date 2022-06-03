package Vues.vue_Joueur;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Joueur.Banquier;
import Modele.Joueur.Groupe_Joueur;
import Modele.Joueur.Joueur;
import Modele.Paquet;
import Vue.Abstracte_Vue;
import Vue.Frame_nonVisible;
import Vues.vue_Joueur.vue_carte.Abstract_vuePaquet;
import Vues.vue_Joueur.vue_carte.Vue_Paquet_Banquier;

public class Vue_Banq extends Abstract_vue {
	
	private Abstracte_Vue abpioche;
	protected JPanel banqpioche,jpioche;
	protected JLabel banquier;
	protected Abstract_vuePaquet banq;
	
	public Vue_Banq(int width,int height,int i,Joueur joueur) {
		super(width,height,i,joueur);
	}
	/**
	 * permet de créer un jpanel pour l'affichage de la création des mises
	 */
	public JPanel mise(Paquet pioche) {
		this.setLayout(null);
		this.setSize(width, height);
		this.setLocation(width,0);
		this.setBackground(new Color(165,89,8));
		JLabel banquier=new JLabel("Banquier");
		banquier.setSize(200,50);
		banquier.setLocation(this.getWidth()/2-100,10);
		banquier.setForeground(new Color(255,255,255));
		this.add(banquier);
		abpioche=new Frame_nonVisible(pioche);
		jpioche = abpioche.creat_Panel();
		jpioche.setBackground(new Color(165,89,8));
		jpioche.setLocation(this.getWidth()/2-100,this.getHeight()-(jpioche.getHeight()));
		this.add(jpioche);
		this.repaint();
		return this;
	}
	
	/**
	 * permet de créer un jpanel montrant les cartes du joueur
	 */
	@SuppressWarnings("deprecation")
	public JPanel choix(Groupe_Joueur gp_Joueur,Paquet pioche,Banquier pbanq) {
		System.out.println("j affiche les carte banq");
		Abstract_vuePaquet banq = new Vue_Paquet_Banquier(width,(Joueur)pbanq,this);
		banq.creatPan();
		banq.setLocation(0,30);
		vuePaquet=banq;
		joueur.getPaquet_Index(0).addObserver(vuePaquet);
		this.add(banq);
		return this;
	}

	@Override
	public JPanel fin_choix() {
		// TODO Auto-generated method stub
		return this;
	}
	public Abstracte_Vue getPioche() {
		return abpioche;
	}

}
