package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.metier.Singe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingeTests {
	
	static Singe singe;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		singe = new Singe("Miti",2,50);
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testSinge() {
		Singe s = new Singe();
		assertEquals("SSSSS", s.getNom());
		assertEquals(1, s.getAge());
		assertEquals(30, s.getPoids());
	}

	@Test
	void testSingeStringIntDouble() {
		Singe s = new Singe("Moana",4,80);
		assertEquals("Moana", s.getNom());
		assertEquals(4, s.getAge());
		assertEquals(80, s.getPoids());
	}

	@Test
	void testManger() {
		double p = singe.getPoids();
		singe.manger();
		assertEquals(p+1.4, singe.getPoids());
		
	}

	@Test
	void testDormir() {
		double p = singe.getPoids();
		singe.dormir();
		assertEquals(p-0.8, singe.getPoids());
	}

}
