package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.metier.Gazelle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GazelleTests {

	static Gazelle gazelle;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gazelle = new Gazelle("gaga",3,80,25);
	}
	
	@Test
	void testGazelle() {
		Gazelle g = new Gazelle();
		assertNotNull(g);
		assertEquals("GGGGG", g.getNom());
		assertEquals(1, g.getAge());
		assertEquals(30, g.getPoids());
		assertEquals(20, g.getLgCornes());
	}

	@Test
	void testGazelleStringIntDoubleInt() {
		Gazelle g = new Gazelle("zaza",5,110,30);
		assertNotNull(g);
		assertEquals("zaza", g.getNom());
		assertEquals(5, g.getAge());
		assertEquals(110, g.getPoids());
		assertEquals(30, g.getLgCornes());
	}
	
	@Test
	void testCourir() {
		assertNotNull(gazelle);
		double p = gazelle.getPoids();
		assertEquals(p, gazelle.getPoids());
		gazelle.courir();
		assertEquals(p-0.2, gazelle.getPoids());
	}

	@Test
	void testManger() {
		assertNotNull(gazelle);
		double p = gazelle.getPoids();
		assertEquals(p, gazelle.getPoids());
		gazelle.manger();
		assertEquals(p+0.8, gazelle.getPoids());
	}

	@Test
	void testDormir() {
		assertNotNull(gazelle);
		double p = gazelle.getPoids();
		assertEquals(p, gazelle.getPoids());
		gazelle.dormir();
		assertEquals(p-0.4, gazelle.getPoids());
	}

	@Test
	void testPrelever() {
		assertNotNull(gazelle);
		double p = gazelle.getPoids();
		assertEquals(p/3, gazelle.prelever());
	}

}
