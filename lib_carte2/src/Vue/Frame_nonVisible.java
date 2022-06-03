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


public class Frame_nonVisible extends Abstracte_Vue {

	private ArrayList<Carte> liste_button = new ArrayList<>();

	public Frame_nonVisible(Paquet paquet) {
		super(paquet);
	}

//	public void affiche_simple() {
//		JFrame fenetre = new JFrame("paquet de carte");
//		int width = 20 + paquet.getPaquet().size() + 80;
//		int height = 1 + paquet.getPaquet().size() + 140;
//		fenetre.setSize(width, height);
//		fenetre.setLocationRelativeTo(null);
//		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		fenetre.setLayout(null);
//		JPanel frame = new JPanel();
//		frame.setSize(fenetre.getSize());
//		frame.setLayout(null);
//		frame.setLocation(0, 0);
//		for (int i = 0; i < paquet.getPaquet().size(); i++) {
//			paquet.getPaquet().get(i).affiche_dos_Carte();
//			paquet.getPaquet().get(i).setLocation((10 + (i * 1)), 1 + (i * 1));
//			paquet.getPaquet().get(i).setActionCommand(paquet.getPaquet().get(i).ToString());
//			frame.add(paquet.getPaquet().get(i));
//			liste_button.add(paquet.getPaquet().get(i));
//		}
//		fenetre.add(frame);
//		fenetre.setVisible(true);
//	}

	public JPanel creat_Panel() {
		int width = 20 + paquet.getPaquet().size() + 80;
		int height = 1 + paquet.getPaquet().size() + 140;
		this.setSize(width, height);
		this.setLayout(null);
		this.setLocation(0, 0);
		Carte bt;
		for (int i = 0; i < paquet.getPaquet().size(); i++) {
			paquet.getPaquet().get(i).affiche_dos_Carte();
			bt = paquet.getPaquet().get(i);
			bt.setLocation((10 + (i * 1)), 1 + (i * 1));
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
