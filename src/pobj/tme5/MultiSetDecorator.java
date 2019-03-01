package pobj.tme5;

import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> implements MultiSet<T> {
	
	private MultiSet<T> decorated;
	
	public MultiSetDecorator(MultiSet<T> decorated) {
		this.decorated = decorated;
	}

	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	public boolean add(T e, int count) {
		boolean check = decorated.add(e, count);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}

	public boolean add(T e) {
		boolean check = decorated.add(e);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}

	public boolean remove(Object e) {
		boolean check = decorated.remove(e);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}

	public boolean remove(Object e, int count) {
		boolean check = decorated.remove(e, count);
		try {
			assert isConsistent();
		} catch (InternalError ie) {
			System.out.println(ie.getMessage());
		}
		return check;
	}

	public int count(T o) {
		return decorated.count(o);
	}

	public void clear() {
		decorated.clear();
	}

	public int size() {
		return decorated.size();
	}

	public List<T> elements() {
		return decorated.elements();
	}
	
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
