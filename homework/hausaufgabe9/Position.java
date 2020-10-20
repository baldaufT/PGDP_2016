package hausaufgabe9;

/**
 * Die Klasse Position repraesentiert eine Spielsituation.
 *
 */
public class Position {

	/**
	 * Die Tiere werden intern in einem Array gespeichert. nrAnimals gibt an,
	 * wie viele Tiere auf dem Brett sind. Diese sind in myAnimals an den
	 * Positionen 0 bis nrAnimals-1 enthalten.
	 *
	 * Es ist empfohlen, aber nicht vorgeschrieben, diese Attribute zu
	 * verwenden.
	 *
	 * Falls die beiden Attribute NICHT verwendet werden, muss die Ausgabe der
	 * Spielposition unten entsprechend auf die verwendete Datenstruktur
	 * angepasst werden. Die toString-Methode darf dabei nicht veraendert
	 * werden, muss jedoch die selbe Rueckgabe liefern. D.h. es ist dann
	 * notwendig, die Hilfsmethode boardRepresentation auf die verwendete
	 * Datenstruktur anzupassen.
	 */
	private Animal[] myAnimals;
	private int nrAnimals;
	private int nrWAnimal = 16, nrMAnimal = 16;
	/**
	 * Spieler, der als naechstes ziehen darf ('M' oder 'W'). Wird jedes Mal
	 * aktualisiert, wenn eine Seite ihre Zuege ausfuehrt.
	 */
	private char next = 'W';

	/**
	 * liefert den char für den nächsten Spieler zurück
	 */
	public char getNext() {
		return next;
	}

	/**
	 * reduziert die Variable nrAnimals um 1
	 * 
	 * @return true außer die variable ist bereits auf 0
	 */
	public boolean reduceNrAnimal(boolean female) {
		if (nrAnimals == 0)
			return false;
		nrAnimals--;
		if (female == true) {
			nrWAnimal--;
		} else
			nrMAnimal--;
		if (nrMAnimal + nrWAnimal != nrAnimals) {
			System.out.println("Es gibt ein internes Berechnungsproblem mit den Anzahlen der einzelnen Spieltiere!");
			return false;
		}
		return true;
	}

	public int getNumberOfAnimals() {
		return nrAnimals;
	}

	public int getNumberOfAnimals(boolean female) {
		return female ? nrWAnimal : nrMAnimal;
	}

	/**
	 * Stellt die Anfangsposition des Spiels her. Der Parameter gibt an, welche
	 * Seite beginnt ('M' oder 'W').
	 */
	public void reset(char movesNext) {
		this.next = movesNext;
		nrAnimals = 32;
		myAnimals = new Animal[32];
		int k = 1;
		char help = 'a';
		for (int i = 0; i < nrAnimals; i++) {
			if (i < 16) {
				myAnimals[i] = (i == 0 || i == 7) ? new Snake(true)
						: (i == 1 || i == 6) ? new Elephant(true)
								: (i == 2 || i == 5) ? new Horse(true)
										: (i == 3 || i == 4) ? new Leopard(true)
												: (i == 8 || i == 15) ? new Penguin(true) : new Rabbit(true);
			} else {
				myAnimals[i] = (i == 24 || i == 31) ? new Snake(false)
						: (i == 25 || i == 30) ? new Elephant(false)
								: (i == 26 || i == 29) ? new Horse(false)
										: (i == 27 || i == 28) ? new Leopard(false)
												: (i == 16 || i == 23) ? new Penguin(false) : new Rabbit(false);

			}
			myAnimals[i].position = this;
			myAnimals[i].square = help + "" + k;
			help++;
			if (help == 'i') {
				help = 'a';
				k++;
				if (k == 3)
					k = 7;
			}
		}
		System.out.println(toString());
	}

	/**
	 * Tiere bekommen
	 */
	public Animal[] getAnimals() {
		return myAnimals;
	}

	/**
	 * Tiere auf dem Spielfeld finden die einen Platz versperren also
	 * gegnerische Vegetarier werden nicht mitgeliefert
	 */
	public String[] getAnimalPlaces(boolean female, boolean ispredator) {
		String[] str = new String[32];
		for (int i = 0; i < myAnimals.length; i++) {
			if (!(ispredator && myAnimals[i].female != female && myAnimals[i] instanceof Vegetarian))
				str[i] = myAnimals[i].square;		
		}
		return str;
	}

	/**
	 * Fuehrt die uebergebenen Zuege fuer einen der Spieler aus. Die Reihenfolge
	 * soll keinen Unterschied machen. Diese Methode geht davon aus, dass dies
	 * bereits ueberprueft wurde.
	 *
	 * Der Zustand des Spiels wird entsprechend angepasst, d. h. ein Spiel kann
	 * von der Anfangsposition aus allein mittels Aufrufen dieser Methode
	 * gespielt werden. Insbesondere wechselt durch den Aufruf das Zugrecht, da
	 * M und W abwechselnd ziehen.
	 *
	 * @param move
	 *            Array mit den Zuegen, die ausgefuehrt werden sollen.
	 *
	 */
	public void applyMoves(Move[] move) {
		for (int i = 0; i < move.length; i++) {
			for (int k = 0; k < myAnimals.length; k++) {
				if (myAnimals[k].square.equals(move[i].toString().charAt(0) + "" + move[i].toString().charAt(1))) {
					myAnimals[k].square = "" + move[i].toString().charAt(2) + move[i].toString().charAt(3);
					break;
				}
			}
		}
		// abschluss nach dem die Züge ausgeführt wurden
		for (Animal a : myAnimals) {
			a.sunset();
		}
		next = (next == 'M') ? 'W' : 'M';
		System.out.println(toString());
		theWinner();
	}

	/**
	 * Ermittelt, ob/wer gewonnen hat.
	 *
	 * @return 'W' falls W gewonnen hat, 'M' falls M gewonnen hat, 'N' falls das
	 *         Spiel unentschieden zu Ende ist, 'X' falls das Spiel noch nicht
	 *         zu Ende ist.
	 *
	 */
	public char theWinner() {
		int predatorzahl = 0;
		for (int i = 0; i < myAnimals.length; i++) {
			if (myAnimals[i].alive == true && myAnimals[i] instanceof Predator)
				predatorzahl++;
		}
		if (nrMAnimal == 0 && nrWAnimal == 0)
			return 'N';
		if (nrMAnimal == 0 && nrWAnimal > 0 && predatorzahl == 0)
			return 'W';
		if (nrMAnimal > 0 && nrWAnimal == 0 && predatorzahl == 0)
			return 'M';
		if (nrMAnimal < 0 || nrWAnimal < 0) {
			System.out.println(
					"Wir haben eine negative Anzahl von Tieren. Es muss ein Fehler aufgetreten sein. Bitte entschuldigen Sie dies!");
		}
		if (predatorzahl == 0)
			return nrMAnimal > nrWAnimal ? 'M' : (nrMAnimal == nrWAnimal) ? 'N' : 'W';
		return 'X';
	}

	// Ausgabe der Spielposition

	private static final int[] I = { 8, 7, 6, 5, 4, 3, 2, 1 };
	private static final String[] J = { "a", "b", "c", "d", "e", "f", "g", "h" };

	private static int toIndex(String s) {
		return (s.charAt(0) - 'a');
	}

	// Erzeugt eine 2D-Repraesentation der Spielposition.
	// Darf ggf. auf neue Datenstruktur angepasst werden (s.o.)
	// Die Rueckgabe ist ein zweidimensionales Array, welches
	// jedem Feld das darauf befindliche Tier (oder null) zuordnet.
	// Dabei laeuft der erste Index von der 'a'-Linie zur 'h'-Linie,
	// der zweite von der 1. zur 8. Reihe. D.h. wenn z.B. bei a[3][7]
	// ein Tier ist, ist das zugehörige Feld "d8" (vierter Buchstabe,
	// achte Zahl).
	public Animal[][] boardRepresentation() {
		Animal[][] a = new Animal[8][8];
		for (int i : I) {
			for (String j : J) {
				for (int k = 0; k < myAnimals.length; k++) {
					if (null == myAnimals[k]) {
						break;
					}
					if (myAnimals[k].square.equals(j + i)) {
						a[toIndex(j)][i - 1] = myAnimals[k];
					}
				}
			}
		}
		return a;
	}

	@Override
	public String toString() {
		String str = "   a b c d e f g h\n";
		Animal[][] ani = boardRepresentation();
		for (int i : I) {
			str += (i + " ");
			for (String j : J) {
				if (null == ani[toIndex(j)][i - 1]) {
					str += (i + toIndex(j)) % 2 == 1 ? Globals.ts_empty_square_dark : Globals.ts_empty_square_light;
				} else {
					str += ani[toIndex(j)][i - 1].toString();
				}
			}
			str += " " + i + "\n";
		}
		str += "   a b c d e f g h\nIt is " + next + "'s turn.\n";
		return str;
	}

}
