package hausaufgabe11;

public class NotEnoughNumber extends NotEnoughExc {

	public NotEnoughNumber(int should, int is) {
		super(should, is);
	}

	public String toString() {
		String str = "Sie benötigen mindestens " + should + " Zahlen - haben aber nur " + is;
		return str;
	}
}
