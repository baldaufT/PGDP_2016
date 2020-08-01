package tutorium5;

public class Vektormultiplikation {

	public static void main(String[] args) {
		int[][] a = new int[][] { { 10, 30 }, { 30, 5 }, { 5, 60 } };
		int[] b = new int[] { 1, 2 };
		Arrays.print(matvecmul(a, b));
		Arrays.print(a[0]);
		Arrays.print(a[1]);
		Arrays.print(a[2]);

		int[][] c = new int[a[0].length][a.length];
		c = transpose(a);
		Arrays.print(c[0]);
		Arrays.print(c[1]);
		int[][] h = new int[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } };
		int[][] d = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		matmatmult(h, d);
	}

	public static int vecvecmul(int[] a, int[] b) {
		int ergebnis = 0;
		for (int i = 0; i < a.length; i++) {
			ergebnis = ergebnis + (a[i] * b[i]);
		}
		return ergebnis;
	}

	public static int[] matvecmul(int[][] a, int[] b) {
		int[] ergebnis = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			ergebnis[i] = vecvecmul(a[i], b);
		}
		return ergebnis;
	}

	public static int[][] transpose(int[][] a) {
		int[][] ergebnis = new int[a[0].length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[0].length; k++) {
				if (k > i) {
					ergebnis[i][k] = a[k][i];
					ergebnis[k][i] = a[i][k];
				}
				if (k == i) {
					ergebnis[i][k] = a[i][k];
				}
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[0].length; k++) {
				ergebnis[k][i] = a[i][k];
			}
		}
		return ergebnis;
	}

	public static int[][] matmatmult(int[][] a, int[][] b) {
		int[][] ergebnis = new int[a.length][b[0].length];
		int[] help = new int[b.length];
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < b[0].length; k++) {
				for (int g = 0; g < b.length; g++) {
					help[g] = b[g][k];
				}
				ergebnis[i][k] = vecvecmul(a[i], help);
			}
		}
		Arrays.print(ergebnis[0]);
		Arrays.print(ergebnis[1]);
		Arrays.print(ergebnis[2]);

		return ergebnis;
	}
}
