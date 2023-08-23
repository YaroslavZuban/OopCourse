package ru.academits.java.zuban.hash_table;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    void contains() {
        HashTable<Integer> table = new HashTable<>();

        fillingTable(table, 20);

        assertTrue(table.contains(2));
        assertTrue(table.contains(5));
        assertFalse(table.contains(225));

        try {
            boolean isValue = table.contains(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void toArray() {
        HashTable<Integer> table = new HashTable<>();

        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});

        fillingTable(table, 5);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3, 4, 0,});

        fillingTable(table, 5);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 0, 0});

        fillingTable(table, 2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 1, 1, 2, 2, 3, 3, 4, 4, 0, 0, 0,});

        fillingTable(table, 1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 1, 1, 2, 2, 3, 3, 4, 4, 0, 0, 0, 0,});
    }

    @Test
    void testToArray() {
        HashTable<Integer> table = new HashTable<>();

        Assertions.assertArrayEquals(table.toArray(new Integer[0]), new Integer[]{});

        Integer[] array = new Integer[5];
        fillingTable(table, 3);
        Assertions.assertArrayEquals(table.toArray(array), new Integer[]{1, 2, 0, null, null});

        fillingTable(table, 3);
        Assertions.assertArrayEquals(table.toArray(array), new Integer[]{1, 1, 2, 2, 0, 0});

        try {
            table.toArray((Object[]) null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void add() {
        HashTable<Integer> table = new HashTable<>();

        Assertions.assertEquals(table.size(), 0);

        for (int i = 0; i < 20; i++) {
            table.add(i);
        }

        Assertions.assertEquals(table.size(), 20);

        table.add(6);
        table.add(6);
        table.add(6);
        table.add(6);

        Assertions.assertEquals(table.size(), 24);

        try {
            table.add(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void remove() {
        HashTable<Integer> table = new HashTable<>();

        fillingTable(table, 5);
        table.remove(0);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3, 4});

        table.remove(4);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3});

        table.remove(2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 3});

        fillingTable(table, 3);
        table.remove(1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3, 0});

        table.remove(20);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3, 0});

        try {
            table.remove(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void containsAll() {
        HashTable<Integer> table1 = new HashTable<>();
        List<Integer> list1 = new ArrayList<>();

        fillingTable(list1, 5);
        fillingTable(table1, 5);

        Assertions.assertTrue(table1.containsAll(list1));

        HashTable<Integer> table2 = new HashTable<>();
        List<Integer> list2 = new ArrayList<>();

        fillingTable(list2, 40);
        fillingTable(table2, 40);

        Assertions.assertTrue(table2.containsAll(list2));


        HashTable<Integer> table3 = new HashTable<>();
        List<Integer> list3 = new ArrayList<>();

        fillingTable(list3, 0);
        fillingTable(table3, 0);

        Assertions.assertTrue(table3.containsAll(list3));

        HashTable<Integer> table4 = new HashTable<>();
        List<Integer> list4 = null;

        try {
            Assertions.assertTrue(table4.containsAll(list4));
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }

        HashTable<Integer> table5 = new HashTable<>();
        List<Integer> list5 = new ArrayList<>();

        fillingTable(list5, 5);
        fillingTable(table5, 5);
        table5.add(20);

        Assertions.assertFalse(table5.containsAll(list5));
    }

    @Test
    void addAll() {
        List<Integer> list1 = new ArrayList<>();
        fillingTable(list1, 5);

        HashTable<Integer> table = new HashTable<>();
        table.addAll(list1);
        Assertions.assertEquals(table.size(), list1.size());
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3, 4, 0});

        fillingTable(table, 2);
        Assertions.assertEquals(table.size(), 7);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 1, 2, 3, 4, 0, 0});

        List<Integer> list2 = new ArrayList<>();
        fillingTable(list2, 4);

        table.addAll(list2);
        Assertions.assertEquals(table.size(), 11);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 1, 1, 2, 2, 3, 3, 4, 0, 0, 0,});

        try {
            table.addAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void removeAll() {
        List<Integer> list1 = new ArrayList<>();
        fillingTable(list1, 5);

        HashTable<Integer> table = new HashTable<>();
        fillingTable(table, 7);

        table.removeAll(list1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{5, 6});

        List<Integer> list2 = new ArrayList<>();
        table.removeAll(list2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{5, 6});

        List<Integer> list3 = new ArrayList<>();
        fillingTable(list3, 10);

        table.removeAll(list3);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});

        try {
            table.removeAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void retainAll() {
        List<Integer> list1 = new ArrayList<>();
        fillingTable(list1, 5);

        HashTable<Integer> table = new HashTable<>();
        fillingTable(table, 7);

        table.retainAll(list1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3, 4, 0});

        List<Integer> list2 = new ArrayList<>();
        table.retainAll(list2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});

        List<Integer> list3 = new ArrayList<>();
        fillingTable(list3, 10);
        table.add(12);

        table.retainAll(list3);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});

        try {
            table.retainAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void clear() {
        HashTable<Integer> table = new HashTable<>();
        fillingTable(table, 7);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        assertEquals(table.size(), 0);

        fillingTable(table, 9);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        assertEquals(table.size(), 0);

        fillingTable(table, 100);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        assertEquals(table.size(), 0);

        fillingTable(table, 2);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        assertEquals(table.size(), 0);

        fillingTable(table, 0);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        assertEquals(table.size(), 0);
    }

    private static void fillingTable(Collection<Integer> collection, int size) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                collection.add(i);
            }
        }
    }
}