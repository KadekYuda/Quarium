package com.quarium.util;

public class ListException extends Exception {
  private int idMsg;
  private static final long serialVersionUID = 1L;

  private static String[] msg = {
    "Linked List is Empty.",
    "The element you are looking for is not available."};

  /**
   * Konstruktor ListException dengan indeks  n.
   * @param n Nilai idMsg
   */
  public ListException(int n) {
    idMsg = n;
  }

  /**
   * Copy Constructor ListException.
   * @param l ListException yang akan diacu
   */
  public ListException(ListException l) {
    idMsg = l.idMsg;
  }

  /**
   * Method untuk menampilkan pesan error.
   */
  public void displayMsg() {
    System.out.println(msg[idMsg]);
  }

  /**
   * Method untuk mengembalikan pesan error.
   * @return Pesan kesalahan
   */
  public String message() {
    return msg[idMsg];
  }
}
