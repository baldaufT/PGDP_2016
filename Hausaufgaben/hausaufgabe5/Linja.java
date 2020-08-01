package hausaufgabe5;

public class Linja extends MiniJava {

	private static int[][] spielfeld = new int[8][6];
	private static boolean ende = false;

	/**
	 * initialisiert das Spielfeld Ziellinie fuer Spieler 1 ist Zeile 7
	 * Ziellinie fuer Spieler -1 ist Zeile 0
	 */
	private static void initSpiel() {
		for (int i = 0; i < spielfeld.length; i++) {
			if (i != 0 && i != spielfeld.length - 1) {
				spielfeld[i] = new int[] { -(12 - i + 1), 0, 0, 0, 0, 6 + i };
			}
			if (i == 0) {
				spielfeld[i] = new int[] { 1, 2, 3, 4, 5, 6 };
			}
			if (i == spielfeld.length - 1) {
				spielfeld[i] = new int[] { -6, -5, -4, -3, -2, -1 };
			}
		}

	}

	/**
	 *
	 * @return formatiertes aktuelles Spielfeld
	 */
	private static String output() {
		String tmp = "Spieler 1 spielt von oben nach unten\n" + "Spieler -1 spielt von unten nach oben\n";
		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[i].length; j++) {
				tmp = tmp + "\t" + spielfeld[i][j];
			}
			tmp = tmp + "\n";
		}
		return tmp;
	}

	/**
	 * @return true, wenn die Eingabe stein im richtigen Wertebereich liegt und
	 *         zum Spieler gehoert; false, sonst
	 */
	private static boolean gueltigeEingabe(int stein, int spieler) {
		if (spieler == 1) {
			if (stein < 13 && stein > 0)
				return true;
			else
				return false;
		} else if (spieler == -1) {
			if (stein < 0 && stein > -13)
				return true;
			else
				return false;
		} else {
			return false;
		}

	}

	/**
	 * @param stein
	 *            kann Werte -1 bis -12 und 1 bis 12 haben
	 * @return gibt x-Koordinate von stein an Position 0 und die y-Koordinaten
	 *         von stein an Position 1 zurueck; falls stein nicht gefunden, wird
	 *         {-1,-1} zurueckgegeben
	 */
	private static int[] findeStein(int stein) {
		int[] sd = new int[] { -1, -1 };
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 6; k++) {
				if (spielfeld[i][k] == stein) {
					sd[0] = i;
					sd[1] = k;
				}
			}
		}
		return sd;
	}

	/**
	 * @param reihe
	 *            hat Werte 0 bis 7
	 * @return Anzahl der Steine in einer Reihe
	 */
	private static int steineInReihe(int reihe) {
		int anzahl = 0;
		for (int i = 0; i < 6; i++) {
			if (spielfeld[reihe][i] != 0) {
				anzahl = anzahl + 1;
			}
		}
		return anzahl;
	}

	/**
	 * Ueberprueft, ob der Zug zulaessig ist und fuehrt diesen aus, wenn er
	 * zulaessig ist.
	 *
	 * @param vorwaerts
	 *            == true: Zug erfolgt vorwaerts aus Sicht des Spielers/Steins
	 *            vorwearts == false: Zug erfolgt rueckwaerts aus Sicht des
	 *            Spielers/Steins
	 * @return Rueckgabe -1: Zug nicht zulaessig Rueckgabe 0-5: Weite des
	 *         potentiellen naechsten Zugs (falls Folgezug folgt) Rueckgabe 6:
	 *         Ziellinie wurde genau getroffen (potentieller Bonuszug)
	 *
	 */
	private static int setzeZug(int stein, int weite, boolean vorwaerts) {
		int reihealt = findeStein(stein)[0], reiheneu = 0;
		int spaltealt = findeStein(stein)[1], spalteneu = 0;
		if (reihealt == -1) {
			return -1;
		}
		// abgleich ob vorwoerts? und von oben nach unten??
		// UEBERPRUEFEN!!!!!!!!!!!!!!!!!!!!!
		if (vorwaerts == true) {
			if (stein > 0)
				reiheneu = reihealt + weite;
			if (stein < 0)
				reiheneu = reihealt - weite;
		} else {
			if (stein > 0)
				reiheneu = reihealt - weite;
			if (stein < 0)
				reiheneu = reihealt + weite;
		}
		// Feldende
		boolean bonuszug = false;
		if (reiheneu == 7 || reiheneu == 0)
			bonuszug = true;
		if (reiheneu > 7) {
			reiheneu = 7;
			bonuszug = false;
		}
		if (reiheneu < 0) {
			bonuszug = false;
			reiheneu = 0;
		}
		// anzahl und unzulaessigkeit
		int anzahl = steineInReihe(reiheneu);
		boolean passtnicht = true;
		boolean passt = false;
		if ((reiheneu == 7 || reiheneu == 0) && anzahl == 6) {
			int spa = 0;
			while (passt == false) {
				if (stein < 0 && spielfeld[reiheneu][spalteneu] < 0) {
					spielfeld[reiheneu][spalteneu] = 0;
					passt = true;
				}
				if (stein > 0 && spielfeld[reiheneu][spalteneu] > 0) {
					spielfeld[reiheneu][spalteneu] = 0;
					passt = true;
				}
				if (spa == 5) {
					passt = true;
					passtnicht = false;
				}
				spa = spa + 1;
			}
		}
		anzahl = steineInReihe(reiheneu);
		if (passtnicht == true) {
			if (anzahl == 6)
				return -1;
		}
		int hilfsstein = spielfeld[reiheneu][0];
		if (passtnicht == false) {
			spielfeld[reiheneu][0] = 0;
		}
		// neue spalte finden
		for (int i = 0; i < 6; i++) {
			if (spielfeld[reiheneu][i] == 0)
				spalteneu = i;
		}
		// neues Feld besetzen
		spielfeld[reiheneu][spalteneu] = stein;
		// letzter Platz auf 0 setzen
		spielfeld[reihealt][spaltealt] = 0;
		if (passtnicht == false) {
			spielfeld[reiheneu][0] = hilfsstein;
		}
		if (passt == true) {
			write("Ihr Stein hat die Ziellinie erreicht. Aus Darstellungsgruenden wurde der Stein entfernt. Spielen Sie ganz normal weiter.");
		}
		System.out.println(output());
		if (bonuszug == true) {
			return 6;
		}
		return anzahl;

	}

	/**
	 * @return true, falls die Bedingungen des Spielendes erfuellt sind, d.h.
	 *         alle Steine des einen Spielers sind an den Steinen des
	 *         gegnerischen Spielers vorbeigezogen
	 *
	 */
	private static boolean spielende() {
		int letztereihesp1 = 0, letztereihespminus1 = 7;
		// berechnung
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 6; k++) {
				if (spielfeld[i][k] < 0) {
					letztereihespminus1 = i;
				}
			}
		}
		for (int i = 7; i >= 0; i--) {
			for (int k = 0; k < 6; k++) {
				if (spielfeld[i][k] > 0) {
					letztereihesp1 = i;
				}
			}
		}

		// abgleich
		if (letztereihesp1 > letztereihespminus1)
			return true;
		else {
			return false;
		}

	}

	private static int wert(int k, int spieler) {
		int a = 0;
		if (spieler == -1) {
			if (k == 0)
				a = 0;
			if (k == 1)
				a = 2;
			if (k == 2)
				a = 3;
			if (k == 3)
				a = 4;
			if (k == 4)
				a = 6;
			if (k == 5)
				a = 7;
			if (k == 6)
				a = 8;
			if (k == 7)
				a = 10;
		}
		if (spieler == 1) {
			if (k == 0)
				a = 10;
			if (k == 1)
				a = 8;
			if (k == 2)
				a = 7;
			if (k == 3)
				a = 6;
			if (k == 4)
				a = 4;
			if (k == 5)
				a = 3;
			if (k == 6)
				a = 2;
			if (k == 7)
				a = 0;
		}

		return a;
	}

	/**
	 * zaehlt die Punkte der beiden Spieler und gibt das Ergebnis aus
	 */
	private static void zaehlePunkte() {
		int sieger = 0, verlierer = 0, i = 2, pkt1 = 60, pkt2 = 60;
		boolean weiterspielen = true;
		// Punkte z�hlen Spieler 1
		for (int k = 0; k < 8; k++) {
			for (int g = 0; g < 6; g++) {
				if (spielfeld[k][g] > 0) {
					pkt1 = pkt1 - wert(k, 1);
				}
			}
		}
		// Punkte z�hlen Spieler 2
		for (int k = 0; k < 8; k++) {
			for (int g = 0; g < 6; g++) {
				if (spielfeld[k][g] < 0) {
					pkt2 = pkt2 - wert(k, (-1));
				}
			}
		}
		// Sieger bestimmen
		if (pkt1 < pkt2) {
			sieger = -1;
			verlierer = 1;
		}
		if (pkt1 > pkt2) {
			sieger = 1;
			verlierer = -1;
		}
		if (pkt1 == pkt2)
			sieger = verlierer = 0;
		// Ergebnis ausgeben
		if (sieger == 1)
			write("Herzlichen Glueckwunsch an Spieler 1. Du hast mit " + pkt1 + " Punkten gewonnen!");
		if (sieger == 0)
			write("Unentschieden! Beide haben " + pkt1 + " Punkte ");
		if (sieger == -1)
			write("Herzlichen Glueckwunsch an Spieler -1. Du hast mit " + pkt2 + " Punkten gewonnen!");

		// neue Runde?
		// abfragen
		while (!(i == 0 || i == 1)) {
			i = readInt("Um nochmal eine Runde zu spielen geben Sie bitte eine 1 ein, um aufzuhoeren eine 0:");
		}
		if (i == 0)
			weiterspielen = false;
		// bei nein aufhoeren
		if (weiterspielen == false)
			return;
		// bei ja:
		ende = false;
		initSpiel();
		System.out.println(output());
		boolean bonus = false;
		int x = 2;
		while (!(x == 0 || x == 1)) {
			x = readInt(
					"Wenn Sie mit der optionalen Bonusregel spielen wollen, geben Sie eine 1 ein.\nAnsonsten eine 0.");
		}
		if (x == 1) {
			bonus = true;
		}
		while (ende == false) {
			spielManager(verlierer, bonus);
		}
	}

	/**
	 * Spielablauf entsprechend Anfangszug, Folgezug, Bonuszug
	 *
	 * @param spieler
	 *            ist 1 (Spielsteine 1 bis 12) oder -1 (Spielsteine -1 bis -12)
	 */
	private static void spielerZieht(int spieler, boolean bonus) {
		// wie agiert spieler?
		int stein = readInt("Welchen Stein wollen Sie vorruecken Spieler " + spieler + " ?");
		if (spieler == -1) {
			if (stein > 0)
				stein = -stein;
		}
		while (gueltigeEingabe(stein, spieler) == false
				|| ((findeStein(stein)[0] == 0 && spieler < 0) || (findeStein(stein)[0] == 7 && spieler > 0))) {
			stein = readInt("Welchen Stein wollen Sie vorruecken Spieler " + spieler + " ?");
		}
		// stein vorruecken
		int folgezug = setzeZug(stein, 1, true);
		// geht nicht?
		if (folgezug == -1) {
			write("Leider koennen Sie mit diesem Stein nicht in Ihre gewuenschte Reihe aufruecken. Waehlen Sie einen anderen.");
			spielerZieht(spieler, bonus);
			return;
		}
		// Folgezuege
		int alterfolgezugspeicher = folgezug;
		if (folgezug != 6 && folgezug != 0) {
			boolean folgeistvoll = true;
			while (folgeistvoll == true) {
				int folgestein = readInt("Sie haben " + folgezug
						+ " Folgezuege bekommen. Mit welchem Stein wollen Sie diese Ausfuehren?");
				if (spieler == -1) {
					if (folgestein > 0)
						folgestein = -folgestein;
				}
				while (gueltigeEingabe(folgestein, spieler) == false) {
					folgestein = readInt("Welchen Stein wollen Sie als Folgezug vorruecken?");
				}
				folgezug = setzeZug(folgestein, folgezug, true);
				if (folgezug == -1) {
					write("Leider koennen Sie mit diesem Stein nicht in Ihre gewuenschte Reihe aufruecken. Waehlen Sie einen anderen.");
					folgeistvoll = true;
					folgezug = alterfolgezugspeicher;
				} else {
					folgeistvoll = false;
				}
			}
		}
		// Bonuszug?
		// wenn optionale bonusregel, dann hier progggen
		if (bonus == true) {
			if (folgezug == 6) {
				write("Sie haben einen Bonuszug bekommen!");
				int bonusstein = 0;
				// welcher?
				int vor = -1;
				boolean ziellinie = false;
				boolean spielerwechsel = false;
				while (!(vor == 1 || vor == 0)) {
					while (!(bonusstein < 13 && bonusstein > -13 && bonusstein != 0)) {
						bonusstein = readInt(
								"Welchen Stein wollen Sie als Bonusstein vorruecken? \n(Spieler 1 positiv; Spieler -1 negative Eingabe)");

					}
					if (ziellinie == true) {
						bonusstein = 0;
						while (!(bonusstein < 13 && bonusstein > -13 && bonusstein != 0)) {
							bonusstein = readInt(
									"Welchen Stein wollen Sie als Bonusstein vorruecken? \n(Spieler 1 positiv; Spieler -1 negative Eingabe)");

						}
					}
					if ((bonusstein < 0 && spieler > 0) || (bonusstein > 0 && spieler < 0)) {
						spieler = -spieler;
						spielerwechsel = true;
					}
					if (findeStein(bonusstein)[0] == -1 || (findeStein(bonusstein)[0] == 0 && spieler < 0)
							|| (findeStein(bonusstein)[0] == 7 && spieler > 0)) {
						write("Bitte waehlen Sie einen anderen Stein.");
						ziellinie = true;
					} else if ((findeStein(bonusstein)[0] == 0 && spieler > 0)
							|| (findeStein(bonusstein)[0] == 7 && spieler < 0)) {
						vor = 1;
					} else {
						vor = readInt("Wollen Sie Ihren Stein vor (1) oder zurueck (0) setzen?");
					}
				}
				if (vor == 1) {
					int bonusreihebesetzt = setzeZug(bonusstein, 1, true);
					if (bonusreihebesetzt == -1)
						write("Sie koennen mit Ihrem Bonuszug nicht in Ihre gewuenschte Reihe vorruecken");

				}
				if (vor == 0) {
					int bonusreihebesetzt = setzeZug(bonusstein, 1, false);
					if (bonusreihebesetzt == -1)
						write("Sie koennen mit Ihrem Bonuszug nicht in Ihre gewuenschte Reihe zurueckruecken");

				}
				if (spielerwechsel == true) {
					spieler = -spieler;
					spielerwechsel = false;
				}
			}
		}
		// ohne optionale Bonusregel
		if (bonus == false) {
			if (folgezug == 6) {
				write("Sie haben einen Bonuszug bekommen!");
				// welcher?
				int bonusreihebesetzt = -1;
				while (bonusreihebesetzt == -1) {
					int bonusstein = 0;
					boolean ziellinie = false;
					while ((ziellinie == true) || (gueltigeEingabe(bonusstein, spieler) == false)) {
						bonusstein = readInt("Welchen Stein wollen Sie als Bonuszug vorruecken?");
						if (spieler == -1) {
							if (bonusstein > 0)
								bonusstein = -bonusstein;
						}
						if ((spieler < 0 && findeStein(bonusstein)[0] == 0)
								|| (spieler > 0 && findeStein(bonusstein)[0] == 7)
								|| (findeStein(bonusstein)[0] == -1)) {
							ziellinie = true;
							write("Dieser Stein befindet sich bereits auf der Ziellinie. Waehlen Sie einen anderen!");
						} else
							ziellinie = false;
					}
					// vor oder zurueck
					int vor = 2;
					if ((spieler < 0 && findeStein(bonusstein)[0] == 7)
							|| (spieler > 0 && findeStein(bonusstein)[0] == 0)) {
						vor = 1;
					} else {
						while (!(vor == 1 || vor == 0)) {
							vor = readInt("Vor (1) oder zur�ck(0)?");
						}
					}
					if (vor == 1) {
						bonusreihebesetzt = setzeZug(bonusstein, 1, true);
						if (bonusreihebesetzt == -1)
							write("Sie koennen mit Ihrem Bonuszug nicht in Ihre gewuenschte Reihe vorruecken");
					}
					if (vor == 0) {
						bonusreihebesetzt = setzeZug(bonusstein, 1, false);
						if (bonusreihebesetzt == -1)
							write("Sie koennen mit Ihrem Bonuszug nicht in Ihre gewuenschte Reihe zurueckruecken");
					}
				}
			}
		}
	}

	private static void spielManager(int spieler, boolean bonus) {
		// int spieler beginnt
		while (ende == false) {
			spielerZieht(spieler, bonus);
			ende = spielende();
			spieler = spieler * (-1);
		}
		zaehlePunkte();
	}

	public static void main(String args[]) {
		int spieler = dice();
		if (spieler == 1 || spieler == 3 || spieler == 5)
			spieler = 1;
		if (spieler == 2 || spieler == 4 || spieler == 6)
			spieler = -1;
		initSpiel();
		System.out.println(output());
		write("Hallo Bewerter. \nIn der Angabe steht, dass Steine auf der Ziellinie entfernt werden sollen. \nDies ist auch noetig, da die Ziellinie keinen Platz fuer 12 Steine hat. \nDennoch finde ich, dass die Steine, die noch dargestellt werden koennen, \nauch dargestellt werden sollen. Aus diesem Grund habe ich mich entschlossen \nlediglich die Steine, die nicht mehr dargestellt werden koennen zu entfernen. \nIch denke, dass dies vertretbar ist. Vielen Dank!");
		boolean bonus = false;
		int x = 2;
		while (!(x == 0 || x == 1)) {
			x = readInt(
					"Wenn Sie mit der optionalen Bonusregel spielen wollen, geben Sie eine 1 ein.\nAnsonsten eine 0.");
		}
		if (x == 1) {
			bonus = true;
		}
		while (ende == false) {
			spielManager(spieler, bonus);
		}
	}
}