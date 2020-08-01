package hausaufgabe4;

public class Verhext extends MiniJava {

	static String input, hexzahl;
	static int output = 0;
	static boolean negativ = false, fehlerhaft = true;

	public static void main(String[] args) {
		input = readString("Geben Sie Ihre hexadezimal Zahl ein: ") + "f";
		while (fehlerhaft == true) {
			fRaus();
			// jetzt kommt die Kontrolle
			// wenn diese passt dann muss fehlerhaft = false;
			if (input == null) {
				fehlerhaft = true;
			} else {
				fehlerhaft();
			}
			// Ausgabe
			if (fehlerhaft == true) {
				write("Ihre Eingabe ist leider nicht korrekt. Bitte wiederholen Sie Ihre Eingabe!");
				input = readString("Geben Sie Ihre hexadezimal Zahl ein: ") + "f";
			}
		}
		anpassen();
		umformen();
		if (negativ == true) {
			output = -output;
		}
		write(output);
	}

	private static void fRaus(){
		// f wieder raus holen
				char[] help = new char[(input.length()-1)];
				String helpinput = input.charAt(0)+"";
				for (int i = 0; i < (input.length()-1); i++) {
					help[i] = input.charAt(i);
				}
				for(int i = 1; i < (input.length()-1); i++){
					helpinput = helpinput + help[i];
				}
				input = helpinput;
	}
	
	private static void start() {
		while (fehlerhaft == true) {
			// jetzt kommt die Kontrolle
			// wenn diese passt dann muss fehlerhaft = false;
			fehlerhaft();
			// Ausgabe
			if (fehlerhaft == true) {
				write("Ihre Eingabe ist leider nicht korrekt. Bitte wiederholen Sie Ihre Eingabe!");
				input = readString("Geben Sie Ihre hexadezimal Zahl ein: ");
			}
		}
		anpassen();
		umformen();
		if (negativ == true) {
			output = -output;
		}
	}

	private static void anpassen() {
		char[] liste = new char[input.length()];
		int richtigeLaenge = input.length();
		for (int i = 0; i < input.length(); i++) {
			liste[i] = input.charAt(i);
		}
		if (liste[0] == '-') {
			negativ = true;
			for (int i = 0; i < input.length() - 1; i++) {
				liste[i] = input.charAt(i + 1);
			}
			richtigeLaenge = richtigeLaenge - 1;
		}
		for (int i = 0; i < richtigeLaenge; i++) {
			if (liste[i] == '_') {
				for (int k = i; k < richtigeLaenge; k++) {
					liste[k] = liste[k + 1];
				}
				richtigeLaenge = richtigeLaenge - 1;
			}
		}
		char[] richtigeListe = new char[richtigeLaenge];
		for (int i = 0; i < richtigeLaenge; i++) {
			richtigeListe[i] = liste[i];
		}
		hexzahl = richtigeListe[0] + "";
		for (int i = 1; i < richtigeLaenge; i++) {
			hexzahl = hexzahl + richtigeListe[i];
		}
	}

	private static void fehlerhaft() {
		int laenge = input.length();
		boolean negativ = false;

		// erstmal testen ob die korrekten Ziffern verwendet werden
		for (int i = 0; i < laenge; i++) {
			if (input.charAt(i) == 'X' || input.charAt(i) == 'x' || input.charAt(i) == '0' || input.charAt(i) == '1'
					|| input.charAt(i) == '2' || input.charAt(i) == '3' || input.charAt(i) == '4'
					|| input.charAt(i) == '5' || input.charAt(i) == '6' || input.charAt(i) == '7'
					|| input.charAt(i) == '8' || input.charAt(i) == '9' || input.charAt(i) == 'A'
					|| input.charAt(i) == 'B' || input.charAt(i) == 'C' || input.charAt(i) == 'D'
					|| input.charAt(i) == 'E' || input.charAt(i) == 'F' || input.charAt(i) == '-'
					|| input.charAt(i) == '_') {
				fehlerhaft = false;
			} else {
				fehlerhaft = true;
				return;
			}
		}
		// jetzt noch testen ob die Syntax stimmt

		// wenn dann nur ein Minus vorne dran
		for (int i = 1; i < laenge; i++) {
			if (input.charAt(i) == '-') {
				fehlerhaft = true;
				return;
			}
		}
		// negativ?
		if (input.charAt(0) == '-') {
			negativ = true;
		}
		// start mit null und kleinem oder großem x
		// testen dass x nicht woanders vorkommt
		if (negativ == true) {
			if (input.charAt(1) != '0') {
				fehlerhaft = true;
				return;
			}
			if (input.charAt(2) != 'x' && input.charAt(2) != 'X') {
				fehlerhaft = true;
				return;
			}
			for (int i = 3; i < laenge; i++) {
				if (input.charAt(i) == 'x' || input.charAt(i) == 'X') {
					fehlerhaft = true;
					return;
				}
			}
			for (int i = 0; i < 4; i++) {
				if (input.charAt(i) == '_') {
					fehlerhaft = true;
					return;
				}
			}
			if (input.charAt(3) == '0') {
				System.out.println(
						"Sie haben redundante Ziffern eingegeben. Geben Sie beim nächsten Mal bitte keine führenden Nullen nach '0x...' ein! Die Berechnung findet dennoch statt.");
			}
		} else {
			if (input.charAt(0) != '0') {
				fehlerhaft = true;
				return;
			}
			if (input.charAt(1) != 'x' && input.charAt(1) != 'X') {
				fehlerhaft = true;
				return;
			}
			for (int i = 2; i < laenge; i++) {
				if (input.charAt(i) == 'x' || input.charAt(i) == 'X') {
					fehlerhaft = true;
					return;
				}
			}
			for (int i = 0; i < 3; i++) {
				if (input.charAt(i) == '_') {
					fehlerhaft = true;
					return;
				}
			}
			if (input.charAt(2) == '0') {
				System.out.println(
						"Sie haben redundante Ziffern eingegeben. Geben Sie beim nächsten Mal bitte keine führenden Nullen nach '0x...' ein! Die Berechnung findet dennoch statt.");
			}
		}

		// Unterstriche testen
		if (input.charAt(laenge - 1) == '_') {
			fehlerhaft = true;
			return;
		}
	}

	private static void umformen() {
		int laenge = hexzahl.length();
		char[] liste = new char[laenge - 2];
		for (int i = 0; i < laenge - 2; i++) {
			liste[i] = hexzahl.charAt(i + 2);
		}
		char[] gedreht = new char[laenge - 2];
		for (int i = 0; i < laenge - 2; i++) {
			gedreht[laenge - 3 - i] = hexzahl.charAt(i + 2);
		}
		for (int i = 0; i < laenge - 2; i++) {
			output = output + wertbestimmen(gedreht[i], i);
		}
	}

	private static int wertbestimmen(char a, int exp) {
		int wert = 0, g = 0;
		if (a == '1')
			g = 1;
		if (a == '2')
			g = 2;
		if (a == '3')
			g = 3;
		if (a == '4')
			g = 4;
		if (a == '0')
			g = 0;
		if (a == '5')
			g = 5;
		if (a == '6')
			g = 6;
		if (a == '7')
			g = 7;
		if (a == '8')
			g = 8;
		if (a == '9')
			g = 9;
		if (a == 'A')
			g = 10;
		if (a == 'B')
			g = 11;
		if (a == 'C')
			g = 12;
		if (a == 'D')
			g = 13;
		if (a == 'E')
			g = 14;
		if (a == 'F')
			g = 15;

		wert = g * pow(16, exp);

		return wert;
	}

	private static int pow(int x, int y) {
		return java.math.BigInteger.valueOf(x).pow(y).intValueExact();
	}

	public static int hexToInt(String str) {
		input = str;
		start();
		negativ = false;
		fehlerhaft = true;
		return output;
	}
}