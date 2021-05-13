package net.weirdwired.java.interview.trees;

import java.util.*;
import java.util.function.Consumer;

public class NodeUtil {
    public static String getBreadthFirstOrder(Node<?> node) {
        Queue<Node<?>> queue = new ArrayDeque<>(); //FIFO
        Queue<Node<?>> order = new ArrayDeque<>(); // to display

        queue.add(node); // add the given node

        while (!queue.isEmpty()) {
            // Step 1: Pop first node
            Node<?> current = queue.poll();

            // Step 2: Visit the current node
            order.add(current);

            // Step 3: Add left
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            // Step 4: Add right
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }

        // print
        return String.join(" ", order.stream().map(x -> x.getValue().toString()).toList());
    }

    // current, left, right
    public static String getDepthFirstPreOrder(Node<?> node) {
        Queue<Node<?>> order = new ArrayDeque<>(); //FIFO

        // do this iteratively
        Stack<Node<?>> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            // Step 1: add the given node
            Node<?> current = stack.pop();
            order.add(current);

            // Step 2: go to left tree - go to right first (stack is LIFO)
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }

            // Step 3: go to right tree - go to left (this will be popped first)
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }

        // print
        return String.join(" ", order.stream().map(x -> x.getValue().toString()).toList());
    }

    // left, current, right
    public static String getDepthFirstInOrder(Node<?> node) {
        Queue<Node<?>> order = new ArrayDeque<>(); //FIFO

        // do this iteratively
        Stack<Node<?>> stack = new Stack<>();
        Node<?> current = node;
        stack.push(node);

        while (!stack.isEmpty()) {
            // Step 1. Left branch
            while (current.getLeft() != null) {
                current = current.getLeft();
                stack.push(current);
            }

            // Step 2. Root
            current = stack.pop();
            order.add(current);

           // Step 3. Right branch
            if (current.getRight() != null) {
                current = current.getRight();
                stack.push(current);
            }
        }

        // print
        return String.join(" ", order.stream().map(x -> x.getValue().toString()).toList());
    }

    // left, right, current
    public static String getDepthFirstPostOrder(Node<?> node) {
        Queue<Node<?>> order = new ArrayDeque<>(); //FIFO

        // do this iteratively
        Stack<Node<?>> stack = new Stack<>();
        Node<?> current;
        Node<?> prev = node;
        stack.push(node);

        while (!stack.isEmpty()) {
            current = stack.peek();
            boolean hasChild = current.getLeft() != null || current.getRight() != null;
            boolean isPrevLastChild = prev == current.getRight()
                    || prev == current.getLeft() && current.getRight() == null;

            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                order.add(current);
                prev = current;
            } else {
                if (current.getRight() != null) {
                    stack.push(current.getRight());
                }
                if (current.getLeft() != null) {
                    stack.push(current.getLeft());
                }
            }
        }

        // print
        return String.join(" ", order.stream().map(x -> x.getValue().toString()).toList());
    }

    public static void traverseDfsPreOrder(Node<?> node, Consumer<Node<?>> visitor) {
        if (node != null) {
            visitor.accept(node);
            traverseDfsPreOrder(node.getLeft(), visitor);
            traverseDfsPreOrder(node.getRight(), visitor);
        }
    }

    public static void traverseDfsInOrder(Node<?> node, Consumer<Node<?>> visitor) {
        if (node != null) {
            traverseDfsInOrder(node.getLeft(), visitor);
            visitor.accept(node);
            traverseDfsInOrder(node.getRight(), visitor);
        }
    }

    public static void traverseDfsPostOrder(Node<?> node, Consumer<Node<?>> visitor) {
        if (node != null) {
            traverseDfsPostOrder(node.getLeft(), visitor);
            traverseDfsPostOrder(node.getRight(), visitor);
            visitor.accept(node);
        }
    }
}
