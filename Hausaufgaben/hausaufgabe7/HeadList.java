package hausaufgabe7;

public class HeadList {

	Entry head;

	/**
	 * constructor empty HeadList
	 */
	HeadList() {
		head = null;
	}

	/**
	 * Appends a new element with value info to the end of this list
	 * 
	 * @param info
	 *            value of the new element
	 */
	public void add(int info) {
		if (head == null) {
			head = new Entry(head, null, info);
		} else {
			Entry tmp = head;
			while (tmp != null) {
				if (tmp.next != null) {
					tmp = tmp.next;
				} else {
					break;
				}
			}
			tmp.next = new Entry(head, null, info);
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
		if (index == 0) {
			int wert = head.elem;
			head = head.next;
			return wert;
		}
		Entry tmp = head;
		int anzahl = 0;
		while (tmp != null) {
			tmp = tmp.next;
			anzahl++;
		}
		if (index < 0 || index > anzahl - 1) {
			return Integer.MIN_VALUE;
		}
		tmp = head;
		anzahl = 0;
		while (anzahl < index - 1) {
			tmp = tmp.next;
			anzahl++;
		}
		int wert = tmp.next.elem;
		tmp.next = tmp.next.next;
		return wert;
	}

	/**
	 * sets the head of each list element to newHead
	 * 
	 * @param newHead
	 *            reference to the new head
	 */
	private void setHead(Entry newHead) {
		if (head == null) {
			add(newHead.elem);
			return;
		}
		Entry tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
			tmp.first = newHead;
		}
		head.first = newHead;
	}

	/**
	 * reverse the list example: [1,2,3,4,5] --> [5,4,3,2,1], [] --> [], [1] -->
	 * [1]
	 */
	public void reverse() {
		// leere Liste und Liste mit 1 wird ausgeschlossen
		// muss man noch testen und ausbauen
		if (head != null && head.next != null) {
			Entry tmp = head;
			while (tmp.next != null) {
				tmp = tmp.next;
			}
			setHead(tmp);

			Entry vor = null;
			Entry nach = null;
			tmp = head;
			while (tmp != null) {
				// hier muss die reihenfolge noch umgedreht werden;
				nach = tmp.next;
				tmp.next = vor;
				vor = tmp;
				tmp = nach;
			}

			// head aktualisieren
			head = head.first;
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
		HeadList l = new HeadList();
		System.out.println("empty list: " + l);
		// Test implementation
	}

	class Entry {

		Entry first;
		Entry next;
		int elem;

		public Entry(Entry first, Entry next, int elem) {
			this.first = first;
			this.next = next;
			this.elem = elem;
		}

	}

}
