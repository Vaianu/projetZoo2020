package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.controleur.Manager;
import org.formation.zoo.modele.Animal;
import org.formation.zoo.vue.Zoo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ZooTests {

	static Zoo z;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		z = new Zoo();
	}

	@Test
	void testDevorer() {
		//singe singe
		assertEquals("INCOMPATIBLE",z.devorer(0, 0));
		// singe lion
		assertEquals("INCOMPATIBLE",z.devorer(0, 1));
		//singe vide
		assertEquals("INCOMPATIBLE",z.devorer(0,2));
		//singe gazelle
		assertEquals("Je n'aime pas Ã§a",z.devorer(0, 3));
		//Lion Lion
		assertEquals("INCOMPATIBLE",z.devorer(1,1));
		//lion singe
		assertEquals("INCOMPATIBLE",z.devorer(1, 0));
		//lion vide
		assertEquals("INCOMPATIBLE",z.devorer(1, 2));
		//lion gazelle
		assertEquals("MIAM",z.devorer(1, 3));
	}
	
	@Test
	void testNourrir() {
		// a référence sur l'objet lion
		Animal a = Manager.getInstance().getLesCages().get(1).getOccupant();
		a.dormir();
		a.dormir();
		double p = a.getPoids();
		assertEquals(p, a.getPoids());
		z.nourrir();
		assertEquals(p+2.1, a.getPoids());
	}
}
