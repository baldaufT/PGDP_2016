package hausaufgabe11;

public class IllegalCharExc extends Exception {

	private final char used;

	public IllegalCharExc(char used) {
		this.used = used;
	}

	public String toString() {
		return "Sie haben das unerlaubte Zeichen: " + used() + " verwendet. ";
	}

	private String used() {
		String str = "";
		if (used == '\n')
			str = "line break (\\n)";
		else if (used == '\t')
			str = "tab (\\t)";
		else if (used == '\r')
			str = "carriage return (\\r)";
		else if (used == '\b')
			str = "backspace (\\b)";
		else if (used == '\f')
			str = "formfeed (\\f)";
		else
			str = used + "";
		return str;
	}
}
