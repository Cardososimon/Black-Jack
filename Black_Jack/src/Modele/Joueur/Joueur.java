package Modele.Joueur;

import java.util.ArrayList;
import java.util.Observable;

import Modele.Carte;
import Modele.Paquet;

public abstract class Joueur extends Observable {

	private String nom;
	protected boolean etat;
	protected int jeton, insurance_mise;
	protected ArrayList<Integer> liste_mise = new ArrayList<>();
	protected ArrayList<Paquet> liste_paquet = new ArrayList<>();
/**
 * 
 * @param nom nom du joueur
 * @param jeton nombre de jetons du joueur
 */
	public Joueur(String nom, int jeton) {
		this.nom = nom;
		this.jeton = jeton;
		this.etat = true;
	}
/**
 * 
 * @return la mise totale faite par le joueur 
 */
	public int getMise() {
		int tmp = 0;
		for (int i = 0; i < liste_mise.size(); i++) {
			tmp += liste_mise.get(i);
		}
		return tmp;
	}
/**
 * 
 * @param index permet de savoir quel élément de la liste nous intéresse 
 * @return la mise à l'index 
 */
	public int getMiseindex(int index) {
		return liste_mise.get(index);
	}

	public int getInsuranceMise() {
		return insurance_mise;
	}

	public String getNom() {
		return this.nom;
	}

	public int getJeton() {
		return this.jeton;
	}

	public void setJeton(int jeton) {
		this.jeton = jeton;
	}

	public ArrayList<Paquet> getListe_Paquet() {
		return this.liste_paquet;
	}

	public int size() {
		return liste_paquet.size();
	}

	public void setListe_Paquet(ArrayList<Paquet> liste_paquet) {
		this.liste_paquet = liste_paquet;
	}
/**
 * 
 * @param index index du paquet que l'on veut récupérer
 * @return le paquet que nous voulons sélectionner
 */
	public Paquet getPaquet_Index(int index) {
		return liste_paquet.get(index);
	}
	public ArrayList<Integer> getListeMise(){
		return liste_mise;
	}
	public void setInsurance(int assurance) {
		this.insurance_mise=assurance;
	}
/**
 * permet de changer l'état d'un joueur afin de savoir si il a fini de jouer ou non 	
 */
	public void change_Etat() {
		if (etat) {
			etat = false;
		} else {
			etat = true;
		}
	}

	public boolean getEtat() {
		return this.etat;
	}
/**
 * 	
 * @param paquet permet de savoir sur quel paquet on travaille 
 * @return la valeur du paquet passé en paramètre 
 */
	public int Valeur_Paquet(Paquet paquet) {
		int compteur = 0;
		for (int i = 0; i < paquet.getPaquet().size(); i++) {
			compteur += Carte_getnewValeur(paquet.getPaquet().get(i));
		}
		return compteur;
	}
/**
 * ajoute aux jetons du joueur le gain généré par un blackJack
 */
	@SuppressWarnings("deprecation")
	public void winmise_BlackJack() {
		int mise = liste_mise.get(0);
		int r = mise * 2 + mise / 2;
		jeton += r;
		liste_mise.remove(0);
		liste_mise.add(0);
		this.setChanged();
		this.notifyObservers();
	}
/**
 * 
 * @param index permet de savoir de quelle mise on parle
 * permet d'ajouter aux jetons la mise gagné
 */
	@SuppressWarnings("deprecation")
	public void win_Mise(int index) {
		int r = liste_mise.get(index);
		jeton += r*2;
		liste_mise.remove(index);
		liste_mise.add(index, 0);
		this.setChanged();
		this.notifyObservers();
	}
/**
 * 
 * @param index permet de savoir de quelle mise on parle
 * on passe la mise index à 0 et on ne change pas jeton
 */
	@SuppressWarnings("deprecation")
	public void lose_Mise(int index) {
		liste_mise.remove(index);
		liste_mise.add(index, 0);
		this.setChanged();
		this.notifyObservers();
	}
/**
 * 
 * @param index permet de savoir de quelle mise on parle
 * ici on ajoute aux jetons la valeur de la mise
 */
	@SuppressWarnings("deprecation")
	public void egalite(int index) {
		jeton+=liste_mise.get(index);
		liste_mise.remove(index);
		liste_mise.add(index, 0);
		this.setChanged();
		this.notifyObservers();
	}
/**
 * 
 *  @return permet de savoir si le joueur peut ou non split son paquet
 **/
	public boolean CanSplit_Paquet() {
		if (liste_paquet.size() == 1 && liste_paquet.get(0).size() == 2) {
			int carte1, carte2;
			carte1 = Carte_getnewValeur(liste_paquet.get(0).getCarteIndex(0));
			carte2 = Carte_getnewValeur(liste_paquet.get(0).getCarteIndex(1));
			if (carte1 == carte2) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
/**
 * 
 * @param paquet permet de savoir dans quel paquet on travaille
 * @return permet de savoir pour un paquet donné si le joueur peut ou non piocher
 */
	public boolean cantake_Carte(Paquet paquet) {
		if (Valeur_Paquet(paquet) < 22) {
			return true;
		} else {
			return false;
		}
	}
/**
 * 
 * @return permet de savoir si le joueur a un blackJack ou non 
 */
	public boolean BlackJack() {
		int valeur = Carte_getnewValeur(liste_paquet.get(0).getCarteIndex(0))
				+ Carte_getnewValeur(liste_paquet.get(0).getCarteIndex(1));
		if (valeur == 21 && liste_paquet.size() == 1 && liste_paquet.get(0).size() == 2) {
			if (this instanceof Banquier==false) {
				winmise_BlackJack();
			}
			return true;
		} else {
			return false;
		}
	}
/**
 * 
 * @return permet de savoir si un joueur peut doubler sa mise ou non 
 *  */
	public boolean canDouble() {
		if (jeton - liste_mise.get(0) >= 0) {
			return true;
		} else {
			return false;
		}
	}
/**
 * 
 * @param bq représente le banquier du jeux
 * @return permet de savoir si on peut faire une mise Assurance ou non 
 */
	public boolean canAssurance(Banquier bq) {
		Integer r = liste_mise.get(0);
		if (r + (r / 2) <= jeton && bq.getPaquet_Index(0).getCarteIndex(0).getValeur().contains("as")) {
			return true;
		} else {
			return false;
		}
	}
/**
 * permet de split un paquet en deux
 * @param pioche représente le paquet pioche du jeux
 */
	@SuppressWarnings("deprecation")
	public void split_Paquet(Paquet pioche) {
		liste_mise.add(liste_mise.get(0));
		Carte carte = this.liste_paquet.get(0).getCarteIndex(1);
		Paquet paquet2 = new Paquet(carte);
		this.liste_paquet.add(paquet2);
		this.liste_paquet.get(0).Del_Carte(1);
		this.liste_paquet.get(0).Add_Carte_fin(pioche.getCarteIndex(0));
		pioche.Del_Carte(0);
		this.liste_paquet.get(1).Add_Carte_fin(pioche.getCarteIndex(0));
		pioche.Del_Carte(0);
		this.setChanged();
		this.notifyObservers();
	}
/**
 * 
 * @param c carte
 * @return permet de savoir la valeur d'une carte pour le jeux du blackJack
 */
	public int Carte_getnewValeur(Carte c) {
		if (c.getValeur().equals("as")) {
			return 11;
		} else if (c.getValeur().equals("10") || c.getValeur().equals("j") || c.getValeur().equals("q")
				|| c.getValeur().equals("k")) {
			return 10;
		} else {
			return Integer.valueOf(c.getValeur());
		}
	}
/**
 *  permet de doubler sa mise et de piocher une carte
 * @param pioche représente le paquet pioche du jeux
 */
	@SuppressWarnings("deprecation")
	public void Double(Paquet pioche) {
		jeton -= liste_mise.get(0);
		Integer valeur = liste_mise.get(0) * 2;
		liste_mise.remove(0);
		liste_mise.add(valeur);
		Carte ma_carte = pioche.getCarteIndex(0);
		pioche.Del_Carte(0);
		this.liste_paquet.get(0).Add_Carte_debut(ma_carte);
		this.setChanged();
		this.notifyObservers();
	}
/**
 * permet de changer les valeurs des JLabellObserver quand la valeur d'une des mises ou jetons change
 * @return une chaine qu'on affiche dans un jLabellObserver
 **/
	public String something_havechanged() {
		String machaine;
		//System.out.println("je suis dans modifier labell " + getMise());
		if (getMise() == 0 && insurance_mise == 0) {
			machaine = nom + " à : " + jeton + " jetons ";
			return machaine;
		} else if (getMise() != 0 && insurance_mise == 0) {
			machaine = "<html>" + nom + " à :" + jeton + " jetons <br> Vous avez misé : " + getMise()
					+ " jetons </html>";
			return machaine;
		} else if (getMise() != 0 && insurance_mise != 0) {
			machaine = "<html>" + nom + " à :" + jeton + " jetons, Vous avez misé : " + getMise()
					+ "jetons<br>Vous avez mis " + insurance_mise + " jetons en assurance </html>";
			return machaine;
		} else if (getMise() == 0 && insurance_mise != 0) {
			machaine = "<html>" + nom + " à :" + jeton + " jetons <br> Vous avez mis " + insurance_mise
					+ " jetons en assurance</html>";
			return machaine;
		} else {
			return "passer en Integer";
		}
	}
/**
 * 
 * @param paquet permet de savoir dans quel paquet on se situe
 * @return permet de savoir si le joueur a perdu car son paquet à une valeur supp à 21 
 */
	public boolean Perdu(Paquet paquet) {
		if (Valeur_Paquet(paquet) > 21) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public void tout_Perdu() {
		for (int i = 0; i < size(); i++) {
			if (Perdu(liste_paquet.get(i))) {
				lose_Mise(i);
				this.setChanged();
				this.notifyObservers();
			}
		}

	}
/**
 * 
 * @return les mises possibles pour le choix de l'assurance
 */
	public ArrayList<Integer> insuranceMise_Possible() {
		ArrayList<Integer> liste_mise2 = new ArrayList<>();
		for (int i = 2; i <= 6; i += 2) {
			if (liste_mise.get(0) / i > 20) {
				liste_mise2.add(liste_mise.get(0) / i);
			}
		}
		return liste_mise2;
	}
/**
 * cette methode permet de savoir si on a gagné ou perdu contre le banquier
 * @param valeurBanquier représente la valeur du paquet du banquier
 */
	public void perdu_contreBanquer(int valeurBanquier) {
		for(int i=0;i<liste_mise.size();i++) {
			if (liste_mise.get(i)!=0) {
				if (Valeur_Paquet(liste_paquet.get(i))>valeurBanquier) {
					win_Mise(i);
				}
				else if(Valeur_Paquet(liste_paquet.get(i))==valeurBanquier) {
					egalite(i);
				}
				else {
					lose_Mise(i);
				}
			}
		}
	}
/**
 * permet d'ajouter toutes les cartes à la pioche afin de pouvoir faire une nouvelle manche
 * @param pioche représente le paquet pioche du jeux
 */
	public void rendre_Carte(Paquet pioche) {
		for (int i=0;i<size();i++) {
			for (int j=0;j<getPaquet_Index(i).size();j++) {
				pioche.Add_Carte_debut(getPaquet_Index(i).getCarteIndex(j));
			}
		}
		liste_paquet.removeAll(liste_paquet);
	}
/**
 * permet de remettre toutes les mises à 0 pour la nouvelle manche
 */
	public void dell_mise() {
		liste_mise.removeAll(liste_mise);
	}

	@SuppressWarnings("deprecation")
	public void lose_Insurance() {
		insurance_mise=0;
		this.setChanged();
		this.notifyObservers();
	}
	@SuppressWarnings("deprecation")
	public void win_Insurrance() {
		jeton+=insurance_mise*2;
		insurance_mise=0;
		this.setChanged();
		this.notifyObservers();
	}
	public abstract void creat_mise(int mise);
	public abstract void creat_insuranceMise(int insurance_mise);
	public abstract String type();
}
