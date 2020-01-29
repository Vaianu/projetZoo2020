package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.metier.Gazelle;
import org.formation.zoo.modele.metier.Lion;
import org.formation.zoo.modele.metier.Mangeable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LionTests {

	static Lion lion;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		lion = new Lion("Baba",4,101);
	}
	
	@Test
	void testLion() {
		Lion l = new Lion();
		assertNotNull(l);
		assertEquals("LLLLL", l.getNom());
		assertEquals(1, l.getAge());
		assertEquals(30, l.getPoids());
	}

	@Test
	void testLionStringIntDouble() {
		Lion l = new Lion("Bobo",10,150);
		assertNotNull(l);
		assertEquals("Bobo", l.getNom());
		assertEquals(10, l.getAge());
		assertEquals(150, l.getPoids());
	}
	
	@Test
	void testCrier() {
		assertNotNull(lion);
		assertEquals("Je rugi rhoaaaa", lion.crier());
	}
	
	@Test
	void testSauter() {
		assertNotNull(lion);
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		lion.sauter();
		assertEquals(p-0.7, lion.getPoids());
	}

	@Test
	void testManger() {
		assertNotNull(lion);
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		lion.manger();;
		assertEquals(p+2.1, lion.getPoids());
	}
	
	@Test
	void testDormir() {
		assertNotNull(lion);
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		lion.dormir();
		assertEquals(p-1, lion.getPoids());
	}

	@Test
	void testMangerMangeable() {
		Mangeable g = new Gazelle();
		assertNotNull(lion);
		assertNotNull(g);
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		assertEquals("MIAM", lion.manger(g)); 
		assertEquals(p+g.prelever(), lion.getPoids());
	}

}
