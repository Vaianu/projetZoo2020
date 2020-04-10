package org.formation.zoo.service;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gazelle database table.
 * 
 */
@Entity
@Table(name="gazelle")
@NamedQuery(name="GazellePOJO.findAll", query="SELECT g FROM GazellePOJO g")
public class GazellePOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idAnimal")
	private int idAnimal;

	private int lgCornes;

	public GazellePOJO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAnimal() {
		return this.idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public int getLgCornes() {
		return this.lgCornes;
	}

	public void setLgCornes(int lgCornes) {
		this.lgCornes = lgCornes;
	}

}