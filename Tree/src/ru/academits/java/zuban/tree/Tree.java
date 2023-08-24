package ru.academits.java.zuban.tree;

import ru.academits.java.zuban.tree_comparator.NodeComparator;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Tree<T extends Comparable<T>> {
    private Node<T> peak;
    private int size;

    public void insert(T value) {
        if (peak == null) {
            peak = new Node<>(value);
            size++;
            return;
        }

        Node<T> currentNode = peak;
        Node<T> newNode = new Node<>(value);

        NodeComparator<T> nodeComparator = new NodeComparator<>();

        while (currentNode != null) {
            int compareResult = nodeComparator.compare(newNode, currentNode);

            if (compareResult == 0) {
                return;
            } else if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    size++;
                    return;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    size++;
                    return;
                }

                currentNode = currentNode.getLeft();
            }
        }
    }

    public boolean findNode(T value) {
        if (peak == null) {
            return false;
        }

        Node<T> currentNode = peak;
        Node<T> searchNode = new Node<>(value);

        NodeComparator<T> nodeComparator = new NodeComparator<>();

        while (currentNode != null) {
            int compareResult = nodeComparator.compare(searchNode, currentNode);

            if (compareResult == 0) {
                return true;
            } else if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(searchNode);
                    size++;
                    return false;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(searchNode);
                    size++;
                    return false;
                }

                currentNode = currentNode.getLeft();
            }
        }

        return false;
    }

    public boolean remove(T value) {
        if (peak == null) {
            return false;
        }

        int oldSize = size;

        NodeComparator<T> nodeComparator = new NodeComparator<>();

        Node<T> parent = null;
        Node<T> current = peak;

        while (current != null) {
            int compareResult = nodeComparator.compare(new Node<>(value), current);

            if (compareResult > 0) {
                parent = current;
                current = current.getRight();
            } else if (compareResult < 0) {
                parent = current;
                current = current.getLeft();
            } else {
                if (current.getLeft() == null && current.getRight() == null) {
                    if (parent.getLeft() == current) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                } else if (current.getLeft() != null && current.getRight() == null) {
                    if (parent.getLeft() == current) {
                        parent.setLeft(current.getLeft());
                    } else {
                        parent.setRight(current.getLeft());
                    }
                } else if (current.getLeft() == null && current.getRight() != null) {
                    if (parent.getLeft() == current) {
                        parent.setLeft(current.getRight());
                    } else {
                        parent.setRight(current.getRight());
                    }
                } else {
                    Node<T> successor = getMinimumKey(current.getRight());

                    current.setValue(successor.getValue());
                    value = successor.getValue();

                    parent = current;
                    current = current.getRight();

                    continue;
                }

                size--;
                break;
            }
        }

        return oldSize != size;
    }

    public static <T> Node<T> getMinimumKey(Node<T> currentNode) {
        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }

        return currentNode;
    }

    public int size() {
        return size;
    }

    public void traversalsDepthRecursive() {
        traversalsDepthRecursive(peak);
    }

    private void traversalsDepthRecursive(Node<T> node) {
        if (node != null) {
            System.out.println(node.getValue());

            traversalsDepthRecursive(node.getLeft());
            traversalsDepthRecursive(node.getRight());
        }
    }

    public void traversalsDepth() {
        if (peak == null) {
            return;
        }

        Node<T> currentNode = peak;

        //noinspection DuplicatedCode
        Stack<Node<T>> stack = new Stack<>();
        stack.push(currentNode);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();

            System.out.println(node.getValue());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public void traversalsWidth() {
        if (peak == null) {
            return;
        }

        Node<T> currentNode = peak;

        //noinspection DuplicatedCode
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(currentNode);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();

            System.out.println(node.getValue());

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
        }
    }
}