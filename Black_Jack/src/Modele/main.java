package Modele;

import java.util.ArrayList;

import javax.swing.JButton;

import Event.Event;
import Vues.frame;

public class main {

	public static void main(String[] args) {

		frame game1 = new frame("moyen");

		Event event1 = new Event(game1);

		ArrayList<JButton> listebt1 = game1.getListe_button();
		for (int i = 0; i < listebt1.size(); i++) {
			listebt1.get(i).addActionListener(event1);
		}
	}

}
