package tutorium7;

public class DoublyLinkedList {

	Entry head;
	int size;

	/**
	 * constructor empty DoublyLinkedList
	 */
	public DoublyLinkedList() {
		head = null;
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return number of elements in this list
	 */
	public int size() {
		Entry tmp = head;
		int anzahl = 0;
		while (tmp != null) {
			anzahl++;
			tmp = tmp.next;
		}
		return anzahl;
	}

	/**
	 * Appends a new element with value info to the end of this list
	 * 
	 * @param info
	 *            value of the new element
	 */
	public void add(int info) {
		if (head != null)
			addhelp(info, head);
		else {
			head = new Entry(null, null, info);
		}
	}

	private void addhelp(int info, Entry tmp) {
		if (tmp.next == null)
			tmp.next = new Entry(tmp, null, info);
		else
			addhelp(info, tmp.next);
	}

	/**
	 * Inserts a new element with value info at the specified position in this
	 * list.
	 * 
	 * @param index
	 *            position where the element is inserted, from 0 ...
	 *            list.size()-1
	 * @param info
	 *            value of the new element
	 */
	public void add(int index, int info) {
		if (head == null) {
			head = new Entry(null, null, info);
			System.out.println("Ihr neues Element wurde auf Head gesetzt!");
		}
		if (index < 0 || index > size() - 1) {
			System.out.println("Ihr Index kann nicht eingeordnet werden!");
		} else if ((head != null && head.next == null) || index == 0) {
			head.prev = new Entry(null, head, info);
			head = head.prev;
		} else {
			Entry tmp = head;
			for (int i = 0; i < index; i++) {
				tmp = tmp.next;
			} // TODO funktioniert nicht sauber
			tmp.prev.next = new Entry(tmp.prev, tmp, info);
			tmp.next.prev = tmp;
		}
	}

	/**
	 * Removes and returns the element at position index from this list.
	 * 
	 * @param index
	 *            position of the element that is removed
	 * @return value of the removed element
	 */
	public int remove(int index) {
		if (index < 0 || index > size() - 1) {
			return Integer.MIN_VALUE;
		} else {
			Entry tmp = head;
			for (int i = 1; i < index; i++) {
				tmp = tmp.next;
			}
			int ergebnis = tmp.next.elem;
			tmp.next = tmp.next.next;
			tmp.next.prev = tmp;
			return ergebnis;
		}
	}

	/**
	 * shifts the list the specified number of positions to the left example:
	 * [1,5,6,7] ---shift(2)---> [6,7,1,5]
	 * 
	 * @param index
	 *            number of position to shift, from 0 to size-1
	 */
	public void shiftLeft(int index) {
		if (index > 0 && index < size() - 1) {
			Entry tmp = head;
			while (tmp.next != null) {
				tmp = tmp.next;
			}
			tmp.next = head;
			head.prev = tmp;
			tmp = head;
			for (int i = 0; i < index; i++) {
				tmp = tmp.next;
			}
			head = tmp;

			head.prev.next = null;
			head.prev = null;
		}
	}

	@Override
	public String toString() {
		String out = "[";
		if (head != null) {
			out += head.elem;
			Entry tmp = head.next;
			while (tmp != null) {
				out = out + "," + tmp.elem;
				tmp = tmp.next;
			}
		}
		out += "]";
		return out;
	}

	public static void main(String[] args) {
		DoublyLinkedList dl = new DoublyLinkedList();
		dl.add(1);
		System.out.println(dl);
		dl.add(0, 0);
		System.out.println(dl);
		dl.add(3);
		dl.add(4);
		System.out.println(dl);
		dl.add(2, 2);
		System.out.println(dl + "\n\n");
		for (int i = 0; i < 5; i++) {
			dl.shiftLeft(i % dl.size());
			System.out.println(dl);
		}
	}

	class Entry {

		Entry prev;
		Entry next;
		int elem;

		public Entry(Entry prev, Entry next, int elem) {
			this.prev = prev;
			this.next = next;
			this.elem = elem;
		}

	}
}
