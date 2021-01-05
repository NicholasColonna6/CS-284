package homework5;
import homework5.Treap;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Nicholas Colonna
 * "I pledge my honor that I have abided by the Stevens Honor System." ncolonna
 */

public class TreapTest {

	@Test
	public void findTest() {
		Treap<Integer> testTree = new Treap<>();
		testTree.add(5,1);
		testTree.add(3,3);
		testTree.add(7,6);
		testTree.add(4,2);
		
		assertEquals(testTree.find(7), true);
		assertEquals(testTree.find(3), true);
		assertEquals(testTree.find(22), false);
	}

	@Test
	public void addTest() {
		Treap<Integer> testTree = new Treap<>();
		boolean temp1 = testTree.add(22,11);
		boolean temp2 = testTree.add(15,5);
		boolean temp3 = testTree.add(3,9);
		boolean temp4 = testTree.add(6);
		boolean temp5 = testTree.add(22);
		
		assertTrue(temp1 == true);
		assertTrue(temp2 == true);
		assertTrue(temp3 == true);
		assertTrue(temp4 == true);
		assertTrue(temp5 == false);
	}
	
	
	@Test
	public void deleteTest() {
		Treap <Integer> testTree = new Treap<>();
		testTree.add(6,70);
		testTree.add(3,12);
		testTree.add(1,84);
		
		boolean w = testTree.delete(3);
		boolean x = testTree.delete(10);
		boolean y = testTree.delete(1);
		boolean z = testTree.delete(33);
		
		assertEquals(w, true);
		assertEquals(x, false);
		assertEquals(y,true);
		assertEquals(z,false);
	}
	
	@Test
	public void toStringTest() {
		Treap<Integer> testTree = new Treap<>();
		assertEquals(testTree.toString(), "null\n");
		testTree.add(4,19);
		assertEquals(testTree.toString(), "(key=4, priority=19)\n   null\n   null\n");
		testTree.add(7,26);
		assertEquals(testTree.toString(), "(key=7, priority=26)\n   (key=4, priority=19)\n      null\n      null\n   null\n" );
		}
}
