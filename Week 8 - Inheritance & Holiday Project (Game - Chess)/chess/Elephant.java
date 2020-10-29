package hausaufgabe9;

public class Elephant extends Vegetarian {

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Elephant(boolean female) {
		super(female);
	}

	@Override
	public Move[] possibleMoves() {
		if(this.alive == false)
			return null;
		String[] zug = new String[14];
		String[] besetzteFelder = this.position.getAnimalPlaces(this.female, false);
		char zahl = this.square.charAt(1);
		char letter = this.square.charAt(0);
		int zaehler = 0;

		int helpzaehler = 0;
		int intzahl = (int) zahl - 48;
		for (int i = intzahl + 1; i < 9; i++) {
			zug[helpzaehler] = this.square + "" + (char) letter + "" + i;
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
		for (int i = intzahl - 1; i > 0; i--) {
			zug[helpzaehler] = this.square + "" + (char) letter + "" + i;
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
		for (int i = (letter + 1); i < 105; i++) {
			zug[helpzaehler] = this.square + "" + (char) (i) + "" + zahl;
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
		for (int i = (letter - 1); i > 96; i--) {
			zug[helpzaehler] = this.square + "" + (char) (i) + "" + zahl;
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

		String[] help = new String[zaehler];
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
				? (Globals.darkSquare(this.square) ? Globals.ts_female_elephant_dark : Globals.ts_female_elephant_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_elephant_dark : Globals.ts_male_elephant_light);
	}

}
