package hausaufgabe5;

public class FunctionalCaesar extends MiniJava {
	/*
	 * 3.Aufgabe: Die gemeinsten Aufgaben stellt Raphaela Schluessel : 9 heraus
	 * gefunden durch ergaenzung in main: for(int i = 0;i < 26; i++){
	 * System.out.println(decrypt(input, i)+i); }
	 * 
	 */
	public static char shift(char c, int k) {
		char v = c;
		int s = 0;
		if (k > 0) {
			s = k % 26;
		} else if (k < 0) {
			s = 26 - (k * (-1) % 26);
		}
		if (c < 95)
			v = (char) ((((c + s) - 65) % 26) + 65);
		else
			v = (char) ((((c + s) - 97) % 26) + 97);
		return v;
	}

	public static String encrypt(String s, int k) {
		char[] liste = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			liste[i] = s.charAt(i);
			if ((s.charAt(i) > 64 && s.charAt(i) < 91) || (s.charAt(i) > 96 && s.charAt(i) < 123)) {
				liste[i] = shift(s.charAt(i), k);
			}
		}
		String out = liste[0] + "";
		for (int i = 1; i < s.length(); i++) {
			out = out + liste[i];
		}
		return out;
	}

	public static String decrypt(String s, int k) {
		return encrypt(s, -(k % 26));
	}

	public static void main(String[] args) {
		String input = readString();
		int k = read();
		String out = encrypt(input, k);
		write(out);
	}

}
