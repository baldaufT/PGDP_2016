package hausaufgabe7;

public class Tailrec1 {

	// x^y
	public static int pow(int x, int y) {
		return java.math.BigInteger.valueOf(x).pow(y).intValueExact();
	}

	// function f recursive
	public static int frec(int x) {
		return grec(x, 0);
	}

	// helper function g recursive
	public static int grec(int x, int pos) {
		if (x / 10 == 0) {
			return pow(x, pos);
		}
		return pow(x % 10, pos) + grec(x / 10, ++pos);
	}

	// function f tail recursive
	public static int ftailrec(int x) {
		return gtailrechelper(x, 0, 0);
	}

	private static int gtailrechelper(int x, int k, int ergebnis) {
		if (x / 10 == 0)
			return ergebnis + pow(x, k);
		else {
			return gtailrechelper(x / 10, k + 1, ergebnis + pow(x % 10, k));
		}
	}

	public static void main(String[] args) {
		int n = 512345;

		System.out.println("f recursive: " + frec(n));
		System.out.println("f tailrec: " + ftailrec(n));
	}

}
