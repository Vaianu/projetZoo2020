package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.metier.Gazelle;
import org.formation.zoo.modele.metier.Lion;
import org.formation.zoo.modele.metier.Mangeable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LionTests {

	static Lion lion;
	@BeforeAll
	static void setUpBeforeClass() {
		lion = new Lion("Taporo",3,150);
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testCrier() {
		assertEquals("Je rugi rhoaaaa", lion.crier());
	}

	@Test
	void testSauter() {
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		lion.sauter();
		assertEquals(p-0.7, lion.getPoids());
	}

	@Test
	void testLion() {
		Lion l = new Lion();
		assertEquals("LLLLL", l.getNom());
		assertEquals(1, l.getAge());
		assertEquals(30, l.getPoids());
	}

	@Test
	void testLionStringIntDouble() {
		Lion l = new Lion("Patiri",4,200);
		assertEquals("Patiri", l.getNom());
		assertEquals(4, l.getAge());
		assertEquals(200, l.getPoids());
	}

	@Test
	void testManger() {
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		lion.manger();
		assertEquals(p+2.1, lion.getPoids());
	}

	@Test
	void testMangerMangeable() {
		Mangeable g = new Gazelle();
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		assertEquals("MIAM", lion.manger(g));
		assertEquals(p+=g.prelever(), lion.getPoids());
	}

	@Test
	void testDormir() {
		double p = lion.getPoids();
		assertEquals(p, lion.getPoids());
		lion.dormir();
		assertEquals(p-1, lion.getPoids());
	}

}
