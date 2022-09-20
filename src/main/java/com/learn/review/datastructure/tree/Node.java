package com.learn.review.datastructure.tree;

/**
 * @Author: Kelo
 * @Date: 2022/9/20
 */
public class Node {
    protected Integer data;
    protected Node left;
    protected Node right;

    public Node(Integer data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void add(Integer e) {
        // 根节点是空的，把插入的元素当成根节点
        if (e.compareTo(this.data) > 0) {
            if (this.right != null) {
                // 插入的值比根节点大
                this.right.add(e);
            } else {
                this.right = new Node(e, null, null);
            }
        } else {
            if (this.left != null) {
                // 插入的值比根节点大
                this.left.add(e);
            } else {
                this.left = new Node(e, null, null);
            }
        }
    }

    public Node search(int value) {
        if (this.data == value) {
            return this;
        } else if (this.data > value && this.left != null) {
            return this.left.search(value);
        } else if (this.data < value && this.right != null) {
            return this.right.search(value);
        }
        return null;
    }

    public Node searchParent(int value) {
        if (this.left == null && this.right == null && this.data != value) {
            return null;
        }
        // 左节点
        if (this.left.data == value || this.right.data == value) {
            return this;
        }
        if (this.data > value && this.left != null) {
            return this.left.searchParent(value);
        } else if (this.data < value && this.right != null) {
            return this.right.searchParent(value);
        }
        return null;
    }

    public int height() {
        return Math.max(leftHeight(), rightHeight()) + 1;
    }

    public int leftHeight() {
        int height = 0;
        if (this.left == null) {
            height = 0;
        } else {
            height = this.left.height();
        }
        return height;
    }

    public int rightHeight() {
        int height = 0;
        if (this.right == null) {
            height = 0;
        } else {
            height = this.right.height();
        }
        return height;
    }

}
