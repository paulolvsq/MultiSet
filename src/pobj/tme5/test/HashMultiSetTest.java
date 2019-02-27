package pobj.tme5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;

public class HashMultiSetTest {
	
	private MultiSet<String> m;
	private MultiSet<String> mPlein;
	private MultiSet<String> mMoitie ;
	
	@Before
	public void before() {
		m = new HashMultiSet<>();
		
		mPlein = new HashMultiSet<>();
		for(int i=0; i<10; i++) {
			mPlein.add(""+i);
		}
		
		mMoitie = new HashMultiSet<>();
		for(int i=0; i<5; i++) {
			mMoitie.add(""+i);
		}
	}
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
	}
	
	@Test
	public void testRemove() {
		mPlein.remove("1");
		assertEquals(0, mPlein.count("1"));
		System.out.println(mPlein.toString());
		System.out.println(mPlein.size());
		assertEquals(9, mPlein.size());
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		mPlein.remove("1",-1);
	}
	
	@Test
	public void testSize() {
		assertEquals(10, mPlein.size());
	}
	
	@Test
	public void testClear() {
		mPlein.clear();
		assertTrue(mPlein.size() == 0);
	}
	
	@Test
	public void testComplexe1() {
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
		assertEquals(6, mMoitie.size());
	}
}
