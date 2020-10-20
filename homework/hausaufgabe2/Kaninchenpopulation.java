package hausaufgabe2;

public class Kaninchenpopulation {

	static int monat;
	static int geschlechtsreif, geschlechtsreif3;
	static int eins = 1, zwei = 0, drei = 0, x = 1, hilf, eins3 = 1, zwei3 = 0, drei3 = 0, hilf3;

	public static void main(String[] args) {
		monat = MiniJava.readInt("Für welchen Monat soll die Berechnung stattfinden?");
		berechnungManager();
		System.out.println("Dies gilt für Kanninchenpaare mit jeweils einem neuen Kaninchenpaar pro Monat :");
		if (monat == 1) {
			System.out.println("\tIm " + monat + ". Monat ist " + geschlechtsreif
					+ " Kaninchenpaar geschlechtsreif. Dieses befindet sich in erster Generation.\n");
		} else {
			System.out.println("\tIm " + monat + ". Monat sind " + geschlechtsreif
					+ " Kaninchenpaare geschlechtsreif. Davon befinden sich " + eins + " in erster Gerneration, " + zwei
					+ " in zweiter Generation und " + drei + " in dritter Generation.\n");
		}

		System.out.println(
				"Dies gilt für Kanninchenpaare mit drei neuen Kaninchenpaar pro Monat in der zweiten Generation:");
		if (monat == 1) {
			System.out.print("\tIm " + monat + ". Monat ist " + geschlechtsreif3
					+ " Kaninchenpaar geschlechtsreif. Dieses befindet sich in erster Generation.");
		} else {
			System.out.print("\tIm " + monat + ". Monat sind " + geschlechtsreif3
					+ " Kaninchenpaare geschlechtsreif. Davon befinden sich " + eins3 + " in erster Gerneration, "
					+ zwei3 + " in zweiter Generation und " + drei3 + " in dritter Generation.");
		}
	}

	private static void berechnungManager() {

		while (x <= monat - 1) {
			hilf = eins + zwei + drei;
			drei = zwei;
			zwei = eins;
			eins = hilf;

			hilf3 = eins3 + zwei3 * 3 + drei3;
			drei3 = zwei3;
			zwei3 = eins3;
			eins3 = hilf3;

			x++;
		}

		geschlechtsreif = eins + zwei + drei;
		geschlechtsreif3 = eins3 + zwei3 + drei3;
	}
}
