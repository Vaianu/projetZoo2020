package org.formation.zoo.stockage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AccesJPA<T> implements Dao<T>{
	private EntityManager em;

	public AccesJPA() {
		EntityManagerFactory emf = null;
		
		emf = Persistence.createEntityManagerFactory("projetzoo2020");
		em = emf.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<T> lireTous() {
		return em.createNamedQuery("findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	public T lire(int cle) {
		return (T) em.createNamedQuery("lire").setParameter("id", cle).getSingleResult();
	}

	@Override
	public void ecrireTous(List<T> elts) {
		// vide !!!!! car n'a pas de sens avec les bases de données
		
	}

	public void modifier(int cle, T obj) {
		if(obj != null)
		{
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		}	
	}

	@SuppressWarnings("unchecked")
	public void effacer(int cle) {
		T obj = lire(cle);
		if(obj != null)
		{
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		}
	}

	public void effacer(T obj) {
		if(obj != null)
		{
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		}
	}

	public void ajouter(T obj) {
		if(obj != null)
		{
			em.getTransaction();
			em.persist(obj);
			em.getTransaction().commit();
		}
	}
	
}
