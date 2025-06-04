package com.home.java.logic;

import java.util.Arrays;

public class ReverseArray {
    public static void main (String[] args){
        int[] my_array1 = {
                1789, 2035, 1899, 1456, 2013,
                1458, 2458, 1254, 1472, 2365,
                1456, 2165, 1457, 2456, 8989
        };

        ReverseArray ra = new ReverseArray();
        int[] reverse_array=  ra.reverseArray(my_array1);


        // Print the reversed array using Arrays.toString() method.
        System.out.println("Reverse array : " + Arrays.toString(my_array1));

    }

    public int[]  reverseArray(int[] my_array1){
        // Declare and initialize an integer array 'my_array1'.


        // Print the original array using Arrays.toString() method.
        System.out.println("Original array : " + Arrays.toString(my_array1));

        // Iterate through the first half of the array and reverse its elements.
        for (int i = 0; i < my_array1.length / 2; i++) {
            // Swap the elements at positions 'i' and 'length - i - 1'.
            int temp = my_array1[i];
            my_array1[i] = my_array1[my_array1.length - i - 1];
            my_array1[my_array1.length - i - 1] = temp;
        }
        return my_array1;
    }
}
