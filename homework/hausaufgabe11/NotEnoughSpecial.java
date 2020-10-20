package hausaufgabe11;

public class NotEnoughSpecial extends NotEnoughExc {

	public NotEnoughSpecial(int should, int is) {
		super(should, is);
	}

	public String toString() {
		String str = "Sie benötigen mindestens " + should + " Sonderzeichen - haben aber nur " + is;
		return str;
	}
}
