package queue;

import constants.Constant;

import java.util.Arrays;

public class MyArrayDeque<T> implements MyQueue<T> {
    private Object[] elements = new Object[Constant.INITIAL_ARRAY_SIZE];
    private int front = -1;
    private int rear = -1;

    @Override
    public boolean add(T value) {
        if (front < 0) {
            front++;
            rear++;
        }

        if (rear == elements.length - 1) {
            int newLength = elements.length * 2;
            Object[] newElements = new Object[newLength];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }

        elements[rear] = value;
        rear++;

        return true;
    }

    @Override
    public void clear() {
        front = -1;
        rear = -1;
        elements = new Object[Constant.INITIAL_ARRAY_SIZE];
    }

    @Override
    public int size() {
        return rear - front;
    }

    @Override
    public T peek() {
        return (T) elements[Constant.INDEX_ARRAY_ZERO];
    }

    @Override
    public T poll() {
        if (front < 0) {
            return null;
        }

        T result = (T) elements[front];
        Object[] newElements = new Object[size()];
        System.arraycopy(elements, front + 1, newElements, 0, size());
        rear--;
        elements = newElements;

        if (size() == 0) {
            front = -1;
            rear = -1;
        }

        return result;
    }

    @Override
    public String toString() {
        Object[] newElementData = new Object[size()];
        System.arraycopy(elements, 0, newElementData, 0, size());

        return Arrays.toString(newElementData);
    }
}
