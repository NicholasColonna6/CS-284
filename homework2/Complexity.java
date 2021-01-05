package homework2;

//Nicholas Colonna        CS 284A
//"I pledge my honor that I have abided by the Stevens Honor System." ncolonna

public class Complexity {
	/**
	 * Method that has time complexity O(n^2)
	 * @param n
	 */
	public void method1(int n) {
		int counter=1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
	}
	
	/**
	 * Method that has time complexity O(n^3)
	 * @param n
	 */
	public void method4(int n) {
		int counter=1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					System.out.println("Operation "+counter);
					counter++;
				}
			}
		}
	}
	
	/**
	 * Method that has time complexity O(log(n))
	 * @param n
	 */
	public void method2(int n) {
		int counter=1;
		for(int i=1; i<n; i*=2) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	/**
	 * Method that has time complexity O(nlog(n))
	 * @param n
	 */
	public void method3(int n) {
		int counter=1;
		for(int i=0; i<n; i++) {	
			for(int j=1; j<n; j*=2) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
	}
	
	/**
	 * Method that has time complexity O(log(log(n)))
	 * @param n
	 */
	public void method5(int n) {
		int counter=1;
		for(int i=2; i<n; i*=i) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	/**
	 * BONUS: Method that has time complexity O(2^n)
	 * @param n
	 * @return fibonacci of n
	 */
	public int method6(int n) {
		if(n<=1) {
			return n;
		}else {
			return method6(n-2) + method6(n-1);
		}
	}
	
	
	public static void main(String[] args) {
//		Complexity num = new Complexity();
//		num.method1(5);
//		num.method4(5);
//		num.method2(32);
//		num.method3(32);
//		num.method5(256);
//		System.out.println(num.method6(7));
	}
}
