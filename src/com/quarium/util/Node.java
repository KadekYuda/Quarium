package com.quarium.util;

public class Node<T> {
  private T value;
  private Node<T> next;

  public T getValue() {
    return value;
  }

  public Node<T> getNextNode() {
    return next;
  }

  public void setValue(T val) {
    value = val;
  }

  public void setNextNode(Node<T> next) {
    this.next = next;
  }
}