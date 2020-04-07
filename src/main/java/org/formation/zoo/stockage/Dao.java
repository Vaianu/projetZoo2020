package org.formation.zoo.stockage;

import java.util.List;

public interface Dao<T> {
	public List<T> lireTous();
	public T lire(int cle);
	public void ecrireTous(List<T> elts);
	public void modifier(int cle, T obj);
	public void effacer(int cle);
	public void effacer(T obj);
	public void ajouter(T obj);
}
