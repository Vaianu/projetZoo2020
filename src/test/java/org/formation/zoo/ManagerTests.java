package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.formation.zoo.controleur.Manager;
import org.formation.zoo.modele.metier.Cage;
import org.junit.jupiter.api.Test;

class ManagerTests {

	@Test
	void testGetInstance() {
		Manager m = null;
		assertNotNull(Manager.getInstance());
		m = Manager.getInstance();
		assertEquals(m, Manager.getInstance());
		assertEquals(m, Manager.getInstance());
	}

	@Test
	void testGetLesCages() {
		List<Cage> c = null;
		/*assertNotNull(Manager.getInstance().getLesCages());
		c = Manager.getInstance().getLesCages();
		assertEquals(c, Manager.getInstance().getLesCages());
		assertEquals(c, Manager.getInstance().getLesCages());*/
	}

}
