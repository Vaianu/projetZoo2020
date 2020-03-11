package org.formation.zoo.modele.technique;

import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.stockage.Dao;
import org.formation.zoo.utilitaires.Conversion;

public final class CageManagee {
	private final static String IMAGES="./images/";
	private Cage controleur;
	private CagePOJO vue;
	private Dao<CagePOJO> modele;
	public CageManagee(CagePOJO pojo, Dao<CagePOJO> dao) {
		modele = dao;
		vue = pojo;
		controleur = Conversion.pojoToCage(pojo);
	}
	public void entrer(Animal a) throws PorteException, CagePleineException {
		controleur.entrer(a);
		vue.setAge(a.getAge());
		//mettre à jour le pojo
		//modifier le pojo
	}
	public void nourrir() {
		controleur.nourrir();
		if(controleur.getOccupant() != null) {
			vue.setPoids(controleur.getOccupant().getPoids());
			modele.modifier(vue.getCle(),vue);
		}
	}
	@Override
	public String toString() {
		return controleur.toString();
	}
	public CagePOJO getVue() {
		String tmp = null;
		if(vue.getCodeAnimal() != null) {
			tmp = String.join(" ",vue.getNom(),Integer.toString(vue.getAge()),"ans",Double.toString(vue.getPoids()),"kg");
			vue.setPancarte(tmp);
			tmp = String.join("", IMAGES,vue.getCodeAnimal().toLowerCase()+".gif");
		}else
		{
			vue.setPancarte("cage vide");
			tmp = String.join("", IMAGES,"cage.jpg");
		}
		vue.setImage(tmp);
		return vue;
	}

}
