package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.DaoJDBCImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * fonctionne uniquement avec le script de base zoo.sql fournit
 * zoo.sql sert donc de jeu de tests. 
 * @author jacques
 *
 */
class DaoJDBCTests {
	static DaoJDBCImpl dao;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dao = new DaoJDBCImpl();
	}

	@Test
	/**
	 * Les 2 gazelles sont en premier, suit le lion et le singe dans le resultset.
	 */
	void testLireTous() {
		CagePOJO cp = null;
		List<CagePOJO>res = dao.lireTous();
		assertEquals(5, res.size());
		cp = res.get(3);
		assertEquals("Singe", cp.getCodeAnimal());
		assertEquals("chita", cp.getNom());
		assertEquals(4, cp.getAge());
		//assertEquals(38, cp.getPoids());
		assertEquals(500, cp.getX());
		assertEquals(480, cp.getY());
	}

	/**
	 * C'est une méthode très complète et compliquée.
	 * On teste le changement d'une valeur: nom, age, poids, x et y
	 * on teste les cornes d'une gazelle
	 * on teste l'entrée et la sortie d'un animal.
	 */
	@Test
	void testModifier() {
		CagePOJO cp = null;
		CagePOJO sav = null;
		

		List<CagePOJO>res = dao.lireTous();
		assertEquals(5, res.size());
		sav = res.get(3);
		cp = new CagePOJO();
		cp.setNom("Baloo");
		cp.setAge(34);
		cp.setPoids(56);
		cp.setX(100);
		cp.setY(200);
		cp.setIdAnimal(sav.getIdAnimal());
		cp.setCodeAnimal(sav.getCodeAnimal());
		//on modifie le singe
		testMAJAnimal(cp, sav);
		//on modifie la gazelle
		sav = dao.lire(2); //gazelle
		assertEquals("GAGAG", sav.getNom());
		int lgCornes = sav.getGaz().getLgCornes();
		sav.getGaz().setLgCornes(35);
		dao.modifier(2, sav);
		sav = dao.lire(2);
		assertEquals(35, sav.getGaz().getLgCornes());
		sav.getGaz().setLgCornes(lgCornes);
		dao.modifier(2, sav);
		sav = dao.lire(2);
		assertEquals(lgCornes, sav.getGaz().getLgCornes());

		//on recupere le singe pour le faire sortir
		sav = dao.lire(3);
		cp = new CagePOJO();
		cp.setCodeAnimal(null);
		cp.setIdAnimal(3);
		cp.setX(777);
		cp.setY(888);
		dao.modifier(3, cp);
		cp = dao.lireTous().get(3);
		assertNull(cp.getCodeAnimal());
		//on remet dans l'etat on le fait entrer
		dao.modifier(3, sav);
		
	}
	private void testMAJAnimal(CagePOJO animal, CagePOJO sav) {
		List<CagePOJO>res = null;
		CagePOJO cp = null;
		
		//on modifie l'animal
		dao.modifier(sav.getIdAnimal(), animal);
		res = dao.lireTous();
		cp = res.get(3);
		assertEquals(animal.getNom(), cp.getNom());
		assertEquals(animal.getAge(), cp.getAge());
		assertEquals(animal.getPoids(), cp.getPoids());
		assertEquals(animal.getX(), cp.getX());
		assertEquals(animal.getY(), cp.getY());
		//on remet dans l'etat
		dao.modifier(sav.getIdAnimal(), sav);
		res = dao.lireTous();
		cp = res.get(3);
		assertEquals(sav.getNom(), cp.getNom());
		assertEquals(sav.getAge(), cp.getAge());
		assertEquals(sav.getPoids(), cp.getPoids());
		assertEquals(sav.getX(), cp.getX());
		assertEquals(sav.getY(), cp.getY());
	}
	@Test
	void testEffacerInt() {
		CagePOJO sav = null;
		assertEquals(5, dao.lireTous().size());
		//on lit le singe
		sav = dao.lire(3);
		dao.effacer(3);
		assertEquals(4, dao.lireTous().size());
		dao.ajouter(sav);
		assertEquals(5, dao.lireTous().size());
	}

	@Test
	void testEffacerCagePOJO() {
		CagePOJO sav = null;
		assertEquals(5, dao.lireTous().size());
		//on lit le singe
		sav = dao.lire(3);
		dao.effacer(sav);
		assertEquals(4, dao.lireTous().size());
		dao.ajouter(sav);
		assertEquals(5, dao.lireTous().size());
	}

	@Test
	void testAjouter() {
		CagePOJO aAjouter = null;
		aAjouter = new CagePOJO();
		aAjouter.setNom("Leo");
		aAjouter.setAge(8);
		aAjouter.setPoids(176);
		aAjouter.setX(300);
		aAjouter.setY(400);
		aAjouter.setIdAnimal(99);
		aAjouter.setCodeAnimal("Lion");
		
		assertEquals(5, dao.lireTous().size());
		dao.ajouter(aAjouter);
		assertEquals(6, dao.lireTous().size());
		aAjouter = dao.lire(99);
		assertEquals("Leo", aAjouter.getNom());
		assertEquals(8, aAjouter.getAge());
		assertEquals(300, aAjouter.getX());
		dao.effacer(99);
		assertEquals(5, dao.lireTous().size());
	}

	@Test
	void testLire() {
		CagePOJO sav = dao.lire(2); //gazelle
		assertEquals("GAGAG", sav.getNom());
		assertEquals(34, sav.getGaz().getLgCornes());
	}

}
