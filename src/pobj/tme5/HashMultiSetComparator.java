package pobj.tme5;

import java.util.Comparator;

public class HashMultiSetComparator<T> implements Comparator<T> {
	/**
	 * attributs de la classe HashMultiSetComparator<T> : 
	 * un HashMultiSet<T> ensemble
	 */
	private HashMultiSet<T> ensemble;
	/**
	 * constructeur qui prend en paramètre un HashMultiSet<T> ensemble et initialise ensemble avec
	 * @param HashMultiSet<T> ensemble
	 */
	public HashMultiSetComparator(HashMultiSet<T> ensemble) {
		this.ensemble = ensemble;
	}
	/**
	 * compare deux éléments de ensemble
	 */
	public int compare(T o1, T o2) {
		return ensemble.getEnsemble().get(o1).compareTo(ensemble.getEnsemble().get(o2));
	}

}