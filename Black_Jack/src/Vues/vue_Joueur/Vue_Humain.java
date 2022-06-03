package Vues.vue_Joueur;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.ControleurPiocheVersPaquet;
import Modele.Joueur.Banquier;
import Modele.Joueur.Groupe_Joueur;
import Modele.Joueur.Joueur;
import Modele.Paquet;
import Vue.Abstracte_Vue;
import Vue.Frame_Visible;
import Vues.vue_Joueur.vue_carte.*;
public class Vue_Humain extends Abstract_vue {
	
	//JPanel main;
	protected ArrayList<JButton>liste_button=new ArrayList<>();
	protected JLabel choix_mise;
	protected Abstract_vue abvue;
	protected Abstracte_Vue vuepioche;
	public Vue_Humain(int width,int height,int i,Joueur joueur,Abstracte_Vue vuepioche) {
		super(width,height,i,joueur);
		this.vuepioche=vuepioche;
	}
	
	public void vider_liste_button() {
		for (int i=liste_button.size()-1;i>=0;i--) {
			this.remove(liste_button.get(i));
			liste_button.remove(i);
		}
	}
	/**
	 * permet de créer un jpanel pour l'affichage de la création des mises où le joueur a différents choix
	 * de mise possible
	 */
	@SuppressWarnings("deprecation")
	public JPanel mise( Paquet pioche) {
		choix_mise = new JLabel("choisissez votre mise :");
		choix_mise.setSize(300, 50);
		info = new Jlabel_Observer(joueur.getNom() + " a :" + joueur.getJeton() + " jetons");
		joueur.addObserver(info);
		this.setLayout(null);
		this.setSize(width, height);
		this.setBackground(new Color(132, 72, 8));
		info.setSize(300, 50);
		info.setLocation(this.getWidth() / 2 - 100, 10);
		info.setForeground(new Color(255, 255, 255));
		this.add(info);
		this.setLocation(width, height);
		for (int j = 1; j < 5; j++) {
			int mise = j * 100;
			JButton bt_mise = new JButton("" + mise);
			bt_mise.setActionCommand("mise : " + mise);
			bt_mise.setSize((this.getWidth() / 4) - (5 * 10), 30);
			bt_mise.setLocation(10 * j + (this.getWidth() / 4) * (j - 1), this.getHeight() - 100);
			bt_mise.setForeground(new Color(128, 70, 8));
			bt_mise.setBackground(new Color(255, 255, 255));
			if (joueur.getJeton() >= mise) {
				liste_button.add(bt_mise);
				this.add(bt_mise);
			}
		}
		choix_mise.setLocation(this.getWidth() / 2 - 100, this.getHeight() - 200);
		choix_mise.setForeground(new Color(255, 255, 255));
		this.add(choix_mise);
		
		return this;
	}
		
	/**
	 * permet de créer un jpanel montrant les cartes du joueur
	 * affiche également les différents choix que peut faire le joueur (piocher ,doubler,...)
	 */
	@SuppressWarnings("deprecation")
	public JPanel choix(Groupe_Joueur gp_Joueur, Paquet pioche, Banquier pbanq) {
		vider_liste_button();
		this.remove(choix_mise);
		JButton bt_Stand = new JButton("Stand");
		bt_Stand.setActionCommand("Stand");
		bt_Stand.setSize((this.getWidth() / 4) - (5), 30);
		bt_Stand.setLocation(10 * 1 + (this.getWidth() / 4) * (1 - 1), this.getHeight() - 100);
		bt_Stand.setForeground(new Color(128, 70, 8));
		bt_Stand.setBackground(new Color(255, 255, 255));
		liste_button.add(bt_Stand);
		this.add(bt_Stand);
		if (pbanq.getPaquet_Index(0).getCarteIndex(0).getValeur().contains("as")) {
			JButton bt_Insurance = new JButton("Insurance");
			bt_Insurance.setActionCommand("Insurance");
			bt_Insurance.setSize((this.getWidth() / 4) - (5), 30);
			bt_Insurance.setLocation(5* 4 + (this.getWidth() / 4) * (4 - 1), this.getHeight() - 100);
			bt_Insurance.setForeground(new Color(128, 70, 8));
			bt_Insurance.setBackground(new Color(255, 255, 255));
			liste_button.add(bt_Insurance);
			this.add(bt_Insurance);
		}
		if (joueur.BlackJack() == false) {
			if (joueur.canDouble()) {
				JButton bt_Double = new JButton("Double");
				bt_Double.setActionCommand("Double");
				bt_Double.setSize((this.getWidth() / 4) - (5), 30);
				bt_Double.setLocation(5*2 + (this.getWidth() / 4) * (2 - 1), this.getHeight() - 100);
				bt_Double.setForeground(new Color(128, 70, 8));
				bt_Double.setBackground(new Color(255, 255, 255));
				liste_button.add(bt_Double);
				this.add(bt_Double);
			}
			if (joueur.CanSplit_Paquet()) {
				JButton bt_Split = new JButton("Split");
				bt_Split.setActionCommand("Split");
				bt_Split.setSize((this.getWidth() / 4) - (5), 30);
				bt_Split.setLocation(5*3 + (this.getWidth() / 4) * (3 - 1), this.getHeight() - 100);
				bt_Split.setForeground(new Color(128, 70, 8));
				bt_Split.setBackground(new Color(255, 255, 255));
				liste_button.add(bt_Split);
				this.add(bt_Split);
			}
		}
		vuePaquet=new Vue_Paquet_Player(width,joueur,this,vuepioche);
		vuePaquet.creatPan();
		vuePaquet.setBackground(new Color(132,72,0));
		vuePaquet.setLocation(10,60);
		liste_Jcarte=vuePaquet.getListe_Jcarte();
		//vuePaquet.add_Listener();
		joueur.getPaquet_Index(0).addObserver(vuePaquet);
		this.add(vuePaquet);
		//this.repaint();
		return this;
	}
	/**
	 * change l'affichage une fois que le joueur humain ne peut plus jouer ou ne veut plus jouer 
	 * pour faire jouer les autres joueurs 
	 */
	public  JPanel fin_choix(){
		vider_liste_button();
		JButton next_Player =new JButton ("next Player");
		next_Player.setActionCommand("next_player");
		next_Player.setSize((this.getWidth() / 4), 30);
		next_Player.setLocation(((this.getWidth() / 2)-(this.getWidth() / 4)), this.getHeight() - 100);
		next_Player.setForeground(new Color(128, 70, 8));
		next_Player.setBackground(new Color(255, 255, 255));
		this.add(next_Player);
		liste_button.add(next_Player);
		this.repaint();
		return this;
	}
	/**
	 * change le button, le texte et la commande du button de la vue afin qu'on puisse réclamer les jetons
	 * @return
	 */
	public JPanel give_allMise() {
		liste_button.get(0).setText("claim jetons");
		liste_button.get(0).setActionCommand("claim");
		return this;
	}
	
	public ArrayList<JButton> getListe_button(){
		return liste_button;
	}
	public void update(Joueur jobs) {
			if (jobs.getListe_Paquet().isEmpty()) {
				
			}else {
				if(jobs.size()==2)  {
					if(liste_button.size()>2) {
					liste_button.get(liste_button.size()-1).setVisible(false);
					liste_button.get(liste_button.size()-2).setVisible(false);
					}
				}
				if(jobs.size()==1&&jobs.getPaquet_Index(0).size()>2) {
					if(liste_button.size()>1) {
					liste_button.get(liste_button.size()-1).setVisible(false);
					}
				}
				
			}		
	}	
}
