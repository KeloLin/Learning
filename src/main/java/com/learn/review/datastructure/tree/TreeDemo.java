package com.learn.review.datastructure.tree;

public class TreeDemo {
    public static void main(String[] args) {
        // 二叉排序树的构建
//        bst();
        // 二叉排序树的删除
//        bstRemove();
        // 平衡二叉树的构建与调整
        avl();
    }

    public static void bst() {
        int[] val = new int[]{20, 10, 15, 6, 50, 60, 45, 40, 55};
        BinarySortTree tree = new BinarySortTree();
        for (int i : val) {
            tree.add(i);
        }
        // 前序遍历
        tree.preOrder(tree.root);
        System.out.println();
        // 中序遍历
        tree.midOrder(tree.root);
        System.out.println();
        // 后序遍历
        tree.postOrder(tree.root);
        System.out.println();

    }

    public static void bstRemove() {
        int[] val = new int[]{20, 10, 15, 6, 50, 60, 45, 40, 55};
        BinarySortTree tree = new BinarySortTree();
        for (int i : val) {
            tree.add(i);
        }

        // 中序遍历
        tree.midOrder(tree.root);
        System.out.println();
        // 删除
        tree.removeRecursion(50);
//        tree.removeCycle(50);
        // 中序遍历
        tree.midOrder(tree.root);
        System.out.println();

    }

    public static void avl() {
        AvlTree tree = new AvlTree();
        // LL型
//        int[] val = new int[]{20, 15, 10, 6, 12, 5};
        // RR型
        int[] val = new int[]{20, 30, 40, 60, 35, 120};

        for (int i : val) {
            tree.add(i);
        }
        // 中序遍历
        tree.midOrder(tree.root);
        System.out.println();
    }
}
