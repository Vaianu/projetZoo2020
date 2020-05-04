package org.formation.zoo.stockage;

import java.util.List;
import java.util.Vector;

import org.formation.zoo.service.CagePOJO;

public class DaoMemoire implements Dao<CagePOJO> {
	private List<CagePOJO> elts;

	public DaoMemoire() {
		init();
	}

	@Override
	public List<CagePOJO> lireTous() {
		return elts;
	}

	@Override
	public void ecrireTous(List<CagePOJO> elts) {
		// Vide volontairement
		
	}
	private void init()
	{
		/*CagePOJO tmp = null;
		elts = new Vector<CagePOJO>();
		tmp = new CagePOJO();
		tmp.setCodeAnimal("Singe");
		tmp.setCle(0);
		tmp.setX(100);
		tmp.setY(200);
		tmp.setAge(20);
		tmp.setPoids(75);
		tmp.setNom("Baloo");
		elts.add(tmp);
		tmp = new CagePOJO();
		tmp.setX(234);
		tmp.setY(254);
		tmp.setCle(1);
		elts.add(tmp);
		tmp = new CagePOJO();
		tmp.setX(666);
		tmp.setY(150);
		tmp.setCle(2);
		elts.add(tmp);*/
	
	}

	@Override
	public void modifier(int cle, CagePOJO obj) {
		elts.set(cle, obj);
		
	}

	@Override
	public void effacer(int cle) {
		elts.remove(cle);
		
	}

	@Override
	public void effacer(CagePOJO obj) {
		elts.remove(obj);
	}

	@Override
	public void ajouter(CagePOJO obj) {
		elts.add(obj);
	}

	@Override
	public CagePOJO lire(int cle) {
		
		return elts.get(cle);
	}

}
