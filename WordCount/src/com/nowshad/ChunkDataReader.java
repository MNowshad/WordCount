
package com.nowshad;
import java.io.IOException;
import java.io.InputStream;

/**Read the input in chunks and partition at the end of line.  
 * 
 * @author: Nowshad
 * @version 1.0
 * 
 * */
public class ChunkDataReader {
	private InputStream input;
	private byte[] chunkBuffer;
    private int chunkBufferSize;
    
    
    public ChunkDataReader(InputStream input) {
        this.input = input;
    }
  
    /**
     * @param buffer:  The output buffer 
     * @return Total number of characters read OR -1 if the end of the data stream 
     * 
     */
    public int readChunkData(byte[] buffer) throws IOException {
        int bufferIndex = 0;
        
        if (chunkBuffer != null && chunkBufferSize > 0) {
            System.arraycopy(chunkBuffer, 0, buffer, 0, chunkBufferSize); //copying the data into byte array buffer
            bufferIndex = chunkBufferSize;
        }
        chunkBufferSize = 0;
        
        int read = readUntilFull(buffer, bufferIndex);
        
        if (read < 0) {
            if (bufferIndex > 0) {
                return bufferIndex;
            }
            else {
                return -1;
            }
        }
        
        bufferIndex += read;
        
        int newLineIndex = 0;
        int newLineChars = 0;
        for (newLineIndex = bufferIndex - 1; newLineIndex >= 0; newLineIndex--) {
            if (buffer[newLineIndex] == '\r') {
                newLineChars += 1;
            }
            else if (buffer[newLineIndex] == '\n') {
                newLineChars += 1;
            }
            else if (newLineChars > 0) {
                newLineIndex += 1;
                break;
            }
        }
        
        if (newLineIndex < 0) {
            System.out.println("Newline not found!! Chunk size " + buffer.length + " not large enough.");
            return bufferIndex;
        }
        
        chunkBufferSize = bufferIndex - newLineIndex - newLineChars;
        
        if (chunkBuffer == null || chunkBuffer.length < chunkBufferSize) {
            chunkBuffer = new byte[chunkBufferSize];
        }
        
        System.arraycopy(buffer, newLineIndex + newLineChars, chunkBuffer, 0, chunkBufferSize);
        
        return newLineIndex;
    }
    
    private int readUntilFull(byte[] buffer, int startIndex) throws IOException {
        int read = 0;
        while (startIndex < buffer.length) {
            int numRead = input.read(buffer, startIndex, buffer.length - startIndex);
            if (numRead < 0) {
                if (read == 0) {
                    return -1;
                }
                break;
            }
            read += numRead;
            startIndex += numRead;
        }
        return read;
    }
    
    public void close() throws IOException {
        input.close();
    }

}
