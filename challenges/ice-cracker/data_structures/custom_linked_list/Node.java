package com.keksec.data_structures.custom_linked_list;

import com.keksec.data_structures.custom_hash_map.MyHashMap;

import java.util.Hashtable;

public class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value) {
        this.value = value;
        this.next = null;
    }

    public void appendToList(Node<V> a) {
       Node<V> current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = a;
    }

    public void addToStart(Node<V> a) {
        a.next = this;
    }

    //Task 1: Remove duplicates in linked list(solution takes O(n) time and temporary holder)
    public void removeDuplicatesWithBuffer() {
        MyHashMap<V, Boolean> hashmap = new MyHashMap<>();
        Node<V> previous = null;
        Node<V> current = this;
        while (current != null) {
            if (hashmap.containsKey(current.value)) {
                previous.next = current.next;
            } else {
                hashmap.put(current.value, true);
                previous = current;
            }
            current = current.next;
        }
    }

    //Task 1: Remove duplicates in linked list(solution takes O(n^2) time and no temporary holder)
    public void removeDuplicatesNoBuffer() {
        Node<V> current = this;
        while (current != null) {
            Node<V> runner = current;
            while (runner.next != null) {
                if (runner.next.value == current.value) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    //Task 2: Get element which has kth position to last
    public Node<V> getKthToLast(int k) {
        Node<V> p1 = this, p2 = this;
        for (int i = 0; i < k - 1; i++) {
            if (p2 == null) {
                return null;
            }
            p2 = p2.next;
        }
        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;
    }

    //Task 2: print element which has kth position to last RECURSIVELY
    public int getKthToLastRecursively(Node<V> p, int k) {
        if (p == null) {
            return 0;
        }
        int i = getKthToLastRecursively(p.next, k) + 1;
        if (i == k) {
            System.out.println(p.value);
        }
        return i;
    }

    //Task 3: delete node having no access to previous
    public boolean deleteNode(Node<V> n) {
        if (n != null && n.next != null) {
            n.value = n.next.value;
            n.next = n.next.next;
            return true;
        } else {
            return false;
        }
    }

    //Task 4: partition all elements in linked list around a value x
    public static Node<Integer> partition(Node<Integer> start, int threshold) {
        Node<Integer> less = null;
        for (Node<Integer> e = start; e.next != null; e = e.next) {
            if (e.next.value < threshold) {
                if (less == null) {
                    less = e.next;
                    e.next = e.next.next;
                    less.next = null;
                } else {
                    Node<Integer> temp = e.next;
                    e.next = e.next.next;
                    temp.next = null;
                    less.appendToList(temp);
                }
            } else if (e == start && e.value < threshold) {
                less = e;
                less.next = null;
            }
        }
        assert less != null;
        less.appendToList(start);
        return less;
    }

    //Task 5(Iterative): Write function that gets two linked list looking like 1 -> 2 -> 3, 4 -> 5 -> 6 and returns sum of these lists in reversed order
    //Example: With (1 -> 2 -> 3) + (4 -> 5 -> 6) function returns 9 -> 7 -> 5(321 + 654)
    public static Node<Integer> reverseSum(Node<Integer> a, Node<Integer> b) {
        Node<Integer> start;
        int remainder = 0;
        if (a.value + b.value > 0) {
            start = new Node<>(a.value + b.value - 10);
            remainder = 1;
        } else {
            start = new Node<>(a.value + b.value);
        }
        a = a.next;
        b = b.next;
        Node<Integer> current = start;
        while (a != null &&  b != null) {
            current.next = new Node<>(remainder);
            int sum = a.value + b.value;
            if (sum + current.next.value >= 10) {
                remainder = 1;
                current.next.value = sum + current.next.value % 10;
            } else {
                remainder = 0;
                current.next.value += sum;
            }
            a = a.next;
            b = b.next;
            current = current.next;
        }
        if (remainder > 0) current.next = new Node<>(remainder);
        return start;
    }

    //Task 5(Recursive): Write function that gets two linked list looking like 1 -> 2 -> 3, 4 -> 5 -> 6 and returns sum of these lists in reversed order
    //Example: With (1 -> 2 -> 3) + (4 -> 5 -> 6) function returns 9 -> 7 -> 5(321 + 654)
    //TODO: Добавить поддержку сложения листов по типу 1 -> 2 -> 3 -> 4 + 6 -> 5 ->9
    public static Node<Integer> recursiveReverseSum(Node<Integer> a, Node<Integer> b, int remainder) {
        if (a != null && b != null) {
            int sum = a.value + b.value;
            if (sum + remainder >= 10) {
                a.value = (sum + remainder) % 10;
                a.next = recursiveReverseSum(a.next, b.next, 1);
            } else {
                a.value = sum + remainder;
                a.next = recursiveReverseSum(a.next, b.next, 0);
            }
        } else {
            if (remainder > 0) return new Node<>(remainder);
            else return null;
        }
        return a;
    }

    //TODO: Добавить поддержку сложения листов по типу 1 -> 2 -> 3 -> 4 + 6 -> 5 -> 9 и ориг порядка сложения
    //Task 5(Recursive): Write function that gets two linked list looking like 1 -> 2 -> 3, 4 -> 5 -> 6 and returns sum of these lists in order
    //Example: With (1 -> 2 -> 3) + (4 -> 5 -> 6) function returns 5 -> 7 -> 9(123 + 456)
    public static Node<Integer> recursiveSum(Node<Integer> a, Node<Integer> b, int  remainder) {
        //TODO
        return null;
    }
}

