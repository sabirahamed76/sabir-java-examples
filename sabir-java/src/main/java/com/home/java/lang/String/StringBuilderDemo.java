package com.home.java.lang.String;

public class StringBuilderDemo {
	public static void main(String[] args) {
        // Create a new StringBuilder with the
        // initial content "GeeksforGeeks"
        StringBuilder sb = new StringBuilder("GeeksforGeeks");
        System.out.println("Initial StringBuilder: " + sb);

        // Append a string to the StringBuilder
        sb.append(" is awesome!");
        System.out.println("After append: " + sb);
        
        StringBuilder sb1 = new StringBuilder("GeeksforGeeks");
        
        // Converting StringBuilder to String
        String str = sb1.toString();
        
        // Printing the String
        System.out.println(str);
        
        StringBuilder sb2 = new StringBuilder("GeeksforGeeks");
        System.out.println("Initial StringBuilder: " + sb2);

        // 1. Append a string to the StringBuilder
        sb2.append(" is awesome!");
        System.out.println("After append: " + sb2);

        // 2. Insert a substring at a specific position
        sb2.insert(13, " Java");
        System.out.println("After insert: " + sb2);

        // 3. Replace a substring with another string
        sb2.replace(0, 5, "Welcome to");
        System.out.println("After replace: " + sb2);

        // 4. Delete a substring from the StringBuilder
        sb2.delete(8, 14);
        System.out.println("After delete: " + sb2);

        // 5. Reverse the content of the StringBuilder
        sb2.reverse();
        System.out.println("After reverse: " + sb2);

        // 6. Get the current capacity of the StringBuilder
        int capacity = sb2.capacity();
        System.out.println("Current capacity: " + capacity);

        // 7. Get the length of the StringBuilder
        int length = sb2.length();
        System.out.println("Current length: " + length);

        // 8. Access a character at a specific index
        char charAt5 = sb2.charAt(5);
        System.out.println("Character at index 5: " + charAt5);

        // 9. Set a character at a specific index
        sb2.setCharAt(5, 'X');
        System.out.println("After setCharAt: " + sb2);

        // 10. Get a substring from the StringBuilder
        String substring = sb2.substring(5, 10);
        System.out.println("Substring (5 to 10): " + substring);

        // 11. Find the index of a specific substring
        sb2.reverse(); // Reversing back to original order for search
        int indexOfGeeks = sb2.indexOf("Geeks");
        System.out.println("Index of 'Geeks': " + indexOfGeeks);

        // 12. Delete a character at a specific index
        sb2.deleteCharAt(5);
        System.out.println("After deleteCharAt: " + sb2);

        // 13. Convert the StringBuilder to a String
        String result = sb2.toString();
        System.out.println("Final String: " + result);
        
        
        
    }
}
