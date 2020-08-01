package tutorium8;

public class CompareObjects {

	public static String copyString(String t) {
		String c = "";
		for (int i = 0; i < t.length(); i++)
			c += t.charAt(i);
		return c;
	}

	public static boolean equals(String a, String b) {
		if (a.length() != b.length())
			return false;
		else {
			for (int i = 0; i < a.length(); i++) {
				if (a.charAt(i) != b.charAt(i))
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		String z = "hallo";
		String t = "hal" + "lo";
		System.out.println(t + " == " + z + " = " + (t == z));
		System.out.println(t + " == " + z + " = " + equals(z, t));
	}
}
