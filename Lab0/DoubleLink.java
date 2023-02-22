import java.io.*;
import java.util.*;

public class DoubleLink {
  private class Node {
    int n;
    Node prev, next;

    public Node(int n) {
      this.n = n;
    }
  }

  Node head, tail = null;

  public void add(int num) {
    Node p = new Node(num);
    p.n = num;
    p.next = null;

    if (head == null) {
      head = p;
      tail = p;
      head.prev = null;
      return;
    }

    if (p.n < head.n) {
      p.prev = null;
      head.prev = p;
      p.next = head;
      head = p;
      return;
    }

    if (p.n > tail.n) {
      p.prev = tail;
      tail.next = p;
      tail = p;
      return;
    }

    Node temp = head.next;
    while (temp.n < p.n)
      temp = temp.next;

    (temp.prev).next = p;
    p.prev = temp.prev;
    temp.prev = p;
    p.next = temp;
  }

  public void print() {

    Node current = head;
    if (head == null) {
      System.out.println("List is empty");
      return;
    }
    // System.out.println("Nodes of doubly linked list: ");
    while (current != null) {
      System.out.print(current.n + " ");
      current = current.next;
    }
    System.out.println();
  }

  public void printBackwards() {
    Node current = head;
    int i = size(current);
    int j = i;
    if (head == null) {
      System.out.println("List is empty");
    } else {
      while (i != 0) {
        while (j != 1) {
          current = current.next;
          j = j - 1;
        }
        System.out.print(current.n + " ");
        i = i - 1;
        j = i;
        current = head;
      }
      System.out.println();
    }
  }

  public int size(Node n) {
    if (n == null) {
      return 0;
    } else {
      return 1 + size(n.next);
    }
  }

  public void deletenth(int n) {
    if (head == null) {
      return;
    } else {
      Node current = head;
      int pos = n;
      for (int i = 1; i < pos; i++) {
        current = current.next;
      }

      if (current == head) {
        head = current.next;
      } else if (current == tail) {
        tail = tail.prev;
      } else {
        current.prev.next = current.next;
        current.next.prev = current.prev;
      }
      current = null;
    }
  }

  public boolean delete(int num1) {
    try {
      boolean nil = true;
      if (head.n == num1) {
        head = head.next;
        nil = true;
      } else {
        Node current = null;
        for (current = head; current.next.n != num1; current = current.next) {
        }
        current.next = current.next.next;
      }
      return nil;
    } catch (NullPointerException e) {
      return false;
    }
  }

  public static void main(String args[]) {
    DoubleLink list = new DoubleLink();
    list.add(5);
    list.add(9);
    list.add(7);
    list.add(3);
    list.add(8);
    list.add(10);
    list.add(4);
    list.add(6);
    System.out.println("Call print, should get 3,4,5,6,7,8,9,10");
    list.print();
    System.out.println("Call printBackwards, should get 10,9,8,7,6,5,4,3");
    list.printBackwards();
    list.delete(8);
    list.delete(4);
    System.out.println("Call print, should get 3,5,6,7,9,10");
    list.print();
    list.delete(3);
    list.delete(10);
    System.out.println("Call print, should get 5,6,7,9");
    list.print();
    list.delete(6);
    list.delete(6);
    list.delete(7);
    list.delete(9);
    list.delete(5);
    list.delete(5);
    System.out.println("Call print, should be empty");
    list.print();
    System.out.println("Call printBackwards, should be empty");
    list.printBackwards();
    list.add(55);
    System.out.println("Call print, should get 55");
    list.print();
  }
}
