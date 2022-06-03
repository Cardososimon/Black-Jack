package Modele;

import java.util.ArrayList;

public class Factory_Paquet {

	private static Paquet paquet = new Paquet();

	public Factory_Paquet(int nb_carte) {
		Cree_paquet(nb_carte);
	}

	static ArrayList<String> Remplir_Valeur_Carte() {
		ArrayList<String> valeur_carte = new ArrayList<>();
		for (int i = 2; i < 11; i++) {
			valeur_carte.add(Integer.toString(i));
		}
		valeur_carte.add("j");
		valeur_carte.add("q");
		valeur_carte.add("k");
		valeur_carte.add("as");
		return valeur_carte;
	}

	private static Paquet Cree_paquet(int nb_carte) {
		ArrayList<String> valeur_carte = Remplir_Valeur_Carte();
		if (nb_carte == 52) {
			for (int i = 0; i < 13; i++) {
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "Coeur"));
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "pique"));
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "trefle"));
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "carreau"));
			}
		}
		if (nb_carte == 32) {
			for (int i = 5; i < 13; i++) {
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "Coeur"));
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "pique"));
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "trefle"));
				paquet.Add_Carte_debut(new Carte(valeur_carte.get(i), "carreau"));
			}
		}
		return paquet;
	}

	public static Paquet getPaquet() {
		return paquet;
	}
}
