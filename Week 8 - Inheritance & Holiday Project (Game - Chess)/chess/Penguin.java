package hausaufgabe9;

public class Penguin extends Predator {

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Penguin(boolean female) {
		super(female);
		withoutFood = startwert = 12;
	}

	@Override
	public Move[] possibleMoves() {
		if(this.alive == false)
			return null;
		String[] zug = new String[8];
		String[] besetzteFelder = this.position.getAnimalPlaces(this.female, true);
		char zahl = this.square.charAt(1);
		char letter = this.square.charAt(0);

		zug[0] = this.square + "" + (char) letter + "" + (char) (zahl - 1);
		zug[1] = this.square + "" + (char) letter + "" + (char) (zahl + 1);
		zug[2] = this.square + "" + (char) (letter + 1) + "" + (char) (zahl - 1);
		zug[3] = this.square + "" + (char) (letter + 1) + "" + (char) (zahl + 1);
		zug[4] = this.square + "" + (char) (letter + 1) + "" + (char) zahl;
		zug[5] = this.square + "" + (char) (letter - 1) + "" + (char) (zahl - 1);
		zug[6] = this.square + "" + (char) (letter - 1) + "" + (char) (zahl + 1);
		zug[7] = this.square + "" + (char) (letter - 1) + "" + (char) zahl;

		int zaehler = 0;

		for (int i = 0; i < zug.length; i++) {
			zug[i] = (zug[i].charAt(2) == 'i' || ((char) (zug[i].charAt(2) + 1)) == 'a' || zug[i].charAt(3) == 0
					|| zug[i].charAt(3) == 9) ? "k" : zug[i];
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
				? (Globals.darkSquare(this.square) ? Globals.ts_female_penguin_dark : Globals.ts_female_penguin_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_penguin_dark : Globals.ts_male_penguin_light);
	}

}
