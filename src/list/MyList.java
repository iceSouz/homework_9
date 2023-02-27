package list;

public interface MyList<T> {
    boolean add(T value);

    T remove(int index);

    void clear();

    int size();

    T get(int index);
}
