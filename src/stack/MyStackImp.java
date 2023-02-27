package stack;

import constants.Constant;
import exception.Exception;

import java.util.Arrays;

public class MyStackImp<T> implements MyStack<T> {
    private Object[] elementData = new Object[Constant.INITIAL_ARRAY_SIZE];
    private int size;

    @Override
    public T push(T value) {
        if (size == elementData.length) {
            int newLength = elementData.length * 2;
            Object[] newElementData = new Object[newLength];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }

        elementData[size] = value;
        size++;

        return value;
    }

    @Override
    public T remove(int index) {
        Exception.indexOutOfRange(index, size);

        int numberOfElements = size - index - 1;
        T removedItem = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, numberOfElements);
        elementData[--size] = null;

        return removedItem;
    }

    @Override
    public void clear() {
        size = 0;
        elementData = new Object[Constant.INITIAL_ARRAY_SIZE];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        Exception.checkNotEmpty(size);

        return (T) elementData[size - 1];
    }

    @Override
    public T pop() {
        Exception.checkNotEmpty(size);

        T result = (T) elementData[size - 1];
        elementData[size - 1] = null;
        size--;

        return result;
    }

    @Override
    public String toString() {
        Object[] newElementData = new Object[size];
        System.arraycopy(elementData, 0, newElementData, 0, size);

        return Arrays.toString(newElementData);
    }
}
