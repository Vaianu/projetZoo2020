package org.formation.zoo.service;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the animal database table.
 * 
 */
@Entity
@Table(name="animal")
@NamedQueries({@NamedQuery(name = "findAll", query = "SELECT cage FROM CagePOJO cage"),
	@NamedQuery(name = "lire", query = "SELECT cage FROM CagePOJO cage WHERE cage.cle = :id")})
public class CagePOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idAnimal")
	private int cle;
	private String codeAnimal;
	private String nom;
	private int age;
	private double poids;
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "idAnimal", referencedColumnName = "idAnimal", updatable = false, insertable = false)
	private GazellePOJO gaz;
	
	@Transient
	private String pancarte;
	@Transient
	private String image;

	public CagePOJO() {
		x= 0;
		y = 0;
		cle = 0;
		codeAnimal = null;
		nom = null;
		age = 0;
		poids = 0;
		gaz = null;
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

	public GazellePOJO getGaz() {
		return gaz;
	}

	public void setGaz(GazellePOJO gaz) {
		this.gaz = gaz;
	}

	public int getCle() {
		return this.cle;
	}

	public void setCle(int cle) {
		this.cle = cle;
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
		return "CagePOJO [x=" + x + ", y=" + y + ", cle=" + cle + ", codeAnimal=" + codeAnimal + ", nom=" + nom
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

}