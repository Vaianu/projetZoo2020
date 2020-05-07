package org.formation.zoo.modele.technique;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
	 * chemin pour instancier aimal
	 */
	public final static String CHEMIN = "org.formation.zoo.modele.metier.";
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
			String pancarte = String.join(" ", "cage n°", Integer.toString(vue.getCle()), "vide");
			vue.setPancarte(pancarte);
			tmp = String.join("", IMAGES, "cage.jpg");
		}
		vue.setImage(tmp);
		return vue;
	}
	
	///////////////NOUVEAU////////////////
	
	
	/**
	 * Méthode appelé pour effacer une gazelle mangé par un lion.
	 * Enlève la gazelle dans la cage dans base de données
	 * et supprime l'enregistrement dans table Gazelle concernant la longueur de ses cornes 
	 */
	private void retirer() {
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
	
	public String devorer(CageManagee cmProie) {
		String s = "INCOMPATIBLE";
		Mangeable laBeteConvoitee = null;
		if(controleur.getOccupant() != null && controleur.getOccupant() != cmProie.controleur.getOccupant()
				&& cmProie.controleur.getOccupant() instanceof Mangeable)
		{
			cmProie.controleur.ouvrir();
			try {
				laBeteConvoitee = (Mangeable) cmProie.controleur.sortir();
			} catch (PorteException e2) {
				e2.printStackTrace();
			}
			try {
				s = controleur.devorer(laBeteConvoitee);
				vue.setPoids(controleur.getOccupant().getPoids());
				modele.modifier(vue.getCle(), vue);
				cmProie.retirer();
			} catch (BeurkException e) {
				s = e.getMessage();
				try {
					cmProie.entrer((Animal)laBeteConvoitee);
				} catch (PorteException | CagePleineException e1) {
					e1.printStackTrace();
				}
				cmProie.controleur.fermer();
			}
		}
		return s;
	}
	
	public String creer(String typeAnimal, String nom, int age, double poids, int lgCornes) {
		String s = "Cage occupée";
		if(controleur.getOccupant() == null)
		{
			Animal a = null; 
			Constructor classeAnimal = null;
			try {
				if(typeAnimal.equals("Gazelle"))
				{
					classeAnimal = (Constructor) Class.forName(String.join("", CHEMIN,typeAnimal)).getConstructor(String.class, Integer.TYPE, Double.TYPE, Integer.TYPE);
					a = (Animal) classeAnimal.newInstance(nom, age, poids, lgCornes);
				}
				else
				{
					classeAnimal = (Constructor) Class.forName(String.join("", CHEMIN,typeAnimal)).getConstructor(String.class, Integer.TYPE, Double.TYPE);
					a = (Animal) classeAnimal.newInstance(nom, age, poids);
				}
				controleur.entrer(a);
				controleur.fermer();
				
				if(typeAnimal.equals("Gazelle"))
				{
					GazellePOJO pojo = new GazellePOJO();
					pojo.setIdAnimal(vue.getCle());
					pojo.setLgCornes(lgCornes);
					vue.setGaz(pojo);
				}
				
				vue.setCodeAnimal(typeAnimal);
				vue.setNom(nom);
				vue.setAge(age);
				vue.setPoids(poids);
				modele.modifier(vue.getCle(), vue);
				
				s = "Opération réussie... Nouveau Animal ajouté";
				
			} catch (ClassNotFoundException | PorteException | CagePleineException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		return s;
	}
	
}
