package com.home.java.logic;
public class Sort {
	
	public static void main (String[] args){
		 int[] A= {7, 21, 3, 42, 3, 7};
	      
	        
	        //Sorting
	        for (int i=0; i<A.length-1; i++){
	        	for (int k=i+1; k<A.length; k++){
	        		if (A[i]>A[k]){
	        			int t = A[i];
	        			A[i]=A[k];
	        			A[k]=t;
	        		}
	        	}
	        }
	        
	        for (int i=0; i<A.length; i++){
	        	System.out.println(A[i]);
	        	}
	        }

}
