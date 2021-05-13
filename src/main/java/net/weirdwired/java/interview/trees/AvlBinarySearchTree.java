package net.weirdwired.java.interview.trees;

public class AvlBinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T>{
    private Node<T> root;

    public AvlBinarySearchTree() {

    }

    public AvlBinarySearchTree(Node<T> root) {
        this.root = root;
    }

    @Override
    public void insert(T value) {
        root = insert(root, value);
    }

    @Override
    public void delete(T value) {
        root = delete(root, value);
    }

    @Override
    public Node<T> find(T value) {
        return null;
    }

    @Override
    public Node<T> max() {
        return mostRightChild(root);
    }

    @Override
    public Node<T> min() {
        return mostLeftChild(root);
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public int height() {
        return root == null ? -1 : root.getHeight();
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value); // at the leaf, so insert
        } else if (node.getValue().compareTo(value) > 0) { //node.value > value, go to left
            node.setLeft(insert(node.getLeft(), value));
        } else if (node.getValue().compareTo(value) < 0) { //node.value < value, go to right
            node.setRight(insert(node.getRight(), value));
        } else {
            throw new IllegalArgumentException("duplicate value: " + value); // not handling duplicate values
        }

        return rebalance(node); // once added, rebalance if required
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) {
            return null;
        } else if (node.getValue().compareTo(value) > 0) { //node.value > value, go to left to find
            node.setLeft(delete(node.getLeft(), value));
        } else if (node.getValue().compareTo(value) < 0) { //node.value < value, go to right to find
            node.setRight(delete(node.getRight(), value));
        } else { // found
            if (node.getLeft() == null || node.getRight() == null) { // with one or zero child
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            } else { // with two children
                Node<T> mostLeftChild = mostLeftChild(node.getRight()); // find the next larger value
                node.setValue(mostLeftChild.getValue()); // replace it with the next larger value
                node.setRight(delete(node.getRight(), node.getValue()));
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private Node<T> mostLeftChild(Node<T> node) {
        Node<T> current = node;
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private Node<T> mostRightChild(Node<T> node) {
        Node<T> current = node;
        /* loop down to find the rightmost leaf */
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    private Node<T> rebalance(Node<T> node) {
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1) {
            if (height(node.getRight().getRight()) > height(node.getRight().getLeft())) {
                node = rotateLeft(node);
            } else {
                node.setRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        } else if (balance < -1) {
            if (height(node.getLeft().getLeft()) > height(node.getLeft().getRight())) {
                node = rotateRight(node);
            } else {
                node.setLeft(rotateLeft(node.getLeft()));
                node = rotateRight(node);
            }
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.getLeft();
        Node<T> leftRight = left.getRight();
        left.setRight(node);
        node.setLeft(leftRight);
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.getRight();
        Node<T> rightLeft = right.getLeft();
        right.setLeft(node);
        node.setRight(rightLeft);
        updateHeight(node);
        updateHeight(right);
        return right;
    }

    private void updateHeight(Node<T> node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }

    private int height(Node<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    public int getBalance(Node<T> node) {
        return (node == null) ? 0 : height(node.getRight()) - height(node.getLeft());
    }
}
