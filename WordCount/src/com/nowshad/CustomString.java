
package com.nowshad;
import java.nio.charset.Charset;



/**This class is a Mutable string to improve the performance over Java default String object. 
 * 
 * @author: Nowshad
 * @version 1.0
 * 
 * */
public class CustomString {
	private static final Charset US_ASCII = Charset.forName("US-ASCII");
    public byte[] data;
    public int start;
    public int length;
    public int end;
  
    /**
     * 
     * @param string
     */
    public CustomString(String string) {
        char[] array = string.toCharArray();
        byte[] buffer = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            buffer[i] = (byte) array[i];
        }
        useAsNewString(buffer, 0, string.length());
    }
    
    /**
     * Create a MutableString from a backing array.
     * 
     * @param data
     * @param start
     * @param length
     */
    public CustomString(byte[] data, int offset, int length) {
        useAsNewString(data, offset, length);
    }
    
    public CustomString useAsNewString(byte[] data, int offset, int length) {
        this.data = data;
        this.start = offset;
        this.length = length;
        this.end = start + length;
        
        return this;
    }
 
   @Override
    public String toString() {
        return new String(data, start, length, US_ASCII);
    }
   
   

}
