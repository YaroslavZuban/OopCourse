package ru.academits.java.zuban.singly_linked_list_main;

import ru.academits.java.zuban.singly_linked_list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        list.addFirst(7);

        System.out.println("setFirst  result: [7, 6, 5, 4, 3, 2] happened: " + list);

        list.insert(1, 1);
        System.out.println("insert result: [7, 1, 6, 5, 4, 3, 2] happened: " + list);

        list.deleteByIndex(0);
        System.out.println("deleteIndex result: [1, 6, 5, 4, 3, 2] happened: " + list);

        list.deleteByIndex(3);
        System.out.println("deleteIndex result: [1, 6, 4, 3, 2] happened: " + list);

        list.deleteByValue(1);
        System.out.println("deleteValue result: [6, 4, 3, 2] happened: " + list);

        list.deleteByValue(3);
        System.out.println("deleteValue result: [6, 4, 2] happened: " + list);

        list.addFirst(null);
        System.out.println("setFirst  result: [null, 6, 4, 2] happened: " + list);

        list.deleteByValue(null);
        System.out.println("deleteValue result: [6, 4, 2] happened: " + list);

        list.deleteByValue(10);
        System.out.println("deleteValue result: [6, 4, 2] happened: " + list);

        list.reverse();
        System.out.println("revers result: [2, 4, 6] happened: " + list);

        list.reverse();
        System.out.println("revers result: [6, 4, 2] happened: " + list);

        System.out.println("copy list result: [6, 4, 2] happened: " + list.copy());

        list.setNode(1, 3);
        System.out.println("set list result: [6, 3, 2] happened: " + list);

        list.setNode(0, 9);
        System.out.println("set list result: [9, 3, 2] happened: " + list);

        list.setNode(list.getSize() - 1, 20);
        System.out.println("set list result: [9, 3, 20] happened: " + list);

        System.out.println("get(0)=9 list result: [9, 3, 20] happened: " + list.get(0));
        System.out.println("get(1)=3 list result: [9, 3, 20] happened: " + list.get(1));
        System.out.println("get(2)=20 list result: [9, 3, 20] happened: " + list.get(2));
    }
}