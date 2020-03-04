package org.formation.zoo.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.modele.metier.Mangeable;
import org.formation.zoo.modele.technique.BeurkException;
import org.formation.zoo.modele.technique.CageManagee;
import org.formation.zoo.modele.technique.CagePleineException;
import org.formation.zoo.modele.technique.PorteException;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.Dao;
import org.formation.zoo.stockage.DaoFactory;
import org.formation.zoo.utilitaires.Conversion;

/**
 * SINGLETON et une FACADE
 * @author vu.samgmouit
 *
 */
public final class Manager {
	/**
	 * Vecteur de Cages. C'est la COMPOSITION.
	 */
	private List<CageManagee> lesCages;
	private Dao acces;
	
	/**
	 * pour SINGLETON
	 */
	private static Manager instance = null;
	/**
	 * constructeur prive ==> SINGLETON
	 */
	private Manager() {
		lesCages = null;
		acces = DaoFactory.getInstance().getDao();
		init();	
	}
	/**
	 * Singleton
	 * @return l'instance unique du singleton
	 */
	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}
	/**
	 * M�thode priv�e qui charge le mod�le.
	 * Pour l'instant elle instancie les animaux
	 */
	private void init()
	{
		List<CagePOJO> tmp = null;
		tmp = acces.lireTous();
		lesCages = new ArrayList<CageManagee>();
		for (CagePOJO cagePOJO : tmp) {
			lesCages.add(new CageManagee(cagePOJO, acces));
		}
	}
	/**
	 * 
	 * @return un tableau des informations des cages et des animaux (position x et y de la cage, nom, age, poids des animaux)
	 */
	public String[] afficher()
	{
		String[] ret = new String[lesCages.size()];
		ret = new String[lesCages.size()];
		for(int i=0; i<lesCages.size(); i++) {
			ret[i] = lesCages.get(i).toString(); 
		}
		
		
		return ret;
		
	}
	public List<CagePOJO> getAnimaux(){
		List<CagePOJO> ret = null;
		ret = new Vector<CagePOJO>();
		for (CageManagee cm : lesCages) {
			ret.add(cm.getVue());
		}
		return ret;
	}
	
	/**
	 * Permet de nourrir tous les animaux du zoo
	 */
	public void nourrir ()
	{
		for (CageManagee cageManagee : lesCages) {
			cageManagee.nourrir();
		}
	}
	/**
	 * 
	 * @param mangeur indice de l'animal mangeur (sa cage)
	 * @param mange indice de la cage de la proie
	 * @return le texte sur ce qu'il s'est pass�
	 */
	/*public String devorer(int mangeur, int mange)
	{
		Mangeable laBeteConvoitee = null;
		String s = "INCOMPATIBLE";
		if (lesCages.get(mange).getOccupant() != null && lesCages.get(mangeur).getOccupant() != null && lesCages.get(mange).getOccupant() instanceof Mangeable)
			{
				lesCages.get(mange).ouvrir();
				try {
					laBeteConvoitee = (Mangeable)lesCages.get(mange).sortir();
				} catch (PorteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try
				{
					s = lesCages.get(mangeur).getOccupant().manger(laBeteConvoitee);
				}
				catch (BeurkException e)
				{
					s = e.getMessage();
					try {
						lesCages.get(mange).entrer((Animal)laBeteConvoitee);
					} catch (PorteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CagePleineException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lesCages.get(mange).fermer();
				}
		}
		return s;
	}*/
//
//		public void fermer() {
		
//		acces.ecrireTous(lesCages);
//	}

}
