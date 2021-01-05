package homework5;
import java.util.*;

/**
 * @author Nicholas Colonna
 * "I pledge my honor that I have abided by the Stevens Honor System." ncolonna
 */

public class Treap<E extends Comparable<E>> {
	private static class Node<E extends Comparable<E>> {
		//Data Fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		//Constructor
		/**
		 * Creates a new node with the given data and priority
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if(data==null) {
				throw new IllegalArgumentException();
			}
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
		}
		
		//Methods
		/**
		 * Performs a right rotation
		 * @return reference to the root of the result
		 */
		public Node<E> rotateRight(){
			if(this.left==null) {
				throw new NullPointerException();
			}
			Node<E> temp = this.left;
			this.left = temp.right;			
			temp.right = this;
			return temp;
		}
		
		/**
		 * Performs a left rotation
		 * @return reference to the root of the result
		 */
		public Node<E> rotateLeft(){
			if(this.right==null) {
				throw new NullPointerException();
			}
			Node<E> temp = this.right;
			this.right = temp.left;
			temp.left = this;
			return temp;
		}
		
		public String toString() {
			return "(key=" +data.toString()+ ", priority=" +priority+")";
		}
		
	}
	
	//Data Fields
	private Random priorityGenerator;
	private Node<E> root;
	
	//Constructors
	/**
	 * Creates an empty treap
	 */
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}
	
	/**
	 * Creates an empty treap and initializes priorityGenerator using seed
	 * @param seed
	 */
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	//Methods
	/**
	 * Generates new random priority then calls other add method
	 * @param key
	 * @return true if added successfully & false if the key already exists in the treap
	 */
	public boolean add(E key) {
		int p = priorityGenerator.nextInt();
		return add(key, p);
	}
	
	/**
	 * Adds the given element into the tree
	 * @param key
	 * @param priority
	 * @return true if added successfully & false if the key already exists in the treap
	 */
	public boolean add(E key, int priority) {
		if(find(key) == true) {		//key already exists in treap
			return false;
		}else {						//key not currently in treap
			Node<E> current = new Node<E>(key, priority);
			if(root == null) {			//if treap is empty, make new element root
				root = current;
				return true;
			}else {						//treap is not empty
				Node<E> temp = root;
				Stack<Node<E>> path = new Stack<Node<E>>();
				boolean isDone = false;
				while(!isDone) {
					if(key.compareTo(temp.data) > 0) {
						path.push(temp);
						if(temp.right==null) {
							temp.right = new Node<E>(key, priority);
							temp = temp.right;
							isDone = true;
						}else {
							temp = temp.right;
						}
					}else {
						path.push(temp);
						if(temp.left == null) {
							temp.left = new Node<E>(key, priority);
							temp = temp.left;
							isDone = true;
						}else {
							temp = temp.left;
						}
					}
				}
				reheap(temp, path);
				return isDone;
			}
		}
	}
			
	
	/**
	 * Restores the heap invariant
	 * @param a stack of nodes
	 */
	public void reheap(Node<E> temp, Stack<Node<E>> path) {
		if(path.isEmpty()) {
			root = temp;
		}else if(path.peek().priority > temp.priority) {
			return;
		}else {
			if(temp == path.peek().left) {
				Node<E> first = path.pop();
				first.rotateRight();
				if(!path.isEmpty()) {
					if(first == path.peek().left) {
						path.peek().left = temp;
					}else {
						path.peek().right = temp;
					}
				}
			}else {
				Node<E> first = path.pop();
				first.rotateLeft();
				if(!path.isEmpty()) {
					if(first == path.peek().left) {
						path.peek().left = temp;
					}else {
						path.peek().right = temp;
					}
				}
			}
			reheap(temp, path);
		}
		
	}
	
	
	/**
	 * Deletes the node that has the given key
	 * @param key
	 * @return true if removed successfully & false if key not found in treap
	 */
	public boolean delete(E key) {
		if(find(key) == false) {		//if key isn't in treap
			return false;
		}else {						//key is in treap
			Node<E> temp = root;
			Node<E> parent = null;
			while(true) {
				if(find(temp.left, key) == true) {
					parent = temp;
					temp = temp.left;
				}else if(find(temp.right, key) == true) {
					parent = temp;
					temp = temp.right;
				}else {
					break;
				}
			}
			while(true) {
				if(temp.left == null && temp.right == null) {	//temp is a leaf
					if(temp == root) {
						root = null;
					}else if(parent.left == temp) {
						parent.left = null;
					}else if(parent.right == temp) {
						parent.right = null;
					}
					return true;
				}
				if(temp.left == null) {					//temp only has left child
					parent = temp.rotateLeft();
				}else if(temp.right == null) {			//temp only has right child
					parent = temp.rotateRight();
				}else {									//temp has left and right childs
					if(temp.left.priority > temp.right.priority) {
						parent = temp.rotateRight();
					}else if(temp.left.priority <= temp.right.priority) {
						parent = temp.rotateLeft();
					}
				}
			}
		}
	}
	
	/**
	 * Finds a node with the given key in the reap rooted at root
	 * @param root
	 * @param key
	 * @return true if found & false otherwise
	 */
	private boolean find(Node<E> root, E key) {
		if(root == null) {
			return false;
		}else {
			int comparison = key.compareTo(root.data);
			if(comparison==0) {
				return true;
			}else {
				if(comparison<0) {
					return find(root.left, key);
				}else {
					return find(root.right, key);
				}
			}
		}
	}
	
	/**
	 * Finds a node with the given key in the treap
	 * @param key
	 * @return true if found & false otherwise
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	/**
	 * PreOrder Traversal of the treap to help with printing it in readable string format
	 */
	private void preOrderTraverse(Node<E> n, int depth, StringBuilder s) {
		for (int i=1;i<depth;i++) {
			s.append("   ");
		}
		if (n==null) {
			s.append("null\n");
		} else {
			s.append(n.toString());
			s.append("\n");
			preOrderTraverse(n.left, depth+1, s);
			preOrderTraverse(n.right, depth+1, s);
		}
	}
	
	/**
	 * Makes the treap a readable string
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		preOrderTraverse(root, 1, s);
		return s.toString();
	}
	
	
	public static void main(String[] args) {
//		Treap<Integer> testTree = new Treap<Integer>();
//		testTree.add(4,19); 
//		testTree.add(2,31);
//		testTree.add(6,70); 
//		testTree.add(1,84);
//		testTree.add(3,12); 
//		testTree.add(5,83);
//		testTree.add(7,26);
//		System.out.println(testTree);
//		testTree.delete(3);
//		System.out.println(testTree);
	}
}
