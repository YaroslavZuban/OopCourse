package ru.academits.java.zuban.hash_table.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.academits.java.zuban.hash_table.HashTable;

import java.util.*;

class HashTableTest {
    @Test
    protected void contains() {
        Object[] myArray;

        HashTable<Object> table = new HashTable<>();

        myArray = new Object[20];
        fillArray(myArray, 20);
        table.addAll(List.of(myArray));

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
        Object[] myArray;
        HashTable<Object> table = new HashTable<>();

        Assertions.assertArrayEquals(table.toArray(), new Object[]{});

        myArray = new Object[5];
        fillArray(myArray, 5);
        table.addAll(List.of(myArray));

        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 1, 2, 3, 4});

        myArray = new Object[5];
        fillArray(myArray, 5);
        table.addAll(List.of(myArray));

        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4});

        myArray = new Object[2];
        fillArray(myArray, 2);
        table.addAll(List.of(myArray));

        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4});

        myArray = new Object[1];
        fillArray(myArray, 1);
        table.addAll(List.of(myArray));

        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4});
    }

    @Test
    protected void testToArray() {
        Object[] myArray;

        HashTable<Object> table = new HashTable<>();

        Assertions.assertArrayEquals(table.toArray(new Object[0]), new Object[]{});

        Object[] array = new Object[5];

        myArray = new Object[3];
        fillArray(myArray, 3);
        table.addAll(List.of(myArray));

        Assertions.assertArrayEquals(table.toArray(array), new Object[]{0, 1, 2, null, null});

        myArray = new Object[3];
        fillArray(myArray, 3);
        table.addAll(List.of(myArray));

        Assertions.assertArrayEquals(table.toArray(array), new Object[]{0, 0, 1, 1, 2, 2});

        try {
            table.toArray((Object[]) null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void add() {
        HashTable<Object> table = new HashTable<>();

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
        Object[] myArray;

        HashTable<Object> table = new HashTable<>();
        myArray = new Object[5];
        fillArray(myArray, 5);
        table.addAll(List.of(myArray));

        table.remove(0);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{1, 2, 3, 4});

        table.remove(4);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{1, 2, 3});

        table.remove(2);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{1, 3});

        myArray = new Object[3];
        fillArray(myArray, 3);
        table.addAll(List.of(myArray));

        table.remove(1);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 1, 2, 3});

        table.remove(20);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 1, 2, 3});

        try {
            table.remove(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void containsAll() {
        Object[] myArray;

        HashTable<Object> table1 = new HashTable<>();
        myArray = new Object[5];
        fillArray(myArray, 5);
        table1.addAll(List.of(myArray));

        myArray = new Object[5];
        fillArray(myArray, 5);
        List<Object> list1 = new ArrayList<>(List.of(myArray));

        Assertions.assertTrue(table1.containsAll(list1));

        HashTable<Object> table2 = new HashTable<>();
        myArray = new Object[20];
        fillArray(myArray, 20);
        table2.addAll(List.of(myArray));

        myArray = new Object[20];
        fillArray(myArray, 20);
        List<Object> list2 = new ArrayList<>(List.of(myArray));

        Assertions.assertTrue(table2.containsAll(list2));

        HashTable<Object> table3 = new HashTable<>();
        myArray = new Object[0];
        fillArray(myArray, 0);
        table3.addAll(List.of(myArray));

        myArray = new Object[0];
        fillArray(myArray, 0);
        List<Object> list3 = new ArrayList<>(List.of(myArray));

        Assertions.assertTrue(table3.containsAll(list3));

        HashTable<Object> table4 = new HashTable<>();
        List<Object> list4 = null;

        try {
            Assertions.assertTrue(table4.containsAll(list4));
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }

        HashTable<Object> table5 = new HashTable<>();

        myArray = new Object[5];
        fillArray(myArray, 5);
        table5.addAll(List.of(myArray));

        myArray = new Object[5];
        fillArray(myArray, 5);
        List<Object> list5 = new ArrayList<>(List.of(myArray));

        table5.add(20);

        Assertions.assertTrue(table5.containsAll(list5));
    }

    @Test
    protected void addAll() {
        Object[] myArray;

        myArray = new Object[5];
        fillArray(myArray, 5);
        List<Object> list1 = new ArrayList<>(List.of(myArray));

        HashTable<Object> table = new HashTable<>();
        table.addAll(list1);
        Assertions.assertEquals(table.size(), list1.size());
        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 1, 2, 3, 4});

        myArray = new Object[2];
        fillArray(myArray, 2);
        table.addAll(List.of(myArray));

        Assertions.assertEquals(table.size(), 7);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 0, 1, 1, 2, 3, 4});

        myArray = new Object[4];
        fillArray(myArray, 4);
        List<Object> list2 = new ArrayList<>(List.of(myArray));

        table.addAll(list2);
        Assertions.assertEquals(table.size(), 11);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4});

        try {
            table.addAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void removeAll() {
        Object[] myArray;

        myArray = new Object[5];
        fillArray(myArray, 5);
        List<Object> list1 = new ArrayList<>(List.of(myArray));

        HashTable<Object> table = new HashTable<>();
        myArray = new Object[7];
        fillArray(myArray, 7);
        table.addAll(List.of(myArray));

        table.removeAll(list1);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{5, 6});

        List<Object> list2 = new ArrayList<>();
        table.removeAll(list2);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{5, 6});

        myArray = new Object[10];
        fillArray(myArray, 10);
        List<Object> list3 = new ArrayList<>(List.of(myArray));

        table.removeAll(list3);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});

        try {
            table.removeAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void retainAll() {
        Object[] myArray = new Object[5];
        fillArray(myArray, 5);
        List<Object> list1 = new ArrayList<>(List.of(myArray));

        HashTable<Object> table = new HashTable<>();
        myArray = new Object[7];
        fillArray(myArray, 7);
        table.addAll(List.of(myArray));

        table.retainAll(list1);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{0, 1, 2, 3, 4});

        List<Object> list2 = new ArrayList<>();
        table.retainAll(list2);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});

        myArray = new Object[10];
        fillArray(myArray, 10);
        List<Object> list3 = new ArrayList<>(List.of(myArray));

        table.add(12);

        table.retainAll(list3);
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});

        try {
            table.retainAll(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    protected void clear() {
        HashTable<Object> table = new HashTable<>();

        Object[] myArray = new Object[7];
        fillArray(myArray, 7);
        table.addAll(List.of(myArray));

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});
        Assertions.assertEquals(table.size(), 0);

        myArray = new Object[9];
        fillArray(myArray, 9);
        table.addAll(List.of(myArray));

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});
        Assertions.assertEquals(table.size(), 0);

        myArray = new Object[100];
        fillArray(myArray, 100);
        table.addAll(List.of(myArray));

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});
        Assertions.assertEquals(table.size(), 0);

        myArray = new Object[2];
        fillArray(myArray, 2);
        table.addAll(List.of(myArray));

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});
        Assertions.assertEquals(table.size(), 0);

        myArray = new Object[0];
        fillArray(myArray, 0);
        table.addAll(List.of(myArray));

        table.clear();
        Assertions.assertArrayEquals(table.toArray(), new Object[]{});
        Assertions.assertEquals(table.size(), 0);
    }

    private static void fillArray(Object[] array, int size) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                array[i] = i;
            }
        }
    }
}