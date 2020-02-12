package com.keksec.data_structures.custom_hash_map;

import com.keksec.data_structures.custom_hash_map.constants.Constants;

import java.util.Arrays;

//TODO: Possible imrovements: 1.always rehash with new hash function when resizing 2.upgrade example 3.write tests
public class MyHashMap<K, V> {
    private int capacity;
    private final double loadFactor;
    private int nodesCount;
    Node<K, V>[] table;

    public MyHashMap() {
        this.capacity = Constants.DEFAULT_CAPACITY;
        this.loadFactor = Constants.DEFAULT_LOAD_FACTOR;
        this.nodesCount = 0;
        this.table = new Node[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
    }

    public MyHashMap(int initCapacity, double loadFactory) {
        this.capacity = initCapacity;
        this.loadFactor = loadFactory;
    }

    public boolean containsKey(K key) {
        int hash = hash(key);
        int i = indexFor(hash, capacity);
        if (table[i] != null) return true;
        return false;
    }

    public V put(K key, V value) throws NullPointerException {
        if (key == null || value == null) throw new NullPointerException("Key or value provided to put method is null");
        if (nodesCount > capacity * loadFactor) {
            table = resizeArray();
        }
        int hash = hash(key);
        int i = indexFor(hash, capacity);
        for (Node<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldVal = e.value;
                e.value = value;
                return oldVal;
            }
        }

        nodesCount++;
        addNode(hash, key, value, i);
        return null;
    }

    public V get(K key) throws NullPointerException {
        if (key == null) throw new NullPointerException("Key provided to get method is null");
        int hash = hash(key.hashCode());
        int i = indexFor(hash, table.length);
        for (Node<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }

    public V remove(K key) throws NullPointerException {
        if (key == null) throw new NullPointerException("Key provided to remove method is null");
        int hash = hash(key.hashCode());
        int i = indexFor(hash, capacity);
        for (Node<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.next != null) {
                if (e.next.hash == hash && ((k = e.next.key) == key || key.equals(k))) {
                    V value = e.next.value;
                    e.next = e.next.next;
                    return value;
                } else if (e == table[i] && e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    V value = table[i].value;
                    table[i] = e.next;
                    return value;
                }
            } else if (e == table[i] && e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V value = table[i].value;
                table[i] = null;
                return value;
            }
        }
        return null;
    }

    private void addNode(int hash, K key, V value, int index) {
        Node<K, V> node = new Node<>(hash, key, value);
        if (table[index] == null) {
            table[index] = node;
        } else {
            table[index].appendToList(node);
        }
    }

    private Node<K, V>[] resizeArray() {
        capacity *= 2;
        @SuppressWarnings("unchecked")
        Node<K, V>[] resultTable = new Node[capacity];
        Arrays.stream(table).forEach(node -> {
            while (node.next != null) {
                int index = indexFor(node.hash, capacity);
                if (resultTable[index] == null) {
                    resultTable[index] = node;
                } else {
                    resultTable[index].appendToList(node);
                }
            }
        });
        return resultTable;
    }

    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }
}
