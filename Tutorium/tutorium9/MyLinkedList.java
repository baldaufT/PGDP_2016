package tutorium9;

import java.util.LinkedList;

public class MyLinkedList {

	int n;
	LinkedList<String> list;

	public static void main(String[] args) {
		MyLinkedList l = new MyLinkedList();
		for (int i = 0; i <= l.getN(); i++) {
			l.add(i + "");
		}
		System.out.println(l);
	}

	public String toString() {
		String out = "";
		for (String myString : list) {
			out = out + ", " + myString;
		}

		return out;
	}

	public int getN() {
		return n;
	}

	public void add(String s) {
		list.add(s);
	}

	private int readInt() {
		return readInt("Geben Sie eine positive Zahl ein: ");
	}

	private int readInt(String msg) {
		return readInt(msg, 0);
	}

	private int readInt(String msg, int low) {
		int input = -1;
		while (input < low) {
			System.out.print(msg);
			input = new java.util.Scanner(System.in).nextInt();
		}
		return input;
	}

	public MyLinkedList() {
		n = readInt();
		list = new LinkedList<String>();
	}

}
