package com.learn.review.datastructure.array;

public class Array<E> {

    private E[] data;
    private int size;

    public Array(int maxSize) {
        this.data = (E[]) new Object[maxSize];
        this.size = 0;
    }

    public void add(E e) {
        // 数组是否已经满了，数组的长度小于现有元素数量+1
        if (this.data.length < this.size + 1) {
            // 扩容
            this.resize();
        }

        this.data[size] = e;
        size++;
    }

    public void add(int index, E e) {
        // 数组是否已经满了
        if (this.data.length < this.size + 1) {
            // 扩容
            this.resize();
        }
        // 位置是否有元素
        if (this.data[index] != null) {
            // 有的话后面的元素全部后移一位
            for (int i = this.size; i > index; i--) {
                this.data[i] = this.data[i - 1];
            }
        }
        this.data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(e);
    }

    public E getIndex(int index) {
        E e = null;
        if (index >= 0 && index < this.data.length) {
            e = this.data[index];
        }
        return e;
    }

    public E remove(int index) {
        E e = null;
        if (index >= 0 && index < this.data.length) {
            e = this.data[index];
            if (e != null) {
                for (int i = index; i < size; i++) {
                    this.data[i] = this.data[i + 1];
                }
                size--;
            }
        }
        return e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    private void resize() {
        E[] tmp = (E[]) new Object[this.data.length * 2];
        for (int i = 0; i < this.data.length; i++) {
            tmp[i] = data[i];
        }
        this.data = tmp;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.data[i]);
            if (i != this.size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
