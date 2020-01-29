package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.stockage.FichierAccess;
import org.junit.jupiter.api.Test;

class FichierAccessTest {

	@Test
	void testFichierAccess() {
		FichierAccess f = new FichierAccess("A.data");
		assertNotNull(f);
		assertEquals("A.data", f.getFichier());
		assertEquals("A.data", f.getFichier());
		f.setFichier("B.data");
		assertNotEquals("A.data", f.getFichier());
		assertEquals("B.data", f.getFichier());
		assertEquals("B.data", f.getFichier());
	}

}
