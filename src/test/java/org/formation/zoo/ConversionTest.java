package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.utilitaires.Conversion;
import org.junit.jupiter.api.Test;

class ConversionTest {

	@Test
	void testPojoToCage() {
		CagePOJO cp = null;
		Cage cage = null;
		cp = new CagePOJO();
		cp.setX(23);
		cp.setY(24);
		cp.setCle(0);
		cage = Conversion.pojoToCage(cp);
		assertNotNull(cage);
		assertEquals(23, cage.getX());
		assertNull(cage.getOccupant());
		
		cp.setCodeAnimal("Lion");
		cp.setNom("Leo");
		cp.setAge(4);
		cp.setPoids(143.3);
		cage = Conversion.pojoToCage(cp);
		assertNotNull(cage);
		assertEquals(23, cage.getX());
		assertNotNull(cage.getOccupant());
		assertEquals("Leo", cage.getOccupant().getNom());
		assertEquals("Lion", cage.getOccupant().getClass().getSimpleName());
	}

}
