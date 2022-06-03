package Modele;
import Modele.Joueur.*;
import Modele.Joueur.Groupe_Joueur;


import java.util.ArrayList;
/**
 * 
 * @author slurfy
 *
 */
public class Game {

	private Joueur jc;
	private Groupe_Joueur gp_joueur;
	private Banquier banquier;
	private Paquet pioche;
	private int indexJc;
/**
 * 
 * @param gp_joueur prend un groupe de joueurs en paramètre
 * @param pioche prend un paquet de cartes afin de créer la pioche
 */
	public Game(Groupe_Joueur gp_joueur,Paquet pioche) {
		this.gp_joueur = gp_joueur;
		this.jc = gp_joueur.getJoueur(1);
		this.banquier =(Banquier) gp_joueur.getJoueur(0);
		this.gp_joueur.remove_Banquier();
		this.pioche=pioche;
	}
/**
 * permet de savoir quel joueur doit jouer
 */
	public void creat_Jc() {
		if (gp_joueur.size()>1) {
			jc=gp_joueur.getJoueur(1);
			indexJc=1;
		}
	}
/**
 * permet de changer le joueur courant
 */
	public void changeJc() {
		if(gp_joueur.size()-1>indexJc) {
			indexJc+=1;
			jc=gp_joueur.getJoueur(indexJc);
		}
		else {
			jc=null;
		}
	}
/**
 * permet de donner deux cartes à tous les joueurs
 */
	public void give_deuxCarte() {
		for (int i = 0; i < gp_joueur.size(); i++) {
			Paquet two_carte = new Paquet(2, pioche);
			ArrayList<Paquet> liste_paquet = gp_joueur.getJoueur(i).getListe_Paquet();
			liste_paquet.add(two_carte);
			gp_joueur.getJoueur(i).setListe_Paquet(liste_paquet);
			creat_Jc();
		}
	}
/**
 * permet de redistribuer tous les jetons à la fin d'une manche
 */
	public void claimJeton() {
		int valeur =0;
		boolean insu=false;
		if (banquier.Insurance_win()) {
			insu=true;
		}
		if (banquier.Perdu(banquier.getPaquet_Index(0))==false) {
			valeur=banquier.Valeur_Paquet(banquier.getPaquet_Index(0));
		}
		for (int i=0;i<gp_joueur.size();i++) {
			gp_joueur.getJoueur(i).perdu_contreBanquer(valeur);
			if (gp_joueur.getJoueur(i).getInsuranceMise()!=0) {
				if (insu==true) {
					gp_joueur.getJoueur(i).lose_Insurance();
				}
				else {
					gp_joueur.getJoueur(i).win_Insurrance();
				}
			}
		}
	}
	/**
	 * permet à tous les joueurs de rendre leurs cartes à la pioche
	 */
	public void rendre_CartetoPioche() {
		for (int i=0;i<gp_joueur.size();i++) {
			gp_joueur.getJoueur(i).rendre_Carte(pioche);
			gp_joueur.getJoueur(i).dell_mise();
			gp_joueur.getJoueur(i).change_Etat();
		}
		banquier.rendre_Carte(pioche);
		System.out.println(pioche.size()+" pioche nb carte");
		banquier.change_Etat();
	}
	/**
	 * permet de supprimer un joueur qui a moins de 100 jetons car il ne peut plus miser
	 */
	public void dell_Joueur() {
		int j=0;
		for(int i=1;i<gp_joueur.size();i++) {
			if (gp_joueur.getJoueur(i-j).getJeton()<100) {
				gp_joueur.remove_Index(i-j);
				j+=1;
			}
		}
	}
	/**
	 * permet de faire jouer le joueur courant
	 */
	public void Play() {
		if (jc==null) {
			if (banquier.getEtat()==true) {
				banquier.Jouer(pioche);
			}
			else {
			}
		}
		else { 
			if(jc instanceof Bot) {
				Bot player =(Bot) jc;
				while(player.getEtat()) {
					player.jouer(banquier, pioche);
					System.out.println(player.getNom()+" "+player.getPaquet_Index(0).size());
				}
			}
			changeJc();
		}
	}
	/**
	 * permet de savoir si on a perdu
	 * @return true si on a perdu false sinon
	 */
	public boolean end_game() {
		if (gp_joueur.getJoueur(0).getJeton()<100) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * permet de savoir le nombre de personnes qui joue
	 * @return le nombre de personnes
	 */
	public int nb_Joueur() {
		return gp_joueur.size();
	}
	/**
	 * permet de sélectionner le joueur courant
	 * @return joueur courant
	 */
	public Joueur getJc() {
		return jc;
	}
/**
 * permet de changer le joueur courant
 * @param jc joueur courant
 */
	public void setJc(Joueur jc) {
		this.jc = jc;
	}
/**
 * permet de sélectionner le groupe de joueurs
 * @return groupe joueurs
 */
	public Groupe_Joueur getGp_joueur() {
		return gp_joueur;
	}
/**
 * permet de modifié le groupe de joueurs
 * @param gp_joueur nouveau groupe joueurs
 */
	public void setGp_joueur(Groupe_Joueur gp_joueur) {
		this.gp_joueur = gp_joueur;
	}
/**
 * permet de sélectionner le banquier de la partie
 * @return le banquier
 */
	public Banquier getBanquier() {
		return this.banquier;
	}
/**
 * permetde sélectionner le paquet pioche
 * @return paquet pioche
 */
	public Paquet getPioche() {
		return pioche;
	}
/**
 * permet de modifier le paquet pioche
 * @param pioche nouveau paquet pioche
 */
	public void setPioche(Paquet pioche) {
		this.pioche = pioche;
	}
/**
 * permet à tous les joueurs de miser
 */
	public void all_mise() {
		for (int i=0;i<gp_joueur.size();i++) {
			if (gp_joueur.getJoueur(i)instanceof Bot) {
				gp_joueur.getJoueur(i).creat_mise(0);
			}
		}
	}
}
