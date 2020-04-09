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
	public List<T> lireTous(){
		return em.createNamedQuery("findAll").getResultList();
	}
	
	public void creer(T obj) {
		if(obj != null)
		{
			em.getTransaction().begin();
		    em.persist(obj);
		    em.getTransaction().commit();
		}
	}
	
	public T lire(int cle) {
		return (T) em.createNamedQuery("lire").setParameter("id", cle).getSingleResult();
	}
	
	public void mettreAJour(T obj) {
		if(obj != null)
		{
			em.getTransaction().begin();
			em.merge(obj);
		    em.getTransaction().commit();
		}
	}
	
	public void supprimer(T obj) {
		if(obj != null)
		{
			em.getTransaction().begin();
			em.remove(obj);
		    em.getTransaction().commit();
		}
	}

	@Override
	public void ecrireTous(List<T> elts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifier(int cle, T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effacer(int cle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effacer(T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouter(T obj) {
		// TODO Auto-generated method stub
		
	}
}
