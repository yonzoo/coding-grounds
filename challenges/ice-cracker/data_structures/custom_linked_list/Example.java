package com.keksec.data_structures.custom_linked_list;

public class Example {

    public static void main(String[] args) {
//        Node<String> start = new Node<>("Lol");
//        Node<String> current = start;
//        for (int i = 0; i < 3; i++) {
//            current.next = new Node<>("HELLO");
//            current = current.next;
//        }
//        for (int i = 0; i < 1; i++) {
//            current.next = new Node<>("Bonjour");
//            current = current.next;
//        }
//        for (Node<String> e = start; e != null; e = e.next) {
//            System.out.println(e.value);
//        }
//        System.out.println(start.getKthToLast(2).value);


//        Node<Integer> start = new Node<>(1);
//        Node<Integer> current = start;
//        for (int i = 2; i < 25; i++) {
//            Integer val = (int)Math.floor(i * Math.random() * 10);
//            System.out.println(val);
//            current.next = new Node<>(val);
//            current = current.next;
//        }
//        for (Node<Integer> e = start; e != null; e = e.next) {
//            System.out.println(e.value + " ");
//        }
//
//        System.out.println("_____________PARTITION_______________");
//        Node<Integer> partition = Node.partition(start, 25);
//        for (Node<Integer> e = partition; e != null; e = e.next) {
//            System.out.println(e.value + " ");
//        }

        Node<Integer> startA = new Node<>(8);
        Node<Integer> startB = new Node<>(6);
        Node<Integer> currentA = startA;
        Node<Integer> currentB = startB;
        for (int i = 1; i < 4; i++) {
            Integer valA = (int)Math.floor(Math.random() * 9);
            Integer valB = (int)Math.floor(Math.random() * 9);
            currentA.next = new Node<>(valA);
            currentB.next = new Node<>(valB);
            currentA = currentA.next;
            currentB = currentB.next;
        }
        for (Node<Integer> e = startA; e != null; e = e.next) {
            System.out.print(e.value + " ");
        }
        System.out.println();
        System.out.println("_____________PARTITION_______________");
        for (Node<Integer> e = startB; e != null; e = e.next) {
            System.out.print(e.value + " ");
        }
        System.out.println();
        System.out.println("_____________RESULT_______________");
        Node<Integer> res = Node.recursiveReverseSum(startA, startB, 0);
        for (Node<Integer> e = res; e != null; e = e.next) {
            System.out.print(e.value + " ");
        }
    }
}