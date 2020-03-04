package org.formation.zoo.service;

public class CagePOJO {
	private int x;
	private int y;
	private int cle;
	private String codeAnimal; // type de l'animal
	private String nom;
	private int age;
	private double poids;
	private GazellePOJO gaz;
	
	private String pancarte;
	private String image;
	
	public CagePOJO() {
		x = 0;
		y = 0;
		cle = 0;
		codeAnimal = null;
		nom = null;
		age = 0;
		poids = 0;
		gaz = null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCle() {
		return cle;
	}

	public void setCle(int cle) {
		this.cle = cle;
	}

	public String getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(String codeAnimal) {
		this.codeAnimal = codeAnimal;
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

	public void setAge(int age) {
		this.age = age;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	@Override
	public String toString() {
		return "CagePOJO [x=" + x + ", y=" + y + ", cle=" + cle + ", codeAnimal=" + codeAnimal + ", nom=" + nom
				+ ", age=" + age + ", poids=" + poids + "]" + gaz;
	}

	public GazellePOJO getGaz() {
		return gaz;
	}

	public void setGaz(GazellePOJO gaz) {
		this.gaz = gaz;
	}

	public String getPancarte() {
		return pancarte;
	}

	public void setPancarte(String pancarte) {
		this.pancarte = pancarte;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
