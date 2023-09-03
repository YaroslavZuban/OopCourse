package ru.academits.java.zuban.tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.Stack;


//остановился на задаче 7
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

    public boolean findNode(E value) {
        if (root == null) {
            return false;
        }

        Node<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compareNodes(value, currentNode.getValue());

            if (compareResult == 0) {
                return true;
            } else if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node<>(value));
                    size++;
                    return false;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node<>(value));
                    size++;
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

        int oldSize = size;

        Node<E> parent = null;
        Node<E> current = root;

        while (current != null) {
            int compareResult = compareNodes(value, current.getValue());

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
                    Node<E> successor = getMinimumKey(current.getRight());

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

    private static <E> Node<E> getMinimumKey(Node<E> currentNode) {
        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }

        return currentNode;
    }

    public int size() {
        return size;
    }

    public void traversalsDepthRecursive() {
        traversalsDepthRecursive(root);
    }

    private void traversalsDepthRecursive(Node<E> node) {
        if (node != null) {
            System.out.println(node.getValue());

            traversalsDepthRecursive(node.getLeft());
            traversalsDepthRecursive(node.getRight());
        }
    }

    public void traversalsDepth() {
        if (root == null) {
            return;
        }

        Node<E> currentNode = root;

        //noinspection DuplicatedCode
        Stack<Node<E>> stack = new Stack<>();
        stack.push(currentNode);

        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();

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
        if (root == null) {
            return;
        }

        Node<E> currentNode = root;

        //noinspection DuplicatedCode
        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.add(currentNode);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            System.out.println(node.getValue());

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
        }
    }

    private int compareNodes(E node1, E node2) {
        if (node1 == null && node2 == null) {
            return 0;
        } else if (node1 == null) {
            return -1;
        } else if (node2 == null) {
            return 1;
        }

        return comparator.compare(node1, node2);
    }
}