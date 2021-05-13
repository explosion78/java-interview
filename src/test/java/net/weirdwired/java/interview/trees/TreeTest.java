package net.weirdwired.java.interview.trees;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeTest {
    @Test
    @DisplayName("Should walk the tree in BFS manner")
    public void testLevelOrder() {
        // Arrange
        // Level 1:     40
        //            /    \
        // Level 2:  47    45
        //           / \   /
        // Level 3: 11  3 44
        Node<Integer> root = new Node<>(
                new Node<>( // Level 1 left
                        new Node<>(11), // Level 2 left
                        new Node<>(3), // Level 2 right
                        47),
                new Node<>( // Level 1 right
                        new Node<>(44), // Level 2 left
                        null, // Level 2 right
                        45),
                40);
        String expected = "40 47 45 11 3 44";

        // Act
        String result = NodeUtil.getBreadthFirstOrder(root);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should walk the tree in DFS PreOrder manner")
    public void testDfsPreOrder() {
        // Arrange
        // Level 1:     40
        //            /    \
        // Level 2:  47    45
        //           / \   /
        // Level 3: 11  3 44
        Node<Integer> root = new Node<>(
                new Node<>( // Level 1 left
                        new Node<>(11), // Level 2 left
                        new Node<>(3), // Level 2 right
                        47),
                new Node<>( // Level 1 right
                        new Node<>(44), // Level 2 left
                        null, // Level 2 right
                        45),
                40);
        String expected = "40 47 11 3 45 44";
        List<Node<?>> order = new ArrayList<>();

        // Act
        String result = NodeUtil.getDepthFirstPreOrder(root);
        NodeUtil.traverseDfsPreOrder(root, order::add);

        // Assert
        assertThat(result).isEqualTo(expected);
        assertThat(String.join(" ", order.stream().map(x -> x.getValue().toString()).toList())).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should walk the tree in DFS InOrder manner")
    public void testDfsInOrder() {
        // Arrange
        // Level 1:     40
        //            /    \
        // Level 2:  47    45
        //           / \   /
        // Level 3: 11  3 44
        Node<Integer> root = new Node<>(
                new Node<>( // Level 1 left
                        new Node<>(11), // Level 2 left
                        new Node<>(3), // Level 2 right
                        47),
                new Node<>( // Level 1 right
                        new Node<>(44), // Level 2 left
                        null, // Level 2 right
                        45),
                40);
        String expected = "11 47 3 40 44 45";
        List<Node<?>> order = new ArrayList<>();

        // Act
        String result = NodeUtil.getDepthFirstInOrder(root);
        NodeUtil.traverseDfsInOrder(root, order::add);

        // Assert
        assertThat(result).isEqualTo(expected);
        assertThat(String.join(" ", order.stream().map(x -> x.getValue().toString()).toList())).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should walk the tree in DFS PostOrder manner")
    public void testDfsPostOrder() {
        // Arrange
        // Level 1:     40
        //            /    \
        // Level 2:  47    45
        //           / \   /
        // Level 3: 11  3 44
        Node<Integer> root = new Node<>(
                new Node<>( // Level 1 left
                        new Node<>(11), // Level 2 left
                        new Node<>(3), // Level 2 right
                        47),
                new Node<>( // Level 1 right
                        new Node<>(44), // Level 2 left
                        null, // Level 2 right
                        45),
                40);
        String expected = "11 3 47 44 45 40";
        List<Node<?>> order = new ArrayList<>();

        // Act
        String result = NodeUtil.getDepthFirstPostOrder(root);
        NodeUtil.traverseDfsPostOrder(root, order::add);

        // Assert
        assertThat(result).isEqualTo(expected);
        assertThat(String.join(" ", order.stream().map(x -> x.getValue().toString()).toList())).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should rebalance itself - AVL BST")
    public void testAvl() {
        // Arrange
        // When balanced
        // Level 1:         50
        //               /      \
        // Level 2:     45      56
        //             /  \       \
        // Level 3:   42  48      57
        //             \    \
        // Level 4:    44   49
        List<Integer> values = Arrays.asList(50, 56, 45, 42, 48, 57, 44, 49);
        String expected = "42 44 45 48 49 50 56 57"; //InOrder traversal
        IBinarySearchTree<Integer> sut = new AvlBinarySearchTree<>();

        // Act
        values.forEach(sut::insert);
        String orderedList = NodeUtil.getDepthFirstInOrder(sut.getRoot());

        // Assert
        assertThat(orderedList).isEqualTo(expected);
        assertThat(sut.min().getValue()).isEqualTo(42);
        assertThat(sut.max().getValue()).isEqualTo(57);
    }
}
