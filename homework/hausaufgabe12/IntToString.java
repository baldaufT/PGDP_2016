package hausaufgabe12;

public class IntToString implements Fun<Integer, String> {

	@Override
	public String apply(Integer x) {
		if (x == null)
			return "";
		return x + "h";
	}
}
