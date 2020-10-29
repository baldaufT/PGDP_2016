package tutorium5;

public class Palindrom extends MiniJava {
	static String eingabe = "f";

	public static void main(String[] args) {
		boolean eingabe2 = false;
		while (eingabe2 == false) {
			eingabe = readString();
			if (eingabe.length() > 0)
				eingabe2 = true;

		}
		String wort = "ein";
		if (isPalindrome(toCharArray(toLowerCase(eingabe))) == false)
			wort = "kein";
		write("Ihr eingegebener String ist " + wort + " Palidrom");
	}

	public static String toLowerCase(String input) {
		String inpu = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) > 64 && input.charAt(i) < 91) {
				inpu = inpu + (char) (input.charAt(i) + 32);
			} else {
				inpu = inpu + input.charAt(i);
			}
		}
		return inpu;
	}

	public static char[] toCharArray(String input) {
		char[] cha = new char[input.length()];
		for (int i = 0; i < input.length(); i++) {
			cha[i] = input.charAt(i);
		}
		return cha;
	}

	public static boolean isPalindrome(char[] input) {
		boolean[] pal = new boolean[input.length];
		for (int i = 0; i < input.length; i++) {
			if (input[i] == input[input.length - 1 - i])
				pal[i] = true;
			else {
				pal[i] = false;
				return false;
			}
		}
		return true;
	}
}
