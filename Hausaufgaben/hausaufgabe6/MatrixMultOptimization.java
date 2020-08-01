package hausaufgabe6;

public class MatrixMultOptimization {

	public static void main(String[] args) {
		System.out.println(f(new int[][] { { 10, 30 }, { 30, 5 }, { 5, 60 } }));
		System.out.println(f(new int[][] { { 60, 5 }, { 5, 30 }, { 30, 10 } }));
	}

	public static int f(int[][] mm) {
		return f(mm, 0, mm.length - 1);
	}

	public static int f(int[][] mm, int i, int j) {
		int[] liste = new int[3];
		for (int k = 0; k < mm.length; k++) {
			liste[i] = 999999999;
		}
		/*
		 * for (int k = 0; k < mm.length; k++) { liste[i] = helpallg(mm, 0,
		 * mm.length - 1, i); }
		 */
		liste[0] = helpvor(mm, 0, mm.length - 1, 0);
		liste[1] = helpallg(mm, 0, mm.length - 1, 999999999);
		liste[2] = helpmitte(mm, 0, mm.length - 1);

		return min(liste);

	}

	private static int min(int[] a) {
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min > a[i] && a[i] >= 0)
				min = a[i];
		}
		return min;
	}

	private static int helpvor(int[][] mm, int i, int j, int x) {
		if (i == j) {
			return 0;
		}
		int ergebnis = 0;
		ergebnis = helpvor(mm, i, x, x + 1) + helpvor(mm, x + 1, j, x + 1) + (mm[i][0] * mm[x][1] * mm[j][1]);

		return ergebnis;
	}

	private static int helpallg(int[][] mm, int i, int j, int ergebnis) {
		if (i == j) {
			return 0;
		}
		int zwischenergebnis = 99999999;
		for (int k = i; k < j; k++) {
			zwischenergebnis = helpallg(mm, i, k, ergebnis) + helpallg(mm, k + 1, j, ergebnis)
					+ (mm[i][0] * mm[k][1] * mm[j][1]);
			if (zwischenergebnis < ergebnis)
				ergebnis = zwischenergebnis;
		}
		return ergebnis;
	}

	private static int helpmitte(int[][] mm, int i, int j) {
		if (i == j) {
			return 0;
		}
		int ergebnis = 0;
		int x = i + (j - i) / 2;
		ergebnis = helpmitte(mm, i, x) + helpmitte(mm, x + 1, j) + (mm[i][0] * mm[x][1] * mm[j][1]);

		return ergebnis;
	}
}
