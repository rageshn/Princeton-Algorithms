/**
 * 
 */
package week1;

import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author ragesh
 *
 */
public class WordNet {
	
	/**
	 * constructor takes the name of the two input files
	 * @param synsets
	 * @param hypernyms
	 */
	public WordNet(String synsets, String hypernyms) {
		if(synsets.isEmpty() || synsets == "" || hypernyms.isEmpty() || hypernyms == "")
			throw new java.lang.NullPointerException("Arguments cannot be null");
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(synsets));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * loops through the WordNet nouns
	 * @return all the WordNet nouns
	 */
	public Iterable<String> nouns() {
		return null;
	}
	
	/**
	 * checks whether the word is a noun or not.
	 * @param word
	 * @return whether the word is a noun or not
	 */
	public boolean isNoun(String word) {
		return false;
	}
	
	/**
	 * Gets the distance between the two nouns
	 * @param nounA
	 * @param nounB
	 * @return distance between nounA and nounB
	 */
	public int distance(String nounA, String nounB) {
		return 0;
	}
	
	/**
	 *  a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	 *  in a shortest ancestral path.
	 * @param nounA
	 * @param nounB
	 * @return a synset
	 */
	public String sap(String nounA, String nounB) {
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
