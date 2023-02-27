package list;

import exception.Exception;

public class MyLinkedList<T> implements MyList<T> {
    private Node<T> header;
    private Node<T> last;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        public Node(T element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }
    @Override
    public boolean add(T value) {
        Node<T> newNode = new Node<>(value);
        if (header == null) {
            header = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;

        return true;
    }

    @Override
    public T remove(int index) {
        Exception.indexOutOfRange(index, size);

        Node<T> current;

        if (index < size / 2) {
            current = header;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = last.prev;
            }
        }

        Node<T> previous = current.prev;
        Node<T> next = current.next;

        if (previous == null) {
            header = next;
        } else {
            previous.next = next;
            current.prev = null;
        }

        if (next == null) {
            last = previous;
        } else {
            next.prev = previous;
            current.next = null;
        }

        size--;

        return current.element;
    }

    @Override
    public void clear() {
        header = null;
        last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        Exception.indexOutOfRange(index, size);

        int indexPoint = size / 2;
        Node<T> currentNode;

        if (index < indexPoint) {
            currentNode = header;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = last;
            for (int j = size - 1; j > index; j--) {
                currentNode = currentNode.prev;
            }
        }

        return currentNode.element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> currentNode = header;

        while (currentNode != null) {
            sb.append(currentNode.element);
            if (currentNode.next != null) {
                sb.append(", ");
            }

            currentNode = currentNode.next;
        }
        sb.append("]");

        return sb.toString();
    }
}
