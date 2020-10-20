package tutorium8;

public class Queue {
	int first, last;
	int[] arr;

	// TODO instance variables, methods, ...
	public static void main(String[] args) {
		Queue q = new Queue();
		for (int i = 0; i < 10; i++) {
			System.out.println(q);
			q.enqueue(i);
		}
		System.out.println("\n" + q + "\n");
		for (int i = 0; i < 10; i++) {
			q.dequeue();
			System.out.println(q);
		}
	}

	public Queue() {
		arr = new int[2];
		first = last = -1;
	}

	private int numberOfElements() {
		if (first == -1)
			return 0;
		if (first == last)
			return 1;
		if (first < last)
			return (last - first + 1);
		return (last + 1 + arr.length - first);
	}

	private void increase() {
		int number = numberOfElements();
		if (number == arr.length) {
			int[] arrhelp = new int[arr.length * 2];
			int k = first;
			for (int i = 0; i < arr.length; i++) {
				arrhelp[i] = arr[k];
				if (k < arr.length - 1)
					k++;
				else
					k = 0;
			}
			arr = arrhelp;
			first = 0;
			last = number - 1;
		}
	}

	public void enqueue(int x) {
		increase();
		if (first == -1) {
			first = last = 0;
			arr[0] = x;
			return;
		}
		if (last == arr.length - 1) {
			last = 0;
			arr[last] = x;
			return;
		}
		arr[last + 1] = x;
		last = last + 1;
	}

	private void decrease() {
		int number = numberOfElements();
		if (number <= arr.length / 4) {
			int[] help = new int[arr.length / 2];
			int k = first;
			for (int i = 0; i < number; i++) {
				help[i] = arr[k];
				if (k < arr.length - 1)
					k++;
				else
					k = 0;
			}
			arr = help;
			first = 0;
			last = number - 1;
		}
	}

	public void dequeue() {
		if (numberOfElements() == 1 || numberOfElements() == 0) {
			first = last = -1;
			arr = new int[2];
			return;
		}
		first = (first == arr.length - 1) ? 0 : (first + 1);
		decrease();
	}

	public boolean isEmpty() {
		return first == -1 ? true : false;
	}

	@Override
	public String toString() {

		String out = "";
		for (int i = 0; i < arr.length; i++) {
			if (first <= last && (i < first || i > last))
				out += " *";
			if (first <= last && i > first && i < last)
				out += " " + arr[i];
			if (first > last && i > last && i < first)
				out += " *";
			if (first > last && (i > first || i < last))
				out += " " + arr[i];
			if (i == first)
				out = out + " (" + arr[i];
			if (i == last)
				out += (first == last ? "" : " " + arr[i]) + ")";
		}
		return out;
	}

}
