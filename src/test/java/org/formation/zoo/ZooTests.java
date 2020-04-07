package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.formation.zoo.controleur.Manager;
import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.Dao;
import org.formation.zoo.stockage.DaoFactory;
import org.formation.zoo.vue.Zoo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ZooTests {

	static Zoo z;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		z = new Zoo();
	}

	@Test
	void testDevorer() {
		/*//singe singe
		assertEquals("INCOMPATIBLE",z.devorer(0, 0));
		// singe lion
		assertEquals("INCOMPATIBLE",z.devorer(0, 1));
		//singe vide
		assertEquals("INCOMPATIBLE",z.devorer(0,2));
		//singe gazelle
		assertEquals("Je n'aime pas Ã§a",z.devorer(0, 3));
		//Lion Lion
		assertEquals("INCOMPATIBLE",z.devorer(1,1));
		//lion singe
		assertEquals("INCOMPATIBLE",z.devorer(1, 0));
		//lion vide
		assertEquals("INCOMPATIBLE",z.devorer(1, 2));
		//lion gazelle
		assertEquals("MIAM",z.devorer(1, 3));*/
	}
	
	@Test
	void testNourrir() {
		
		/*CagePOJO obj = null;
		obj = new CagePOJO();
		obj.setCodeAnimal("Lion");
		obj.setNom("Pai");
		obj.setAge(12);
		obj.setPoids(152.4);
		obj.setX(100);
		obj.setY(350);
		
		Dao<CagePOJO> cp = DaoFactory.getInstance().getDao();
		cp.ajouter(obj);
		
		List<CagePOJO> listcp = null;	
		listcp = cp.lireTous();
		obj = listcp.get(listcp.size()-1);
		
		//Avant
		assertEquals(152.4, listcp.get(listcp.size()-1).getPoids());
		//Aprés
		z.nourrir();
		listcp = cp.lireTous();
		assertEquals(154.5, listcp.get(listcp.size()-1).getPoids());
		cp.effacer(obj);*/
	}
}
