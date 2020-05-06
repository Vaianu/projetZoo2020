package org.formation.zoo.modele.technique;

import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.modele.metier.Mangeable;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.Dao;
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
	
	public Animal getOccupant() {
		return controleur.getOccupant();
	}
	
	public void ouvrir() {
		controleur.ouvrir();
	}
	
	public void fermer() {
		controleur.fermer();
	}
	
	public Animal sortir() {
		try {
			return controleur.sortir();
		} catch (PorteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Efface l'animal dans la base de données
	 */
	public void retirer() {
		vue.setCodeAnimal(null);
		vue.setNom(null);
		vue.setAge(0);
		vue.setPoids(0);
		modele.modifier(vue.getCle(), vue);
	}
	
	public String devorer(CageManagee cageManagee) {
		String s = "INCOMPATIBLE";
		Mangeable laBeteConvoitee = null;
		if(cageManagee.getOccupant() instanceof Mangeable)
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
