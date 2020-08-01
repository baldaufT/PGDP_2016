package tutorium5;

public class Arrays {

	public static void print(int[] feld) {
		String h = ", ";
		System.out.print("{");
		for (int i = 0; i < feld.length - 1; i++) {
			System.out.print(feld[i] + h);
		}
		System.out.print(feld[feld.length - 1]);
		System.out.println("}");
	}

	public static void minUndMax(int[] feld) {
		int min = feld[0], max = feld[0];
		for (int i = 0; i < feld.length; i++) {
			if (feld[i] > max) {
				max = feld[i];
			}
			if (feld[i] < min) {
				min = feld[i];
			}
		}
		System.out.println("Minimum: " + min + ", Maximum: " + max);
	}

	public static int[] invertieren(int[] feld) {
		int[] invert = new int[feld.length];
		for (int i = 0; i < feld.length; i++) {
			invert[invert.length - (1 + i)] = feld[i];
		}
		return invert;
	}

	public static int[] schneiden(int[] feld, int laenge) {
		int[] schneid = new int[laenge];
		if (laenge <= feld.length) {
			for (int i = 0; i < laenge; i++) {
				schneid[i] = feld[i];
			}
		} else {
			for (int i = 0; i < feld.length; i++) {
				schneid[i] = feld[i];
			}
		}
		return schneid;
	}

	public static int[] linearisieren(int[][] feld) {
		int laenge = 0;
		// laenge definieren
		for (int i = 0; i < feld.length; i++) {
			laenge = laenge + feld[i].length;
		}
		// lin initialisieren... hauptaufgabe
		int[] lin = new int[laenge];
		int t = 0;
		for (int i = 0; i < feld.length; i++) {
			for (int k = 0; k < feld[i].length; k++) {
				lin[t] = feld[i][k];
				t++;
			}
		}
		return lin;
	}

}
