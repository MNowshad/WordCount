package test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import org.junit.Test;

import com.nowshad.WordCount;

public class WordCountTest {
	 static int topN;
	 static String path;
	/**
	 * It test against the sample dataset given and the result found in the wordcounter.net 
	 * @throws Exception
	 */
	@Test
	public void testTopTenWord() throws Exception {
		
	     readInput();
		
		String[] res = new String [topN];
		//String [] expectedResult = {"the", "a","and", "she",  "to", "her", "thumbelina","in", "of",  "it"};
		WordCount wc = new WordCount();
		res = wc.readFileStream(path,topN);
		for(String s: res){
			if(s.length()>1) System.out.println(s);
		}
			
		//assertTrue(compareArrays(res, expectedResult));  //use this if you want to test your expected resultset with the file output
		
	}

	
	/**
	 * It compares two arrays if they have same values in different order. 
	 * 
	 */
	public static boolean compareArrays(String[] res, String[] expectedResult) {
	    HashSet<String> set1 = new HashSet<String>(Arrays.asList(res));
	    HashSet<String> set2 = new HashSet<String>(Arrays.asList(expectedResult));
	    return set1.equals(set2);
	}
	public void readInput() {
		System.out.println("Enter the File path");
		Scanner scanner = new Scanner(System.in);
		path = scanner.nextLine();
		System.out.println("Enter the N value");
		topN=scanner.nextInt();
		scanner.close();
		
	}

}
