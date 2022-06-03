package Modele.Joueur;


import Modele.Paquet;

public class Banquier extends Joueur {

	public Banquier (String nom,int jeton) {
		super(nom,jeton);
	}
/**
 * permet de créer deux paquets distints 
 * @param pioche représente le paquet pioche du jeux 
 */
	public void premierTour(Paquet pioche) {
		Paquet paquet1 = new Paquet (1,pioche);
		Paquet paquet2 = new Paquet (1,pioche);
		liste_paquet.add(paquet1);
		liste_paquet.add(paquet2);
	}
/**
 * permet au banquier de jouer un coup
 * @param pioche représente le paquet pioche du jeux
 */
	public void Jouer(Paquet pioche) {
		if (liste_paquet.size()==2) {
			liste_paquet.get(0).Add_Carte_fin(liste_paquet.get(1).getCarteIndex(0));
			liste_paquet.remove(1);
		}
		if (BlackJack()) {
			change_Etat();
		}
		while(getEtat()!=false) {
			int valeur = Valeur_Paquet(liste_paquet.get(0));
			if (valeur>21) {
				change_Etat();
			}
			else if (valeur<17) {
				this.liste_paquet.get(0).Add_Carte_fin(pioche.getCarteIndex(0));
				pioche.Del_Carte(0);
			}
			else {
				change_Etat();
			}
			
		}
		
	}
	@Override
	public void creat_mise(int mise) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getMise() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void creat_insuranceMise(int insurance_mise) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * permet de savoir si le banquier gagne l'assurance
	 * @return true si vrai sinon false
	 */
	public boolean Insurance_win() {
		if (liste_paquet.get(0).size()==2&&Valeur_Paquet(liste_paquet.get(0))==21) {
			return true;
		}
		else {
			return false;
		}
	}
	public String type() {
		return "banquier";
	}
}
