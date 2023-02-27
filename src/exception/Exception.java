package exception;

import java.util.EmptyStackException;

public class Exception {
    public static void indexOutOfRange(int index, int size) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
    }

    public static void illegalArgument(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
    }

    public static void checkNotEmpty(int size) {
        if (size == 0) {
            throw new EmptyStackException();
        }
    }
}
