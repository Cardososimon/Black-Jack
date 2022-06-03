package Modele.Joueur;

import java.util.ArrayList;

public class Groupe_Joueur {

	private ArrayList<Joueur> liste_joueur;
/**
 * class qui permet de créer un groupe de joueurs
 * @param liste_joueur
 */
	public Groupe_Joueur(ArrayList<Joueur> liste_joueur) {
		this.liste_joueur = liste_joueur;
	}
	/**
	 * permet de sélectionner le groupe de joueurs
	 * @return groupe joueurs
	 */
	public ArrayList<Joueur> getListe_Joueur(){
		return this.liste_joueur;
	}
	/**
	 * permet de savoir combien de joueurs sont dans le groupe
	 * @return nombre de joueurs dans groupe joueurs
	 */
	public int size() {
		return liste_joueur.size();
	}
	/**
	 * permet de supprimer un joueur à un index donné
	 * @param index index du joueur dans la liste que l'on doit supprimer
	 */
	public void remove_Index(int index) {
		liste_joueur.remove(index);
	}
	/**
	 * permet de sélectionner un joueur à un index donné
	 * @param index index index du joueur dans la liste que l'on doit sélectionner
	 * @return le joueur à l'index donné
	 */
	public Joueur getJoueur(int index) {
		return liste_joueur.get(index);
	}
	/**
	 * permet d'ajouter un groupe de joueurs au groupe de joueurs
	 * @param groupe représente le groupe de joueurs que l'on ajoute à ce groupe de joueurs
	 */
	public void add_Groupe(Groupe_Joueur groupe) {
		for (int i=0;i<groupe.size();i++) {
			liste_joueur.add(groupe.getJoueur(i));
		}
	}
	/**
	 * permet d'ajouter un bot au groupe de joueurs
	 */
	public void add_Player() {
		ArrayList<String> liste_nom = new ArrayList<>();
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
		for (int i = 0; i < liste_joueur.size(); i++) {
			for (int j = 0; j < liste_nom.size(); j++) {
				if (getJoueur(i).getNom().equals(liste_nom.get(j))) {
					liste_nom.remove(j);
				}
			}
		}
		int rand = (int) (Math.random() * (liste_nom.size() - 1));
		String name = liste_nom.get(rand);
		Joueur bot = new Bot(name, 1000);
		liste_joueur.add(bot);
	}
/**
 * permet de supprimer un joueur du groupe
 */
	public void remove_Player() {
		int last = liste_joueur.size();
		liste_joueur.remove(last - 1);
	}
/**
 * permet de supprimer le banquier du groupe
 */
	public void remove_Banquier() {
		liste_joueur.remove(0);
	}
/**
 * permet d'ajouter des jetons à tous les membres du groupe excepté le banquier
 */
	public void add_Jeton() {
		for (int i = 1; i < liste_joueur.size(); i++) {
			getJoueur(i).setJeton(getJoueur(i).getJeton() + 100);
		}
	}
	/**
	 * permet de supprimer toutes les personnes du groupe
	 */
	public void removeAll() {
		liste_joueur.removeAll(liste_joueur);
	}
}
