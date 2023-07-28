package ru.academits.java.zuban.singlyLinkedList_main;

import ru.academits.java.zuban.singlyLinkedList.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

        singlyLinkedList.setFirst(2);

        singlyLinkedList.setFirst(3);

        singlyLinkedList.setFirst(4);

        singlyLinkedList.setFirst(5);

        singlyLinkedList.setFirst(6);

        singlyLinkedList.setFirst(7);

        System.out.println("setFirst  result: [7, 6, 5, 4, 3, 2] happened: " + singlyLinkedList.toString());

        singlyLinkedList.insert(1, 1);

        System.out.println("insert result: [7, 1, 6, 5, 4, 3, 2] happened: " + singlyLinkedList);

        singlyLinkedList.deleteIndex(0);
        System.out.println("deleteIndex result: [1, 6, 5, 4, 3, 2] happened: " + singlyLinkedList);

        singlyLinkedList.deleteIndex(3);
        System.out.println("deleteIndex result: [1, 6, 5, 3, 2] happened: " + singlyLinkedList);

        singlyLinkedList.deleteValue(1);
        System.out.println("deleteValue result: [6, 5, 3, 2] happened: " + singlyLinkedList);

        singlyLinkedList.deleteValue(3);
        System.out.println("deleteValue result: [6, 5, 2] happened: " + singlyLinkedList);

        singlyLinkedList.deleteValue(10);
        System.out.println("deleteValue result: [6, 5, 2] happened: " + singlyLinkedList);

        singlyLinkedList.reverse();
        System.out.println("revers result: [2, 5, 6] happened: " + singlyLinkedList);

        singlyLinkedList.reverse();
        System.out.println("revers result: [6, 5, 2] happened: " + singlyLinkedList);

        System.out.println("copy list result: [6, 5, 2] happened: " + singlyLinkedList.copy());
    }
}