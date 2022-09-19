package com.learn.review.datastructure.tree;

public class TreeDemo {
    public static void main(String[] args) {
        int[] val = new int[]{20, 10, 15, 6, 50, 60, 45, 40, 55};
        BinarySortTree tree = new BinarySortTree();
        for (int i : val) {
            tree.add(i);
        }
//        // 前序遍历
//        tree.preOrder(tree.root);
//        System.out.println();
//        // 中序遍历
//        tree.midOrder(tree.root);
//        System.out.println();
//        // 后序遍历
//        tree.postOrder(tree.root);
//        System.out.println();

//        BinarySortTree.Node node = tree.root.searchParent(20);
//        System.out.println(node);

        // 中序遍历
        tree.midOrder(tree.root);
        System.out.println();
        // 删除
//        tree.removeRecursion(50);
        tree.removeCycle(50);
        // 中序遍历
        tree.midOrder(tree.root);
        System.out.println();

    }
}
