package com.learn.review.datastructure.tree;

/**
 * 二叉排序树
 */
public class BinarySortTree {

    protected Node root;

    public BinarySortTree() {
    }

    public BinarySortTree(Node root) {
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
        int height = node.height();
        for (int i = 0; i < height; i++) {
            System.out.print("-");
        }
        System.out.println(node.data);
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
        int height = node.height();
        for (int i = 0; i < height; i++) {
            System.out.print("-");
        }
        System.out.println(node.data);
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
        int height = node.height();
        for (int i = 0; i < height; i++) {
            System.out.print("-");
        }
        System.out.println(node.data);
    }

    public Integer removeRecursion(int value) {
        if (this.root == null) {
            return null;
        }
        Node node = this.root.search(value);
        if (node == null) {
            return null;
        }
        Integer data = node.data;
        Node parent = this.root.searchParent(value);
        // 删除的节点为叶子节点，直接删除，删除节点的引用清空
        if (node.left == null && node.right == null) {
            // 删除节点的引用要清空
            if (parent.left == node) {
                parent.left = null;
            } else if (parent.right == node) {
                parent.right = null;
            }
            return data;
        } else if (node.left != null && node.right == null) {
            // 删除的节点只存在左子树，子树节点晋升
            if (parent.left == node) {
                parent.left = node.left;
            } else if (parent.right == node) {
                parent.right = node.left;
            }
        } else if (node.left == null && node.right != null) {
            // 删除的节点只存在右子树，子树节点晋升
            if (parent.left == node) {
                parent.left = node.right;
            } else if (parent.right == node) {
                parent.right = node.right;
            }
        } else if (node.left != null && node.right != null) {
            // 删除的节点存在左子树和右子树
            Node target = node.right;
            Integer minNodeValue = null;
            while (target != null) {
                if (target.left != null) {
                    target = target.left;
                    continue;
                } else {
                    // 当前节点就是最小
                    minNodeValue = removeRecursion(target.data);
                    break;
                }
            }

            Node temp = new Node(minNodeValue, node.left, node.right);
            if (parent.left == node) {
                parent.left = temp;
            } else if (parent.right == node) {
                parent.right = temp;
            }
            return data;
        }
        return null;
    }

    /**
     * 用循环的办法实现
     *
     * @param value
     * @return
     */
    public Integer removeCycle(int value) {
        Node parent = null;
        Node current = this.root;
        if (current.data == null) {
            return null;
        }
        // 如果删除的是叶子节点，则直接删除。
        // 如果删除的节点只有左子树或右子树，则子树的节点晋升，删除节点。
        // 如果删除的节点有左子树和右子树，选择右子树最小值作为替代

        // 只要当前节点的值与要查找的值不一致，则继续循环
        while (current != null) {
            // 当前节点的值等于要查找的值
            if (current.data == value) {
                Integer data = current.data;
                if (current.left == null && current.right == null) {
                    // 当前节点无叶子节点
                    // 这里就是叶子节点
                    if (parent != null) {
                        // 删除叶子节点
                        if (parent.left.data == value) {
                            parent.left = null;
                        } else if (parent.right.data == value) {
                            parent.right = null;
                        }
                    } else {
                        // 是根节点，且没有子树
                        root = null;
                    }
                    return data;
                } else if (current.left != null && current.right == null) {
                    // 当前删除节点的左子树不为空，把当前节点的左子树替换删除节点
                    if (parent.data > value) {
                        parent.left = current.left;
                    } else {
                        parent.right = current.left;
                    }
                    return data;
                } else if (current.left == null && current.right != null) {
                    // 当前删除节点的右子树不为空，把当前节点的右子树替换删除节点
                    if (parent.data > value) {
                        parent.left = current.right;
                    } else {
                        parent.right = current.right;
                    }
                    return data;
                } else {
                    // 左右子树均不为空，需要找到右子树最小值替代删除节点
                    Node minNodeParent = current;
                    // 从右子树找大于value的最小值
                    Node minNode = current.right;
                    while (minNode != null) {
                        if (minNode.left != null) {
                            // 有左子树，说明还有更小的数值
                            minNodeParent = minNode;
                            minNode = minNode.left;
                            continue;
                        } else {
                            // 没有左子树，说明当前节点（minNode）已经是最小的
                            if (parent.left == current) {
                                parent.left = minNode;
                            } else if (parent.right == current) {
                                parent.right = minNode;
                            }
                            // 把指向最小节点置为空
                            if (minNodeParent.left == minNode) {
                                minNodeParent.left = null;
                            } else if (minNodeParent.right == minNode) {
                                minNodeParent.right = null;
                            }
                            minNode.left = current.left;
                            minNode.right = current.right;
                            return data;
                        }
                    }
                }
            } else if (current.data > value && current.left != null) {
                // 要删除的数值比当前节点的值小，向左子树查询
                // 将当前节点作为父节点
                parent = current;
                // 将当前节点的右子树节点作为当前节点，进入遍历
                current = current.left;
            } else if (current.data < value && current.right != null) {
                // 要删除的数值比当前节点的值大，向右子树查询
                // 将当前节点作为父节点
                parent = current;
                // 将当前节点的右子树节点作为当前节点，进入遍历
                current = current.right;
            } else {
                return null;
            }
        }
        return null;
    }


    class Node {
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
}
