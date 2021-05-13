package net.weirdwired.java.interview.trees;

public class Node<T extends Comparable<T>> {
    private Node<T> left;
    private Node<T> right;

    private T value;
    private int height;

    public Node (T element) {
        this(null, null, element);
    }

    public Node (Node<T> left, Node<T> right, T element) {
        this.value = element;
        this.left = left;
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
