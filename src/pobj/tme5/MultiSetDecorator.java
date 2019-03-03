package pobj.tme5;

import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> implements MultiSet<T> {
	/**
	 * attributs de la classe MultiSetDecorator : 
	 * un MultiSet<T> decorated 
	 */
	private MultiSet<T> decorated;
	/**
	 * le constructeur prend en paramètre un MultiSet<T> et l'initialise 
	 * @param decorated
	 */
	public MultiSetDecorator(MultiSet<T> decorated) {
		this.decorated = decorated;
	}
	/**
	 * itérateur sur le MultiSet<T> decorated
	 */
	public Iterator<T> iterator() {
		return decorated.iterator();
	}
	/**
	 * prend en paramètres un objet T e et un int count
	 * délègue au MultiSet<T> la méthode
	 * attrape une exception en cas de problèmes
	 */
	public boolean add(T e, int count) {
		boolean check = decorated.add(e, count);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}
	/**
	 * prend en paramètres un objet T e
	 * l'ajoute dans decorated par déléguation
	 * attrape une exception en cas de problèmes
	 */
	public boolean add(T e) {
		boolean check = decorated.add(e);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}
	/**
	 * prend en paramètre un Object e 
	 * supprime par déléguation cet objet dans decorated
	 * attrape une exception en cas de problèmes
	 */
	public boolean remove(Object e) {
		boolean check = decorated.remove(e);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}
	/**
	 * prend en paramètre un Object e et un int count
	 * supprime par déléguation count fois cet objet dans decorated
	 * attrape une exception en cas de problèmes
	 */
	public boolean remove(Object e, int count) {
		boolean check = decorated.remove(e, count);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}
	/**
	 * prend en paramètres un T o
	 * retourne par déléguation le nombre d'occurrences de o dans decorated
	 */
	public int count(T o) {
		return decorated.count(o);
	}
	/**
	 * vide par déléguation le MultiSet<T> decorated
	 */
	public void clear() {
		decorated.clear();
	}
	/**
	 * retourne par déléguation la taille du MultiSet<T> decorated
	 */
	public int size() {	
		return decorated.size();
	}
	/**
	 * retourne la liste des éléments du MultiSet<T> decorated par déléguation
	 */
	public List<T> elements() {
		return decorated.elements();
	}
	/**
	 * retourne true si la structure du multi ensemble est cohérente
	 * false sinon
	 * @return boolean
	 */
	public boolean isConsistent() {
		int nb_Elm = 0;
		Iterator<T> iterator = decorated.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			nb_Elm++;
		}
		if(nb_Elm != decorated.size()) return false;
		return true;
	}

}
