package hausaufgabe9;

/**
 * Die Klasse IO enthaelt Hilfsprogramme zum Einlesen.
 */

import java.util.Scanner;

public class IO {

	@SuppressWarnings("resource")
	public static String readString(String msg) {
		System.out.print(msg);
		return (new Scanner(System.in)).nextLine();
	}

	@SuppressWarnings("resource")
	public static int readInt(String msg, int low, int high) {
		int result;
		do {
			System.out.print(msg);
			result = (new Scanner(System.in)).nextInt();
		} while (result < low || result > high);
		return result;
	}

	@SuppressWarnings("resource")
	public static int readInt() {
		return (new Scanner(System.in)).nextInt();
	}

	public static int readInt(String msg) {
		System.out.print(msg);
		return readInt();
	}

}
