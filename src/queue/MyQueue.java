package queue;

public interface MyQueue<T> {
    boolean add(T value);

    void clear();

    int size();

    T peek();

    T poll();
}
