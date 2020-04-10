package org.formation.zoo.service;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the animal database table.
 * 
 */
@Entity
@Table(name="animal")
@NamedQueries({@NamedQuery(name = "findAll", query = "SELECT c FROM CagePOJO c"),
	@NamedQuery(name = "lire", query = "SELECT c FROM CagePOJO c WHERE c.idAnimal = :id")})

public class CagePOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAnimal;
	private int age;
	private String codeAnimal;
	private String nom;
	private double poids;
	private int x;
	private int y;
	
	@Transient
	private GazellePOJO gaz;
	
	@Transient
	private String pancarte;
	@Transient
	private String image;

	public CagePOJO() {
		x= 0;
		y = 0;
		idAnimal = 0;
		codeAnimal = null;
		nom = null;
		age = 0;
		poids = 0;
		gaz = null;
	}

	public int getIdAnimal() {
		return this.idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCodeAnimal() {
		return this.codeAnimal;
	}

	public void setCodeAnimal(String codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPoids() {
		return this.poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}
	
	@Override
	public String toString() {
		return "CagePOJO [x=" + x + ", y=" + y + ", cle=" + idAnimal + ", codeAnimal=" + codeAnimal + ", nom=" + nom
				+ ", age=" + age + ", poids=" + poids + "]" + gaz;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
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