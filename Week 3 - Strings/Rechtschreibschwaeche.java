package tutorium4;

public class Rechtschreibschwaeche extends MiniJava {

	public static void main(String[] args) {
		String str = readString();
		char[] liste = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			liste[i] = str.charAt(i);
		}
		for (int i = 0; i < str.length(); i++) {
			if (liste[i] < 92 && liste[i] > 64) {
				liste[i] = (char) (liste[i] + 32);
			} else if (liste[i] > 96 && liste[i] < 123) {
				liste[i] = (char) (liste[i] - 32);
			}
		}
		String help = liste[0] + "";
		for (int i = 1; i < str.length(); i++) {
			help = help + liste[i];
		}
		write(help);
	}

}
