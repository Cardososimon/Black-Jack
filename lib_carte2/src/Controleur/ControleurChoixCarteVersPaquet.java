package Controleur;
import Vue.*;
import Modele.Carte;
import Modele.Paquet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurChoixCarteVersPaquet implements ActionListener {
	
	private Abstracte_Vue vueSource;
	private Paquet destination;
	
	public ControleurChoixCarteVersPaquet(Abstracte_Vue vueSource, Paquet destination) {
		this.vueSource =vueSource;
		this.destination=destination;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Carte ma_carte = (Carte)e.getSource();
		System.out.println("choix carte vers paquet");
		//System.out.println(ma_carte.ToString());
		//System.out.println(" nb carte = "+vueSource.getPaquet().getPaquet().size()+" dest nb carte = "+destination.getPaquet().size());
		destination.Add_Carte_debut(ma_carte);
		vueSource.getPaquet().Del_Carte(ma_carte);
		//System.out.println(" nb carte = "+vueSource.getPaquet().getPaquet().size()+" dest nb carte = "+destination.getPaquet().size());
	}
}
