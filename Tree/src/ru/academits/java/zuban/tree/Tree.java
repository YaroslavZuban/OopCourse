package ru.academits.java.zuban.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<E> {
    private final Comparator<E> comparator;
    private Node<E> root;
    private int size;

    public Tree() {
        comparator = null;
    }

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void insert(E value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return;
        }

        Node<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compareNodes(value, currentNode.getValue());

            if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node<>(value));
                    size++;
                    return;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node<>(value));
                    size++;
                    return;
                }

                currentNode = currentNode.getLeft();
            }
        }
    }

    public boolean contains(E value) {
        if (root == null) {
            return false;
        }

        Node<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compareNodes(value, currentNode.getValue());

            if (compareResult == 0) {
                return true;
            }

            if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            }
        }

        return false;
    }

    public boolean remove(E value) {
        if (root == null) {
            return false;
        }

        Node<E> parent = root;
        Node<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compareNodes(value, currentNode.getValue());

            if (compareResult == 0) {
                break;
            }

            parent = currentNode;

            if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            }
        }

        if (currentNode.getLeft() == null) {
            if (parent.getLeft() == currentNode) {
                parent.setLeft(currentNode.getRight());
            } else {
                parent.setRight(currentNode.getRight());
            }

            size--;
            return true;
        }

        if (currentNode.getRight() == null) {
            if (parent.getLeft() == currentNode) {
                parent.setLeft(currentNode.getLeft());
            } else {
                parent.setRight(currentNode.getLeft());
            }

            size--;
            return true;
        }

        Node<E> successorParent = currentNode;
        Node<E> successor = currentNode.getRight();

        while (successor.getLeft() != null) {
            successorParent = successor;
            successor = successor.getLeft();
        }

        currentNode.setValue(successor.getValue());

        if (successorParent.getLeft() == successor) {
            successorParent.setLeft(successor.getRight());
        } else {
            successorParent.setRight(successor.getRight());
        }

        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public void traversalInDepthRecursive(Consumer<E> consumer) {
        traversalInDepthRecursive(root, consumer);
    }

    private void traversalInDepthRecursive(Node<E> node, Consumer<E> consumer) {
        if (node != null) {
            consumer.accept(node.getValue());

            traversalInDepthRecursive(node.getLeft(), consumer);
            traversalInDepthRecursive(node.getRight(), consumer);
        }
    }

    public void traversalInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Node<E> currentNode = root;

        Deque<Node<E>> stack = new ArrayDeque<>();
        stack.push(currentNode);

        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();

            consumer.accept(node.getValue());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public void traversalInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Node<E> currentNode = root;

        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.add(currentNode);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            consumer.accept(node.getValue());

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
        }
    }

    private int compareNodes(E value1, E value2) {
        if (value1 == null && value2 == null) {
            return 0;
        } else if (value1 == null) {
            return -1;
        } else if (value2 == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        //noinspection unchecked
        Comparable<E> comparableValue = (Comparable<E>) value1;
        //noinspection unchecked
        Comparable<E> comparableOtherValue = (Comparable<E>) value2;

        //noinspection unchecked
        return comparableValue.compareTo((E) comparableOtherValue);
    }
}