package map;

import constants.Constant;
import exception.Exception;

import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private Node<K, V>[] buckets;
    private int size;

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + " = " + value;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Node) {
                Node<?, ?> e = (Node<?, ?>) o;
                return Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    public MyHashMap() {
        this(Constant.INITIAL_ARRAY_SIZE);
    }

    public MyHashMap(int initialCapacity) {
        Exception.illegalArgument(initialCapacity);
        buckets = new Node[initialCapacity];
    }

    @Override
    public void clear() {
        Node<K, V>[] bs = buckets;
        Arrays.fill(bs, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        Node<K, V> n = getNode(hash(key), key);
        return (n == null) ? null : n.value;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int i = indexFor(hash, buckets.length);
        for (Node<K, V> n = buckets[i]; n != null; n = n.next) {
            if (n.hash == hash && Objects.equals(n.key, key)) {
                V oldValue = n.value;
                n.value = value;
                return oldValue;
            }
        }
        addNode(hash, key, value, i);
        return null;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int i = indexFor(hash, buckets.length);
        Node<K, V> p = null;
        for (Node<K, V> n = buckets[i]; n != null; p = n, n = n.next) {
            if (n.hash == hash && Objects.equals(n.key, key)) {
                if (p == null)
                    buckets[i] = n.next;
                else
                    p.next = n.next;
                size--;
                return n.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int countBuckets = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                sb.append(buckets[i]);
                countBuckets++;

                if (size > countBuckets) {
                    sb.append(", ");
                }
            }
        }
        sb.append("}");

        return sb.toString();
    }

    private void addNode(int hash, K key, V value, int bucketIndex) {
        Node<K, V> first = buckets[bucketIndex];
        buckets[bucketIndex] = new Node<>(hash, key, value, first);
        size++;
        if (size > buckets.length * 0.75) {
            resize(buckets.length * 2);
        }
    }

    private Node<K, V> getNode(int hash, Object key) {
        int i = indexFor(hash, buckets.length);
        for (Node<K, V> n = buckets[i]; n != null; n = n.next) {
            if (n.hash == hash && Objects.equals(n.key, key))
                return n;
        }
        return null;
    }

    private void resize(int newCapacity) {
        Node<K, V>[] oldBuckets = buckets;
        int oldCapacity = oldBuckets.length;
        if (oldCapacity >= 1 << 30) {
            return;
        }
        Node<K, V>[] newBuckets = new Node[newCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> e = oldBuckets[i];
            if (e != null) {
                oldBuckets[i] = null;
                do {
                    Node<K, V> next = e.next;
                    int j = indexFor(e.hash, newCapacity);
                    e.next = newBuckets[j];
                    newBuckets[j] = e;
                    e = next;
                } while (e != null);
            }
        }
        buckets = newBuckets;
    }

    private static int hash(Object key) {
        return (key == null) ? 0 : key.hashCode();
    }

    private static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }
}
