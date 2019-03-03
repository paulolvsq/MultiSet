package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
    /**
     * attributs de la classe HashMultiSet : 
     * une HashMap<T, Integer> ensemble qui contient les éléments avec pour chaque élément son nombre d'occurences associé
     * un int size qui est la taille de ensemble
     */
	private HashMap<T, Integer> ensemble;
	private int size;
	/**
	 * constructeur sans paramètres qui crée une nouvelle HashMap vide et qui met la taille à 0
	 */
	public HashMultiSet() {
		ensemble = new HashMap<T, Integer>();
		size = 0;
	}
	/**
	 * constructeur qui prend en paramètre une Collection<T> liste
	 * crée une nouvelle HashMap
	 * la remplit avec les éléments de la liste
	 * @param liste
	 */
	public HashMultiSet(Collection<T> liste) {
		ensemble = new HashMap<T, Integer>();
		for(T el : liste) {
			add(el);
		}
	}
	/**
	 * prend en paramètres un objet T e et un int count
	 * ajoute count fois l'objet e dans la HashMap ensemble
	 * on n'oublie pas d'incrémenter correctement la taille et le nombre d'occurences de e
	 */
	public boolean add(T e, int count) {
		if(count < 0) throw new IllegalArgumentException("erreur : count négatif");
		int nb_Occ = count(e);
		ensemble.put(e, count + nb_Occ);
		size += count;
		//assert isConsistent();
		return true;
		
	}
	/**
	 * prend en paramètre un objet T e
	 * ajoute une seule fois dans la HashMap ensemble l'objet e
	 * on n'oublie pas d'incrémenter la taille et le nombre d'occurences de e
	 */
	public boolean add(T e) {
		int nb_Occ = count(e);
		ensemble.put(e, nb_Occ + 1);
		size++;
	    //assert isConsistent();
		return true;
	}
	/**
	 * prend en paramètre un objet e
	 * réalise un cast en T de l'objet
	 * on regarde si dans ensemble il y a l'objet 
	 * dans ce cas on compte combien il y en a et s'il y en a un seul on le supprime et on retourne true
	 * sinon on le retire une seule fois, on n'oublie pas de décrémenter la taille et le nombre d'occurrences de e puis on retourne true
	 * dans le dernier cas c'est que l'objet n'est pas dans ensemble, on ne peut pas le supprimer, on retourne false
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object e) {
		T tmp = (T) (e);
		if (ensemble.containsKey(e)) {
			int nb_Occ = count(tmp);
			if (nb_Occ == 1) {
				ensemble.remove(tmp);
				size--;
				//assert isConsistent();
				return true;
			}
			ensemble.put(tmp, nb_Occ - 1);
			size--;
			//assert isConsistent();
			return true;
		}
		//assert isConsistent();
		return false;
	}
	/**
	 * prend en paramètre un objet e et un int count
	 * réalise un cast en T de l'objet
	 * on regarde si dans ensemble il y a l'objet 
	 * s'il est présent moins de fois que count on le supprime totalement et on retourne true
	 * sinon on le retire count fois et on n'oublie pas de décrémenter la taille et le nombre d'occurrences de e puis on retourne true
	 * dans le dernier cas c'est que l'objet n'est pas présent donc on retourne false
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object e, int count) {
		if(count < 0) throw new IllegalArgumentException("erreur : count négatif");
		T tmp = (T) (e);
		if(ensemble.containsKey(tmp)) {
			int nb_Occ = ensemble.get(tmp);
			if(nb_Occ < count) {
				ensemble.remove(tmp);
				assert isConsistent();
				return true;
			}
			ensemble.put(tmp, nb_Occ - count);
			size -= count;
			assert isConsistent();
			return true;
		}
		assert isConsistent();
		return false;
	}
	/**
	 * prend en paramètre un objet T o
	 * on regarde si l'objet est dans l'ensemble, si oui c'est qu'il a déjà un nombre d'occurrences qui lui est associé
	 * on retourne ce nombre
	 * s'il n'est pas présent c'est qu'il y en 0
	 */
	public int count(T o) {
		if (ensemble.containsKey(o)) return ensemble.get(o);
		else return 0;
	}
	/**
	 * nettoie la HashMap ensemble et remet la taille à 0
	 */
	public void clear() {
		ensemble.clear();
		size = 0;
	}
	/**
	 * retourne la taille de la HashMap ensemble
	 */
	public int size() {
		return this.size;
	}
	/**
	 * retourne une HashMap<T, Integer> ensemble
	 * @return HashMap<T, Integer> ensemble
	 */
	public HashMap<T, Integer> getEnsemble() {
		return this.ensemble;
	}
	/**
	 * retourne une nouvelle HashMultiSetIterator<T> sur laquelle on peut itérer
	 */
	public Iterator<T> iterator() {
		return new HashMultiSetIterator<T>(this);
	}
	/**
	 * retourne la chaine de caractères qui affiche correctement la HashMap ensemble
	 * pour la question 5.2
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("[\n");
		for(Map.Entry<T, Integer> element : ensemble.entrySet()) {
			T tmp = element.getKey();
			Integer valeur = element.getValue();
			s.append(tmp.toString()+" : "+ valeur.toString()+" ;\n");
		}
		s.append("]");
		return s.toString();
	}
	/**
	 * retourne la chaine de caractères qui affiche correctement la HashMap ensemble
	 * pour la question 5.6
	 */
	/*public String toString() {
		StringBuffer s = new StringBuffer();
		for(Map.Entry<T, Integer> element : ensemble.entrySet()) {
			T tmp = element.getKey();
			Integer valeur = element.getValue();
			s.append(tmp.toString()+":"+ valeur.toString()+"\n");
		}
		return s.toString();
	}*/
	/**
	 * retourne une ArrayList<T> qui contient tous les éléments de la HashMap ensemble
	 */
	public List<T> elements() {
		/*List<T> elements = new ArrayList<T>();
		for(Map.Entry<T, Integer> elt : ensemble.entrySet()) {
			elements.add(elt.getKey());
		}
		return elements;*/
		return new ArrayList<T>(ensemble.keySet());
	}
	/**
	 * retourne true si la structure du multi ensemble est cohérente
	 * false sinon
	 * @return boolean
	 */
	public boolean isConsistent() {
		int nb_Elem = 0;
		for(Entry<T, Integer> valeur : this.ensemble.entrySet()) {
			Integer n = valeur.getValue();
			if(n < 0) return false;
			nb_Elem += n;
		}
		if(this.ensemble.size() != nb_Elem) return false;
		return true;
	}
	
}
