package ru.academits.java.zuban.main;

import ru.academits.java.zuban.list.List;
import ru.academits.java.zuban.list.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();

        Node<Integer> node1 = new Node<>(2);
        list.insertStart(node1);

        Node<Integer> node2 = new Node<>(3);
        list.insertStart(node2);

        Node<Integer> node3 = new Node<>(4);
        list.insertStart(node3);

        Node<Integer> node4 = new Node<>(5);
        list.insertStart(node4);

        Node<Integer> node5 = new Node<>(6);
        list.insertStart(node5);

        Node<Integer> node6 = new Node<>(7);
        list.insertStart(node6);

        System.out.println("insert start result: {{7},{6},{5},{4},{3},{2}} happened: " + list);

        Node<Integer> node10 = new Node<>(1);
        list.add(1,node10);

        System.out.println("add result: {{7},{1},{6},{5},{4},{3},{2}} happened: " + list);

        list.deleteElement(0);
        System.out.println("deleteElement result: {{1},{6},{5},{4},{3},{2}} happened: " + list);

        list.deleteElement(3);
        System.out.println("deleteElement result: {{1},{6},{5},{3},{2}} happened: " + list);

        list.deleteValue(node10);
        System.out.println("deleteValue result: {{6},{5},{3},{2}} happened: " + list);

        list.deleteValue(node2);
        System.out.println("deleteValue result: {{6},{5},{2}} happened: " + list);

        list.deleteValue(new Node<>(10));
        System.out.println("deleteValue result: {{6},{5},{2}} happened: " + list);

        list.revers();
        System.out.println("revers result: {{2},{5},{6}} happened: " + list);

        list.revers();
        System.out.println("revers result: {{6},{5},{2}} happened: " + list);

        List<Integer> copyList=list.copyList();
        System.out.println("copyList result: {{6},{5},{2}} happened: " + copyList);

    }
}
