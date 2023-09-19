package ru.academits.java.zuban.hash_table.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.academits.java.zuban.hash_table.HashTable;

import java.util.*;

class HashTableTest {
    @Test
    protected void contains() {
        HashTable<Integer> table = new HashTable<>();

        fillArray(table, 20);

        Assertions.assertTrue(table.contains(2));
        Assertions.assertTrue(table.contains(5));

        Assertions.assertFalse(table.contains(225));

        try {
            boolean isValue = table.contains(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void toArray() {
        HashTable<Integer> table = new HashTable<>();

        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});

        fillArray(table, 5);

        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 1, 2, 3, 4});

        fillArray(table, 5);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4});

        fillArray(table, 2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4});

        fillArray(table, 1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4});
    }

    @Test
    protected void testToArray() {
        HashTable<Integer> table = new HashTable<>();

        Assertions.assertArrayEquals(table.toArray(new Integer[0]), new Integer[]{});

        Integer[] array = new Integer[5];

        fillArray(table, 3);

        Assertions.assertArrayEquals(table.toArray(array), new Integer[]{0, 1, 2, null, null});

        fillArray(table, 3);
        Assertions.assertArrayEquals(table.toArray(array), new Integer[]{0, 0, 1, 1, 2, 2});

        try {
            table.toArray((Object[]) null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void add() {
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
    protected void remove() {
        HashTable<Integer> table = new HashTable<>();

        fillArray(table, 5);
        table.remove(0);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3, 4});

        table.remove(4);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 2, 3});

        table.remove(2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{1, 3});

        fillArray(table, 3);
        table.remove(1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 1, 2, 3});

        table.remove(20);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 1, 2, 3});

        try {
            table.remove(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void containsAll() {
        HashTable<Integer> table1 = new HashTable<>();
        List<Integer> list1 = new ArrayList<>();

        fillArray(list1, 5);
        fillArray(table1, 5);

        Assertions.assertTrue(table1.containsAll(list1));

        HashTable<Integer> table2 = new HashTable<>();
        List<Integer> list2 = new ArrayList<>();

        fillArray(list2, 40);
        fillArray(table2, 40);

        Assertions.assertTrue(table2.containsAll(list2));

        HashTable<Integer> table3 = new HashTable<>();
        List<Integer> list3 = new ArrayList<>();

        fillArray(list3, 0);
        fillArray(table3, 0);

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

        fillArray(list5, 5);
        fillArray(table5, 5);
        table5.add(20);

        Assertions.assertFalse(table5.containsAll(list5));
    }

    @Test
    protected void addAll() {
        List<Integer> list1 = new ArrayList<>();
        fillArray(list1, 5);

        HashTable<Integer> table = new HashTable<>();
        table.addAll(list1);
        Assertions.assertEquals(table.size(), list1.size());
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 1, 2, 3, 4});

        fillArray(table, 2);
        Assertions.assertEquals(table.size(), 7);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 0, 1, 1, 2, 3, 4});

        List<Integer> list2 = new ArrayList<>();
        fillArray(list2, 4);

        table.addAll(list2);
        Assertions.assertEquals(table.size(), 11);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4});

        try {
            table.addAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void removeAll() {
        List<Integer> list1 = new ArrayList<>();
        fillArray(list1, 5);

        HashTable<Integer> table = new HashTable<>();
        fillArray(table, 7);

        table.removeAll(list1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{5, 6});

        List<Integer> list2 = new ArrayList<>();
        table.removeAll(list2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{5, 6});

        List<Integer> list3 = new ArrayList<>();
        fillArray(list3, 10);

        table.removeAll(list3);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});

        try {
            table.removeAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void retainAll() {
        List<Integer> list1 = new ArrayList<>();
        fillArray(list1, 5);

        HashTable<Integer> table = new HashTable<>();
        fillArray(table, 7);

        table.retainAll(list1);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{0, 1, 2, 3, 4});

        List<Integer> list2 = new ArrayList<>();
        table.retainAll(list2);
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});

        List<Integer> list3 = new ArrayList<>();
        fillArray(list3, 10);
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
    protected void clear() {
        HashTable<Integer> table = new HashTable<>();
        fillArray(table, 7);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        Assertions.assertEquals(table.size(), 0);

        fillArray(table, 9);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        Assertions.assertEquals(table.size(), 0);

        fillArray(table, 100);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        Assertions.assertEquals(table.size(), 0);

        fillArray(table, 2);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        Assertions.assertEquals(table.size(), 0);

        fillArray(table, 0);

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Integer[]{});
        Assertions.assertEquals(table.size(), 0);
    }

    private static void fillArray(Collection<Integer> collection, int size) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                collection.add(i);
            }
        }
    }
}