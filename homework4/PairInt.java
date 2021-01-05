package Maze;

/**
 *  Encodes pairs of integers that represent coordinates
 *  
 * @author Nicholas Colonna
 * "I pledge my honor that I have abided by the Stevens Honor System." ncolonna
 */

public class PairInt {
	//Data Fields
	private int x;
	private int y;
	
	/**
	 * Constructor that creates the pair of x and y
	 * @param x
	 * @param y
	 */
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the value of x
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the value of y
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the value of x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the value of y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Returns true if the two pairs are equal and false otherwise
	 */
	public boolean equals(Object p) {
		PairInt secondPair = (PairInt) p;
		return (this.getX() == secondPair.getX() && this.getY() == secondPair.getY());
	}
	
	/**
	 * Returns a string output for a pair
	 */
	public String toString() {
		return "(" + this.getX() + "," + this.getY() + ")";
	}
	
	/**
	 * Returns a new copy of a PairInt
	 * @return copy of a PairInt
	 */
	public PairInt copy() {
		return new PairInt(this.x, this.y);
	}
}
