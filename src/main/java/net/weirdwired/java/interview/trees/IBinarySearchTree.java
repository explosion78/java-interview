package net.weirdwired.java.interview.trees;

public interface IBinarySearchTree<T extends Comparable<T>> {
    void insert(T value);
    void delete(T value);
    Node<T> find(T value);
    Node<T> max();
    Node<T> min();
    Node<T> getRoot();
    int height();
}
