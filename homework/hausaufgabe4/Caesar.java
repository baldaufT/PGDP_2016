package hausaufgabe4;

public class Caesar extends MiniJava {

	public static void main(String[] args) {
		String text = readString("Welchen Text wollen Sie verschluesseln? ");
		int code = read("Mit welchem Shift wollen Sie verschluesseln? ");
		int length = text.length();
		char[] outputliste = new char[length];
		// listen werden deklariert und initialisiert
		char[] listeG = new char[26];
		char[] listek = new char[26];
		for (int i = 0; i < 26; i++) {
			listeG[i] = (char) ((char) 'A' + i);
			listek[i] = (char) ((char) 'a' + i);
		}
		boolean buchstabe = false;
		// outputListe erzeugen
		for (int i = 0; i < length; i++) {
			buchstabe = false;
			for (int k = 0; k < 26; k++) {
				if (code >= 0) {
					if (text.charAt(i) == listeG[k]) {
						outputliste[i] = listeG[(k + code) % 26];
						buchstabe = true;
					}
					if (text.charAt(i) == listek[k]) {
						outputliste[i] = listek[(k + code) % 26];
						buchstabe = true;
					}
				} else {
					int codem = code*(-1)%26;
					if (text.charAt(i) == listeG[k]) {
						outputliste[i] = listeG[(k + 26 - codem) % 26];
						buchstabe = true;
					}
					if (text.charAt(i) == listek[k]) {
						outputliste[i] = listek[(k + 26 - codem) % 26];
						buchstabe = true;
					}
				}
			}
			if (buchstabe == false) {
				outputliste[i] = text.charAt(i);
			}
		}

		// output zusammen setzen und ausgeben
		String output = "";
		for (int i = 0; i < length; i++) {
			output = output + outputliste[i];
		}
		write(output);
	}
}
