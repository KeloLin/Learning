package com.learn.review.datastructure.array;

public class ArrayDemo {
    public static void main(String[] args) {
        Array<Integer> list = new Array<>(16);
        list.add(10);
        list.print();

        list.add(20);
        list.print();

        list.add(1, 30);
        list.print();

        list.remove(2);
        list.print();
    }
}
