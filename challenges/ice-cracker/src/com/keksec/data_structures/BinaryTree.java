package com.keksec.data_structures;

public class BinaryTree {
  BinaryTree left;
  BinaryTree right;
  int value;
  ListNode node;

  public BinaryTree(BinaryTree left, BinaryTree right, int value) {
    this.left = left;
    this.right = right;
    this.value = value;
  }

  public BinaryTree(int value) {
    this.value = value;
  }

  public BinaryTree() {
  }

  public static void append(BinaryTree start, ListNode node) {
    append(start, new BinaryTree(node.val));
  }


  public static void append(BinaryTree start, BinaryTree node) {
    if (start == null) throw new NullPointerException("Start node can not be null");
    if (node.value < start.value) {
      if (start.left == null) {
        start.left = node;
      } else {
        append(start.left, node);
      }
    } else {
      if (start.right == null) {
        start.right = node;
      } else {
        append(start.right, node);
      }
    }
  }

  private void transform(BinaryTree tree) {
    if (tree.left != null) {
      transform(tree.left);
    }
    if (node != null) node.appendTreeElement(tree);
    else node = new ListNode(tree.value);
    if (tree.right != null) {
      transform(tree.right);
    }
  }

  public ListNode transformTree(BinaryTree tree) {
    transform(tree);
    return node.next;
  }
}
