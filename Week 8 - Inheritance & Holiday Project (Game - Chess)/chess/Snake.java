package hausaufgabe9;

public class Snake extends Predator {
	// TODO funtioniert nicht besonders sauber
	// also bei possibleMoves() klappt es eig gar nicht so wie es sein soll
	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Snake(boolean female) {
		super(female);
		withoutFood = startwert = 9;
	}

	// bei den possibleMoves() - Methoden muss ich noch die Züge die
	// wegen den Zwischenfeldern nicht ausführbar sind rausnehmen, damit die
	// bei den Gültigkeits Test in GAME erst gar nicht auftauchen

	@Override
	public Move[] possibleMoves() {
		if(this.alive == false)
			return null;
		String[] zug = new String[14];
		String[] besetzteFelder = this.position.getAnimalPlaces(this.female, true), helpbesetzt;
		char zahl = this.square.charAt(1);
		char letter = this.square.charAt(0);
		int helpzaehler = 0, intzahl = (int) zahl - 48, zaehler = 0;
		boolean wechsel = true;

		helpbesetzt = new String[besetzteFelder.length + 36];
		for (int i = 36; i < helpbesetzt.length; i++) {
			helpbesetzt[i] = besetzteFelder[zaehler++];
		}
		char randletter = 'a' - 1;
		for (int i = 0; i < 10; i++) {
			helpbesetzt[i] = randletter + "" + i;
		}
		for (int i = 10; i < 20; i++) {
			helpbesetzt[i] = "i" + (i - 10);
		}
		randletter = 'a';
		for (int i = 20; i < 28; i++) {
			helpbesetzt[i] = randletter + "9";
			randletter++;
		}
		randletter = 'a';
		for (int i = 28; i < 36; i++) {
			helpbesetzt[i] = randletter + "0";
			randletter++;
		}
		besetzteFelder = helpbesetzt;
		zaehler = 0;

		for (int i = intzahl + 1; i < 9; i++) {
			zug[helpzaehler] = this.square + "" + (char) ((wechsel) ? (letter - 1) : (letter)) + "" + i;
			wechsel = wechsel ? false : true;
			for (String besetzt : besetzteFelder) {
				zug[helpzaehler] = (zug[helpzaehler].equals(this.square + "" + besetzt)) ? "k" : zug[helpzaehler];
				if (zug[helpzaehler].equals("k")) {
					break;
				}
			}
			if (zug[helpzaehler].equals("k")) {
				helpzaehler++;
				break;
			}
			zaehler++;
			helpzaehler++;
		}

		wechsel = true;
		for (int i = intzahl - 1; i > 0; i--) {
			zug[helpzaehler] = this.square + "" + (char) ((wechsel) ? (letter + 1) : (letter)) + "" + i;
			wechsel = wechsel ? false : true;
			for (String besetzt : besetzteFelder) {
				zug[helpzaehler] = (zug[helpzaehler].equals(this.square + "" + besetzt)) ? "k" : zug[helpzaehler];
				if (zug[helpzaehler].equals("k")) {
					break;
				}
			}
			if (zug[helpzaehler].equals("k")) {
				helpzaehler++;
				break;
			}
			zaehler++;
			helpzaehler++;
		}

		wechsel = true;
		for (int i = (letter + 1); i < 105; i++) {
			zug[helpzaehler] = this.square + "" + (char) (i) + "" + (char) ((wechsel) ? (zahl + 1) : (zahl));
			wechsel = wechsel ? false : true;
			for (String besetzt : besetzteFelder) {
				zug[helpzaehler] = (zug[helpzaehler].equals(this.square + "" + besetzt)) ? "k" : zug[helpzaehler];
				if (zug[helpzaehler].equals("k")) {
					break;
				}
			}
			if (zug[helpzaehler].equals("k")) {
				helpzaehler++;
				break;
			}
			zaehler++;
			helpzaehler++;
		}

		wechsel = true;
		for (int i = (letter - 1); i > 96; i--) {
			zug[helpzaehler] = this.square + "" + (char) (i) + "" + (char) ((wechsel) ? (zahl - 1) : (zahl));
			wechsel = wechsel ? false : true;
			for (String besetzt : besetzteFelder) {
				zug[helpzaehler] = (zug[helpzaehler].equals(this.square + "" + besetzt)) ? "k" : zug[helpzaehler];
				if (zug[helpzaehler].equals("k")) {
					break;
				}
			}
			if (zug[helpzaehler].equals("k")) {
				helpzaehler++;
				break;
			}
			zaehler++;
			helpzaehler++;
		}

		if (zug[0] == null)
			return null;

		for (int i = 0; i < zug.length; i++) {
			if (zug[i] != null && !zug[i].equals("k"))
				zug[i] = (zug[i].charAt(2) == 'i' || zug[i].charAt(2) == 'j' || ((char) (zug[i].charAt(2) + 1)) == 'a'
						|| ((char) (zug[i].charAt(2) + 2)) == 'a' || (zug[i].charAt(3)) == '0'
						|| zug[i].charAt(3) == '/' || zug[i].charAt(3) == '9' || zug[i].charAt(3) == ':') ? "k"
								: zug[i];
		}

		for (int i = 0; i < zug.length; i++) {
			for (String besetzt : besetzteFelder) {
				if (zug[i] != null)
					zug[i] = (zug[i].equals(this.square + "" + besetzt)) ? "k" : zug[i];
				if (zug[i] != null && zug[i].equals("k")) {
					zaehler++;
					break;
				}
			}
		}

		String[] help = new String[zug.length - zaehler];
		zaehler = 0;
		for (int i = 0; i < help.length; i++) {
			while (zug[i + zaehler] != null && zug[i + zaehler].equals("k")) {
				zaehler++;
			}
			help[i] = zug[i + zaehler];
		}
		zug = help;
		Move[] move = new Move[zug.length];
		for (int i = 0; i < move.length; i++) {
			move[i] = new Move(zug[i]);
		}
		return move;
	}

	@Override
	public String toString() {
		return this.female
				? (Globals.darkSquare(this.square) ? Globals.ts_female_snake_dark : Globals.ts_female_snake_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_snake_dark : Globals.ts_male_snake_light);
	}

}
