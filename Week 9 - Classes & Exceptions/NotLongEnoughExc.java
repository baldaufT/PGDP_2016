package hausaufgabe11;

public class NotLongEnoughExc extends Exception {

	private final int should, is;

	public NotLongEnoughExc(int should, int is) {
		this.should = should;
		this.is = is;
	}

	public String toString() {
		return "Ihr Passwort hat lediglich " + is + " Buchstaben, jedoch muss ein Passwort mindestens " + should
				+ " Buchstaben lang sein!";
	}
}
