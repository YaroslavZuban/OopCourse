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

    public int size() {
        return size;
    }

    private int compare(E value1, E value2) {
        if (value1 == null && value2 == null) {
            return 0;
        }

        if (value1 == null) {
            return -1;
        }

        if (value2 == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        //noinspection unchecked
        return ((Comparable<E>) value1).compareTo(value2);
    }

    public void insert(E value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return;
        }

        Node<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compare(value, currentNode.getValue());

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
            int compareResult = compare(value, currentNode.getValue());

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

        Node<E> parent = null;
        Node<E> currentNode = root;
        boolean isLeftParent = false;

        while (currentNode != null) {
            int compareResult = compare(value, currentNode.getValue());

            if (compareResult == 0) {
                break;
            }

            parent = currentNode;

            if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
                isLeftParent = false;
            } else {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
                isLeftParent = true;
            }
        }

        //noinspection DataFlowIssue
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (parent == null) {
                root = null;
            } else if (parent.getLeft() == currentNode) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (currentNode.getLeft() == null || currentNode.getRight() == null) {
            Node<E> child = (currentNode.getLeft() != null) ? currentNode.getLeft() : currentNode.getRight();

            if (parent == null) {
                root = child;
            } else if (parent.getLeft() == currentNode) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        } else {
            Node<E> successorParent = null;
            Node<E> successor = currentNode.getRight();

            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }

            if (successorParent != null) {
                successorParent.setLeft(successor.getRight());
            } else {
                currentNode.setRight(successor.getRight());
            }

            successor.setLeft(currentNode.getLeft());
            successor.setRight(currentNode.getRight());

            if (parent == null) {
                root = successor;
            } else if (isLeftParent) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
        }

        size--;
        return true;
    }

    public void traverseInDepthRecursive(Consumer<E> consumer) {
        traverseInDepthRecursive(root, consumer);
    }

    private void traverseInDepthRecursive(Node<E> node, Consumer<E> consumer) {
        if (node != null) {
            consumer.accept(node.getValue());

            traverseInDepthRecursive(node.getLeft(), consumer);
            traverseInDepthRecursive(node.getRight(), consumer);
        }
    }

    public void traverseInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<Node<E>> stack = new ArrayDeque<>();
        stack.push(root);

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

    public void traverseInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.add(root);

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
}