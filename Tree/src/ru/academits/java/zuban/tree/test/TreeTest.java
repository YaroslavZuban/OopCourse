package ru.academits.java.zuban.tree.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.academits.java.zuban.tree.Tree;

class TreeTest {
    @Test
    void insert() {
        Tree<Integer> tree = new Tree<>();

        tree.insert(5);
        Assertions.assertEquals(1, tree.size());

        tree.insert(2);
        Assertions.assertEquals(2, tree.size());

        tree.insert(7);
        Assertions.assertEquals(3, tree.size());

        tree.insert(1);
        Assertions.assertEquals(4, tree.size());

        tree.insert(3);
        Assertions.assertEquals(5, tree.size());

        tree.insert(6);
        Assertions.assertEquals(6, tree.size());

        tree.insert(8);
        Assertions.assertEquals(7, tree.size());

        tree.insert(null);
        Assertions.assertEquals(8, tree.size());
    }

    @Test
    void findNode() {
        Tree<Integer> tree = new Tree<>();

        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(8);

        Assertions.assertTrue(tree.contains(5));
        Assertions.assertTrue(tree.contains(2));
        Assertions.assertTrue(tree.contains(7));
        Assertions.assertTrue(tree.contains(1));
        Assertions.assertTrue(tree.contains(3));
        Assertions.assertTrue(tree.contains(8));

        Assertions.assertFalse(tree.contains(9));
        Assertions.assertFalse(tree.contains(10));
        Assertions.assertFalse(tree.contains(0));
    }

    @Test
    void remove() {
        Tree<Integer> tree = new Tree<>();

        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(8);
        tree.insert(null);

        Assertions.assertTrue(tree.remove(5));
        Assertions.assertFalse(tree.remove(13));

        Assertions.assertTrue(tree.remove(7));
        Assertions.assertFalse(tree.remove(17));

        Assertions.assertTrue(tree.remove(1));
        Assertions.assertTrue(tree.remove(null));

        Assertions.assertTrue(tree.remove(3));
        Assertions.assertFalse(tree.remove(3));

        Assertions.assertFalse(tree.remove(4));

        Assertions.assertFalse(tree.remove(9));
        Assertions.assertFalse(tree.remove(10));
        Assertions.assertFalse(tree.remove(0));
    }
}