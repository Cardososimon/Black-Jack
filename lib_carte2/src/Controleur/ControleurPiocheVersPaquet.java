package Controleur;
import Vue.*;
import Modele.Paquet;
import Modele.Carte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControleurPiocheVersPaquet implements ActionListener {

	private Abstracte_Vue vue;
	private Paquet paquet_dest;
	
	public ControleurPiocheVersPaquet (Abstracte_Vue vue,Paquet paquet_dest) {
		this.vue=vue;
		this.paquet_dest=paquet_dest;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("pioche vers paquet");
		Carte a=(Carte)e.getSource();
		Carte ma_carte =vue.getPaquet().getPaquet().get(0);
		//System.out.println("pioche_carte "+a.ToString()+" nb carte = "+vue.getPaquet().getPaquet().size()+" dest nb carte = "+paquet_dest.getPaquet().size());
		vue.getPaquet().Del_Carte(0);
		paquet_dest.Add_Carte_debut(ma_carte);
		System.out.println(ma_carte.getActionListeners().length);
		
		System.out.println("pioche_carte "+a.ToString()+" nb carte = "+vue.getPaquet().getPaquet().size()+" dest nb carte = "+paquet_dest.getPaquet().size());
	}
}