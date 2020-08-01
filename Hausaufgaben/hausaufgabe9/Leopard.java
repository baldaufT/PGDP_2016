package hausaufgabe9;

public class Leopard extends Predator {
	// possibleMoves() ... liefert null String also schon strange... in bewegung
	// nicht getestet aber gab auch schon probleme

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Leopard(boolean female) {
		super(female);
		withoutFood = startwert = 5;
	}

	// TODO siehe eltern klasse possibleMoves() überschreiben
	// bei den possibleMoves() - Methoden muss ich noch die Züge die
	// wegen den Zwischenfeldern nicht ausführbar sind rausnehmen, damit die
	// bei den Gültigkeits Test in GAME erst gar nicht auftauchen

	@Override
	public Move[] possibleMoves() {
		if (this.alive == false)
			return null;
		String[] zug = new String[28];
		String[] besetzteFelder = this.position.getAnimalPlaces(this.female, true);
		char zahl = this.square.charAt(1);
		char letter = this.square.charAt(0);
		int zaehler = 0;

		int helpzaehler = 0;
		int intzahl = (int) zahl - 48;
		
		
		
		//suchschleifen
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

		// diagonal
		char hletter = letter;
		for (int i = intzahl + 1; i < 9; i++) {
			hletter++;
			if (hletter > 104)
				break;
			zug[helpzaehler] = this.square + "" + (char) hletter + "" + i;
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
		hletter = letter;
		for (int i = intzahl - 1; i > 0; i--) {
			hletter++;
			if (hletter > 104)
				break;
			zug[helpzaehler] = this.square + "" + (char) hletter + "" + i;
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
		hletter = letter;
		for (int i = intzahl + 1; i < 9; i++) {
			hletter--;
			if (hletter < 96)
				break;
			zug[helpzaehler] = this.square + "" + (char) hletter + "" + zahl;
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
		hletter = letter;
		for (int i = intzahl - 1; i > 0; i--) {
			hletter--;
			if (hletter < 96)
				break;
			zug[helpzaehler] = this.square + "" + (char) hletter + "" + zahl;
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

		// weiter

		if (zug[0] == null)
			return null;

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

		String[] help = new String[zaehler];
		zaehler = 0;
		for (int i = 0; i < help.length; i++) {
			while (zug[i + zaehler] != null && zug[i + zaehler].equals("k")) {
				zaehler++;
			}
			help[i] = zug[i + zaehler];
			zaehler = 0;
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
				? (Globals.darkSquare(this.square) ? Globals.ts_female_leopard_dark : Globals.ts_female_leopard_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_leopard_dark : Globals.ts_male_leopard_light);
	}

}
