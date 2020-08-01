package hausaufgabe9;

/**
 * Die Klasse Move repraesentiert einen einzelnen Zug.
 *
 * Es gibt zwei Konstruktoren. Einer bekommt Ausgangsfeld und Zielfeld
 * uebergeben, der andere bekommt nur den eingegebenen Zug in der Form
 * <Ausgangsfeld><Zielfeld> als String uebergeben, also z. B. "a7c5" fuer den
 * Zug von "a7" nach "c5".
 */
public class Move {

	private String zug;

	public Move(String from, String to) {
		zug = from + to;
	}

	public Move(String move) {
		zug = move;
	}

	@Override
	public String toString() {
		// Rueckgabe exakt in der Form <Ausgangsfeld><Zielfeld> als String,
		// also z. B. "b2b3" fuer den Zug eines Tiers von "b2" nach "b3".
		return zug;
	}

	public boolean equals(Object other) {
		if (other instanceof String && this.zug != null) {
			return zug.equals((String) other);
		}
		if (other instanceof Move && this.zug != null) {
			return zug.equals(((Move) other).toString());
		}
		return false;
	}

}
