package org.formation.zoo.service;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gazelle database table.
 * 
 */
@Entity
@Table(name="gazelle")
public class GazellePOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
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
	
	@Override
	public String toString() {
		return "GazellePOJO [id=" + id + ", idAnimal=" + idAnimal + ", lgCornes=" + lgCornes + "]";
	}
}