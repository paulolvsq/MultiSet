package pobj.tme4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NaiveMultiSet<T> implements MultiSet<T> {
	/**
	 * attributs de la classe NaiveMultiSet : 
	 * une List<T> ensemble 
	 */
	private List<T> ensemble;
	/**
	 * constructeur sans paramètres qui initialise ensemble
	 */
	public NaiveMultiSet() {
		ensemble = new ArrayList<T>();
	}
	/**
	 * constructeur qui prend en paramètre une List<T> ensemble et qui initialise ensemble
	 * @param List<T> ensemble
	 */
	public NaiveMultiSet(List<T> ensemble) {
		this.ensemble = ensemble;
	}
	/**
	 * prend en paramètres un T e et un int count
	 * ajoute count fois l'objet e dans ensemble
	 * retourne true
	 */
	public boolean add(T e, int count) {
		for(int i = 0; i < count; i++) {
			ensemble.add(e);
		}
		return true;
	}
	/**
	 * prend en paramètres un objet e 
	 * ajoute une seule fois à ensemble cet objet e 
	 * retourne true
	 */
	public boolean add(T e) {
		ensemble.add(e);
		return true;
	}
	/**
	 * supprime l'objet e passé en paramètre de ensemble
	 */
	public boolean remove(Object e) {
		ensemble.remove(e);
		return true;
	}
	/**
	 * supprime count fois l'objet e de ensemble
	 */
	public boolean remove(Object e, int count) {
		for(int i = 0; i < count; i++) {
			ensemble.remove(e);
		}
		return true;
	}
	/**
	 * prend en paramètre un objet T o
	 * retourne le nombre d'occurrences de o dans la liste ensemble
	 */
	public int count(T o) {
		int cpt = 0;
		for(int i = 0; i < ensemble.size(); i++) {
			if(o.equals(ensemble.get(i))) cpt++;
		}
		return cpt;
	}
	/**
	 * vide la liste ensemble
	 */
	public void clear() {
		ensemble.clear();
	}
	/**
	 * retourne la taille de la liste
	 */
	public int size() {
		return ensemble.size();
	}
	/**
	 * retourne la liste ensemble
	 */
	public List<T> elements() {
		return ensemble;
	}
	/**
	 * retourne l'itérateur de la liste ensemble
	 */
	public Iterator<T> iterator() {
		return ensemble.iterator();
	}
	
}
