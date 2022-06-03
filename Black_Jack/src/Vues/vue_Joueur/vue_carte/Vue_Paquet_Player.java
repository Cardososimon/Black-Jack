package Vues.vue_Joueur.vue_carte;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import Controleur.ControleurPiocheVersPaquet;
import Modele.Joueur.Humain;
import Modele.Joueur.Joueur;
import Vue.Abstracte_Vue;
import Vue.Frame_Visible;
import Vues.vue_Joueur.Abstract_vue;
import Vues.vue_Joueur.Vue_Humain;

public class Vue_Paquet_Player extends Abstract_vuePaquet{
	
	private Abstracte_Vue vuepioche;
	private ArrayList<ControleurPiocheVersPaquet>liste_event = new ArrayList<>();
	
	public Vue_Paquet_Player(int width,Joueur j,Abstract_vue vue,Abstracte_Vue vuepioche) {
		super(width,j,vue);
		this.vuepioche=vuepioche;
	}
	/**
	 * permet d'afficher les différents paquets du joueur
	 */
	public JPanel creatPan() {
		this.setLayout(null);
		this.setBackground(new Color(132,72,0));
		this.setSize(width, 280);
		for (int i=0;i<j.getListe_Paquet().size();i++) {
			Abstracte_Vue paquet1=new Frame_Visible(j.getPaquet_Index(i));
			System.out.println(j.getPaquet_Index(i).size());
			paquet1.creat_Panel();
			paquet1.setLocation(10,i*140);
			paquet1.setBackground(new Color(132,72,0));
			this.add(paquet1);
			liste_Jcarte.add(paquet1);
			if(j instanceof Humain && j.BlackJack()) {
				paquet1.desactiver_Carte();
			}
		}
		if(j instanceof Humain) {
			ControleurPiocheVersPaquet event_PiochetoPaquet = new ControleurPiocheVersPaquet(vuepioche,j.getPaquet_Index(0));
			liste_event.add(event_PiochetoPaquet);
			add_Listener();
		}
		return this;
	}

	public JPanel refresh() {
		if (liste_Jcarte.size()!=j.size()) {
			Abstracte_Vue paquet2=new Frame_Visible(j.getPaquet_Index(1));
			j.getPaquet_Index(1).addObserver(this);
			paquet2.creat_Panel();
			ControleurPiocheVersPaquet event_PiochetoPaquet = new ControleurPiocheVersPaquet(vuepioche,j.getPaquet_Index(1));
			liste_event.add(event_PiochetoPaquet);
			paquet2.setLocation(10,1*140);
			paquet2.setBackground(new Color(132,72,0));
			this.add(paquet2);
			liste_Jcarte.add(paquet2);
		}
		else {
			for (int i=0;i<liste_Jcarte.size();i++) {
				liste_Jcarte.get(i).creat_Panel();
				liste_Jcarte.get(i).setLocation(10,i*140);
				if(j.Perdu(j.getPaquet_Index(i))) {
					liste_Jcarte.get(i).desactiver_Carte();
				}
				else {
					liste_Jcarte.get(i).activer_Carte();
				}
			}
		}
		if (j instanceof Humain) {
			((Vue_Humain)vue).update(j);
			remove_Listeners();
			add_Listener();
		}
		j.tout_Perdu();
		return this;
	}
	/**
	 * permet d'ajouter les events correspondant aux cartes de chaque paquet de cartes
	 */
	public void add_Listener() {
		for (int i=0;i<j.size();i++) {
			for(int k=0;k<j.getPaquet_Index(i).size();k++) {
				j.getPaquet_Index(i).getCarteIndex(k).addActionListener(liste_event.get(i));
			}
		}
	}
	/**
	 * permet de supprimer tous les events liés à une carte pour tout le paquet
	 */
	public void remove_Listeners() {
		for (int i=0;i<j.size();i++) {
			for (int k=0;k<j.getPaquet_Index(i).size();k++) {
				for (ActionListener listener : Arrays.asList(j.getPaquet_Index(i).getCarteIndex(k).getActionListeners())) {
					j.getPaquet_Index(i).getCarteIndex(k).removeActionListener(listener);
				}
			}
		}
	}
	
}
