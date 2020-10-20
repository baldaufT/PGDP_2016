package hausaufgabe8;

public class MatrixMultOptMemoization {
	private static int[][] liste;

	public static int f(int[][] mm) {
		liste = new int[mm.length][mm.length];
		for (int i = 0; i < liste.length; i++) {
			for (int k = 0; k < liste[0].length; k++) {
				liste[i][k] = Integer.MAX_VALUE;
			}
		}
		return f(mm, 0, mm.length - 1);
	}

	private static int f(int[][] mm, int i, int j) {
		if (liste[i][j] != Integer.MAX_VALUE) {
			return liste[i][j];
		}
		if (i == j) {
			return 0;
		} else if (i > j) {
			return Integer.MAX_VALUE;
		} else {
			int ergebnis = Integer.MAX_VALUE, aktergebnis = Integer.MAX_VALUE;
			for (int x = i; x < j; x++) {
				aktergebnis = f(mm, i, x) + f(mm, x + 1, j) + ((mm[i][0] * mm[x][1] * mm[j][1]));
				ergebnis = min(aktergebnis, ergebnis);
				liste[i][j] = ergebnis;
			}
			return ergebnis;
		}

	}

	private static int min(int n, int m) {
		return n > m ? m : n;
	}

}
