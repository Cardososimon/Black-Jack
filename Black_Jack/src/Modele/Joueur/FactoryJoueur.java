package Modele.Joueur;

import java.util.ArrayList;

public class FactoryJoueur {
	
	static private ArrayList<Joueur> liste_joueur=new ArrayList<>();
	static private Groupe_Joueur gp_joueur;
	/**
	 * class qui permet de créer tous les joueurs et de renvoyer un groupe de joueurs
	 * @param nb_joueur permet de savoir le nombre de joueur que l'on doit créer
	 * @param name représente le nom du joueur humain
	 */
	public FactoryJoueur(int nb_joueur,String name) {
		ArrayList<String> liste_nom=add_nom();
		if (nb_joueur<1 || nb_joueur>5) {
			System.out.println("pas possible de faire ça");
		}
		else {
		for (int i=0;i<nb_joueur;i++) {
			if (i==0) {
				Joueur banquier = new Banquier("Banquier",10000);
				Joueur humain = new Humain(name,1000);
				liste_joueur.add(banquier);
				liste_joueur.add(humain);
			}
			else {
				Joueur bot = new Bot (choix_name(liste_nom),1000);
				liste_joueur.add(bot);
			}
		}
		 gp_joueur = new Groupe_Joueur(liste_joueur);
	}
	}
	/**
	 * permet de créer une liste de nom pour les bots
	 * @return liste nom
	 */
	static private ArrayList<String>  add_nom() {
		ArrayList<String> liste_nom=new ArrayList<>();
		liste_nom.add("AARON");
		liste_nom.add("COLINE");
		liste_nom.add("ADRIEN");
		liste_nom.add("ANGE");
		liste_nom.add("CANDIDE");
		liste_nom.add("ALADDIN");
		liste_nom.add("MERLIN");
		liste_nom.add("ANGEL");
		liste_nom.add("ATTILA");
		liste_nom.add("GIUSEPPE");
		return liste_nom;
	}
	/**
	 * permet pour un bot de sélectionner un nom parmi une liste de noms
	 * @param liste_nom liste de tous les noms possibles
	 * @return le nom choisi pour le bot
	 */
	static private String choix_name(ArrayList<String> liste_nom) {
		int rand=(int) (Math.random()* (liste_nom.size()-1));
		String name =liste_nom.get(rand);
		liste_nom.remove(rand);
		return name;
	}
	/**
	 * 
	 * @return groupe de joueur 
	 */
	static public  Groupe_Joueur getGroupe_Joueur() {
		return gp_joueur;
	}
}
