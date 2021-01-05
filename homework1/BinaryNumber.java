package homework1;
import java.util.Arrays;

// Nicholas Colonna     CS 284A
// "I pledge my honor that I have abided by the Stevens Honor System." ncolonna

public class BinaryNumber {
	private int data[];
	private boolean overflow;
	
	/**
	 * Constructor for BinaryNumber
	 * @param length (length of the binary number)
	 */
	public BinaryNumber(int length) {
		this.data = new int[length];
	}
	
	/**
	 * Constructor for BinaryNumber
	 * @param str (binary string to convert to type BinaryNumber)
	 */
	public BinaryNumber(String str) {
		this.data = new int[str.length()];
		for(int i=0; i<str.length(); i++) {
			data[i] = java.lang.Character.getNumericValue(str.charAt(i));
		}
	}
	
	/**
	 * Gives the length of the BinaryNumber
	 * @return the length of the BinaryNumber
	 */
	public int getLength() {
		return data.length;
	}
	
	/**
	 * Obtains a specific digit of a BinaryNumber given an index
	 * @param index (index that digit is at)
	 * @return the specific digit at the index
	 */
	public int getDigit(int index) {
		if(index<0 || index >= data.length) {
			System.out.println("Out of bounds");
			return -1;
		}
		return data[index];
	}
	
	/**
	 * Shifts all digits in a BinaryNumber an amount of places to the right
	 * @param amount (number of places the BinaryNumber is shifting)
	 */
	public void shiftR(int amount) {
		if(amount<0) {
			System.out.println("Cannot shift digits to the right with a negative value");
		} else {
			int[] dataCopy = data;
			data = new int[data.length + amount];
			for(int i=amount; i<data.length; i++) {
				data[i] = dataCopy[i-amount];
			}
		}
	}
	
	/**
	 * Adds two BinaryNumbers
	 * @param aBinaryNumber
	 */
	public void add(BinaryNumber aBinaryNumber) {
		int carry = 0;
		int[] result = new int[data.length];
		if(data.length != aBinaryNumber.data.length) {
			System.out.println("The two binary numbers have different lengths");
			return;
		}
		for(int i=0; i<result.length; i++) {
			if((data[i] + aBinaryNumber.data[i] + carry)==0) {
				result[i] = 0;
				carry = 0;
			} else if((data[i] + aBinaryNumber.data[i] + carry)==1) {
				result[i] = 1;
				carry = 0;
			} else if((data[i] + aBinaryNumber.data[i] + carry)==2) {
				result[i] = 0;
				carry = 1;
			} else if((data[i] + aBinaryNumber.data[i] + carry)==3) {
				result[i] = 1;
				carry = 1;
			}
		}
		if(carry==1) {
			overflow = true;
			result = Arrays.copyOf(result, result.length + 1);
			result[result.length - 1] = carry;
		}
		data = result;
	}
	
	/**
	 * Creates a string representation of BinaryNumber
	 * @return a string representation of BinaryNumnber
	 */
	public String toString() {
		String result = "";
		for(int i=0; i<data.length; i++) {
			result += data[i];
		}
		if(overflow == true) {
			System.out.println(result);
			return "Overflow";
		}
		return result;
	}
	
	/**
	 * Turns a BinaryNumber to decimal notation
	 * @return the decimal notation of a BinaryNumber
	 */
	public int toDecimal() {
		int decimal = 0;
		for(int i=0; i<data.length; i++) {
			if(data[i]==1) {
				decimal = (int) (decimal + Math.pow(2, i));
			}
		}
		return decimal;
	}
	
	/**
	 * Clears the overflow flag
	 */
	public void clearOverflow() {
		overflow = false;
	}
	
	public static void main(String[] args) {
//		//Testing methods
//		BinaryNumber num = new BinaryNumber("10110");
//		BinaryNumber num2 = new BinaryNumber("11101");
//		System.out.println(num);
//		System.out.println(num2);
//		System.out.println(num.getLength());
//		System.out.println(num2.getDigit(2));
//		num.add(num2);
//		System.out.println(num);
//		System.out.println(num.toDecimal());
	}
}
