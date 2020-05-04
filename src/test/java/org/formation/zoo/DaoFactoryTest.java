package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.AccesJPA;
import org.formation.zoo.stockage.Dao;
import org.formation.zoo.stockage.DaoFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DaoFactoryTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetDao() {
		Dao<CagePOJO> dao = null;
		dao = DaoFactory.getInstance().getDao();
		assertNotNull(dao.lireTous());
		dao.lireTous().stream().forEach(System.out::println);
	}

}
