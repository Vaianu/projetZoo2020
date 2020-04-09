package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.modele.metier.Lion;
import org.formation.zoo.modele.technique.CagePleineException;
import org.formation.zoo.modele.technique.PorteException;
import org.junit.jupiter.api.Test;

class CageTests {

	@Test
	void testCage() {
		Cage c = new Cage();
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
		assertEquals(true, c.isOuvert());
		assertEquals(null, c.getOccupant());
	}

	@Test
	void testCageIntInt() {
		Cage c = new Cage(300,500);
		assertEquals(300, c.getX());
		assertEquals(500, c.getY());
		assertEquals(true, c.isOuvert());
		assertEquals(null, c.getOccupant());
	}

	@Test
	void testEntrer() {
		Cage c = new Cage(300,500);
		Animal bete = new Lion();
		assertEquals(300, c.getX());
		assertEquals(500, c.getY());
		c.fermer();
		try {
			c.entrer(bete);
			fail("aurait du jeter une PorteException");
		} catch (PorteException e) {
			assertEquals("Problème de porte", e.getMessage());
		} catch (CagePleineException e) {
			fail("la cage ne devrait pas être pleine");
		}
		c.ouvrir();
		try {
			c.entrer(bete);
			assertEquals(bete, c.getOccupant());
			assertEquals(true, c.isOuvert());
			c.fermer();
		} catch (PorteException e) {
			fail("n'aurait pas du jeter une PorteException");
		} catch (CagePleineException e) {
			fail("la cage ne devrait pas �tre pleine");
		}
		bete= new Lion();
		assertEquals(false, c.isOuvert());
		c.ouvrir();
		assertEquals(true, c.isOuvert());
		try {
			c.entrer(bete);
			fail("la cage devrait être pleine");
		} catch (PorteException e) {
			fail("n'aurait pas du jeter une PorteException");
		} catch (CagePleineException e) {
			assertEquals("La cage est déjà occupée", e.getMessage());
		}
	}

	@Test
	void testSortir() {
		Cage c = new Cage(300,500);
		Animal bete = new Lion();
		Animal sauvegarde = bete;
		assertEquals(300, c.getX());
		assertEquals(500, c.getY());
		c.ouvrir();
		try {
			c.entrer(bete);
			assertEquals(bete, c.getOccupant());
			assertEquals(true, c.isOuvert());
			c.fermer();
		} catch (PorteException e) {
			fail("n'aurait pas du jeter une PorteException");
		} catch (CagePleineException e) {
			fail("la cage ne devrait pas �tre pleine");
		}
		try {
			bete = c.sortir();
			fail("la cage est fermée!!!!");
		} catch (PorteException e) {
			assertEquals("Problème de porte", e.getMessage());
		}
		c.ouvrir();
		bete = null;
		assertEquals(true, c.isOuvert());
		try {
			bete = c.sortir();
			assertEquals(null, c.getOccupant());
			assertEquals(sauvegarde, bete);
			assertEquals(true, c.isOuvert());
			c.fermer();
		} catch (PorteException e) {
			fail("n'aurait pas du jeter une PorteException");
		}
	}

	@Test
	void testOuvrir() {
		Cage c = new Cage(300,500);
		assertEquals(true, c.isOuvert());
		c.ouvrir();
		assertEquals(true, c.isOuvert());
		c.fermer();
		assertEquals(false, c.isOuvert());
		c.ouvrir();
		assertEquals(true, c.isOuvert());
		c.ouvrir();
		assertEquals(true, c.isOuvert());
	}

	@Test
	void testFermer() {
		Cage c = new Cage(300,500);
		assertEquals(true, c.isOuvert());
		c.fermer();
		assertEquals(false, c.isOuvert());
		c.fermer();
		assertEquals(false, c.isOuvert());
	}

}
