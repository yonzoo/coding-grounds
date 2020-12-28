package com.keksec.data_structures.custom_binary_tree;

public class BinaryTree {
  BinaryTree left;
  BinaryTree right;
  Integer value;

  public BinaryTree(Integer value) {
    this.value = value;
  }

  public BinaryTree getLeft() {
    return left;
  }

  public void setLeft(BinaryTree left) {
    this.left = left;
  }

  public BinaryTree getRight() {
    return right;
  }

  public void setRight(BinaryTree right) {
    this.right = right;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public void printTree(Integer depthLevel) {
    if (left != null) {
      left.printTree(depthLevel + 1);
    }
    System.out.println(value + " : " + depthLevel.toString());
    if (right != null) {
      right.printTree(depthLevel + 1);
    }
  }

  public static BinaryTree fillRandomNumbers() {
    int randomMax = (int) (Math.random() * 5);
    BinaryTree start = new BinaryTree((int) (Math.random() * 50));
    makeTree(start, randomMax);
    return start;
  }

  private static void makeTree(BinaryTree current, int depth) {
    if (depth == 0) {
      return;
    }
    BinaryTree left = new BinaryTree((int) (Math.random() * 50));
    BinaryTree right = new BinaryTree((int) (Math.random() * 50));
    current.left = left;
    current.right = right;
    makeTree(current.left, depth - 1);
    makeTree(current.right, depth - 1);
  }

  public void insert(BinaryTree node, Integer value) {
    if (node.left != null && node.right != null) {
      if (value <= node.left.value) {
        insert(node.left, value);
      } else {
        insert(node.right, value);
      }
    } else {
      if (value <= node.value) {
        node.left = new BinaryTree(value);
      } else {
        node.right = new BinaryTree(value);
      }
    }
  }
}
