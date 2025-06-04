package com.home.java.utils.object;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ByteArray {

	public static void copySmallArraysToBigArray(final byte[][] smallArrays,final byte[] bigArray){
	    int currentOffset = 0;
	    for(final byte[] currentArray : smallArrays){
	        System.arraycopy(
	            currentArray, 0,
	            bigArray, currentOffset,
	            currentArray.length
	        );
	        currentOffset += currentArray.length;
	    }
	}
	
	public byte[] concatenateByteArrays(List<byte[]> blocks) {
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    for (byte[] b : blocks) {
	        os.write(b, 0, b.length);
	    }
	    return os.toByteArray();
	}
	
	public static void main(final String[] args){
	    final byte[][] smallArrays =
	        {
	           "The"    .getBytes(),
	           " Quick" .getBytes(),
	           " Brown" .getBytes(),
	           " Fox"   .getBytes()
	        };
	    
	    final byte[][] smallArrays1 =
        {
           "The"    .getBytes(),
           " Small" .getBytes(),
           " Mauve" .getBytes(),
           " Cat"   .getBytes()
        };
	    
	    
	    byte[] a = {1,2,3,4,5}; 
	    
	    byte[] b = {6,7,8,9}; 
	    
	    byte[] result = concat(a, b);
	    
	    
	    
	    //final byte[] smArray = "The Small Mauve Cat".getBytes();
	    //copySmallArraysToBigArray(smallArrays, bigArray);
	    //final byte[] bigArray =concatenateByteArrays(smArray);
	    for (int i=0;i<result.length;i++){
	    	System.out.println(result[i]);
	    }
	}
	
	public static byte[] concat(byte[]...arrays)
	{
	    // Determine the length of the result array
	    int totalLength = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        totalLength += arrays[i].length;
	    }

	    // create the result array
	    byte[] result = new byte[totalLength];

	    // copy the source arrays into the result array
	    int currentIndex = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        System.arraycopy(arrays[i], 0, result, currentIndex, arrays[i].length);
	        currentIndex += arrays[i].length;
	    }

	    return result;
	}
	
	
}
