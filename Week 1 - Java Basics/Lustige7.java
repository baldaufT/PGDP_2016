package tutorium2;

import tutorium1.MiniJava;

public class Lustige7 extends MiniJava {
	public static void main(String[] args) {
		// Initialisierung
		int guthaben = 100;
		write("Lass uns spielen! " + "Du hast " + guthaben + " Chips zur Verfuegung.");

		// Spielablauf
		while (guthaben > 0) {
			// Einsatz
			int einsatz = readInt("Wieviel Chips willst Du setzen?");
			if (einsatz == 0) { // Beenden des Spiels
				write("Du gewinnst mit " + guthaben + " Chips. " + "Herzlichen Glueckwunsch.");
				return;
			}
			while (einsatz < 0 || einsatz > guthaben) { // Einsatz zu hoch oder
														// zu klein
				if (einsatz > guthaben) {
					einsatz = readInt("Soviele Chips hast Du nicht mehr. " + "Dein Guthaben betraegt " + guthaben + ". "
							+ "Was ist dein Einsatz?");
				}
				if (einsatz < 0) {
					einsatz = readInt("Ungueltige Eingabe." + "Dein Guthaben betraegt " + guthaben + ". "
							+ "Was ist dein Einsatz?");
				}
				if (einsatz == 0) { // Beenden des Spiels
					write("Du gewinnst mit " + guthaben + " Chips. " + "Herzlichen Glueckwunsch.");
					return;
				}
			}
			// Feld
			int feld = readInt("Auf welches Feld willst Du setzen?");
			if (feld == 0) { // Beenden des Spiels
				write("Du gewinnst mit " + guthaben + " Chips. " + "Herzlichen Glueckwunsch.");
				return;
			}
			while (feld > 12 || feld < 2) { // Feld existiert nicht
				feld = readInt("Es gibt nur die Felder 2 bis 12. " + "Auf welches Feld willst Du setzen?");
				if (feld == 0) { // Beenden des Spiels
					write("Du gewinnst mit " + guthaben + " Chips. " + "Herzlichen Glueckwunsch.");
					return;
				}
			}
			// Wuerfeln
			write("Rien ne va plus. " + "Du hast " + einsatz + " Chips auf die " + feld + " gesetzt.");
			int wurf = dice() + dice();
			write("Die Wuerfel sind gefallen: " + wurf + " gewinnt.");
			// Gewinnberechnung
			int gewinn = 0;
			if (wurf == feld) {
				gewinn = einsatz * 2; // 2. Fall
				if (wurf == 7)
					gewinn = gewinn + einsatz; // 1. Fall
			} else {
				if ((wurf < 7 && feld < 7) || (wurf > 7 && feld > 7)) {
					gewinn = einsatz; // 3. Fall
				}
			}
			guthaben = guthaben - einsatz + gewinn;
			write("Du bekommst " + gewinn + " Chips. " + "Dein Guthaben betraegt nun: " + guthaben + " Chips.");
			if (guthaben == 0) {
				write("Du hast leider alles verloren.");
				return;
			}
		}
	}
}