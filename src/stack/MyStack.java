package stack;

public interface MyStack<T> {
    T push(T value);

    T remove(int index);

    void clear();

    int size();

    T peek();

    T pop();
}
