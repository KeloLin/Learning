package com.learn.review.datastructure.tree;

public class TreeDemo {
    public static void main(String[] args) {
        BstTree tree = new BstTree();
        tree.add(20);
        tree.add(10);
        tree.add(15);
        tree.add(6);
        tree.add(50);
        tree.add(60);

        tree.preOrder(tree.root);
        System.out.println();

        tree.midOrder(tree.root);
        System.out.println();

        tree.postOrder(tree.root);
        System.out.println();

    }
}
