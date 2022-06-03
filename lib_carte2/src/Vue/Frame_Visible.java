package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import Modele.Carte;
import Modele.Paquet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame_Visible extends Abstracte_Vue {

	private ArrayList<Carte> liste_button = new ArrayList<>();

	public Frame_Visible(Paquet paquet) {
		super(paquet);
	}

	public void affiche_simple() {
		JFrame fenetre = new JFrame("paquet de carte");
		int width = 20 + (paquet.getPaquet().size() * 22) + 66;
		fenetre.setSize(width, 140);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLayout(null);
		JPanel frame = new JPanel();
		frame.setSize(fenetre.getSize());
		frame.setLayout(null);
		frame.setLocation(0, 0);
		for (int i = 0; i < paquet.getPaquet().size(); i++) {
			System.out.println(paquet.getPaquet().get(i).affiche_face_Carte());
			paquet.getPaquet().get(i).affiche_face_Carte();
			paquet.getPaquet().get(i).setLocation((10 + (i * 22)), 1);
			paquet.getPaquet().get(i).setActionCommand(paquet.getPaquet().get(i).ToString());
			frame.add(paquet.getPaquet().get(i));
			liste_button.add(paquet.getPaquet().get(i));
		}
		fenetre.add(frame);
		fenetre.setVisible(true);
	}

	public JPanel creat_Panel() {
		int width=20+(paquet.getPaquet().size()*22)+66;
		this.setSize(width,140);
		this.setLayout(null);
		this.setLocation(0, 0);
		Carte bt;
		for (int i=0;i<paquet.getPaquet().size();i++) {
			paquet.getPaquet().get(i).affiche_face_Carte();
			bt=paquet.getPaquet().get(i);
			bt.setLocation((10+(i*22)), 1);
			bt.setActionCommand(bt.ToString());
			this.add(bt);
			liste_button.add(bt);
		}
		return this;
	}
	public JPanel creat_Panel2() {
		int nb = paquet.getPaquet().size();
		int mod = nb % 2;
		int moitier = nb / 2;
		int placement = 0;
		if (mod == 0) {
			placement = moitier * 15;
		} else {
			placement = (moitier + 1) * 15;
		}
		System.out.println(moitier + " " + placement);
		int width = 20 + (paquet.getPaquet().size() * 22) + 66;
		this.setSize(width, 140 + placement);
		this.setLayout(null);
		this.setLocation(0, 0);
		Carte bt;
		int hauteur = placement;
		for (int i = 0; i < paquet.getPaquet().size(); i++) {
			paquet.getPaquet().get(i).affiche_face_Carte();
			if (mod == 0) {
				if (i < moitier + 1) {
					if (i == moitier) {
						hauteur = hauteur;
					} else {
						hauteur -= 15;
					}
				} else {
					hauteur += 15;
				}
			} else {
				if (i < moitier + 1) {

					hauteur -= 15;
				} else {
					hauteur += 15;
				}

			}
			bt = paquet.getPaquet().get(i);
			bt.setLocation((10 + (i * 22)), 1 + hauteur);
			bt.setActionCommand(bt.ToString());
			this.add(bt);
			liste_button.add(bt);
		}
		return this;
	}

	public ArrayList<Carte> getListe_button() {
		return liste_button;
	}

	public void setListe_button(ArrayList<Carte> liste_button) {
		this.liste_button = liste_button;
	}
	public void desactiver_Carte() {
		for (int i=0;i<liste_button.size();i++) {
			liste_button.get(i).setEnabled (false);
		}
	}
	public void activer_Carte() {
		for (int i=0;i<liste_button.size();i++) {
			liste_button.get(i).setEnabled (true);
		}
	}
	
}
