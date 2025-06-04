package com.home.java.util;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;




class UtilDemoTest {

    @Test
    void testDisplayList() {
        List<String> list = Arrays.asList("Jason", "Emily", "Lisa", "Jamie", "Pierre", "Stanley", "Gloria", "Ben", "Ken", "Lela");
        UtilDemo.displayList(list);
        assertEquals(10, list.size());
        assertTrue(list.contains("Jason"));
    }

    @Test
    void testDisplayLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("is");
        list.add("is");
        list.add("a");
        list.add("a");
        list.add(null);
        list.addLast("test");
        list.addFirst("This");
        UtilDemo.displayLinkedList(list);
        assertEquals(7, list.size());
        assertEquals("This", list.getFirst());
        assertEquals("test", list.getLast());
    }

    @Test
    void testDisplaySet() {
        HashSet<String> set = new HashSet<>();
        set.add("This");
        set.add("is");
        set.add("is");
        set.add("a");
        set.add("a");
        set.add(null);
        set.add("test");
        UtilDemo.displaySet(set);
        assertEquals(5, set.size()); // Duplicates and null are handled
        assertTrue(set.contains("This"));
    }

    @Test
    void testDisplayMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        UtilDemo.displayMap(map);
        assertEquals(6, map.size());
        assertEquals("1", map.get("one"));
        assertTrue(map.containsKey("three"));
    }
}