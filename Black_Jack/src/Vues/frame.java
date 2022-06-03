package Vues;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import Event.Event;
import Modele.Joueur.Banquier;
import Modele.Joueur.Groupe_Joueur;
import Modele.Joueur.Humain;
import Modele.Joueur.Joueur;
import Modele.Paquet;
import Vues.vue_Joueur.Abstract_vue;
import Vues.vue_Joueur.Vue_Banq;
import Vues.vue_Joueur.Vue_Bot;
import Vues.vue_Joueur.Vue_Humain;

public class frame {
	
	private JFrame jeux=new JFrame("blackJack");
	private JPanel accueil=new JPanel();
	private String size;
	private JLabel titre =new JLabel("BlackJack");
	private JLabel imgAccueil;
	private JDialog dial;
	private ArrayList<JTextField>liste_textf=new ArrayList<>();
	private ArrayList<JButton> liste_button =new ArrayList<>();
	private ArrayList<JPanel>liste_panPlayer=new ArrayList<>();
	private ArrayList<Abstract_vue>liste_VuepanPlayer=new ArrayList<>();
	/**
	 * permet de créer l'interface graphique du jeux
	 * @param size permet de choisir la taille du jeux
	 */
public frame(String size) {
	this.size=size;
	Creat_frame_game();
}
/**
 * permet d'afficher la première page
 */
	public void Creat_frame_game() {
		jeux.setTitle("BlackJack");
		if (size.equals("petit")){
			jeux.setSize(960, 720);
		}
		else if (size.equals("moyen")) {
			jeux.setSize(1400, 900);
		}
		else if (size.equals("grand")) {
			jeux.setSize(1900,1060);
			}
		else {
			System.out.println("se n'est pas un argument valide");
		}
			jeux.setLocationRelativeTo(null);
			jeux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Font fonttitre = new Font("Arial",Font.BOLD,50);
			titre.setFont(fonttitre);
			File directory = new File ("./");
			String file=directory.getAbsolutePath();
			if (file.contains("dist/.")){
				file=file.substring(0,file.indexOf("dist/."));
				file=file+="src/table_blackjack.png";
			}
			else {
				file=file.substring(0,file.indexOf("."));
				file=file+="src/table_blackjack.png";
			}
			ImageIcon image = new ImageIcon(file);
			imgAccueil= new JLabel(image);
			
			titre.setLayout(null);
			imgAccueil.setLayout(null);
			accueil.setLayout(null);
			
			accueil.setSize(jeux.getSize());
			accueil.setBackground(new Color(165,89,8));
			titre.setSize(300, 50);
			titre.setForeground(new Color(255,255,255));
			int titre_placement=jeux.getWidth()/2-100;
			int image_placement_width=(jeux.getWidth()-900)/2;
			titre.setLocation(titre_placement,20);
			imgAccueil.setSize(900, 600);
			imgAccueil.setLocation(image_placement_width,50);	
			
			for (int i=0;i<5; i++) {
				Font fontbt = new Font("Arial",Font.BOLD,20);
				int placement=0;
				int placement2=0;
				if (i==0) {
					JButton button =new JButton(""+1+" joueur");
					liste_button.add(button);
				}
				else {
					int nb_joueur=i+1;
					JButton button =new JButton(""+nb_joueur+" joueurs");
					liste_button.add(button);
				}
				int nb_joueur=i+1;
				liste_button.get(i).setActionCommand("nombre_joueur:"+nb_joueur);
				if (size.equals("grand")) {
					liste_button.get(i).setSize(200, 200);
					placement=(150*(i+1))+(200*i);
					placement2=750;
				}
				else if (size.equals("moyen")) {
					liste_button.get(i).setSize(150, 150);
					placement=(108*(i+1))+(150*i);
					placement2=650;
				}
				else if (size.equals("petit")) {
					liste_button.get(i).setSize(80, 80);
					placement=(93*(i+1))+(80*i);
					placement2=610;
				}
				liste_button.get(i).setLocation(placement, placement2);
				liste_button.get(i).setBackground(new Color(132,72,8));
				liste_button.get(i).setFont(fontbt);
				liste_button.get(i).setForeground(new Color(255,255,255));
				accueil.add(liste_button.get(i));
			}
			
			accueil.add(titre);
			accueil.add(imgAccueil);
			jeux.add(accueil);
		jeux.setVisible(true);
	}
	/**
	 * permet de vider la liste des buttons
	 */
	public void vider_liste_button() {
		for (int i=liste_button.size()-1;i>=0;i--) {
			accueil.remove(liste_button.get(i));
			liste_button.remove(i);
		}
	}
	/**
	 * permet de vider la liste des jtextfield
	 */
	public void vider_liste_textf() {
		for (int i=liste_textf.size()-1;i>=0;i--) {
			accueil.remove(liste_textf.get(i));
			liste_textf.remove(i);
		}
	}
	/**
	 * permet d'afficher la page où le joueur humain doit renseigner son pseudo
	 */
	public void give_name_frame() {
		
		Font fonttextf = new Font("Arial",Font.BOLD,20);
		JTextField give_name=new JTextField(" entrez votre nom ex: Titeuf");
		JButton bt_valide =new JButton("Validez votre Pseudo");
		int placement_jtextf=liste_button.get(0).getY();
		vider_liste_button();
		give_name.setSize(400, 80);
		bt_valide.setSize(400, 80);
		give_name.setLocation((jeux.getWidth()/2)-200,placement_jtextf);
		bt_valide.setLocation((jeux.getWidth()/2)-200,give_name.getY());
		give_name.setBackground(new Color(132,72,8));
		bt_valide.setBackground(new Color(132,72,8));
		give_name.setForeground(new Color(255,255,255));
		bt_valide.setForeground(new Color(255,255,255));
		give_name.setFont(fonttextf);
		bt_valide.setFont(fonttextf);
		bt_valide.setActionCommand("valide_name");
		give_name.setActionCommand("vider_texte");
		liste_button.add(bt_valide);
		liste_textf.add(give_name);
		
		accueil.add(give_name);
		accueil.add(bt_valide);
		accueil.repaint();
	}
	/**
	 * page permettant de faire un bref récapitulatif des joueurs et permet d'en supprimer ou ajouter 
	 * et d'ajouter des jetons à tous les joueurs
	 * @param gp_joueur représente le groupe de joueurs qui va composer la partie
	 */
public void resume_frame(Groupe_Joueur gp_joueur) {
		accueil.removeAll();
		vider_liste_button();
		Font fontlab = new Font("Arial",Font.BOLD,15);
		System.out.println(gp_joueur.size()+" = nb joueur");
		int titre_placement=jeux.getWidth()/2-100;
		int compteur=0;
		JLabel lb_infoNom = new JLabel("NOM :");
		JLabel lb_infoJeton = new JLabel ("JETONS :");
		lb_infoNom.setSize(300, 50);
		lb_infoJeton.setSize(300, 50);
		lb_infoNom.setForeground(new Color(255,255,255));
		lb_infoJeton.setForeground(new Color(255,255,255));
		lb_infoNom.setLocation(titre_placement/2,120);
		lb_infoJeton.setLocation(titre_placement,120);
		lb_infoNom.setFont(fontlab);
		lb_infoJeton.setFont(fontlab);
		for (int i=0;i<gp_joueur.size();i++) {
			Joueur j=gp_joueur.getJoueur(i);
			JLabel lbnom = new JLabel(j.getNom());
			JLabel lbjeton = new JLabel(String.valueOf(j.getJeton()));
			lbnom.setSize(300, 50);
			lbjeton.setSize(300, 50);
			lbnom.setForeground(new Color(255,255,255));
			lbjeton.setForeground(new Color(255,255,255));
			lbnom.setLocation(titre_placement/2,150+i*30);
			lbjeton.setLocation(titre_placement,150+i*30);
			lbnom.setFont(fontlab);
			lbjeton.setFont(fontlab);
			accueil.add(lbjeton);
			accueil.add(lbnom);
			compteur=i;
		}
		JButton bt_addPlayer=new JButton("Ajouter un joueur");
		JButton bt_removePlayer=new JButton("Suprimer un joueur");
		JButton bt_addJeton = new JButton("Ajouter 100 jeton");
		JButton bt_Game = new JButton ("Commencer à jouer ");
		bt_addPlayer.setSize(200,50);
		bt_removePlayer.setSize(200,50);
		bt_addJeton.setSize(200,50);
		bt_Game.setSize(200,50);
		bt_addPlayer.setForeground(new Color(255,255,255));
		bt_removePlayer.setForeground(new Color(132,72,8));
		bt_addJeton.setForeground(new Color(255,255,255));
		bt_Game.setForeground(new Color(132,72,8));
		bt_addPlayer.setLocation(titre_placement/2, compteur*30+150+50);
		bt_removePlayer.setLocation(titre_placement, compteur*30+150+50);
		bt_addJeton.setLocation(titre_placement+titre_placement/2, compteur*30+150+50);
		bt_Game.setLocation(titre_placement, compteur*30+150+50+100);
		bt_addPlayer.setBackground(new Color(132,72,8));
		bt_removePlayer.setBackground(new Color(255,255,255));
		bt_addJeton.setBackground(new Color(132,72,8));
		bt_Game.setBackground(new Color(255,255,255));
		bt_addPlayer.setActionCommand("add_player");
		bt_removePlayer.setActionCommand("remove_player");
		bt_addJeton.setActionCommand("add_jeton");
		bt_Game.setActionCommand("come_game");
		liste_button.add(bt_Game);
		if (compteur<5) {
			accueil.add(bt_addPlayer);
			liste_button.add(bt_addPlayer);
		}
		if (compteur>1) {
			accueil.add(bt_removePlayer);
			liste_button.add(bt_removePlayer);
		}
		if (gp_joueur.getJoueur(1).getJeton()<2000) {
			accueil.add(bt_addJeton);
			liste_button.add(bt_addJeton);
		}
		accueil.add(bt_Game);
		accueil.add(lb_infoJeton);
		accueil.add(lb_infoNom);
		accueil.add(titre);
		accueil.repaint();
	}

/**
 * permet de sélectionner les buttons de la vue
 * @return liste buttons
 */
	public ArrayList<JButton> getListe_button(){
		return liste_button;
	}
	public void setListe_button(ArrayList<JButton> liste_button){
		this.liste_button=liste_button;
	}
	/**
	 * page qui permet de choisir pour le joueur humain une mise
	 * @param gp_Joueur représente le groupe de joueurs qui joue
	 * @param pioche représente le paquet pioche du jeux
	 * @param bq représente le banquier du jeux
	 */
	public void gameMise_frame(Groupe_Joueur gp_Joueur,Paquet pioche,Banquier bq) {
	vider_liste_button();
	vider_liste_textf();
	accueil.removeAll();
	int width=accueil.getWidth()/3;
	int height=accueil.getHeight()/2;
	Abstract_vue vuebanq = new Vue_Banq(width,height,0,bq);
	liste_VuepanPlayer.add(vuebanq);
	vuebanq.setLocation(width,0);
	accueil.add(vuebanq.mise(pioche));
	Abstract_vue vue;
	System.out.println(gp_Joueur.size()+" nb joueur a miser");
	for (int i=0;i<gp_Joueur.size();i++) {
		if (gp_Joueur.getJoueur(i)instanceof Humain) {
			 vue = new Vue_Humain(width,height,i,gp_Joueur.getJoueur(i),((Vue_Banq)vuebanq).getPioche());
			 //gp_Joueur.getJoueur(i).addObserver(vue);
			 setListe_button(((Vue_Humain) vue).getListe_button());
		}
		else {
			vue = new Vue_Bot(width,height,i,gp_Joueur.getJoueur(i),((Vue_Banq)vuebanq).getPioche());
		}
		accueil.add(vue.mise(pioche));
		liste_VuepanPlayer.add(vue);
		if (i==1) {
			vue.setLocation(0, height);
		}
		if (i==2) {
			vue.setLocation(width*2, height);
		}
		if (i==3) {
			vue.setLocation(0,0);;
		}
		if (i==4) {
			vue.setLocation(width*2, 0);
		}
	}
	accueil.repaint();
	}
	/**
	 * permet de créer une alerte permettant au joueur de choisir une mise pour son Assurance si il a decidé
	 * d'en faire une
	 * @param joueur représente le joueur humain
	 * @param event représente l'événement que l'on doit ajouter au button afin que l'on puisse faire des 
	 * actions avec ces derniers
	 */
	public void messageBox(Joueur joueur,Event event) {
		 ArrayList<Integer>liste=joueur.insuranceMise_Possible();
		 dial=new JDialog(jeux,"Assurance mise");
		 dial.setLocationRelativeTo(null);
		 dial.setSize(400, 300);
		 dial.setLayout(null);
		 dial.setBackground(new Color(132,72,8));
		 JLabel texte=new JLabel("choisisez votre mise :");
		 texte.setSize(200, 30);
		 texte.setLocation(120, 20);
		 dial.add(texte);
		 System.out.println(liste.size()+" liste . size");
		 for (int i=0;i<liste.size();i++) {
			 System.out.println(i);
			 JButton bt = new JButton(liste.get(i)+"");
			 bt.setActionCommand("assurance :"+liste.get(i));
			 bt.addActionListener(event);
			 bt.setSize(80, 50);
			 bt.setLocation(40*(i+1)+80*i, 200);
			 dial.add(bt);
			 
		 }
		 dial.setVisible(true);
	}
	/**
	 * permet de modifier le nom et l'action d'un button
	 */
	public void give_allMise() {
		liste_button.get(0).setText("claim jetons");
		liste_button.get(0).setActionCommand("claim");
	}
	/**
	 * permet de créer la vue où les joueurs ont leurs cartes
	 * @param gp_Joueur représente le groupe de joueurs de la partie
	 * @param pioche représente le paquet pioche de la partie
	 * @param pbanq représente le paquet du joueur banquier
	 */
	public void game_frame(Groupe_Joueur gp_Joueur,Paquet pioche,Banquier pbanq) {
		for (int i=0;i<liste_VuepanPlayer.size();i++) {
			liste_VuepanPlayer.get(i).choix(gp_Joueur, pioche, pbanq);
		}
		accueil.repaint();
	}
	public void Stand_frame() {
		for (int i=0;i<liste_VuepanPlayer.size();i++) {
			liste_VuepanPlayer.get(i).fin_choix();
		}
		accueil.repaint();
	}
	public ArrayList<JTextField> getListe_textf(){
		return liste_textf;
	}
	public void setListe_textf(ArrayList<JTextField> liste_textf){
		this.liste_textf=liste_textf;
	}
	public ArrayList<Abstract_vue> getAbstract_Vue(){
		return this.liste_VuepanPlayer;
	}
	public void fermer_Jd(Joueur j) {
		 dial.setVisible(false);
		 if(j.getInsuranceMise()!=0) {
			 liste_button.get(1).setVisible(false);
		 }
	}
	/**
	 * permet de changer l'affichage et la commande d'un button
	 */
	public void nexte_round() {
		liste_button.get(0).setText("new round");
		liste_button.get(0).setActionCommand("next_round");
	}
	/**
	 * permet de vider toutes les arraylist 
	 */
	public void tout_vider() {
		liste_button.removeAll(liste_button);
		liste_panPlayer.removeAll(liste_panPlayer);
		liste_VuepanPlayer.removeAll(liste_VuepanPlayer);
	}
	/**
	 * permet du supprimer les actions des cartes et de les rendre re-cliquables
	 * @param pioche représente le paquet pioche du jeux
	 */
	public void rendre_carteNormale(Paquet pioche) {
		for (int i=0;i<pioche.size();i++) {
			pioche.getCarteIndex(i).setEnabled (true);
			for (ActionListener listener : Arrays.asList(pioche.getCarteIndex(i).getActionListeners())) {
				pioche.getCarteIndex(i).removeActionListener(listener);
			}
		}
	}
	/**
	 * permet d'afficher la page permettant de savoir si le joueur humain souhaite re-faire une partie
	 * ou bien quitter le jeux après ne plus avoir assez de jetons pour poursuivre sa partie
	 * @param j représente le joueur humain
	 */
	public void fin_game(Joueur j) {
		tout_vider();
		accueil.removeAll();
		ImageIcon image = new ImageIcon("./src/table_blackjack.png");
		imgAccueil= new JLabel(image);
		imgAccueil.setLayout(null);
		int image_placement_width=(jeux.getWidth()-900)/2;
		imgAccueil.setSize(900, 600);
		imgAccueil.setLocation(image_placement_width,50);
		accueil.add(imgAccueil);
		
		JLabel perdu = new JLabel (j.getNom()+" vous avez perdu =(");
		Font fonttitre = new Font("Arial",Font.BOLD,40);
		perdu.setFont(fonttitre);
		perdu.setLayout(null);
		perdu.setSize(accueil.getWidth()-40, 50);
		perdu.setForeground(new Color(255,255,255));
		//int titre_placement=jeux.getWidth()/2-100;
		perdu.setLocation(20,20);
		accueil.add(perdu);
		JButton button = null;
		for (int i=0;i<2;i++) {
		if (i==0) {
			button =new JButton("New game");
			button.setActionCommand("newpartie");
		}
		else {
			button=new JButton("Leave game");
			button.setActionCommand("leavegame");
		}
		int placement =(accueil.getWidth()-200)/3;
		button.setSize(200,50);
		button.setLocation(placement+i*placement,accueil.getHeight()-100);
		button.setBackground(new Color(132,72,8));
		button.setForeground(new Color(255,255,255));
		liste_button.add(button);
		accueil.add(button);
		}
		
		accueil.repaint();
	}
	public void close() {
		jeux.dispose();
	}
	public void dell_toutComponent() {
		accueil.removeAll();
		liste_button.removeAll(liste_button);
	}
}

