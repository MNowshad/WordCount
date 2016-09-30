
package com.nowshad;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.nowshad.ByteReader;
import com.nowshad.ChunkDataReader;
import com.nowshad.CustomString;
import com.nowshad.QuickSelect;
import com.nowshad.WordPreProcessing;

/**
 * WordCount.Java - Returns an array of Top 10 words
 * @version 1.0
 * @author Nowshad
 * 
 */

public class WordCount {
	private static final int BUFFER_SIZE = 1 << 18;   
	final static Map<String, Integer> wordMap = new HashMap<String, Integer>();
	 static int topN;
	
	
	
	/**
	 * @param path : file path of the input file
	 * @return a String array of top 10 words
	 * 
	 * */
    public String[] readFileStream (String path, int n) {
    	topN=n;
    	String[] res = new String [topN];
    	InputStream is = null;
    	
    	try {
			is = new FileInputStream(path);
			res= topTenWord(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return res;
    }
    
    /**
     * It reads the stream in chunks. 
     * It then process each chunk by calling a lineProcessing function to do computation of each lines to create a dictionary object of words and count. 
     * It calls a processWordCounting to  return the top ten word of from the dictionary. 
     *  @param an input stream of 
     * @return top 10 words
     */
    public String[]  topTenWord(InputStream input) throws IOException  {
       String[] res = new String [topN];  
       ChunkDataReader reader = new ChunkDataReader(input);  
        int read = 1; 
      //while there is data left to be read continue the loop, exit when we reach end of data stream.
        while (read > 0) { 
        //create a byte array to hold the data of the current chunk.
            byte[] buffer = new byte[BUFFER_SIZE];   
         // method call returns total number of character reads or -1 
            read = reader.readChunkData(buffer);    
            if (read > 0) {
            	ByteReader arrayReader = new ByteReader(buffer, 0, read);  
            	//call the lineProcessing function to process each lines and pass the instance of ByteReader.
            	lineProcessing(arrayReader);               
                
            }
        }
      //After processing is done on each line of data stream return the top 10 words from dictionary.
        res= processWordCounting();                 
        return res;
    }
    
    /**
     * This method reads each line of the buffer
     * In each line it calls the lowerCaseAndReplace method to convert to uppercase and replace any special character to ','
     * After that it calls addToDictionary method to split the line into words and add the words in the dictionary object   
     * @param dataReader data buffer
    */
    private static void lineProcessing (ByteReader dataReader) {
    	WordPreProcessing wp = new WordPreProcessing();
        CustomString line;
        while ((line = dataReader.readLine()) != null) {
            try {
                line = wp.toLowerAndReplace(line);
            	addToDictionary(line);
            	
            }
            catch (Exception e) {
                // Ignore bad lines
            }
        }
    	
    	
    }
  
   /**
    * This method splits the line into words and add into wordMap dictionary. 
    * @param line each line on the buffer
    * 
    */
    private static void addToDictionary(CustomString line) {
    	int start = line.start;
    	int temp=start;
    	int end = line.end;
      
    	while(start<end) {   
    		 //it checks if the current character is "," , if its not proceed 
    		if(line.data[start]!=',')                              
    		{
    			start++;
    			
    		}
    		else {
    			 //if the current character is "," we check if there is any preceding character ,
    			//if it is then we call our mutable string to return a word. 
    			if(start-temp>0) {                                
    				CustomString str = new CustomString(line.data, temp, start-temp);
    				
    	      		  if(str.length>0){
    	          	        int freq = 1;
    	          	        if (wordMap.containsKey(str.toString())) {
    	          	            freq = wordMap.get(str.toString()) + 1;    
    	          	        }
    	          	       wordMap.put(str.toString(), freq);            
    	          	    }
    				
    			}
    			
    			start++;                                                
    			temp=start;                                             
    		}
    	}
    	
    	// the following block is needed to add the last word of the line. 
    	
    	if(start-temp>0) {                                                 
			CustomString str = new CustomString(line.data, temp, start-temp);
		
      		  if(str.length>0){
          	        int freq = 1;
          	        if (wordMap.containsKey(str.toString())) {
          	            freq = wordMap.get(str.toString()) + 1;
          	        }
          	       wordMap.put(str.toString(), freq);
          	    }
			
		}
    	
    }
    
    /**
     * It calls quickselect algorithm to return the 10th greatest frequency value. 
     * It then loop through the dictionary object to find any word whose frequency count is greater than or equal to the 10th greatest frequency value.
     * add the result into the string array
     * @return  the array of top 10 word
     */
    
    
    private static String[] processWordCounting() {
 	   
	    // Find kth greatest frequency with quickselect algorithm. 
	    int[] frequencies = new int[wordMap.size()];
	    int i = 0;
	    
	    //adding the frequency values into array to pass to quickselect algorithm. 
	    for (final int value : wordMap.values()) {   
	        frequencies[i++] = value;                   
	    }
	    
	    int tenthLargestFreq=0;
	    QuickSelect quickSelect = new QuickSelect();
	    tenthLargestFreq = quickSelect.kthLargest(frequencies, 0, wordMap.size()-1, topN-1);  
        
	    // extract the top K
	    //first loop through the dictionary object to find any value greater than tenthLargestFreq and add it to result array. 
	    
	    String[] topK = new String[topN];
	    i = 0;
	    for ( Map.Entry<String, Integer> entry : wordMap.entrySet()) {
	        if (entry.getValue() > tenthLargestFreq) {
	        	topK[i++] = entry.getKey().toString();
	            
	        }
	    }
	    
	    //Second loop through the dictionary object again to fill up the remaining spot of top ten words. 
	    //This is done to avoid a scenario where tenthLargestFreq might be shared by many words and it might gets
	    //loaded into the result array before any high frequency word. 
	    
	    for ( java.util.Map.Entry<String, Integer> entry : wordMap.entrySet()) {
	        if (entry.getValue().intValue() == tenthLargestFreq) {
	        	  if (i == topN) {
  	                break;
  	            }
	        	
	            topK[i++] = entry.getKey().toString();
	          
	        }
	    }

	    return topK;
	 
	 
 }
    
    
  

}
