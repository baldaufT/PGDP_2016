package hausaufgabe6;

/**
 * WICHTIG wegen PLAGIATEN:
 * 
 * Diese Angabe ist inoffiziell (von Stefan Berktold), wobei das Grundgerüst
 * dasselbe ist wie das offizielle, allerdings um die Methode matrixTest() mit
 * entsprechendem Aufruf in der main-Methode erweitert wurde. Ihr könnt die
 * Methode matrixTest() problemlos zum Testen eurer Lösung verwenden, indem ihr
 * sie in der main-Methode aufruft.
 * 
 * WICHTIG ist nur, dass ihr VOR DER ABGABE ... ... die Methode matrixTest()
 * vollständig aus eurem Code entfernt!!! ... den Aufruf von matrixTest() aus
 * der main-Methode entfernt! ... diese Klasse in MatrixMultOptimization (statt
 * MatrixMultOptimization_Tester) umbenennt.
 *
 * WER DIE METHODE matrixTest() NICHT VOR DER ABGABE ENTFERNT RISKIERT EINE
 * BEWERTUNG VON 0 PUNKTEN WEGEN PLAGIATS!
 * 
 * Hilfreiche Tipps zur Aufgabe:
 * https://en.wikipedia.org/wiki/Matrix_chain_multiplication
 */

public class Matrix_Tester {
	private static int[][] liste;

	public static void main(String[] args) {
		matrixTest();
	}

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

	/**
	 * VOR DER ABGABE ENTFERNEN:
	 */
	private static void matrixTest() {
		/**
		 * Hinweise zu matrixTest: Bitte versuche nicht, das nachfolgende
		 * Programm zu verstehen! Es soll dir lediglich beim Testen helfen und
		 * dich nicht weiterbilden! Da es sich hierbei um ein Testprogramm
		 * handelt, wurden Duplikate bewusst nicht vermieden! Orientiere dich
		 * also für künftige Aufgaben nicht an dieser Methode! Für Tests nutzt
		 * man in der Regel JUnit-Tests, wobei hier der Einfachheit wegen alles
		 * in einer Methode geschrieben wurde, die in der main-Methode
		 * aufgerufen werden soll (um es euch nicht unnötig kompliziert zu
		 * machen).
		 */

		// Testfälle in der Form {{Testfall (n x m x k x l ...)}, {Ergebnis}}
		int[][][] test = {
				/* 3 Matrizen */
				{ { 10, 30, 5, 60 }, { 4500 } }, { { 60, 5, 30, 10 }, { 4500 } }, { { 5, 10, 60, 30 }, { 12000 } },
				{ { 30, 5, 10, 60 }, { 12000 } }, { { 16, 7, 5, 18 }, { 2000 } }, { { 5, 1, 1, 8 }, { 45 } },
				{ { 17, 18, 5, 3 }, { 1188 } }, { { 13, 19, 11, 6 }, { 2736 } }, { { 6, 11, 3, 18 }, { 522 } },
				{ { 19, 5, 4, 6 }, { 690 } }, { { 36, 24, 18, 11 }, { 14256 } }, { { 58, 2, 4, 6 }, { 744 } },
				{ { 2, 4, 6, 8 }, { 144 } }, { { 2, 2, 2, 2 }, { 16 } }, { { 1, 1, 1, 1 }, { 2 } },
				/* 2 Matrizen */
				{ { 3, 4, 5 }, { 60 } }, { { 5, 4, 3 }, { 60 } }, { { 4, 3, 5 }, { 60 } }, { { 4, 5, 3 }, { 60 } },
				{ { 3, 5, 4 }, { 60 } }, { { 5, 3, 4 }, { 60 } }, { { 1, 1, 1 }, { 1 } }, { { 1, 1, 2 }, { 2 } },
				{ { 2, 1, 1 }, { 2 } }, { { 2, 1, 2 }, { 4 } }, { { 2, 2, 1 }, { 4 } }, { { 2, 2, 2 }, { 8 } },
				{ { 5, 8, 19 }, { 760 } }, { { 17, 3, 22 }, { 1122 } }, { { 5, 7, 3 }, { 105 } },
				{ { 8, 8, 5 }, { 320 } },
				/* 4 Matrizen */
				{ { 6, 9, 4, 2, 1 }, { 98 } }, { { 17, 5, 18, 32, 11 }, { 5575 } }, { { 6, 6, 8, 19, 44 }, { 6216 } },
				{ { 9, 4, 5, 41, 3 }, { 783 } }, { { 78, 3, 5, 2, 19 }, { 3462 } }, { { 14, 91, 56, 4, 4 }, { 25704 } },
				{ { 9, 6, 5, 19, 7 }, { 1250 } },
				/* 5-7 Matrizen */
				{ { 7, 6, 5, 7, 3, 5 }, { 426 } }, { { 18, 6, 4, 5, 6, 3, 2, 5 }, { 580 } },
				{ { 7, 2, 1, 8, 7, 9, 4, 2 }, { 191 } }, { { 91, 67, 21, 20, 10, 29 }, { 105630 } },
				{ { 10, 28, 918, 29, 9, 18, 85, 27 }, { 510309 } },
				/* 1 Matrix */
				{ { 1, 1 }, { 0 } }, { { 5, 7 }, { 0 } }, { { 7, 5 }, { 0 } }
				/* optional noch: keine Matrix (nicht verlangt) */
		};

		StringBuilder sb = new StringBuilder();
		String crlf = System.lineSeparator();

		int errors = 0;
		for (int i = 0; i < test.length; i++) {
			int[][] matrix = new int[test[i][0].length - 1][];
			for (int j = 0; j < matrix.length; j++) {
				matrix[j] = new int[] { test[i][0][j], test[i][0][j + 1] };
			}

			sb.append("Test ").append(i + 1).append('/').append(test.length).append(" mit ");
			for (int[] row : matrix)
				sb.append(java.util.Arrays.toString(row));

			int result = f(matrix);
			if (result == test[i][1][0]) {
				sb.append(" erfolgreich.");
			} else {
				sb.append(" fehlgeschlagen.").append(" f hat ").append(result).append(" berechnet, während ")
						.append(test[i][1][0]).append(" erwartet wurde.");
				errors++;
			}
			sb.append(crlf);
		}

		System.out.println("Testübersicht:\nErfolgreich: " + (test.length - errors) + "\nFehlgeschlagen: " + errors
				+ "\n\nAusführlich:");
		System.out.println(sb.toString());
	}

}
