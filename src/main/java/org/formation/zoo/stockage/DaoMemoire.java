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
	/**
	 * méthode privée qui rempli la liste si aucun fichier n'est trouvé
	 */
	private void init()
	{
		CagePOJO tmp = null;
		elts = new Vector<CagePOJO>();
		tmp = new CagePOJO();
		tmp.setCodeAnimal("Singe");
		tmp.setCle(0);
		tmp.setX(100);
		tmp.setY(200);
		tmp.setNom("Baloo");
		tmp.setAge(20);
		tmp.setPoids(75);
		elts.add(tmp);
		tmp = new CagePOJO();
		tmp.setX(500);
		tmp.setY(254);
		tmp.setCle(1);
		elts.add(tmp);
		tmp = new CagePOJO();
		tmp.setX(400);
		tmp.setY(400);
		tmp.setCle(2);
		elts.add(tmp);
			/*tmp = new CagePOJO(100,50);
			tmp.ouvrir();
			tmp.entrer(new Lion("Simba",3,20));
			tmp.fermer();
			elts.add(tmp);
			
			tmp = new CagePOJO(150,250); //CAGE VIDE
			tmp.fermer();
			elts.add(tmp);
			
			tmp = new CagePOJO(90,230);
			tmp.ouvrir();
			tmp.entrer(new Gazelle("Lady Gaga",20,75,10));
			tmp.fermer();
			elts.add(tmp);
			
			tmp = new CagePOJO(60,100);
			tmp.ouvrir();
			tmp.entrer(new Singe("Baloo",30,50));
			tmp.fermer();
			elts.add(tmp);
		} catch (PorteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CagePleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
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


}
