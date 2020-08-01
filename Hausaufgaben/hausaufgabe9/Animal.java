package hausaufgabe9;

/**
 * Oberklasse fuer Tiere.
 */
public abstract class Animal {

	// Attribute fuer den allen Tieren gemeinen Teil des Tierzustands
	public boolean female; // Weibchen?
	public boolean alive; // Lebendig?
	public String square; // Auf welchem Feld? (genau zwei Zeichen, z. B. "e4")
	public Position position; // Auf welchem Spielbrett?
	protected boolean aliveeerledigt = false;

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Animal(boolean female) {
		this.female = female;
		this.alive = true;
	}

	/**
	 * Ermittelt die moeglichen Zuege gemaess den Spielregeln, die das Tier von
	 * seinem Feld aus in der aktuellen Position ausfuehren kann.
	 *
	 * Muss von jeder einzelnen Tierklasse ueberschrieben werden.
	 */
	public Move[] possibleMoves() {
		return null;
	}

	/**
	 * liefert alle möglichen Züge als einen String zurück
	 */
	public String movesToString() {
		String str = "";
		Move[] m = possibleMoves();
		if (m != null) {
			for (int i = 0; i < m.length; i++) {
				if ((i + 1) == m.length)
					str = str + m[i].toString() + "\n";
				else
					str = str + m[i].toString() + ", ";
			}
		}
		return str;
	}

	/**
	 * Wird aufgerufen nach jeder Spielrunde (quasi am Ende vom Tag - jede
	 * Spielrunde zaehlt als ein Tag).
	 *
	 * Muss in jeder einzelnen Tierklasse ueberschrieben sein!
	 */
	public abstract void sunset();// {
	// Methode (und Klasse Animal) sollten eigentlich als abstract
	// deklariert sein.
	// Kommt spaeter in der Vorlesung noch dran...
	// Zum Verstaendnis reicht es, dass diese Methode ueberschrieben werden
	// muss.
	// (Die folgende Zeile wird dann auch nie ausgefuehrt.)
	// throw new RuntimeException("Method sunset should have been overridden");
	// }

}
