package list;

import constants.Constant;
import exception.Exception;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {
    private Object[] elementData = new Object[Constant.INITIAL_ARRAY_SIZE];
    private int size;

    @Override
    public boolean add(T value) {
        if (size == elementData.length) {
            int newLength = elementData.length * 3 / 2 + 1;
            Object[] newElementData = new Object[newLength];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }

        elementData[size] = value;
        size++;

        return true;
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
    public T get(int index) {
        Exception.indexOutOfRange(index, size);

        return (T) elementData[index];
    }

    @Override
    public String toString() {
        Object[] newElementData = new Object[size];
        System.arraycopy(elementData, 0, newElementData, 0, size);

        return Arrays.toString(newElementData);
    }
}
