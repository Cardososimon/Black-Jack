package Modele;


import javax.swing.JFrame;

import Vue.Frame_Visible;

public class Main {

	public static void main(String[] args) {
		
		Paquet paquet= new Factory_Paquet(52).getPaquet();
		Frame_Visible frame = new Frame_Visible(paquet);
		JFrame f = new JFrame();
		f.setSize(400,400);
		f.setLayout(null);
		f.setVisible(true);
		f.add(frame.creat_Panel());
	}

}
