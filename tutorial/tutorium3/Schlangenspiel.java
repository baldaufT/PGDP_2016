package tutorium3;

public class Schlangenspiel extends Spielfeld {

	static int pos1 = 0, pos2 = 0;
	static boolean gewinnt1 = false, gewinnt2 = false;

	public static void main(String[] args) {

		while (gewinnt1 == false && gewinnt2 == false) {
			paintField(pos1, pos2);
			write("Spieler1 würfeln?");
			pos1 = pos1 + dice();
			while ((pos1 % 5 == 0 || pos1 % 7 == 0) && (pos1 > 0 && pos1 < 35)) {
				if (pos1 % 5 == 0) {
					paintField(pos1, pos2);
					pos1 = pos1 + 3;
					write("Sie kommen 3 Felder weiter!");
					paintField(pos1, pos2);
				}
				if (pos1 % 7 == 0) {
					paintField(pos1, pos2);
					pos1 = pos1 - 4;
					write("Sie fallen leider 4 Felder zurück! ");
					paintField(pos1, pos2);
				}
			}
			if (pos1 > 34) {
				gewinnt1 = true;
				paintField(pos1, pos2);
			} else {
				paintField(pos1, pos2);
				write("Spieler2 würfeln?");
				pos2 = pos2 + dice();
				while ((pos2 % 5 == 0 || pos2 % 7 == 0) && (pos2 > 0 && pos2 < 35)) {
					if (pos2 % 5 == 0) {
						paintField(pos1, pos2);
						write("Sie kommen 3 Felder weiter!");
						pos2 = pos2 + 3;
						paintField(pos1, pos2);
					}
					if (pos2 % 7 == 0) {
						paintField(pos1, pos2);
						pos2 = pos2 - 4;
						write("Sie fallen leider 4 Felder zurück! ");
						paintField(pos1, pos2);
					}
				}
				if (pos2 > 34) {
					gewinnt2 = true;
					paintField(pos1, pos2);
				}
			}
		}
		if (gewinnt1 == true) {
			write("Spiel beendet. Spieler1 hat gewonnen!");
		}
		if (gewinnt2 == true) {
			write("Spiel beendet. Spieler2 hat gewonnen!");
		}
		System.exit(0);
	}

}
