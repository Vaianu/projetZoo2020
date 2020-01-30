package org.formation.zoo.controleur;

import java.util.List;

import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.modele.metier.Mangeable;
import org.formation.zoo.modele.technique.BeurkException;
import org.formation.zoo.modele.technique.CagePleineException;
import org.formation.zoo.modele.technique.PorteException;
import org.formation.zoo.stockage.FichierAccess;

/**
 * SINGLETON et une FACADE
 * @author vu.samgmouit
 *
 */
public final class Manager {
	/**
	 * Vecteur de Cages. C'est la COMPOSITION.
	 */
	private List<Cage> lesCages;
	private FichierAccess acces;
	
	/**
	 * pour SINGLETON
	 */
	private static Manager instance = null;
	/**
	 * constructeur prive ==> SINGLETON
	 */
	private Manager() {
		lesCages = null;
		acces = new FichierAccess("zoo.data");
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
		lesCages = acces.lireTous();
	}
	/**
	 * 
	 * @return un tableau des cages d'animaux
	 */
	public List<Cage> getLesCages()
	{
		return lesCages;
	}
	/**
	 * Permet de nourrir tous les animaux du zoo
	 */
	public void nourrir ()
	{
		/*for (int i = 0; i < lesCages.size(); i++) {
			if (lesCages.get(i).getOccupant() != null)
			{
				lesCages.get(i).getOccupant().manger();
			}
		}*/
		lesCages.stream().forEach(e->{
			if (e.getOccupant() != null)
				e.getOccupant().manger();
		});
	}
	/**
	 * 
	 * @param mangeur indice de l'animal mangeur (sa cage)
	 * @param mange indice de la cage de la proie
	 * @return le texte sur ce qu'il s'est passé
	 */
	public String devorer(int mangeur, int mange)
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
	}
	public void fermer() {
		acces.ecrireTous(lesCages);
	}

}
