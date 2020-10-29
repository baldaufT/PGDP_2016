package hausaufgabe9;

/**
 * Die Klasse Main enthaelt das Hauptprogramm.ok
 *
 * Im Hauptprogramm wird zuerst der Benutzer gefragt, wer das Spiel beginnen
 * soll.
 *
 * Dann wird das Spiel gestartet.
 *
 */
public class Main {

	private void start() {
		boolean mstart = true;
		int start = IO.readInt("Welcher Spieler beginnt? - (1) für M, (0) für W:\n", 0, 1);
		if (start == 1)
			mstart = false;
		// jetzt kommt das Spiel
		Game g = new Game();
		g.startGame(mstart);
		String nochmal = IO.readString("\nWollen Sie nochmal spielen? - ('ja' für noch eine Runde.)");
		if (nochmal.equals("ja"))
			start();
	}

	public Main() {
		start();
	}

	public static void main(String args[]) {
		new Main();
	}
}
