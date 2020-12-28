package com.keksec.data_structures.custom_string_buffer;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.String.*;

abstract public class MyAbstractStringBuilder implements CharSequence {

  byte[] value;

  int count;

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


  MyAbstractStringBuilder(int capacity) {
      value = new byte[capacity];
  }

  MyAbstractStringBuilder(String str) {
    int length = str.length();
    int capacity = (length < Integer.MAX_VALUE - 16)
        ? length + 16 : Integer.MAX_VALUE;
    value = new byte[capacity];
    append(str);
  }

  @Override
  public int length() {
    return count;
  }

  @Override
  public char charAt(int index) {
    return 0;
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return value.toString().substring(start, end);
  }

  @Override
  public IntStream chars() {
    //this custom AbstractStringBuilder does not support this feature
    return null;
  }

  @Override
  public IntStream codePoints() {
    return null;
  }

  @Override
  public abstract String toString();

  public MyAbstractStringBuilder append(String str) {
    if (str == null) {
      throw new NullPointerException();
    }
    int len = str.length();
    ensureCapacityInternal(count + len);
    putStringAt(count, str);
    count += len;
    return this;
  }

  public MyAbstractStringBuilder append(Object obj) {
    return append(String.valueOf(obj));
  }

  private void ensureCapacityInternal(int minimumCapacity) {
    // overflow-conscious code
    int oldCapacity = value.length;
    if (minimumCapacity - oldCapacity > 0) {
      value = Arrays.copyOf(value, newCapacity(minimumCapacity));
    }
  }

  private int newCapacity(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = value.length;
    int newCapacity = (oldCapacity << 1) + 2;
    if (newCapacity - minCapacity < 0) {
      newCapacity = minCapacity;
    }
    return (newCapacity <= 0 || MAX_ARRAY_SIZE - newCapacity < 0)
        ? hugeCapacity(minCapacity)
        : newCapacity;
  }

  private int hugeCapacity(int minCapacity) {
    int UNSAFE_BOUND = Integer.MAX_VALUE;
    if (UNSAFE_BOUND - minCapacity < 0) { // overflow
      throw new OutOfMemoryError();
    }
    return Math.max(minCapacity, MAX_ARRAY_SIZE);
  }

  private void putStringAt(int index, String str) {
    System.arraycopy(str.getBytes(), 0, value, index, str.getBytes().length);
  }

}
