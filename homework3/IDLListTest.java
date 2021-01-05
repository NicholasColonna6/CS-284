package homework3;
import homework3.IDLList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 
 * @author Nicholas Colonna
 * CS 284A
 * "I pledge my honor that I have abided by the Stevens Honor System." ncolonna
 */
public class IDLListTest {
	
	@Test
	public void testadd() {
		//Test1
		IDLList<Integer> test1 = new IDLList<Integer>();
		test1.add(3);
		assertTrue(test1.getHead()==3);
		assertTrue(test1.getLast()==3);
		assertTrue(test1.size()==1);
		
		//Test2
		IDLList<Integer> test2 = new IDLList<Integer>();
		try {
			test2.add(null);
			assertTrue(false);
		} catch(IllegalArgumentException E) {
			assert(true);
		}
		try {
			test2.remove();
			assertTrue(false);
		} catch(IndexOutOfBoundsException E) {
			assert(true);
		}
		
		//Test3
		IDLList<Integer> test3 = new IDLList<Integer>();
		test3.add(5);
		test3.add(6);
		test3.add(7);
		assertTrue(test3.getHead()==7);
		test3.removeAt(1);
		assertTrue(test3.get(1)==5);
		try {
			test3.removeAt(10);
			assertTrue(false);
		} catch(IndexOutOfBoundsException E) {
			assert(true);
		}
		
		//Test 4
		IDLList<Integer> test4 = new IDLList<Integer>();
		test4.add(8);
		test4.add(1,5);
		assertTrue(test4.getLast()==5);
		test4.add(0,3);
		assertTrue(test4.getHead()==3);
		test4.add(3,9);
		assertTrue(test4.getLast()==9);
		test4.removeLast();
		assertTrue(test4.getLast()==5);
		test4.remove();
		assertTrue(test4.getHead()==8);
		assertTrue(test4.size()==2);
	}
}
