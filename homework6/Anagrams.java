package homework6;
import java.util.*;	
import java.io.*;

/**
 * "I pledge my honor that I have abided by the Stevens Honor System." ncolonna
 * @author Nicholas Colonna
 */
public class Anagrams {
	//Data Fields
	final Integer[] primes;
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;
	
	/**
	 * Constructor
	 * Initializes all variables
	 */
	public Anagrams() {
		primes = new Integer[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
		letterTable = new HashMap<>();
		anagramTable = new HashMap<>();
		buildLetterTable();
	}
	
	/**
	 * Builds the hash table LetterTable
	 */
	private void buildLetterTable() {
		for(int i=0; i<26; i++) {
			letterTable.put((char) (i+97), primes[i]);
		}
	}

	/**
	 * Compute the has code of the string s passed as an argument, and adds word to has table anagramTable
	 * @param s
	 */
	public void addWord(String s) {
		if(anagramTable.containsKey(myHashCode(s))) {	//key already in table
			anagramTable.get(myHashCode(s)).add(s);
		}else {											//key not in table yet
			ArrayList<String> w = new ArrayList<>();
			w.add(s);
			anagramTable.put(myHashCode(s), w);
		}
	}

	/**
	 * Given string s, it computes its hash code
	 * @param s
	 * @return hash code that was computed
	 */
	public Long myHashCode(String s) {
		long product = 1;
		for(char c: s.toCharArray()) {
			product *= letterTable.get(c);
		}
		return product;
	}
	
	/**
	 * Receives the name of a text file containing words, one per line, and builds the hash table anagramTable
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		String strLine;
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		} 
		br.close();
	}
	
	/**
	 * Finds the entries in anagramTable that have largest number of anagrams
	 * @return list of words with largest number of anagrams
	 */
	public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){
		ArrayList<Map.Entry<Long, ArrayList<String>>> result = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		ArrayList<String> max = new ArrayList<String>();
		
		for(Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			if(entry.getValue().size() == max.size()) {		//same amount as current max
				result.add(entry);
			}else if(entry.getValue().size() > max.size()) {		//greater than current max
				result = new ArrayList<>();
				max = entry.getValue();
				result.add(entry);
			}
		}
		return result;
	}
	
	/**
	 * Read all strings in a file, place them in hash table of anagrams, then iterates over hash table to report which words has the largest number of anagrams
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
