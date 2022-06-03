package Modele.Joueur;



public class Humain extends Joueur {
	
	/**
	 * class permettant de créer un humain
	 * @param nom représente le nom du joueur humain
	 * @param jeton représente le nombre de jetons total dont dispose le joueur humain
	 */
	public Humain(String nom,int jeton) {
		super(nom,jeton);
	}
	/**
	 * permet de mettre à jour les jetons du joueur et la mise
	 */
	@SuppressWarnings("deprecation")
	public void creat_mise(int mise) {
		if (mise>0) {
			jeton-=mise;
			this.liste_mise.add(mise);
		}
		this.setChanged();
		this.notifyObservers();
	}
/**
 * permet de créer l'assurance mise et de mettre à jour les jetons du joueur
 */
	@SuppressWarnings("deprecation")
	@Override
	public void creat_insuranceMise(int insurance_mise2) {
		this.jeton-=insurance_mise2;
		insurance_mise=insurance_mise2;
		this.setChanged();
		this.notifyObservers();
	}
	public String type() {
		return "humain";
	}
}
