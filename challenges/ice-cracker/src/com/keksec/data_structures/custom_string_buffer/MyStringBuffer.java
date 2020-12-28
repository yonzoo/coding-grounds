package com.keksec.data_structures.custom_string_buffer;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyStringBuffer extends MyAbstractStringBuilder implements java.io.Serializable, Comparable<StringBuffer>, CharSequence  {

  private transient String toStringCache;

  public MyStringBuffer() {
    super(16);
  }

  public MyStringBuffer(int capacity) {
    super(capacity);
  }

  public MyStringBuffer(String str) {
    super(str);
  }

  @Override
  public synchronized MyStringBuffer append(String str) {
    toStringCache = null;
    super.append(str);
    return this;
  }

  @Override
  public int length() {
    return Arrays.toString(value).length();
  }

  @Override
  public char charAt(int index) {
    return 0;
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return null;
  }

  @Override
  public IntStream chars() {
    return null;
  }

  @Override
  public IntStream codePoints() {
    return null;
  }

  @Override
  public synchronized String toString() {
    if (toStringCache == null) {
      return toStringCache = new String(value);
    }
    return toStringCache;
  }

  @Override
  public int compareTo(StringBuffer o) {
    return 0;
  }
}
