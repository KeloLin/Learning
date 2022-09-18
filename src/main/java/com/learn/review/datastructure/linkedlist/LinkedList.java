package com.learn.review.datastructure.linkedlist;

public class LinkedList<E> {

    private Node<E> head;

    public LinkedList() {
        Node<E> head = new Node<>(null, null);
        this.head = head;
    }

    public void addLast(E e) {
        add(e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void add(E e) {
        Node<E> node = new Node(e, null);
        Node<E> next = head.next;
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
        Node<E> node = new Node(e, null);
        Node<E> next = head.next;
        if (next == null) {
            head.next = node;
            return;
        }
        int i = 0;
        while (i <= index) {
            if (next.next != null) {
                next = next.next;
            } else {
                break;
            }
            i++;
        }
        next.next = node;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        Node<E> prev = this.head;
        Node<E> current = this.head.next;
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        Node<E> next = prev.next;
        prev.next = null;
        return next.data;
    }

    public E remove(int index) {
        int i = 0;
        Node<E> prev = head;
        Node<E> current = head.next;
        while (current != null) {
            if (i == index) {
                Node<E> tmp = current.next;
                prev.next = tmp;
                return tmp.data;
            }
            // 把当前节点当成上个节点
            prev = current;
            // 把当前节点的下个节点当成当前节点，即遍历到下一个节点了
            current = current.next;
            i++;
        }
        return null;
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
