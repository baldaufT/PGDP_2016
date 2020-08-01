package hausaufgabe9;

public class Horse extends Vegetarian {

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Horse(boolean female) {
		super(female);
	}

	@Override
	public Move[] possibleMoves() {
		if (this.alive == false)
			return null;
		String[] zug = new String[12];
		String[] besetzteFelder = this.position.getAnimalPlaces(this.female, false);
		char zahl = this.square.charAt(1);
		char letter = this.square.charAt(0);

		zug[0] = this.square + "" + (char) letter + "" + (char) (zahl - 1);
		zug[1] = this.square + "" + (char) letter + "" + (char) (zahl + 1);
		zug[2] = this.square + "" + (char) (letter + 1) + "" + (char) (zahl);
		zug[3] = this.square + "" + (char) (letter - 1) + "" + (char) (zahl);

		zug[4] = this.square + "" + (char) (letter + 3) + "" + (char) (zahl + 3);
		zug[5] = this.square + "" + (char) (letter + 2) + "" + (char) (zahl + 2);
		zug[6] = this.square + "" + (char) (letter - 3) + "" + (char) (zahl - 3);
		zug[7] = this.square + "" + (char) (letter - 2) + "" + (char) (zahl - 2);
		zug[8] = this.square + "" + (char) (letter - 3) + "" + (char) (zahl + 3);
		zug[9] = this.square + "" + (char) (letter - 2) + "" + (char) (zahl + 2);
		zug[10] = this.square + "" + (char) (letter + 3) + "" + (char) (zahl - 3);
		zug[11] = this.square + "" + (char) (letter + 2) + "" + (char) (zahl - 2);

		int zaehler = 0;

		for (int i = 0; i < zug.length; i++) {
			zug[i] = (zug[i].charAt(2) == 'i' || zug[i].charAt(2) == 'j' || zug[i].charAt(2) == 'k'
					|| ((char) (zug[i].charAt(2) + 1)) == 'a' || ((char) (zug[i].charAt(2) + 2)) == 'a'
					|| ((char) (zug[i].charAt(2) + 3)) == 'a' || (zug[i].charAt(3)) == '0' || zug[i].charAt(3) == '/'
					|| zug[i].charAt(3) == '.' || zug[i].charAt(3) == '9' || zug[i].charAt(3) == ':'
					|| zug[i].charAt(3) == ';') ? "k" : zug[i];
		}
		for (int i = 0; i < zug.length; i++) {
			for (String besetzt : besetzteFelder) {
				zug[i] = (zug[i].equals(this.square + "" + besetzt)) ? "k" : zug[i];
				if (zug[i].equals("k")) {
					zaehler++;
					break;
				}
			}
		}
		String[] help = new String[zug.length - zaehler];
		zaehler = 0;
		for (int i = 0; i < help.length; i++) {
			while (zug[i + zaehler].equals("k")) {
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
				? (Globals.darkSquare(this.square) ? Globals.ts_female_horse_dark : Globals.ts_female_horse_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_horse_dark : Globals.ts_male_horse_light);
	}

}
