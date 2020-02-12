package com.keksec.data_structures.custom_linked_list;

public class Example {

    public static void main(String[] args) {
        Node<Integer> startA = new Node<>(8);
        Node<Integer> currentA = startA;
        for (int i = 1; i < 4; i++) {
            Integer valA = (int)Math.floor(Math.random() * 9);
            currentA.next = new Node<>(valA);
            currentA = currentA.next;
        }

        for (Node<Integer> a = startA; a != null; a = a.next) {
            System.out.println(a.value);
        }
        System.out.println("-----------------------------------");

        startA.printReversed(startA, false);
    }
}