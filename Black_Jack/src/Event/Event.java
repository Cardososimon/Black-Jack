package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modele.Joueur.FactoryJoueur;
import Modele.Factory_Paquet;
import Modele.Game;
import Modele.Paquet;
import Modele.Joueur.Groupe_Joueur;

import Vues.frame;
/**
 * 
 * @author slurfy
 *
 */
public class Event implements ActionListener {

	private frame page;
	private Groupe_Joueur gp_joueur;
	private Game game;
	private int nb_joueurs;
	private Paquet pioche;
/**
 * 
 * @param page prend en paramètre la page de jeux 
 */
	public Event(frame page) {
		this.page = page;

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String element_executer = arg0.getActionCommand();
		if (element_executer.contains("nombre_joueur:")) {
			String nb_joueur = element_executer.substring(element_executer.length() - 1);
			nb_joueurs = Integer.valueOf(nb_joueur);
			page.give_name_frame();
			page.getListe_button().get(0).addActionListener(this);
			page.getListe_textf().get(0).addActionListener(this);

		} else if (element_executer.contains("vider_texte")) {
			System.out.println("je suis dans vider texte");
			if (page.getListe_textf().get(0).getText().equals(" entrez votre nom ex: Titeuf")) {
				page.getListe_textf().get(0).setText("");
			} else if (page.getListe_textf().get(0).getText().equals("")) {
				System.out.println("taper votre pseudo");
			} else {
				System.out.println("tout est bon");
				new FactoryJoueur(nb_joueurs, page.getListe_textf().get(0).getText());
				gp_joueur = FactoryJoueur.getGroupe_Joueur();
				page.resume_frame(gp_joueur);
				add_actionListener();
			}
		} else if (element_executer.contains("add_player")) {
			System.out.println("add player");
			gp_joueur.add_Player();
			page.resume_frame(gp_joueur);
			add_actionListener();
		} else if (element_executer.contains("remove_player")) {
			System.out.println("remove player");
			gp_joueur.remove_Player();
			page.resume_frame(gp_joueur);
			add_actionListener();
		} else if (element_executer.contains("add_jeton")) {
			gp_joueur.add_Jeton();
			page.resume_frame(gp_joueur);
			add_actionListener();

		} else if (element_executer.contains("come_game")) {
			if(pioche==null) {
			pioche=new Factory_Paquet(52).getPaquet();
			pioche = Factory_Paquet.getPaquet();
			}
			game = new Game(gp_joueur,pioche);
			game.getPioche().Melange_Carte();
			System.out.println(game.getGp_joueur().size()+" nb joueur");
			page.gameMise_frame(game.getGp_joueur(),game.getPioche(),game.getBanquier());
			add_actionListener();
			//page.add_ObservertoLabell(game.getGp_joueur());
		} else if (element_executer.contains("mise : ")) {
			int ou=element_executer.indexOf(":");
			String ma_valeur=element_executer.substring(ou+2);
			int result = Integer.parseInt(ma_valeur);
			game.all_mise();
			game.getGp_joueur().getJoueur(0).creat_mise(result);
			game.give_deuxCarte();
			game.getBanquier().premierTour(game.getPioche());
			page.game_frame(game.getGp_joueur(),game.getPioche(),game.getBanquier());
			//page.add_Observer(game.getBanquier(),game.getGp_joueur());
			add_actionListener();
		}else if (element_executer.contains("Stand")) {
			System.out.println("Stand ");
			page.Stand_frame();
			add_actionListener();
			page.getAbstract_Vue().get(1).getVuePaquet().desactiver_touteCarte();
		}else if (element_executer.contains("Double")) {
			System.out.println("Double");
			game.getGp_joueur().getJoueur(0).Double (game.getPioche());
			page.Stand_frame();
			page.getAbstract_Vue().get(1).getVuePaquet().desactiver_touteCarte();
			add_actionListener();
			//game.getGp_joueur().getJoueur(1).tout_Perdu();
			//tout le reste joue
		}else if (element_executer.contains("Insurance")) {
			System.out.println("Insurance ");
			page.messageBox(game.getGp_joueur().getJoueur(0),this);
		}else if (element_executer.contains("Split")) {
			System.out.println("Split");
			game.getGp_joueur().getJoueur(0).split_Paquet(game.getPioche());	
		}else if (element_executer.contains("next_player")) {
			System.out.println("next player");
			game.Play();
			
			if (game.getBanquier().getEtat()==false) {
				page.give_allMise();
			}
		}else if (element_executer.contains("claim")) {
			System.out.println("claim");
			game.claimJeton();
			if(game.end_game()) {
				System.out.println("je suis la dans end_game");
				page.fin_game(game.getGp_joueur().getJoueur(0));
				add_actionListener();
			}
			else {
			page.nexte_round();
			}
		}else if (element_executer.contains("assurance :")) {
			int index = element_executer.indexOf(":");
			String nb_joueur = element_executer.substring(index+1);
			index = Integer.valueOf(nb_joueur);
			game.getGp_joueur().getJoueur(0).creat_insuranceMise(index);
			page.fermer_Jd(game.getGp_joueur().getJoueur(0));
		}else if (element_executer.contains("next_round")) {
			System.out.println("next round");
			page.tout_vider();
			game.rendre_CartetoPioche();
			game.dell_Joueur();
			page.rendre_carteNormale(game.getPioche());
			page.gameMise_frame(game.getGp_joueur(), game.getPioche(), game.getBanquier());
			add_actionListener();
		}else if(element_executer.contains("newpartie")) {
			game.rendre_CartetoPioche();
			page.dell_toutComponent();
			page.Creat_frame_game();
			add_actionListener();
			game=null;
			gp_joueur.removeAll();
			nb_joueurs=0;
		}else if (element_executer.contains("leavegame")) {
			page.close();
		}
		
	}

	public void add_actionListener() {
		for (int i = 0; i < page.getListe_button().size(); i++) {
			page.getListe_button().get(i).addActionListener(this);
		}
	}

}
