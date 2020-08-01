package hausaufgabe3;

public class SuV {

	static int summe1, summe2;
	static boolean spieler1beginnt = true, spieler1fertig = false;
	static boolean sp1u21 = true, sp2u21 = true, spieler2fertig = false;

	public static void main(String[] args) {
		summe1 = MiniJava.drawCard() + MiniJava.drawCard();
		summe2 = MiniJava.drawCard() + MiniJava.drawCard();
		int x = MiniJava.dice();
		if (x == 1 || x == 3 || x == 5) {
			spieler1beginnt = false;
		}
		if (spieler1beginnt == true) {
			System.out.println("Spieler 1 beginnt.");
		}
		if (spieler1beginnt == false) {
			System.out.println("Spieler 2 beginnt.");
		}
		System.out.println(
				"Während des Spiels werden Sie aufgefordert eine Zahl einzugeben. \n1 bedeutet Sie wollen eine weitere Karte ziehen, 0 bedeutet Sie ziehen keine weiteren Karte\n");
		spielStart();
		spielerwechsel();
		auswertung();
	}

	private static void spielerwechsel() {
		boolean hilf = spieler1beginnt;
		if (hilf == true)
			spieler1beginnt = false;
		if (hilf == false)
			spieler1beginnt = true;
		if (spieler1fertig == false || spieler2fertig == false) {
			spielStart();
		}

	}

	private static void spielStart() {
		// Teil der Spieler1 behandelt
		if (spieler1beginnt == true) {
			if (spieler1fertig == false) {
				System.out.println("Spieler1, Ihre aktuelle Kartensumme ist " + summe1
						+ ". Möchten Sie eine weitere Karte ziehen? ");
				while (spieler1fertig == false) {
					int x = MiniJava.readInt("1 für ja, 0 für nein");
					if (x == 1) {
						summe1 = summe1 + MiniJava.drawCard();
						test();
						if (sp1u21 == true) {
							System.out.println("Ihre neuer Punktestand beträgt : " + summe1
									+ "\nWollen Sie eine weitere Karte ziehen?");
						} else {
							spieler1fertig = true;
							spieler2fertig = true;
							System.out.println(
									"\nLeider ist Ihr Punktestand über 21. Sie haben damit verloren. \nSpieler2 gewinnt. Herzlichen Glückwunsch.");
						}
					}
					if (x == 0) {
						spieler1fertig = true;
					}
					if (x != 1 && x != 0) {
						System.out.println("Sie muessen entweder eine 1 oder eine 0 eingeben.");
					}
				}
				if (sp1u21 == true)
					System.out.println("Vielen Dank. Ihre Endsumme ist " + summe1 + ". Wir fahren nun fort:\n");
			}
		}
		// Teil der Spieler2 behandelt
		if (spieler1beginnt == false) {
			if (spieler2fertig == false) {
				System.out.println("Spieler2, Ihre aktuelle Kartensumme ist " + summe2
						+ ". Möchten Sie eine weitere Karte ziehen? ");
				while (spieler2fertig == false) {
					int x = MiniJava.readInt("1 für ja, 0 für nein");
					if (x == 1) {
						summe2 = summe2 + MiniJava.drawCard();
						test();
						if (sp2u21 == true) {
							System.out.println("Ihre neuer Punktestand beträgt : " + summe2
									+ "\nWollen Sie eine weitere Karte ziehen?");
						} else {
							spieler1fertig = true;
							spieler2fertig = true;
							System.out.println(
									"\nLeider ist Ihr Punktestand über 21. Sie haben damit verloren. \nSpieler1 gewinnt. Herzlichen Glückwunsch.");
						}
					}
					if (x == 0) {
						spieler2fertig = true;
					}
					if (x != 1 && x != 0) {
						System.out.println("Sie muessen entweder eine 1 oder eine 0 eingeben.");
					}
				}
				if (sp2u21 == true)
					System.out.println("Vielen Dank. Ihre Endsumme ist " + summe2 + ". Wir fahren nun fort:\n");
			}
		}
	}

	private static void auswertung() {
		if (sp1u21 == true && sp2u21 == true) {
			System.out.println("\nVielen Dank. Beide Spieler haben gespielt.\n");
			if (summe2 > summe1) {
				System.out.println("Herzlichen Glückwunsch. Spieler 2 hat mit " + summe2 + " Punkten gewonnen.");
			}
			if (summe1 > summe2) {
				System.out.println("Herzlichen Glückwunsch. Spieler 1 hat mit " + summe1 + " Punkten gewonnen.");
			}
			if (summe1 == summe2) {
				System.out.println("Herzlichen Glückwunsch. Spieler 1 hat mit " + summe1
						+ " Punkten gewonnen.\nSpieler2 hat zwar genauso viele Punkte, jedoch besagt das Regelwerk, dass bei Punktegleichstand Spieler1 gewinnt.");
			}
		}
	}

	private static void test() {
		if (summe1 > 21)
			sp1u21 = false;
		if (summe2 > 21)
			sp2u21 = false;
	}
}
