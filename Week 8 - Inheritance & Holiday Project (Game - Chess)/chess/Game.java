package hausaufgabe9;

/**
 * Die Klasse Game fuehrt die Benutzerinteraktion durch.
 *
 */

public class Game {

	private Position pos;
	private boolean schoneinfleischfresser = false;

	/**
	 * Startet ein neues Spiel. Der Benutzer wird ueber das Spielgeschehen
	 * informiert.
	 *
	 * Dazu gehoert auch die Information, wie lange die einzelnen Raubtiere noch
	 * ohne Essen auskommen koennen. Diese Information soll auf Anfrage oder
	 * immer angezeigt werden.
	 *
	 * Es soll ausserdem eine Moeglichkeit geben, sich alle Zuege anzeigen zu
	 * lassen, die in der Spielsituation moeglich sind.
	 *
	 * Bei fehlerhaften Eingaben wird die Eingabe natuerlich wiederholt.
	 *
	 * Der Parameter spezifiziert, wer das Spiel beginnen darf.
	 */
	public void startGame(boolean ladiesFirst) {
		pos = new Position();
		pos.reset(ladiesFirst ? 'W' : 'M');
		Move eins = new Move(""), zwei = new Move(""), drei = new Move(""), vier = new Move("");
		boolean fleischfresser;

		System.out.println(
				"Wenn Sie alle möglichen Spielzüge für eine Runde ansehen wollen, geben Sie bei der Eingabe für Ihren \nZug 'help' ein! Speziell die Züge für ein Ausgangsfeld '<startfeld> help'\n");
		String lifedauer = IO.readString(
				"Sie können die Überlebensdauer jederzeit mit dem Begriff 'life' einsehen oder jetzt mit 'ja' dauernd anzeigen lassen!");
		while (pos.theWinner() == 'X') {
			System.out.println("\nEs sind noch " + pos.getNumberOfAnimals() + " Tiere im Spiel. Davon sind "
					+ pos.getNumberOfAnimals(true) + " weiblich und " + pos.getNumberOfAnimals(false) + " männlich!\n");
			if (lifedauer.equals("ja")) {
				printLifes();
			}
			schoneinfleischfresser = false;
			fleischfresser = false;
			int anzahl = 0;
			do {
				eins = new Move("" + IO.readString(
						"\nWelchen ersten Zug von 4 wollen Sie ausfuehren? Um keinen Zug zu machen 'Enter' ohne eine Eingabe drücken! \nFormalität beachten!"));
				for (Animal a : pos.getAnimals()) {
					if (a instanceof Predator && eins.toString().length() == 4
							&& a.square.equals(eins.toString().charAt(0) + "" + eins.toString().charAt(1))) {
						fleischfresser = true;
						break;
					}
				}
				eins = gueltigeEingabeEinzelnerMove(eins.toString(), fleischfresser) ? (eins) : new Move("k");
				if (eins.toString() == "k")
					System.out.println("Ihre Eingabe ist ungültig. Versuchen Sie es nocheinmal!");
				fleischfresser = false;
			} while (eins.toString() == "k");
			if (!eins.toString().equals(""))
				anzahl++;
			// zweiter Move
			if (!eins.toString().equals("")) {
				do {
					zwei = new Move("" + IO.readString(
							"Welchen zweiten von 4 Zügen wollen Sie ausfuehren? Um keinen Zug zu machen 'Enter' ohne eine Eingabe drücken! \nFormalität beachten!"));
					for (Animal a : pos.getAnimals()) {
						if (a instanceof Predator && zwei.toString().length() == 4
								&& a.square.equals(zwei.toString().charAt(0) + "" + zwei.toString().charAt(1)))
							fleischfresser = true;
					}
					zwei = gueltigeEingabeEinzelnerMove(zwei.toString(), fleischfresser) ? (zwei) : new Move("k");
					if (zwei.toString() == "k")
						System.out.println("Ihre Eingabe ist ungültig. Versuchen Sie es nocheinmal!");
					fleischfresser = false;
				} while (zwei.toString() == "k");
				if (!zwei.toString().equals(""))
					anzahl++;
				// dritter Move
				if (!zwei.toString().equals("")) {
					do {
						drei = new Move("" + IO.readString(
								"Welchen dritten von 4 Zügen wollen Sie ausfuehren? Um keinen Zug zu machen 'Enter' ohne eine Eingabe drücken! \nFormalität beachten!"));
						for (Animal a : pos.getAnimals()) {
							if (a instanceof Predator && drei.toString().length() == 4
									&& a.square.equals(drei.toString().charAt(0) + "" + drei.toString().charAt(1)))
								fleischfresser = true;
						}
						drei = gueltigeEingabeEinzelnerMove(drei.toString(), fleischfresser) ? (drei) : new Move("k");
						if (drei.toString() == "k")
							System.out.println("Ihre Eingabe ist ungültig. Versuchen Sie es nocheinmal!");
						fleischfresser = false;
					} while (drei.toString() == "k");
					if (!drei.toString().equals(""))
						anzahl++;
					// vierter Move
					if (!drei.toString().equals("")) {
						do {
							vier = new Move("" + IO.readString(
									"Welchen letzten Zug wollen Sie ausfuehren? Um keinen Zug zu machen 'Enter' ohne eine Eingabe drücken! \nFormalität beachten!"));
							for (Animal a : pos.getAnimals()) {
								if (a instanceof Predator && vier.toString().length() == 4
										&& a.square.equals(vier.toString().charAt(0) + "" + vier.toString().charAt(1)))
									fleischfresser = true;
							}
							vier = gueltigeEingabeEinzelnerMove(vier.toString(), fleischfresser) ? (vier)
									: new Move("k");
							if (vier.toString() == "k")
								System.out.println("Ihre Eingabe ist ungültig. Versuchen Sie es noch einmal!");
							fleischfresser = false;
						} while (vier.toString() == "k");
						if (!vier.toString().equals(""))
							anzahl++;
					}
				}
			}
			Move[] f = new Move[anzahl];
			for (int i = 0; i < f.length; i++) {
				f[i] = (i == 0) ? (eins) : (i == 1) ? zwei : (i == 2) ? drei : vier;
			}
			if (gueltigeEingabeAlleZuege(f)) {
				pos.applyMoves(f);
			} else {
				System.out.println(
						"\nLeider können Sie Ihre gewünschten Züge in der Kombination nicht ausfuehren! Versuchen Sie es nocheinmal.");
				System.out.println(pos.toString());
			}
		}
		char winner = pos.theWinner();
		if(winner == 'M')
			System.out.println("Der Gewinner ist: M. Herzlichen Glückwunsch!");
		if(winner == 'W')
			System.out.println("Der Gewinner ist: W. Herzlichen Glückwunsch!");
		if(winner == 'N')
			System.out.println("Unentschieden! Spielen Sie doch noch eine Runde.");
	}

	private void printLifes() {
		String out = "";
		Animal[] a = pos.getAnimals();
		Animal[] b = new Animal[16];
		int zaehler = 0;
		for (Animal ani : a) {
			if (ani instanceof Predator && (ani.female == (pos.getNext() == 'W' ? true : false))) {
				b[zaehler++] = ani;
			}
		}
		a = b;
		for (int i = 0; i < a.length; i++) {
			if (a[i] instanceof Predator && a[i].alive == true)
				out = out + "(" + a[i].square + ": " + ((Predator) a[i]).withoutFood + ")" + ", ";
		}
		System.out.println(out);
	}

	private void printPossibleMoves(String startfeld) {
		if (startfeld.length() != 2)
			return;
		String out = "";
		Animal[] a = pos.getAnimals();
		Animal thisan = new Rabbit(true);
		boolean gefunden = false;
		for (Animal ani : a) {
			if (ani.square.equals(startfeld)) {
				thisan = ani;
				gefunden = true;
			}
		}
		if (gefunden == false)
			return;
		out = thisan.movesToString();
		System.out.println("Mögliche Züge für das Startfeld " + startfeld + ": " + out);
	}

	private void printPossibleMoves() {
		String out = "";
		Animal[] a = pos.getAnimals();
		Animal[] b = new Animal[16];
		int zaehler = 0;
		for (Animal ani : a) {
			if (ani.female == (pos.getNext() == 'W' ? true : false)) {
				b[zaehler++] = ani;
			}
		}
		a = b;
		for (int i = 0; i < a.length; i++) {
			out = out + a[i].movesToString();
		}
		System.out.println(out);
	}

	private boolean gueltigeEingabeAlleZuege(Move[] move) {
		if (move.length < 0 || move.length > 4) {
			return false;
		}
		if (move == null || move.length == 1)
			return true;
		else if (move.length == 2) {
			if ((move[0].toString().charAt(2) + "" + move[0].toString().charAt(3))
					.equals(move[1].toString().charAt(2) + "" + move[1].toString().charAt(3))
					|| (move[0].toString().charAt(0) + "" + move[0].toString().charAt(1))
							.equals(move[1].toString().charAt(0) + "" + move[1].toString().charAt(1))) {
				return false;
			}
			return true;
		} else if (move.length == 3) {
			if ((move[0].toString().charAt(0) + "" + move[0].toString().charAt(1))
					.equals(move[2].toString().charAt(0) + "" + move[2].toString().charAt(1))
					|| (move[0].toString().charAt(2) + "" + move[0].toString().charAt(3))
							.equals(move[2].toString().charAt(2) + "" + move[2].toString().charAt(3))
					|| (move[1].toString().charAt(0) + "" + move[1].toString().charAt(1))
							.equals(move[2].toString().charAt(0) + "" + move[2].toString().charAt(1))
					|| (move[1].toString().charAt(2) + "" + move[1].toString().charAt(3))
							.equals(move[2].toString().charAt(2) + "" + move[2].toString().charAt(3))
					|| (move[0].toString().charAt(0) + "" + move[0].toString().charAt(1))
							.equals(move[1].toString().charAt(0) + "" + move[1].toString().charAt(1))
					|| (move[0].toString().charAt(2) + "" + move[0].toString().charAt(3))
							.equals(move[1].toString().charAt(2) + "" + move[1].toString().charAt(3))) {
				return false;
			}
			return true;
		} else if (move.length == 4) {
			if ((move[0].toString().charAt(0) + "" + move[0].toString().charAt(1))
					.equals(move[2].toString().charAt(0) + "" + move[2].toString().charAt(1))
					|| (move[0].toString().charAt(2) + "" + move[0].toString().charAt(3))
							.equals(move[2].toString().charAt(2) + "" + move[2].toString().charAt(3))
					|| (move[1].toString().charAt(0) + "" + move[1].toString().charAt(1))
							.equals(move[2].toString().charAt(0) + "" + move[2].toString().charAt(1))
					|| (move[1].toString().charAt(2) + "" + move[1].toString().charAt(3))
							.equals(move[2].toString().charAt(2) + "" + move[2].toString().charAt(3))
					|| (move[0].toString().charAt(0) + "" + move[0].toString().charAt(1))
							.equals(move[1].toString().charAt(0) + "" + move[1].toString().charAt(1))
					|| (move[0].toString().charAt(2) + "" + move[0].toString().charAt(3))
							.equals(move[1].toString().charAt(2) + "" + move[1].toString().charAt(3))

					|| (move[0].toString().charAt(0) + "" + move[0].toString().charAt(1))
							.equals(move[3].toString().charAt(0) + "" + move[3].toString().charAt(1))
					|| (move[0].toString().charAt(2) + "" + move[0].toString().charAt(3))
							.equals(move[3].toString().charAt(2) + "" + move[3].toString().charAt(3))
					|| (move[1].toString().charAt(0) + "" + move[1].toString().charAt(1))
							.equals(move[3].toString().charAt(0) + "" + move[3].toString().charAt(1))
					|| (move[1].toString().charAt(2) + "" + move[1].toString().charAt(3))
							.equals(move[3].toString().charAt(2) + "" + move[3].toString().charAt(3))
					|| (move[2].toString().charAt(0) + "" + move[2].toString().charAt(1))
							.equals(move[3].toString().charAt(0) + "" + move[3].toString().charAt(1))
					|| (move[2].toString().charAt(2) + "" + move[2].toString().charAt(3))
							.equals(move[3].toString().charAt(2) + "" + move[3].toString().charAt(3))) {
				return false;
			}
			return true;
		}
		// noch prüfen ob die züge etwas im Weg haben o.ä
		// das muss glaube ich in eine andere Methode ...=> einzelner Zug
		// gultigkeit
		return true;
	}

	private boolean gueltigeEingabeEinzelnerMove(String eingabe, boolean fleischfresser) {
		if (eingabe.equals("help")) {
			printPossibleMoves();
		}
		if (eingabe.length() == 7 && eingabe.charAt(3) == 'h' && eingabe.charAt(4) == 'e' && eingabe.charAt(5) == 'l'
				&& eingabe.charAt(6) == 'p') {
			printPossibleMoves(eingabe.charAt(0) + "" + eingabe.charAt(1));
		}
		if (eingabe.equals("life")) {
			printLifes();
		}
		if (eingabe.equals(""))
			return true;
		if (eingabe.length() != 4)
			return false;
		String a = "";
		if (eingabe.length() == 4)
			a = eingabe.charAt(0) + "" + eingabe.charAt(1);
		boolean thistimefemale = (pos.getNext() == 'W') ? true : false;
		for (Animal an : pos.getAnimals()) {
			if (a != null && an.square.equals(a)) {
				if (an.female != thistimefemale)
					return false;
			}
		}
		if (schoneinfleischfresser == true && fleischfresser == true) {
			return false;
		}
		if (schoneinfleischfresser == false && fleischfresser == true) {
			schoneinfleischfresser = true;
		}
		Animal[] ani = pos.getAnimals();
		boolean possibleMove = false;
		for (Animal f : ani) {
			if (f.square.equals(eingabe.charAt(0) + "" + eingabe.charAt(1))) {
				Move[] posMove = f.possibleMoves();
				for (Move m : posMove) {
					if (m!= null && m.equals(eingabe)) {
						possibleMove = true;
						break;
					}
				}
				break;
			}
		}
		if (possibleMove == false)
			return false;
		/*
		 * abchecken ob Tiere im Weg stehen bei Zuegen über mehrere Felder wenn
		 * nichts greift dann ist die Eingabe gültig aber die Zwischenzüge
		 * 
		 * fehler ziehe ich bei den possibleMoves() raus... also dass die erst
		 * gar nciht möglich sind und folglich nciht auftauchen
		 */
		return true;
	}
}
