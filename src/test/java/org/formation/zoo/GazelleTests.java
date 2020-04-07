package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.metier.Gazelle;
import org.formation.zoo.modele.metier.Lion;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GazelleTests {

	static Gazelle gazelle;
	@BeforeAll
	static void setUpBeforeClass() {
		gazelle = new Gazelle("Puanio",2,80,7);
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCourir() {
		double p = gazelle.getPoids();
		assertEquals(p, gazelle.getPoids());
		gazelle.courir();
		assertEquals(p-0.2, gazelle.getPoids());
	}

	@Test
	void testGazelle() {
		Gazelle g = new Gazelle();
		assertEquals("GGGGG", g.getNom());
		assertEquals(1, g.getAge());
		assertEquals(30, g.getPoids());
		assertEquals(20, g.getLgCornes());
	}

	@Test
	void testGazelleStringIntDoubleInt() {
		Gazelle g = new Gazelle("Puatoro",4,100,12);
		assertEquals("Puatoro", g.getNom());
		assertEquals(4, g.getAge());
		assertEquals(100, g.getPoids());
		assertEquals(12, g.getLgCornes());
	}

	@Test
	void testGetLgCornes() {
		int lgcornes = gazelle.getLgCornes();
		assertEquals(lgcornes, gazelle.getLgCornes());
	}

	@Test
	void testSetLgCornes() {
		gazelle.setLgCornes(8);
		assertEquals(8, gazelle.getLgCornes());
	}

	@Test
	void testManger() {
		double p = gazelle.getPoids();
		assertEquals(p, gazelle.getPoids());
		gazelle.manger();
		assertEquals(p+0.8, gazelle.getPoids());
	}

	@Test
	void testDormir() {
		double p = gazelle.getPoids();
		assertEquals(p, gazelle.getPoids());
		gazelle.dormir();
		assertEquals(p-0.4, gazelle.getPoids());
	}

	@Test
	void testPrelever() {
		double p = gazelle.getPoids();
		assertEquals(p, gazelle.getPoids());
		assertEquals(p/3, gazelle.prelever());
	}

}
