package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pobj.tme5.InvalidMultiSetFormat;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetParser;

public class MultiSetParserTest {
	
	@Test
	public void parserTest() throws InvalidMultiSetFormat {
		MultiSet<String> test = MultiSetParser.parse("data/test.txt");
		assertEquals(15, test.size());
		System.out.println(test.toString());
	}

}
