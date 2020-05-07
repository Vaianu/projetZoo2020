package org.formation.zoo.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.formation.zoo.modele.technique.CageManagee;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.Dao;
import org.formation.zoo.stockage.DaoFactory;

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
	/**
	 * instance du dao choisi
	 */
	private Dao<CagePOJO> acces;
	/**
	 * pour SINGLETON et une FACADE
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
	 * Méthode privée qui charge le modèle.
	 * Pour l'instant elle instancie les animaux
	 */
	private void init()
	{
		List<CagePOJO> tmp = null;
		tmp = acces.lireTous();
		lesCages = new ArrayList<CageManagee>();
		for(CagePOJO cagePOJO : tmp) {
			lesCages.add(new CageManagee(cagePOJO, acces));
		}
	}
	
	/**
	 * nourris tous les animaux du zoo
	 */
	public void nourrir ()
	{
		for (CageManagee cageManagee : lesCages) {
			cageManagee.nourrir();
		}
	}
	
	/**
	 * Fait d�vorer l'animal mange par l'animal mangeur
	 * 
	 * @param mangeur indice de l'animal mangeur (sa cage)
	 * @param mange indice de la cage de la proie
	 * @return le texte sur ce qu'il s'est passé
	 */
	public String devorer(int mangeur, int mange)
	{
		return lesCages.get(mangeur).devorer(lesCages.get(mange));
	}
	
	public String creer(String typeAnimal, String nom, int age, double poids, int lgCornes, int numCage) {
		return lesCages.get(numCage-1).creer(typeAnimal, nom, age, poids, lgCornes);
	}
	
	/**
	 * 
	 * @param mangeur indice de l'animal mangeur (sa cage)
	 * @param mange indice de la cage de la proie
	 * @return le texte sur ce qu'il s'est passé
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
						e1.printStackTrace();
					} catch (CagePleineException e1) {
						e1.printStackTrace();
					}
					lesCages.get(mange).fermer();
				}
		}
		return s;
	}*/
	
	/*public void fermer() {
		
		acces.ecrireTous(lesCages);
	}*/
	
	/**
	 * FACADE
	 * @return infos cage et animaux
	 */
	public String[] afficher() {
		String[] infosCageAnimaux = new String[lesCages.size()];
		for(int i=0; i<infosCageAnimaux.length;i++)
			infosCageAnimaux[i] = lesCages.get(i).toString();
		
		return infosCageAnimaux;
	}
	/**
	 * 
	 * @return une liste animaux
	 */
	public List<CagePOJO> getAnimaux(){
		List<CagePOJO> ret = null;
		ret = new Vector<CagePOJO>();
		for (CageManagee cm : lesCages) {
			ret.add(cm.getVue());
		}
		return ret;
	}
	
}
