package Vue;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Modele.Carte;
import Modele.Paquet;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Abstracte_Vue extends JPanel {

	protected Paquet paquet;

	public Abstracte_Vue(Paquet paquet) {
		this.paquet = paquet;
	}

	public Paquet getPaquet() {
		return this.paquet;
	}

	public abstract JPanel creat_Panel();
	public abstract void desactiver_Carte();
	public abstract void activer_Carte();
	public abstract ArrayList<Carte> getListe_button();
//	public void update(Observable arg0, Object arg1) {
//		System.out.println("je suis notifié");
//		Paquet paquet = (Paquet) arg0;
//		creat_Panel();
//		int width=this.getParent().getWidth();
//		int height=this.getParent().getHeight();
//		this.setLocation(10,60);
//		//this.getParent().repaint();
//	}
}



	
