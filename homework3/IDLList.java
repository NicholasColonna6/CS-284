package homework3;
import java.util.*;

/**
 * 
 * @author Nicholas Colonna
 * CS 284A
 * "I pledge my honor that I have abided by the Stevens Honor System" ncolonna
 * @param <E>
 */
public class IDLList<E> {
	
	private class Node<E> {
		//Data Fields
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		//Constructors
		Node(E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//Data Fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//Constructor
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}
	
	//Methods
	
	/**
	 * Adds elem at position index, counting from head
	 * @param index (where you're adding elem)
	 * @param elem (what you're adding in)
	 * @return true
	 */
	public boolean add(int index, E elem) {
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if(elem == null) {
			throw new IllegalArgumentException();
		}
		
		Node<E> temp = new Node<E>(elem);
		
		if(index==0) {
			this.add(elem);
			return true;
		}else {
			if(index==size) {
				tail.next = temp;
				temp.prev = tail;
				tail = temp;
			}else {
				temp.prev = indices.get(index-1);
				temp.next = indices.get(index);
				indices.get(index-1).next = temp;
				indices.get(index).prev = temp;
			}
			indices.add(index, temp);
			size++;
			return true;
		}
	}
	
	/**
	 * Adds elem at the head
	 * @param elem (what you're adding)
	 * @return true
	 */
	public boolean add(E elem) {
		Node<E> temp = new Node<E>(elem);
		
		if(elem==null) {
			throw new IllegalArgumentException();
		} else if(this.size == 0) {
			this.head = temp;
			this.tail = temp;
		}else {
			this.head.prev = temp;
			temp.next = this.head;
			this.head = temp;
		}
		size++;
		indices.add(0, temp);
		return true;
	}
	
	/**
	 * Adds elem at the new last element of the list
	 * @param elem (what you're adding)
	 * @return true
	 */
	public boolean append(E elem) {
		Node<E> temp = new Node<E>(elem);
		
		if(elem == null) {
			throw new IllegalArgumentException();
		}else if(head == null) {
			this.head = temp;
			this.tail = temp;
		}else {
			tail.next = temp;
			temp.prev = tail;
			tail = temp;
		}
		indices.add(temp);
		size++;
		return true;
	}
	
	/**
	 * Gets an object at position index
	 * @param index (where you're getting the object)
	 * @return object at position index from the head
	 */
	public E get(int index) {
		return indices.get(index).data;
	}
	
	/**
	 * Gets object at the head
	 * @return object at the head
	 */
	public E getHead() {
		if(size==0) {
			return null;
		}else {
			return head.data;
		}
	}
	
	/**
	 * Gets object at the tail
	 * @return object at the tail
	 */
	public E getLast() {
		if(size==0) {
			return null;
		}else {
			return tail.data;
		}
	}
	
	/**
	 * Gets the list size
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes and returns the element at the head
	 * @return the element at the head
	 */
	public E remove() {
		if(size==0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(size ==1) {
			head = null;
			tail = null;
			size--;
			return head.data;
		}else {
			Node<E> oldHead = this.head;
			Node<E> newHead = this.head.next;
			this.head = newHead;
			this.head.next = newHead.next;
			indices.remove(0);
			size--;
			return oldHead.data;
		}
	}
	
	/**
	 * Removes and returns the element at the tail
	 * @return the element at the tail
	 */
	public E removeLast() {
		if(size==0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(size==1) {
			head = null;
			tail = null;
			size--;
			return head.data;
		}else {
			Node<E> oldTail = this.tail;
			Node<E> newTail = this.tail.prev;
			this.tail = newTail;
			this.tail.next = null;
			this.tail.prev = newTail.prev;
			indices.remove(size-1);
			size--;
			return oldTail.data;
		}
	}
	
	/**
	 * Removes and returns element at a specific index
	 * @param index (where you are removing element)
	 * @return the element at position index
	 */
	public E removeAt(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index==0 || size==1) {
			return remove();
		}else if(index==(size-1)){
			return removeLast();
		}else {
			Node<E> temp = indices.get(index);
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			indices.remove(index);
			size--;
			return temp.data;
		}
	}
	
	/**
	 * Removes the first occurrence of elem in the list
	 * @param elem (item you are looking to remove)
	 * @return true
	 */
	public boolean remove(E elem) {
		Node<E> temp = new Node<E>(elem);
		
		if(elem==null) {
			throw new IllegalArgumentException();
		}
		
		if(elem==head.data) {
			head = head.next;
			head.prev = null;
			indices.remove(0);
			return true;
		}else {
			temp = this.head;
			if(temp.next == null) {
				return false;
			}
			
			while(temp.next != null) {
				if(temp.next.data == elem) {
					temp = temp.next;
					System.out.println(temp.data);
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
					indices.remove(elem);
					size--;
					return true;
				}else {
					temp = temp.next;
				}
			}
			return false;
		}
	}
	
	/**
	 * Return a string representation of the list
	 */
	public String toString() {
		String result = "[ ";
		Node<E> current = this.head;
		while(current != null) {
			result = result + current.data.toString() + " ";
			current = current.next;
		}
		result += "]";
		return result;
	}
	
	
	
	public static void main(String[] args) {
//		IDLList<Integer> test = new IDLList<Integer>();
//		test.add(2);
//		test.add(3);
//		test.add(5);
//		test.add(6);
//		test.add(2,4);
//		test.append(1);
//		System.out.println(test);
//		
//		test.removeLast();
//		System.out.println(test);
	}
}
