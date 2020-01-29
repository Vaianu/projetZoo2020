package org.formation.zoo.modele.metier;


/**
 * Classe abstraite de base pour le modèle.
 * Ele regroupe les principaux attributs et leurs méthodes.
 * 
 * @author J.Vincensini
 * @see org.formation.zoo.modele.metier.Lion
 * @version 1.0
 *
 */
public abstract class Animal implements Individu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * nom d'un animal
	 */
	private String nom;
	/**
	 * age de l'animal
	 */
	private int age;
	/**
	 * poids de l'animal
	 */
	private double poids;
	/**
	 * constante d'objet pour la limite de poids.
	 * Initialisé dans le constructeur
	 */
	private final double LIMITE;
	/**
	 * Constructeur par défaut 
	 */
	public Animal()
	{
		this("aaaaaa",1,1,1);
	}
	/**
	 * 
	 * @param n nom de l'animal
	 * @param a	age de l'animal
	 * @param p poids de l'animal
	 * @param l poids limite d'animal
	 */
	public Animal(String n, int a, double p, double l)
	{
		LIMITE = l;
		setNom(n);
		setAge(a);
		setPoids(p);
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	private void setAge(int age) {
		this.age = age;
	}

	public double getPoids() {
		return poids;
	}

	protected void setPoids(double p) {
		if ((p > 0) && (p < LIMITE) )
		{
			poids = p;
		}
	}
	
	public void grandir()
	{
		 setAge(getAge()+1);
	}
	@Override
	public String toString() {
		return getInfos();
	}
	/**
	 * methode JAVA 8 qui utilise String.join
	 * methode POLYMORPHE (donc à trous! 4 trous possibles)
	 * @return la description de l'animal sous forme d'une chaine
	 * 
	 */
	public final String getInfos() {
		String res = "";
		res = String.join(" ", 
				"Je suis un(e)", 
				getClass().getSimpleName(),
				"je m'appelle",
				nom,
				", j'ai",
				Integer.toString(age),
				"ans, je pèse",
				Double.toString(poids),
				"kg"
				);
	
		return res;
				
	}
	
	@Override
	public String crier()
	{
		return "...";
		
	}
	@Override
	@Deprecated
	public String manger(Gazelle g) {
		return "BEURK";
	}
	@Deprecated
	public String manger(Individu i) {
		return "BEURK";
		
	}
	/**méthode vide car le comportement par défaut ne fait rien.
	 * méthode OPTIONNELLE
	 * 
	 */
	@Override
	public void sauter() {

	}
	/**méthode vide car le comportement par défaut ne fait rien.
	 * méthode OPTIONNELLE
	 * 
	 */
	@Override
	public void courir() {
	}

	
}
