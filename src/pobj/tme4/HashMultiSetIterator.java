package pobj.tme4;

import java.util.Iterator;
import java.util.Map;

public class HashMultiSetIterator<T> implements Iterator<T> {
	/**
	 * attributs de la classe HashMultiSetIterator<T> : 
	 * il faut un T res qui correspond à l'objet que l'on manipule 
	 * un int curseur qui correspond à la position sur laquelle on est dans le HashMultiSet<T>
	 * un Iterator<Map.Entry<T, Integer>> iterateur qui correspond à un itérateur 
	 */
	private T res;
	private int curseur = 0;
	private Iterator<Map.Entry<T, Integer>> iterateur;
	/**
	 * constructeur qui prend en paramètre un HashMultiSet<T> ensemble
	 * on définit l'itérateur comme étant un itérateur sur les paires clé-valeur du HashMultiSet passé en paramètre sur lequel on récupère 
	 * les clés-valeurs de la HashMap 
	 * @param HashMultiSet<T> ensemble
	 */
	public HashMultiSetIterator(HashMultiSet<T> ensemble) {
		iterateur = ensemble.getEnsemble().entrySet().iterator();
	}
	/**
	 * retourne true si on a un élément suivant, false sinon
	 */
	public boolean hasNext() {
		return iterateur.hasNext() || curseur > 0;
	}
	/**
	 * retourne l'élément suivant
	 */
	public T next() {
		if(curseur == 0) {
			Map.Entry<T, Integer> elt = iterateur.next();
			res = elt.getKey();
			curseur = elt.getValue();
		}
		curseur--;
		return res;
	}

}
