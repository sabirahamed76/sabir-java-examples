package com.home.java.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


public class CollectionsDemoTest {

    @Test
    void testMinAndMaxValues() {
        List<String> fruitList = new ArrayList<>();
        Collections.addAll(fruitList, "Orange", "Apple", "Banana", "Mango");

        String minimum = Collections.min(fruitList);
        String maximum = Collections.max(fruitList);

        assertEquals("Apple", minimum);
        assertEquals("Orange", maximum);
    }

    @Test
    void testReverseSorting() {
        List<String> fruitList = new ArrayList<>();
        Collections.addAll(fruitList, "Orange", "Apple", "Banana", "Mango");

        Collections.sort(fruitList, Collections.reverseOrder());

        assertEquals("Orange", fruitList.get(0));
        assertEquals("Apple", fruitList.get(fruitList.size() - 1));
    }

    @Test
    void testBinarySearch() {
        List<String> fruitList = new ArrayList<>();
        Collections.addAll(fruitList, "Orange", "Apple", "Banana", "Mango");

        Collections.sort(fruitList);

        int indexOfOrange = Collections.binarySearch(fruitList, "Orange");
        int indexOfApple = Collections.binarySearch(fruitList, "Apple");

        assertEquals(3, indexOfOrange);
        assertEquals(0, indexOfApple);
    }

    @Test
    void testDisjointCollections() {
        List<String> fruitList = new ArrayList<>();
        Collections.addAll(fruitList, "Orange", "Apple", "Banana", "Mango");

        List<String> vegList = new ArrayList<>();
        Collections.addAll(vegList, "Carrot", "Onion", "Tomato", "Dates");

        boolean isDisjoint = Collections.disjoint(fruitList, vegList);
        assertTrue(isDisjoint);

        vegList.set(3, "Mango");
        isDisjoint = Collections.disjoint(fruitList, vegList);
        assertFalse(isDisjoint);
    }

    @Test
    void testCopyCollections() {
        List<String> fruitList = new ArrayList<>();
        Collections.addAll(fruitList, "Orange", "Apple", "Banana", "Mango");

        List<String> fruitListCopy = new ArrayList<>(fruitList);
        Collections.copy(fruitListCopy, fruitList);

        assertEquals(fruitList, fruitListCopy);
    }

    @Test
    void testUnmodifiableList() {
        List<String> fruitList = new ArrayList<>();
        Collections.addAll(fruitList, "Orange", "Apple", "Banana", "Mango");

        List<String> unmodifiableList = Collections.unmodifiableList(fruitList);

        assertThrows(UnsupportedOperationException.class, () -> unmodifiableList.add("Grapes"));
    }
}