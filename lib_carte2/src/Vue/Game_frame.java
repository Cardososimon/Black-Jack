package Vue;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.ControleurChoixCarteVersPaquet;
import Controleur.ControleurPiocheVersPaquet;
import Modele.Paquet;

public class Game_frame implements Observer {
	
	private JFrame fenetre;
	private JPanel base;
	private Abstracte_Vue pioche, main, defausse;
	private ControleurChoixCarteVersPaquet eventchoix;
	private ControleurPiocheVersPaquet eventpioche;

	public Game_frame(Abstracte_Vue pioche, Abstracte_Vue main, Abstracte_Vue defausse,
			ControleurChoixCarteVersPaquet eventchoix, ControleurPiocheVersPaquet eventpioche) {
		this.pioche = pioche;
		this.main = main;
		this.defausse = defausse;
		this.eventchoix = eventchoix;
		this.eventpioche = eventpioche;
		cree_affichage();
	}

	public void cree_affichage() {
		fenetre = new JFrame();
		JPanel jpioche, jmain, jdefausse;
		jpioche = pioche.creat_Panel();
		jmain = main.creat_Panel();
		jdefausse = defausse.creat_Panel();
		fenetre.setSize(1200, 900);
		base = new JPanel();
		base.setSize(fenetre.getSize());
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLayout(null);
		base.setLayout(null);
		jpioche.setLocation(10, 10);
		jmain.setLocation(10, 240);
		jdefausse.setLocation(10, 240 + 300);
		for (int i = 0; i < main.getListe_button().size(); i++) {
			main.getListe_button().get(i).addActionListener(eventchoix);
		}
		for (int i = 0; i < pioche.getListe_button().size(); i++) {
			pioche.getListe_button().get(i).addActionListener(eventpioche);
		}
		base.add(jpioche);
		base.add(jmain);
		base.add(jdefausse);
		fenetre.add(base);

		fenetre.setVisible(true);
	}

	public void refresh_frame() {
		base.removeAll();
		JPanel jpioche, jmain, jdefausse;
		jpioche = pioche.creat_Panel();
		jmain = main.creat_Panel();
		jdefausse = defausse.creat_Panel();
		fenetre.setSize(1200, 800);
		base.setSize(fenetre.getSize());
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLayout(null);
		base.setLayout(null);
		int height_main = jmain.getX();
		System.out.println(height_main);
		jpioche.setLocation(10, 10);
		jmain.setLocation(10, 240);
		jdefausse.setLocation(10, 240 + 300);
		base.add(jpioche);
		base.add(jmain);
		base.add(jdefausse);
		base.repaint();
		fenetre.add(base);

	}

	public Abstracte_Vue getPioche() {
		return this.pioche;
	}

	public Abstracte_Vue getMain() {
		return this.main;
	}

	public Abstracte_Vue getDefausse() {
		return this.defausse;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Paquet paquet = (Paquet) arg0;
		boolean test1 = paquet.ToString().equals(pioche.paquet.ToString());
		boolean test2 = paquet.ToString().equals(main.paquet.ToString());
		boolean test3 = paquet.ToString().equals(defausse.paquet.ToString());
		// System.out.println("j ai etait notifié "+paquet.getPaquet().size()+ " test
		// zone : "+test1+test2+test3);
		refresh_frame();
	}

}
