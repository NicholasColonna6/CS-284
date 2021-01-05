package homework6;
import static org.junit.Assert.*;	
import org.junit.Test;

/**
 * "I pledge my honor that I have abided by the Stevens Honor System." ncolonna
 * @author Nicholas Colonna
 */
public class AnagramsTest {

	@Test
	public void buildLetterTableTest() {
		Anagrams test = new Anagrams();
		assertEquals("{a=2, b=3, c=5, d=7, e=11, f=13, g=17, h=19, i=23, j=29, k=31, l=37, m=41, n=43, o=47, p=53, q=59, r=61, s=67, t=71, u=73, v=79, w=83, x=89, y=97, z=101}", test.letterTable.toString());
	}
	
	@Test
	public void hashCodeAddWordTest() {
		Anagrams test = new Anagrams();
		test.addWord("slater");
		assertEquals("236204078", test.myHashCode("alters").toString());
		
		test.addWord("care");
		assertEquals("6710", test.myHashCode("care").toString());
		
		test.addWord("maker");
		assertEquals("1705682", test.myHashCode("maker").toString());
	}
	
	@Test
	public void getMaxEntriesTest() {
		Anagrams test = new Anagrams();
		test.addWord("slater");
		test.addWord("alerts");
		test.addWord("relast");
		test.addWord("race");
		test.addWord("care");
		test.addWord("maker");
		assertEquals("[236204078=[slater, alerts, relast]]", test.getMaxEntries().toString());
	}

}
