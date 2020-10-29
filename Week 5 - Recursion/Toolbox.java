package hausaufgabe6;

public class Toolbox {

	public static int evenSum(int a) {
		if (zahlungerade(a) == false)
			return hilf(a);
		else {
			if (a >= 0)
				return hilf(a - 1);
			else
				return hilf(a + 1);
		}
	}

	private static int hilf(int n) {
		int ergebnis = 0;
		if (n > 0) {
			ergebnis = n + hilf(n - 2);
		} else if (n < 0) {
			ergebnis = n + hilf(n + 2);
		}
		return ergebnis;
	}

	public static boolean zahlungerade(int n) {
		String zahlstr = n + "";
		int zahltr = zahlstr.charAt(zahlstr.length() - 1);
		char zahl = (char) zahltr;
		if (zahl == '1' || zahl == '3' || zahl == '5' || zahl == '7' || zahl == '9') {
			return true;
		}
		return false;
	}

	public static int multiplication(int x, int y) {
		int ergebnis = 0;
		if ((x > 0 && y > 0) || (x < 0 && y < 0)) {
			if (x < 0) {
				x = -x;
				y = -y;
			}
			ergebnis = x + multiplication(x, y - 1);
		} else if ((x < 0 && y > 0) || (x > 0 && y < 0)) {
			if (x < 0)
				x = -x;
			if (y < 0)
				y = -y;
			ergebnis = x + multiplication(x, y - 1);
			ergebnis = -ergebnis;
		}
		return ergebnis;
	}

	public static void reverse(int[] m) {
		reverse(m, m.length);
	}

	private static void reverse(int[] m, int l) {
		if (l > m.length / 2) {
			int help = m[m.length - l];
			m[m.length - l] = m[l - 1];
			m[l - 1] = help;
			reverse(m, l - 1);
		}
	}

	public static int numberOfOddIntegers(int[] m) {
		return numberOfOddIntegers(m, m.length);
	}

	private static int numberOfOddIntegers(int[] m, int l) {
		int ergebnis = 0;
		if (l > 0) {
			if ((zahlungerade(m[l - 1])) == true) {
				ergebnis = 1;
			}
			ergebnis = ergebnis + numberOfOddIntegers(m, l - 1);
		}
		return ergebnis;
	}

	public static int[] filterOdd(int[] m) {
		int[] ergebnis = new int[numberOfOddIntegers(m)];
		return filterOdd(m, 0, 0, numberOfOddIntegers(m), ergebnis);
	}

	private static int[] filterOdd(int[] m, int u, int um, int anzahlungerade, int[] ergebnis) {
		if (u >= anzahlungerade)
			return ergebnis;
		int[] g = help(m, um);
		int h = g[0];
		ergebnis[u] = h;
		um = g[1] + 1;
		return filterOdd(m, u + 1, um, anzahlungerade, ergebnis);
	}

	private static int[] help(int[] m, int u) {
		int o = m.length - 1;
		int[] ergebnis = new int[] { 2, u };
		if (u > o)
			return ergebnis;
		if (zahlungerade(m[u]) == true) {
			ergebnis[0] = m[u];
			return ergebnis;
		}
		return help(m, u + 1);
	}
}
