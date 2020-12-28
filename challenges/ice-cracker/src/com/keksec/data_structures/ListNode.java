package com.keksec.data_structures;


public class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public ListNode appendTreeElement(BinaryTree binaryTree) {
    ListNode current = this;
    while (current.next != null) {
      current = current.next;
    }
    current.next = new ListNode(binaryTree.value);
    return this;
  }

  public ListNode appendNode(ListNode listNode) {
    ListNode current = this;
    while (current.next != null) {
      current = current.next;
    }
    current.next = listNode;
    return this;
  }
}