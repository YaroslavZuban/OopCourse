package ru.academits.java.zuban.tree_main;

import ru.academits.java.zuban.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(8);

        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        System.out.println("------------ traverseInDepth -----------");
        tree.traverseInDepth(consumer);

        System.out.println();
        System.out.println("------------ traverseInDepthRecursive -----------");
        tree.traverseInDepthRecursive(consumer);

        System.out.println();
        System.out.println("------------ traverseInWidth -----------");
        tree.traverseInWidth(consumer);

        System.out.println();
    }
}