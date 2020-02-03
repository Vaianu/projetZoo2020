package org.formation.zoo.stockage;

import java.util.List;

public interface Dao<T> {
	public List<T> lireTous();
	public void ecrireTous(List<T> elts);
}
