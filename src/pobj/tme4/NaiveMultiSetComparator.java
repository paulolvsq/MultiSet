package pobj.tme4;

import java.util.Comparator;

public class NaiveMultiSetComparator<T> implements Comparator<T> {
	/**
	 * attributs de la classe NaiveMultiSetComparator<T> : 
	 * un NaiveMultiSet<T> ensemble
	 */
	private NaiveMultiSet<T> ensemble;
	/**
	 * constructeur qui prend en paramètre un NaiveMultiSet<T> ensemble et initialise ensemble
	 * @param NaiveMultiSet<T> ensemble
	 */
	public NaiveMultiSetComparator(NaiveMultiSet<T> ensemble) {
		this.ensemble = ensemble;
	}
	/**
	 * compare deux éléments de ensemble
	 */
	public int compare(T arg0, T arg1) {
		if(ensemble.count(arg0) == ensemble.count(arg1)) return 0;
		else if(ensemble.count(arg0) < ensemble.count(arg1)) return -1;
		else return 1;
	}
	
	

}
