package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pobj.tme5.InvalidMultiSetFormat;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetParser;

public class MultiSetParserTest {
	
	@Test
	public void parserTest() throws InvalidMultiSetFormat {
		MultiSet<String> test = MultiSetParser.parse("data/test.txt"); //cas où tout se passe bien
		assertEquals(15, test.size());
		System.out.println(test.toString());
	}
	
	@Test(expected = InvalidMultiSetFormat.class)
	public void parserTest2() throws InvalidMultiSetFormat {
		MultiSet<String> test = MultiSetParser.parse("test.txt"); //cas où le fichier n'existe pas par exemple
		assertEquals(15, test.size());
	}
	
	@Test(expected = InvalidMultiSetFormat.class)
	public void parserTest3() throws InvalidMultiSetFormat {
		MultiSet<String> test = MultiSetParser.parse("data/test2.txt"); //cas où le format du fichier est incorrect
		assertEquals(15, test.size());
	}
	
	@Test(expected = InvalidMultiSetFormat.class)
	public void parserTest4() throws InvalidMultiSetFormat {
		MultiSet<String> test = MultiSetParser.parse("data/test3.txt"); //cas où le nombre d'occurrences n'est pas un nombre
		assertEquals(15, test.size());
	}

}
