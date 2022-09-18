package com.learn.review.datastructure.linkedlist;

public class LinkedListDemo {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.print();

        list.add(20);
        list.print();

        list.add(2, 30);
        list.print();

        list.remove(1);
        list.print();
    }
}
