package com.learn.review.datastructure.stack;

import com.learn.review.datastructure.linkedlist.LinkedList;

public class LinkedListStack<E> {

    private LinkedList<E> stack;

    public LinkedListStack() {
        this.stack = new LinkedList<>();
    }

    public void push(E e) {
        stack.addFirst(e);
    }

    public E pop() {
        return stack.removeFirst();
    }

    public void print() {
        stack.print();
    }
}
