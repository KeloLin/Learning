package com.learn.review.datastructure.tree;

/**
 * 平衡二叉树
 * 不平衡的判断标准为，左右
 */
public class AvlTree extends BinarySortTree {

    @Override
    public void add(Integer e) {
        super.add(e);
        rotate();
    }

    private void rotate() {
        // 判断左子树和右子树的高度差
        Integer leftHeight = this.root.leftHeight();
        Integer rightHeight = this.root.rightHeight();
        if (Math.abs(leftHeight - rightHeight) > 1) {
            // 判断左旋右旋
            if (leftHeight > rightHeight) {
                // 左子树比右子树深，左旋
                this.root = leftRotate(this.root);
            } else {
                // 右子树比左子树深，右旋
                this.root = rightRotate(this.root);
            }
        }

    }

    /**
     * 左旋操作
     * 如果右子树深度超过左子树的深度，打破平衡，
     * <p>
     * “长兄为父”，假设子树深度更深的子树根节点为“长兄”，根节点为“父”
     * 1. 将原根节点的右子树作为调整后的根节点。“长兄上位”
     * 2. 原根节点的右子树的右子树不变，原根节点的右子树的左子树作为原根节点的右子树。
     * 3. 将原根节点作为调整后的根节点的左子树，原根节点的左子树不变。
     */
    private Node leftRotate(Node root) {
        // 将根节点的右子树节点作为调整后的根节点
        Node rebaseLeft = new Node(root.data, root.left, root.right.left);
        // 将根节点的右子树节点作为调整后的根节点
        Node rebaseRoot = root.right;
        rebaseRoot.left = rebaseLeft;
        // 将根节点的右子树节点作为调整后的根节点
        return rebaseRoot;
    }

    /**
     * 右旋操作
     * 如果左子树深度超过右子树的深度，打破平衡，
     * <p>
     * “长兄为父”，假设子树深度更深的子树根节点为“长兄”，根节点为“父”
     * 1. 将原根节点的左子树作为调整后的根节点。“长兄上位”
     * 2. 原根节点的左子树的左子树不变，原根节点的左子树的右子树作为原根节点的左子树。
     * 3. 将原根节点作为调整后的根节点的右子树，原根节点的右子树不变。
     */
    private Node rightRotate(Node root) {
        // 将根节点保存到临时节点，将原根节点的左子树的右子树（即根节点左子树的最大值）作为原根节点的左子树节点
        Node rebaseRight = new Node(root.data, root.left.right, root.right);
        // 将根节点的右子树节点作为调整后的根节点
        Node rebaseRoot = root.left;
        // 调整后的根节点的右子树指向原根节点，左子树不变
        rebaseRoot.right = rebaseRight;
        // 最后把根节点设置为调整后的根节点
        return rebaseRoot;
    }

}
