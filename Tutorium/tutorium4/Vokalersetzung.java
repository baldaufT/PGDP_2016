package tutorium4;

public class Vokalersetzung extends MiniJava {

	public static void main(String[] args) {
		String umwandlung = readString("Geben Sie den gewünschten Vokal ein: ");
		char um;
		um = umwandlung.charAt(0);		
		String text = "Hat der alte Hexenmeister\n" + "sich doch einmal wegbegeben!\n"
				+ "Und nun sollen seine Geister\n" + "auch nach meinem Willen leben.\n" + "Seine Wort und Werke\n"
				+ "merkt ich und den Brauch,\n" + "und mit Geistesstärke\n" + "tu ich Wunder auch.\n"
				+ "Walle! walle\n" + "Manche Strecke,\n" + "daß, zum Zwecke,\n" + "Wasser fließe\n"
				+ "und mit reichem, vollem Schwalle\n" + "zu dem Bade sich ergieße.";
		// umwandlung
		char[] liste = new char[text.length()];
		for (int i = 0; i < text.length(); i++) {
			liste[i] = text.charAt(i);
			if (liste[i] == 'a' || liste[i] == 'A') {
				liste[i] = um;
			}
			if (liste[i] == 'e' || liste[i] == 'E') {
				liste[i] = um;
			}
			if (liste[i] == 'i' || liste[i] == 'I') {
				liste[i] = um;
			}
			if (liste[i] == 'o' || liste[i] == 'O') {
				liste[i] = um;
			}
			if (liste[i] == 'u' || liste[i] == 'U') {
				liste[i] = um;
			}
		}
		String helf = "";
		for (int i = 0; i < text.length(); i++) {
			helf = helf + liste[i];
		}
		text = helf;
		// ausgabe
		write(text);
	}
}
