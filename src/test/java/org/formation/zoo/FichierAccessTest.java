package org.formation.zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.DaoMemoire;
import org.formation.zoo.stockage.FichierAccess;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FichierAccessTest {
	private static DaoMemoire daoMemoire; 
	private static CagePOJO aEffacer; 
	private static String fich; 
	private static FichierAccess<CagePOJO> fAccess; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fich = "tests.data"; 
		fAccess = new FichierAccess<>(fich); 
		aEffacer = new CagePOJO();
		aEffacer.setIdAnimal(99);
		aEffacer.setCodeAnimal("Singe");
		aEffacer.setNom("Chiita");
		aEffacer.setAge(8);
		aEffacer.setPoids(34.9);
		aEffacer.setX(444);
		aEffacer.setY(555);
		aEffacer.setGaz(null);
		daoMemoire = new DaoMemoire();
	}

	@Test 
	/**
	 * On teste tout dans une seule méthode 
	 * car les accès fichiers sont séquentiels
	 */
	public void testsSequence() throws IOException {
		CagePOJO c = null; 
		File f = new File(fich);
		if(f.exists()) {
			if(!f.delete()) {
				throw new IOException("fichier non supprime");
			}
		}
		assertFalse(f.exists()); 
		fAccess = new FichierAccess<CagePOJO>(fich); 
		fAccess.ecrireTous(daoMemoire.lireTous()); 
		int taille = fAccess.lireTous().size(); 
		assertEquals(3, fAccess.lireTous().size()); 
		assertEquals("Baloo",fAccess.lire(0).getNom()); 
		assertEquals(100,fAccess.lire(0).getX()); 
		assertEquals(taille, fAccess.lireTous().size()); 
		//insere 
		fAccess.ajouter(aEffacer);
		taille++; 
		assertEquals(taille, fAccess.lireTous().size()); 
		assertEquals("Chiita", fAccess.lireTous().get(taille-1).getNom()); 
		//insereTous 
		Vector<CagePOJO> cages = new Vector<>(); 
		for(int i = 0; i < 4; i++) 
		{ 
			c = new CagePOJO();
			c.setIdAnimal(i);
			c.setX(100+(10*i));
			c.setY(700);
			c.setCodeAnimal("Singe");
			c.setNom("SSS"+i);
			c.setAge(i);
			c.setPoids(i*10);
			cages.add(c); 
		} 
		//on vide le fichier et on remplace
		fAccess.ecrireTous(cages); 
		taille=4; 
		assertEquals(taille, fAccess.lireTous().size()); 
		assertEquals(130, fAccess.lireTous().get(taille-1).getX()); 
		//modifie
		//on recupere le 2ieme singe
		c = fAccess.lire(1);
		assertEquals("SSS1", c.getNom());
		assertEquals(1, c.getAge());
		c.setNom("autreNom");
		c.setAge(22);
		fAccess.modifier(0, c); 
		assertEquals(22, fAccess.lire(1).getAge()); 
		assertEquals("autreNom", fAccess.lire(1).getNom()); 
		assertEquals(null,fAccess.lire(1).getGaz()); 
		assertEquals(taille, fAccess.lireTous().size());
		//supprime
		fAccess.effacer(0);
		assertEquals(taille-1, fAccess.lireTous().size());
		c= fAccess.lire(0);
		fAccess.effacer(c);
		assertEquals(taille-2, fAccess.lireTous().size());
		
	} 
}
