package com.keksec.data_structures.custom_hash_map;

public class Node<K, V> {
    public int hash;
    public K key;
    public V value;
    public Node<K, V> next = null;

    public Node(int hash, K key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    public void appendToList(Node<K, V> a) {
        Node<K, V> current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = a;
    }
}
