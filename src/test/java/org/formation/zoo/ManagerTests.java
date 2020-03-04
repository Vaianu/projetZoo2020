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
		assertEquals("Baloo 20 ans 75.0 kg", res.get(0).getPancarte());
		assertEquals("cage vide", res.get(1).getPancarte());
		assertEquals("images/singe.gif", res.get(0).getImage());
		assertEquals("images/cage.gif", res.get(1).getImage());
		// FINIR LES TESTS§§§§§§§§
	}

}
