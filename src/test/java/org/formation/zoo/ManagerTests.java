package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.formation.zoo.controleur.Manager;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.Dao;
import org.formation.zoo.stockage.DaoFactory;
import org.formation.zoo.stockage.TypeDao;
import org.junit.jupiter.api.Test;

class ManagerTests {

	@Test
	void testGetAnimaux() {
		List<CagePOJO> res = Manager.getInstance().getAnimaux();
		double poidsGAGAG = res.get(0).getPoids();
		
		assertNotNull(res);
		assertEquals("GAGAG 5 ans " + poidsGAGAG +  " kg", res.get(0).getPancarte());
		assertEquals("./images/gazelle.gif", res.get(0).getImage());
		assertEquals("cage vide", res.get(4).getPancarte());
		assertEquals("./images/cage.jpg", res.get(4).getImage());		
		
		//FINIR LES TESTS!!!!!!!!!!!!!!!!!!!!!!
	}
	
	@Test
	void testAfficher() {
		String[] infosCageAnimaux = Manager.getInstance().afficher();
		List<CagePOJO> res = Manager.getInstance().getAnimaux();
		assertEquals("Cage [x=" + res.get(0).getX() + ", y=" + res.get(0).getY() + "]----Je suis un(e) " +
		res.get(0).getCodeAnimal() + " je m'appelle " + res.get(0).getNom() + " , j'ai " + res.get(0).getAge() +
		" ans, je pèse " + res.get(0).getPoids() + " kg, ses cornes mesurent 34 cm", infosCageAnimaux[0]);
	}
	
	@Test
	void testGetInstance() {
		assertNotNull(Manager.getInstance());
	}
	
	@Test
	void testNourir(){
		
		Dao<CagePOJO> cp = DaoFactory.getInstance().getDao();	
		List<CagePOJO> lcp = null;
		lcp = cp.lireTous();
		double poids = lcp.get(0).getPoids();
		Manager.getInstance().nourrir();
		lcp = cp.lireTous();
		assertEquals(poids+0.8, lcp.get(0).getPoids());
	}

}
