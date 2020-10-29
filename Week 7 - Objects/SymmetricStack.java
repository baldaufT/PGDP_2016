package hausaufgabe8;

public class SymmetricStack {

	private int[] data;
	private int first;
	private int last;

	public SymmetricStack() {
		last = first = -1;
		data = new int[4];
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public int getNumberOfElements() {
		if (first == -1)
			return 0;
		if (last > first)
			return last - (first - 1);
		else if (last == first)
			return 1;
		else {
			return (last + 1) + (data.length - first);
		}
	}

	public void increase() {
		if (isFull() == true) {
			int[] help = new int[data.length];
			int anzahl = getNumberOfElements();
			int n = first;
			for (int i = 0; i < help.length; i++) {
				help[i] = data[n];
				if (n == data.length - 1)
					n = 0;
				else
					n = n + 1;
			}
			data = new int[data.length * 2];
			first = help.length / 2;
			last = first + anzahl - 1;
			for (int i = 0; i < anzahl; i++) {
				data[i + first] = help[i];
			}
		}
	}

	public void decrease() {
		if (data.length / 4 >= getNumberOfElements() && data.length / 4 > 1) {
			int[] help = new int[getNumberOfElements()];
			int n = first, anzahl = getNumberOfElements();
			for (int i = 0; i < getNumberOfElements(); i++) {
				help[i] = data[n];
				if (n == data.length - 1)
					n = 0;
				else
					n = n + 1;
			}
			data = new int[data.length / 2];
			first = data.length / 8;
			last = first + anzahl - 1;
			for (int i = 0; i < anzahl; i++) {
				data[i + first] = help[i];
			}
		}
	}

	public boolean isEmpty() {
		if (first == -1)
			return true;
		return false;
	}

	public boolean isFull() {
		if (getNumberOfElements() == data.length)
			return true;
		return false;
	}

	public void prepend(int x) {
		if (isEmpty() == true) {
			data[data.length / 2] = x;
			last = first = data.length / 2;
			return;
		}
		if (isFull() == true)
			increase();
		if (first > 0) {
			first = first - 1;
			data[first] = x;
		} else if (first == 0) {
			first = data.length - 1;
			data[first] = x;
		}
	}

	public void append(int x) {
		if (isEmpty() == true) {
			data[data.length / 2] = x;
			last = first = data.length / 2;
			return;
		}
		if (isFull() == true)
			increase();
		if (last < data.length - 1) {
			last = last + 1;
			data[last] = x;
		} else if (last == data.length - 1) {
			last = 0;
			data[last] = x;
		}
	}

	public void removeFirst() {
		if (getNumberOfElements() == 1)
			first = last = -1;
		else {
			if (first < data.length - 1)
				first = first + 1;
			else
				first = 0;
		}
		decrease();
	}

	public void removeLast() {
		if (getNumberOfElements() == 1)
			first = last = -1;
		else {
			if (last > 0)
				last = last - 1;
			else
				last = data.length - 1;
		}
		decrease();
	}

	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < data.length; i++) {
			if (first <= last && (i < first || i > last))
				out += "* ";
			if (first <= last && i > first && i < last)
				out += " " + data[i];
			if (first > last && i > last && i < first)
				out += "* ";
			if (first > last && (i > first || i < last))
				out += " " + data[i];
			if (i == first)
				out = out + "(" + data[i];
			if (i == last)
				out += (first == last ? "" : " " + data[i]) + ")";
		}
		return out;
	}
}
