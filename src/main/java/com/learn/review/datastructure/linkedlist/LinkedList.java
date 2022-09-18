package com.learn.review.datastructure.linkedlist;

public class LinkedList<E> {

    private Node<E> head;

    public LinkedList() {
        Node head = new Node(null, null);
        this.head = head;
    }

    public void addLast(E e) {
        add(e);
    }

    public void add(E e) {
        Node node = new Node(e, null);
        Node next = head.next;
        if (next == null) {
            head.next = node;
        } else {
            while (next.next != null) {
                next = next.next;
            }
            next.next = node;
        }
    }

    public void add(int index, E e) {
        Node node = new Node(e, null);
        Node next = head.next;
        if (next == null) {
            head.next = node;
            return;
        }
        int i = 0;
        while (i < index) {
            if (next.next != null) {
                next = next.next;
            } else {
                break;
            }
            i++;
        }
        next.next = node;
    }

    public void remove(int index) {
        int i = 0;
        Node prev = head;
        Node current = head.next;
        while (current != null) {
            if (i == index) {
                Node tmp = current.next;
                prev.next = tmp;
                break;
            }
            // 把当前节点当成上个节点
            prev = current;
            // 把当前节点的下个节点当成当前节点，即遍历到下一个节点了
            current = current.next;
            i++;
        }
    }

    public void print() {
        Node<E> tmp = this.head;
        tmp.print();
        while (tmp.next != null) {
            tmp = tmp.next;
            tmp.print();
        }
        System.out.println("");
    }

    private class Node<E> {
        private E data;
        private Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void print() {
            System.out.print("[" + this.data + "]→");
        }
    }


}
