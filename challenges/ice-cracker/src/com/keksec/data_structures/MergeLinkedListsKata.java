package com.keksec.data_structures;

import java.util.Arrays;

public class MergeLinkedListsKata {

  public static void mergeKLists(ListNode[] listNodes) {
    BinaryTree binaryTree = new BinaryTree();
    Arrays.stream(listNodes).forEach(listNode -> {
      ListNode current = listNode;
      while (current != null) {
        BinaryTree.append(binaryTree, current);
        current = current.next;
      }
    });
    ListNode current = binaryTree.transformTree(binaryTree);
    while (current != null) {
      System.out.println(current.val);
      current = current.next;
    }
  }

  public static void main(String[] args) {
    ListNode one = new ListNode(0);
    one.next = new ListNode(1);
    one.next.next = new ListNode(3);

    ListNode oneA = new ListNode(9);
    oneA.next = new ListNode(7);
    oneA.next.next = new ListNode(11);

    ListNode oneB = new ListNode(424);
    oneB.next = new ListNode(15);
    oneB.next.next = new ListNode(113);

    ListNode[] listNodes = {oneB, oneA, one};
    mergeKLists(listNodes);
  }
}
