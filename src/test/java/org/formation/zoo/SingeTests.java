package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.Singe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SingeTests {

	static Singe singe;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		singe = new Singe("Scooby",5,50);
	}
	
	@Test
	void testSinge() {
		Singe s = new Singe();
		assertNotNull(s);
		assertEquals("SSSSS", s.getNom());
		assertEquals(1, s.getAge());
		assertEquals(30, s.getPoids());	
	}

	@Test
	void testSingeStringIntDouble() {
		Singe s = new Singe("Sami",7,74);
		assertNotNull(s);
		assertEquals("Sami", s.getNom());
		assertEquals(7, s.getAge());
		assertEquals(74, s.getPoids());
	}

	@Test
	void testManger() {
		double p = singe.getPoids();
		assertEquals(p, singe.getPoids());
		singe.manger();
		assertEquals(p+1.4, singe.getPoids());
	}

	@Test
	void testDormir() {
		double p = singe.getPoids();
		assertEquals(p, singe.getPoids());
		singe.dormir();
		assertEquals(p-0.8, singe.getPoids());
	}

}
