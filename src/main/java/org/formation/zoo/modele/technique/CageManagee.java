package org.formation.zoo.modele.technique;

import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.modele.metier.Mangeable;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.service.GazellePOJO;
import org.formation.zoo.stockage.Dao;
import org.formation.zoo.stockage.DaoFactory;
import org.formation.zoo.utilitaires.Conversion;

public final class CageManagee {
	/**
	 * chemin de l'image
	 */
	private final static String IMAGES="./images/";
	/**
	 * attribut de type Cage
	 */
	private Cage controleur;
	/**
	 * attribut de type CagePOJO
	 */
	private CagePOJO vue;
	private Dao<CagePOJO> modele;
	
	public CageManagee(CagePOJO pojo, Dao<CagePOJO> dao) {
		modele = dao;
		vue = pojo;
		controleur = Conversion.pojoToCage(pojo);
	}
	public void entrer(Animal a) throws PorteException, CagePleineException{
			controleur.entrer(a);
			//mettre à jour le pojo
			//modifier le pojo
	}
	
	public void nourrir() {
		controleur.nourrir();
		if(controleur.getOccupant() != null) {
			vue.setPoids(controleur.getOccupant().getPoids());
			modele.modifier(vue.getCle(), vue);
		}
		//modele.modifier(vue)
	}
	
	@Override
	public String toString() {
		return controleur.toString();
	}
	/**
	 * 
	 * @return l'instance de CagePOJO
	 */
	public CagePOJO getVue() {
		String tmp = null;
		if(vue.getCodeAnimal() != null) {
			tmp = String.join(" ",vue.getNom(),Integer.toString(vue.getAge()),"ans",Double.toString(vue.getPoids()),"kg");
			if(vue.getCodeAnimal().equals("Gazelle"))
				tmp += String.join(" ", " ",Integer.toString(vue.getGaz().getLgCornes()),"cm de cornes");
			vue.setPancarte(tmp);
			tmp = String.join("", IMAGES, vue.getCodeAnimal().toLowerCase()+".gif");
		}
		else
		{
			vue.setPancarte("cage vide");
			tmp = String.join("", IMAGES, "cage.jpg");
		}
		vue.setImage(tmp);
		return vue;
	}
	
	///////////////NOUVEAU////////////////
	
	/**
	 * 
	 * @return l'animal dans la cage
	 */
	public Animal getOccupant() {
		return controleur.getOccupant();
	}
	
	/**
	 * Ouvrir la cage
	 */
	public void ouvrir() {
		controleur.ouvrir();
	}
	
	/**
	 * Fermer la cage
	 */
	public void fermer() {
		controleur.fermer();
	}
	
	/**
	 * Sortir l'animal de la cage
	 * 
	 * @return l'animal dans la cage
	 */
	public Animal sortir() {
		try {
			return controleur.sortir();
		} catch (PorteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Méthode appelé pour effacer une gazelle mangé par un lion.
	 * Enlève la gazelle dans la cage dans base de données
	 * et supprime l'enregistrement dans table Gazelle concernant la longueur de ses cornes 
	 */
	public void retirer() {
		Dao<GazellePOJO> modeleGaz = null;
		modeleGaz = DaoFactory.getInstance().getDaoGaz();
		modeleGaz.effacer(vue.getGaz());
		vue.setGaz(null);
		vue.setCodeAnimal(null);
		vue.setNom(null);
		vue.setAge(0);
		vue.setPoids(0);
		modele.modifier(vue.getCle(), vue);
	}
	
	public String devorer(CageManagee cageManagee) {
		String s = "INCOMPATIBLE";
		Mangeable laBeteConvoitee = null;
		if(controleur.getOccupant() != null && controleur.getOccupant() != cageManagee.getOccupant() 
				&& cageManagee.getOccupant() instanceof Mangeable)
		{
			cageManagee.ouvrir();
			laBeteConvoitee = (Mangeable) cageManagee.sortir();
			try {
				s = controleur.devorer(laBeteConvoitee);
				vue.setPoids(controleur.getOccupant().getPoids());
				modele.modifier(vue.getCle(), vue);
				cageManagee.retirer();
			} catch (BeurkException e) {
				s = e.getMessage();
				try {
					cageManagee.entrer((Animal)laBeteConvoitee);
				} catch (PorteException | CagePleineException e1) {
					e1.printStackTrace();
				}
				cageManagee.fermer();
			}
		}
		return s;
	}
	
}
