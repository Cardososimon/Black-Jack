package Modele;

import java.util.ArrayList;
import java.util.Observable;

public class Paquet extends Observable {

	private ArrayList<Carte> paquet = new ArrayList<>();
	private int nb_carte;
	private Paquet pioche;

	public Paquet() {

	}

	public Paquet(int nb_carte, Paquet pioche) {
		this.nb_carte = nb_carte;
		this.pioche = pioche;
		tirage_carte(pioche);
	}

	public int size() {
		return paquet.size();
	}

	public Carte getCarteIndex(int i) {
		return paquet.get(i);
	}

	public Paquet(Carte carte_morte) {
		paquet.add(carte_morte);
	}

	public void tirage_carte(Paquet pioche) {
		for (int i = 0; i < nb_carte; i++) {
			double ma_carte = Math.random() * (pioche.getPaquet().size());
			paquet.add(pioche.getPaquet().get((int) ma_carte));
			pioche.Del_Carte((int) ma_carte);
		}
	}

	public ArrayList<Carte> getPaquet() {
		return this.paquet;
	}

	public void SetPaquet(ArrayList<Carte> pioche) {
		this.paquet = pioche;
		this.setChanged();
		this.notifyObservers();
	}

	public int Quantite_Carte() {
		return paquet.size();
	}

	public void Melange_Carte() {
		ArrayList<Carte> tmp = new ArrayList<>();
		while (paquet.isEmpty() == false) {
			double ma_carte = Math.random() * (paquet.size());
			tmp.add(paquet.get((int) ma_carte));
			paquet.remove((int) ma_carte);
		}
		paquet = tmp;
		this.setChanged();
		this.notifyObservers();
	}

	public void Add_Carte_fin(Carte ma_carte) {
		paquet.add(ma_carte);
		this.setChanged();
		this.notifyObservers();
	}

	public void Add_Carte_debut(Carte ma_carte) {
		paquet.add(0, ma_carte);
		this.setChanged();
		this.notifyObservers();
	}

	public Carte Prendre_premiere_carte() {
		return paquet.get(0);
	}

	public void Del_Carte(Carte carte) {
		String val1 = carte.ToString();
		for (int i = 0; i < paquet.size(); i++) {
			String val2 = paquet.get(i).ToString();
			if (val1.equals(val2)) {
				paquet.remove(i);
			}
		}
		this.setChanged();
		this.notifyObservers();
	}

	public void Del_Carte(int Index_carte) {
		paquet.remove(Index_carte);
		this.setChanged();
		this.notifyObservers();
	}

	public Carte Jouer_Carte(int Index_Carte) {
		Carte ma_carte = paquet.get(Index_Carte);
		Del_Carte(Index_Carte);
		return ma_carte;
	}

	public void Couper_paquet() {
		ArrayList<Integer> possible = new ArrayList<>();

		for (int i = 4; i < (paquet.size() - 3); i++) {
			possible.add(i);
		}
		double coupe = Math.random() * (possible.size());
		int ou_coupe = (int) coupe;
		int couper_la = possible.get(ou_coupe);
		ArrayList<Carte> tmp = new ArrayList<>();
		ArrayList<Carte> tmp2 = new ArrayList<>();
		tmp.addAll(paquet.subList(couper_la - 1, paquet.size()));
		int ici = couper_la - 1;
		System.out.println(ici);
		tmp2.addAll(paquet.subList(0, ici));
		paquet = tmp;
		paquet.addAll(tmp2);
		this.setChanged();
		this.notifyObservers();
	}

	public String ToString() {
		String ma_chaine = Integer.toString(this.hashCode());
		return ma_chaine;
	}
	public boolean uneMeme_Carte(Paquet paquet2) {
		Carte ma_carte=paquet.get(0);
		boolean r=false;
		for (int i=0;i<paquet2.size();i++) {
			if (ma_carte.ToString().equals(paquet2.getCarteIndex(i).ToString())) {
				r=true;
			}
			else {
				r= false;
			}
		}
		return r;
	}
}
