package org.formation.zoo.utilitaires;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.formation.zoo.modele.metier.Animal;
import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.modele.technique.CagePleineException;
import org.formation.zoo.modele.technique.PorteException;
import org.formation.zoo.service.CagePOJO;

/**
 * classe boite à outils
 * @author v.samg mouit
 *
 */
public final class Conversion {
	public static final String MODELE = "org.formation.zoo.modele.metier.";
	private Conversion() {
	}
	public static Cage pojoToCage(CagePOJO cp) {
		Cage ret = null;
		Animal bete = null;
		Class<?> laClassDeLaBete = null;
		Class<?> lesTypes[] = null;
		Object lesValeurs[] = null;
		Constructor<?> construct = null;
		
		ret = new Cage(cp.getX(), cp.getY());
		//SI un occupant
		if (cp.getCodeAnimal() != null) {
			//SI on a une gazelle
			if(cp.getCodeAnimal().equals("Gazelle")) {
				lesTypes = new Class<?>[4];
				lesValeurs = new Object[4];
				lesTypes[3] = int.class;
				lesValeurs[3] = cp.getGaz().getLgCornes();
			}
			else
			{
				lesTypes = new Class<?>[3];
				lesValeurs = new Object[3];
			}
			try {
				laClassDeLaBete = Class.forName(MODELE+cp.getCodeAnimal());
				
				lesTypes[0] = String.class;
				lesTypes[1] = int.class;
				lesTypes[2] = double.class;
				lesValeurs[0] = cp.getNom();
				lesValeurs[1] = cp.getAge();
				lesValeurs[2] = cp.getPoids();
				construct = laClassDeLaBete.getConstructor(lesTypes); // retournent le tableau des constructeurs de cette classes
				bete = (Animal) construct.newInstance(lesValeurs);	  // créé une nouvelle instance de Animal
				ret.ouvrir();
				ret.entrer(bete);
				ret.fermer();
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PorteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CagePleineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
}
