package com.learn.review.datastructure.tree;

/**
 * 二叉排序树
 */
public class BstTree {

    protected Node root;

    public BstTree() {
    }

    public BstTree(Node root) {
        this.root = root;
    }

    public void add(Integer e) {
        // 根节点是空的，把插入的元素当成根节点
        if (root == null) {
            root = new Node(e, null, null);
        } else {
            root.add(e);
        }
    }

    /**
     * 前序遍历
     * 中前后
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     * 前中后
     */
    public void midOrder(Node node) {
        if (node == null) {
            return;
        }
        midOrder(node.left);
        System.out.print(node.data + " ");
        midOrder(node.right);
    }

    /**
     * 后序遍历
     * 前后中
     */
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public Integer remove(Integer data) {


        // 如果删除的是叶子节点，则直接删除。
        // 如果删除的节点只有左子树或右子树，则子树的节点晋升，删除节点。
        // 如果删除的节点有左子树和右子树：
        //  ○ 如果删除的节点在根节点的左子树一侧，则选择左子树最大的节点替代删除节点的位置。
        //  ○ 如果删除的节点在根节点的右子树一侧，则选择右子树最小的节点替代删除节点的位置。


        return 0;
    }


    class Node {
        private Integer data;
        private Node left;
        private Node right;

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

        public Node searchParent(int value)  {


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
}
