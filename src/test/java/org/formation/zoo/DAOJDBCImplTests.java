package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.stockage.DAOJDBCImpl;
import org.junit.jupiter.api.Test;

class DAOJDBCImplTests {

	@Test
	void testLireTous() {
		DAOJDBCImpl daojdbc = null;
		daojdbc = new DAOJDBCImpl();
		assertNotNull(daojdbc.lireTous());
	}

}
