package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;

public class HashMultiSetTest {
	
	private MultiSet<String> m;
	private MultiSet<String> mPlein;
	private MultiSet<String> mMoitie ;
	private MultiSet<String> mDecorator;
	private MultiSet<String> mDecoratorPlein;
	private MultiSet<String> mDecoratorMoitie;
	
	@Before
	public void before() {
		m = new HashMultiSet<>();
		
		mPlein = new HashMultiSet<>();
		for(int i = 0; i < 10; i++) {
			mPlein.add(""+i);
		}
		
		mMoitie = new HashMultiSet<>();
		for(int i = 0; i < 5; i++) {
			mMoitie.add(""+i);
		}
		mDecorator = new MultiSetDecorator<>(m);
		mDecoratorMoitie = new MultiSetDecorator<>(mMoitie);
		mDecoratorPlein = new MultiSetDecorator<>(mPlein);
	}
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
		mDecorator.add("a");
		mDecorator.add("a",5);
		assertEquals(6*2, mDecorator.count("a")); //taille *2 car c'est une référence vers m qui est dans mDecorator
		//dans ce cas si on ajoute 6 éléments dans m puis 6 dans mDecorator il y aura au total 12 éléments dans mDecorator
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
		mDecorator.add("a");
		mDecorator.add("a",-1);
	}
	
	@Test
	public void testRemove() {
		mPlein.remove("1");
		assertEquals(0, mPlein.count("1"));
		assertEquals(9, mPlein.size());
		mDecoratorPlein.remove("1");
		assertEquals(0, mDecoratorPlein.count("1"));
		assertEquals(9, mDecoratorPlein.size());
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		mPlein.remove("1", -1);
		mDecoratorPlein.remove("1", -1);
	}
	
	@Test
	public void testSize() {
		assertEquals(10, mPlein.size());
		assertEquals(10, mDecoratorPlein.size());
	}
	
	@Test
	public void testClear() {
		mPlein.clear();
		assertEquals(mPlein.size(), 0);
		mDecoratorPlein.clear();
		assertEquals(mDecoratorPlein.size(), 0);
	}
	
	@Test
	public void testComplexe() {
		mMoitie.add("a");
		assertEquals(6, mMoitie.size());
		mMoitie.add("1");
		mMoitie.remove("3");
		mMoitie.add("7");
		mMoitie.remove("2");
		mMoitie.add("c");
		mMoitie.remove("a");
		assertEquals(2, mMoitie.count("1"));
		assertEquals(0, mMoitie.count("3"));
		assertEquals(6, mMoitie.size()); //on a 6 éléments dans mMoitie
		
		mDecoratorMoitie.add("a"); //si on en ajoute un autre
		assertEquals(7, mDecoratorMoitie.size()); //on doit avoir 7 dans mDecoratorMoitie car il contient un référence vers mMoitie
		mDecoratorMoitie.add("1");
		mDecoratorMoitie.remove("3");
		mDecoratorMoitie.add("7");
		mDecoratorMoitie.remove("2");
		mDecoratorMoitie.add("c");
		mDecoratorMoitie.remove("a");
		assertEquals(3, mDecoratorMoitie.count("1")); //j'ai dans mMoitie 2 occurrences de 1 et j'en ajoute une dans 
		//mDecoratorMoitie -> 3 occurrences au total
		assertEquals(0, mDecoratorMoitie.count("3")); //j'ai 0 occurrences de 3 dans mMoitie donc dans mMoitieDecorator -> 0
		assertEquals(9, mDecoratorMoitie.size()); //dans le mDecoratorMoitie on a exactement : 
		//0, 1, 1, 1, 4, 7, c, 7, c
	}
}
