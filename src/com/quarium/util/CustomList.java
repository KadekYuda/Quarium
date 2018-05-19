package com.quarium.util;

class CustomList<T> {
  private Node<T> head;
  private final int linkedListEmpty = 0;
  private final int linkedListNotFound = 1;

  /**
   * Konstruktor CustomList default.
   */
  public CustomList() {
    head = null;
  }

  /**
   * Konstruktor CustomList non-default dengan parameter val bertipe int.
   * @param val Objek yang dimasukkan ke CustomList
   */
  public CustomList(T val) {
    head = new Node<T>();
    head.setValue(val);
    head.setNextNode(null);
  }

  /**
   * Cctor CustomList dengan parameter samplelist yang bertipe CustomList.
   * Melakukan penyalinan terhadap setiap atribut head yang bertipe Nod
   * dan atribut next yang bertipe Node.
   * @param samplelist list yang akan diacu
   */
  public CustomList(CustomList<T> samplelist) {
    if (!samplelist.isEmpty()) {
      head = new Node<T>();
      head.setValue(samplelist.head.getValue());
      Node<T> n1 = head;
      Node<T> n2 = samplelist.head.getNextNode();
      while (n2 != null) {
        n1.setNextNode(new Node<T>());
        n1 = n1.getNextNode();
        n1.setValue(n2.getValue());
        n2 = n2.getNextNode();
      }
      n1.setNextNode(null);
    } else {
      head = null;
    }
  }

  /**
   * Cctor CustomList dengan parameter elmt yang bertipe T.
   * @param elmt - objek yang akan dicari dalam CustomList
   * @return nilai -1 jika tidak ditemukan di dalam CustomList; dan
   *         nilai idx jika ditemukan pada CustomList
   */
  public int find(T elmt) {
    Node<T> curr = head;
    boolean found = false;
    int idx = 0;
    while (curr != null && !found) {
      if (elmt.equals(curr.getValue())) {
        found = true;
      } else {
        idx++;
        curr = curr.getNextNode();
      }
    }
    if (!found) {
      return -1;
    } else {
      return idx;
    }
  }

  /**
   * Getter head dari CustomList.
   * @return head dari CustomList
   */
  public Node<T> getHead() {
    return head;
  }

  /**
   * Setter head dari CustomList dengan val.
   * @param val head dari CustomList
   */
  public void setHead(Node<T> val) {
    head.setValue(val.getValue());
    head.setNextNode(val.getNextNode());
  }

  /**
   * Menentukan suatu CustomList apakah tidak memiliki elemen sama sekali.
   * @return nilai true jika head=null; dan
   *         nilai false jika head!=null.
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Menambah elemen dengan val.
   * Jika isEmpty() menambah val sebagai elemen pertama; dan
   *      !isEmpty() menambah val sebagai elemen terakhir.
   * @param val objek yang akan ditambah
   */
  public void add(T val) {
    if (isEmpty()) {
      Node<T> newElmt = new Node<T>();
      newElmt.setValue(val);
      newElmt.setNextNode(null);
      head = newElmt;
    } else {
      Node<T> curr = head;
      while (curr.getNextNode() != null) {
        curr = curr.getNextNode();
      }
      Node<T> newElmt = new Node<T>();
      newElmt.setValue(val);
      curr.setNextNode(newElmt);
    }
  }

  /**
   * Menghapus nilai pada CustomList dengan val bertipe T.
   * Mencari nilai dari val pada CustomList. Jike ketemu, val dihapus
   * @param val objek yang ingin dihapus
   * @throws ListException linkedNotFound jika IsEmpty(); dan
   *                       linkedNotFound jika linkedNotFound
   */
  public void remove(T val)throws ListException {
    if (!isEmpty()) {
      Node<T> prev = null;
      Node<T> curr = head;
      while (curr.getNextNode() != null && curr.getValue() != val) {
        prev = curr;
        curr = curr.getNextNode();
      }
      if (curr.getValue() == val) {
        if (prev == null) {
          prev = head;
          head = head.getNextNode();
          prev = null;
        } else {
          prev.setNextNode(curr.getNextNode());
          curr = null;
        }
      } else {
        throw new ListException(linkedListNotFound);
      }
    } else {
      throw new ListException(linkedListEmpty);
    }
  }

  /**
   * Mencari nilai pada indeks tertentu dalam CustomList dengan idx bertipe int.
   * @param idx indeks dari objek yang akan dicari
   * @return objek pada indeks tersebut yang bertipe T
   * @throws ListException linkedNotFound jika IsEmpty(); dan
   *                       linkedNotFound jika linkedNotFound
   */
  public T get(int idx)throws ListException {
    if (isEmpty()) {
      throw new ListException(linkedListEmpty);
    } else {
      Node<T> curr = head;
      int n = 0;
      while (curr != null && n != idx) {
        curr = curr.getNextNode();
        n++;
      }
      if (n < idx) {
        throw new ListException(linkedListNotFound);
      } else {
        return curr.getValue();
      }
    }
  }

  /**
   * Menghitung jumlah elemen CustomList int.
   * @return 0 jika IsEmpty(); dan
   *         n jika !IsEmpty()
   */
  public int size() {
    if (isEmpty()) {
      return 0;
    } else {
      Node<T> curr = head;
      int count = 0;
      while (curr != null) {
        curr = curr.getNextNode();
        count++;
      }
      return count;
    }
  }

  /**
   * Melakukan assign parameter samplelist bertipe CustomList.
   * @param samplelist list yang akan di-assignkan
   * @return objek bertipe CustomList setelah reference ke head dari parameter samplelist
   *
   */
  public CustomList<T> assign(CustomList<T> samplelist) {
    head = samplelist.head;
    return this;
  }
}
