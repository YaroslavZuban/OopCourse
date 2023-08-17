package ru.academits.java.zuban.tree;

public class Tree<T> {
    private Node<T> peak;
    private int size;

    public void add(T value) {
        if(peak==null){
            peak=new Node<>(value);
        }else{

        }
    }
}
