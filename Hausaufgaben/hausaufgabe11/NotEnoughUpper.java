package hausaufgabe11;

public class NotEnoughUpper extends NotEnoughLetter {

	public NotEnoughUpper(int should, int is) {
		super(should, is);
	}

	public String toString() {
		String str = "Sie benötigen mindestens " + should + " Großbuchstabend - haben aber nur " + is;
		return str;
	}
}
