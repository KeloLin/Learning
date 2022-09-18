package com.learn.review.datastructure.stack;

import java.util.LinkedList;

public class StackDemo {

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
//        ArrayStack<Integer> stack = new ArrayStack<>(10);
        stack.push(10);
        stack.print();

        stack.push(20);
        stack.print();

        stack.push(30);
        stack.print();

        stack.pop();
        stack.print();

        stack.pop();
        stack.print();

    }
}
