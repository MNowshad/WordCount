
package com.nowshad;

import com.nowshad.CustomString;

/**Reads a line of text. 
 * It reads up to line feed ('\n') or Carriage return ('\r') or  carriage return followed by a line feed.
 * @author: Nowshad
 * @version: 1.0
 * 
 * */
public class ByteReader {
	private byte[] buffer;
    private int offset = 0;
    private int end;
    
    public ByteReader(byte[] buffer, int offset, int length) {
        this.buffer = buffer;
        this.offset = offset;
        this.end = offset + length;
    }

    private static byte CR = '\r';
    private static byte LF = '\n';
    
    
    /**
      @return A  Mutable String containing the contents of the line 
     */
    public CustomString readLine() {
        if (offset >= end) {
            return null;
        }
        
        int startIndex = offset;
        int endIndex;
        boolean foundCR = false;
        for (endIndex = startIndex; endIndex < end; endIndex++) {
            byte c = buffer[endIndex];
            if (c == CR) {
                foundCR = true;
            }
            else if (c == LF) {
                offset = endIndex + 1;
                break;
            }
            else if (foundCR) {
                offset = endIndex;
                break;
            }
        }
        if (endIndex == end) {
            offset = end;
        }
        if (foundCR) {
            endIndex--;
        }
        
        if (endIndex == startIndex) {
            return readLine();
        }
        
        return new CustomString(buffer, startIndex, endIndex - startIndex);
    }
    
    /**
     * @return the underlying buffer
     */
    public byte[] getBuffer() {
        return buffer;
    }
    
}
