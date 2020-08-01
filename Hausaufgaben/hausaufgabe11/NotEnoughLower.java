package hausaufgabe11;

public class NotEnoughLower extends NotEnoughLetter {

	public NotEnoughLower(int should, int is) {
		super(should, is);
	}

	public String toString() {
		String str = "Sie benötigen mindestens " + should + " Kleinbuchstabend - haben aber nur " + is;
		return str;
	}
}
