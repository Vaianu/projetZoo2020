package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.formation.zoo.controleur.Manager;
import org.formation.zoo.service.CagePOJO;
import org.junit.jupiter.api.Test;

class ManagerTests {

	@Test
	void testGetAnimaux() {
		List<CagePOJO> res = Manager.getInstance().getAnimaux();
		double poidsGaga = res.get(0).getPoids();
		assertEquals("GAGAG 5 ans " + poidsGaga + " kg", res.get(0).getPancarte());
		assertEquals("cage vide", res.get(4).getPancarte());
		assertEquals(700, res.get(1).getX());
		assertEquals(230, res.get(1).getY());
		assertEquals("Gazelle", res.get(1).getCodeAnimal());
		assertEquals(5, res.get(1).getCle());
		assertEquals("./images/gazelle.gif", res.get(0).getImage());
		assertEquals("./images/cage.jpg", res.get(4).getImage());
		// FINIR LES TESTS§§§§§§§§
	}
	
	@Test
	void testGetInstance() {
		assertNotNull(Manager.getInstance());
	}
	
	@Test
	void testAfficher() {
		String expected = "Cage [x=300, y=300]----VIDE";
		assertEquals(expected, Manager.getInstance().afficher()[4]);
	}
	
	@Test
	void testNourrir() {
		List<CagePOJO> res = Manager.getInstance().getAnimaux();
		double poidsGaga = res.get(0).getPoids()+0.8;
		Manager.getInstance().nourrir();
		assertEquals(poidsGaga, res.get(0).getPoids());
	}

}
