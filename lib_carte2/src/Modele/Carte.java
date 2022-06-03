package Modele;


import java.io.File;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Carte extends JButton {

	private String valeur;
	private String signe;
	private ImageIcon img;
	private ImageIcon dos;

	public Carte(String valeur, String signe) {
		this.valeur = valeur;
		this.signe = signe;
		File directory = new File ("./");
		String file=directory.getAbsolutePath();
		System.out.println(file);
		if (file.contains("Black_Jack\\.")){
			file=file.substring(0,file.indexOf("Black_Jack\\."));
			file=file+="lib_carte2/.";
		}
		else if(file.contains("Black_Jack\\dist\\.")) {
			file=file.substring(0,file.indexOf("Black_Jack\\dist\\."));
			file=file+="lib_carte2/.";
		}
		System.out.println(file);
		this.img = new ImageIcon(file+"/src/" + signe + "/" + valeur + "_" + signe + ".png");
		this.dos = new ImageIcon(file+"/src/images/dos_carte.png");
		this.setIcon(img);
	}

	public String getValeur() {
		return valeur;
	}

	public String getSigne() {
		return signe;
	}

	public ImageIcon getImage_Interface() {
		return img;
	}

	public ImageIcon getImageDos_Interface() {
		return dos;
	}

	public Carte getButton_Carte() {
		this.setSize(66, 98);
		return this;
	}

	public Carte affiche_face_Carte() {
		this.setSize(66, 98);
		this.setIcon(img);
		return this;
	}

	public void affiche_dos_Carte() {
		this.setIcon(dos);
		this.setSize(66, 98);
	}

	public String ToString() {
		return signe + "_" + valeur;
	}

}
