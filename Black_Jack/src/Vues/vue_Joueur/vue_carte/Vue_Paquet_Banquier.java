package Vues.vue_Joueur.vue_carte;

import java.awt.Color;
import javax.swing.JPanel;
import Modele.Joueur.Joueur;
import Vue.Abstracte_Vue;
import Vue.Frame_Visible;
import Vue.Frame_nonVisible;
import Vues.vue_Joueur.Abstract_vue;

public class Vue_Paquet_Banquier extends Abstract_vuePaquet {
	
	public Vue_Paquet_Banquier(int width, Joueur j,Abstract_vue vue) {
		super(width, j,vue);
	}
/**
 * permet de créer la vue des différents paquets de carte du banquier
 */
	public JPanel creatPan() {
		this.setLayout(null);
		this.setSize(width, 150);
		this.setBackground(new Color(128,70,8));
		System.out.println(j.size()+"nb paquet "+j.getPaquet_Index(0).size());
		Abstracte_Vue banq_visible =new Frame_Visible(j.getPaquet_Index(0));
		Abstracte_Vue banq_nonvisible =new Frame_nonVisible(j.getPaquet_Index(1));
		banq_visible.creat_Panel();
		banq_nonvisible.creat_Panel();
		liste_Jcarte.add(banq_visible);
		liste_Jcarte.add(banq_nonvisible);
		banq_visible.setBackground(new Color(128,70,8));
		banq_nonvisible.setBackground(new Color(128,70,8));
		banq_visible.setLocation(this.getWidth()/4,50);
		banq_nonvisible.setLocation(this.getWidth()/2,50);
		this.add(banq_visible);
		this.add(banq_nonvisible);
		return this;
	}

	@Override
	public JPanel refresh() {
			liste_Jcarte.get(0).creat_Panel();
			if (j.size()>0) {
				this.remove(liste_Jcarte.get(1));
			}
			liste_Jcarte.get(0).setLocation(width/3,20);
			return this;
	}

}
