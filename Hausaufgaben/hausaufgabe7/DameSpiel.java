package hausaufgabe7;

public class DameSpiel extends MiniJava {

	private int nrRows, nrColumns; // Board dimensions
	private boolean[][] board; // true = queen, false = empty
	private boolean whiteToMove, spielAbbruch; // Whose turn it is
	private String white, black; // Players' names

	/**
	 * Der Konstruktor registriert Spielernamen fuer Weiss und Schwarz.
	 *
	 * @param white
	 *            Name des als 'Weiss' bezeichneten Spielers
	 * @param black
	 *            Name des als 'Schwarz' bezeichneten Spielers
	 */
	public DameSpiel(String white, String black) {
		this.white = white;
		this.black = black;
	}

	/**
	 * Gibt das Spielbrett aus.
	 */
	private void printBoard() {
		for (int j = board[0].length - 1; j >= 0; j--) {
			System.out.print("\n " + (1 + j));
			for (int i = 0; i < board.length; i++) {
				System.out.print(board[i][j] ? " X" : " -");
			}
		}
		System.out.print("\n  ");
		for (int i = 1; i <= board.length; i++) {
			System.out.print(" " + i);
		}
		System.out.println("\n" + (whiteToMove ? white : black) + " ist am Zug.");
	}

	/**
	 * Initialisiert das Spielbrett ueberall mit false. Dazu wird (ggf. neuer)
	 * Speicher allokiert.
	 */
	private void initBoard() {
		board = new boolean[nrColumns][nrRows];
		for (int i = 0; i < nrRows; i++) {
			for (int k = 0; k < nrColumns; k++) {
				board[k][i] = false;
			}
		}
	}

	/**
	 * Ermittelt die Groesse des Spielbretts gemaess den Spielregeln. Das
	 * Ergebnis der Abfrage wird in den Attributen nrRows und nrColumns
	 * abgelegt.
	 */
	private void determineBoardSize() {
		while (nrColumns > 8 || nrColumns < 5) {
			nrColumns = readInt(
					white + ", geben Sie bitte die Breite des Spielfeldes ganzzahlig zwischen 5 und 8 an: ");
		}
		while (nrRows < nrColumns - 1 || nrRows > nrColumns + 1) {
			nrRows = readInt(black + ", geben Sie bitte die Länge des Spielfeldes zwischen " + (nrColumns - 1) + " und "
					+ (nrColumns + 1) + " ein: ");
		}
	}

	/**
	 * Ermittelt, wer anfaengt zu ziehen. Das Ergebnis der Abfrage wird im
	 * Attribut whiteToMove abgelegt.
	 */
	private void determineFirstPlayer() {
		int a = 5;
		while (!(a == 1 || a == 0)) {
			a = readInt(white + ", beginnen Sie (1) oder " + black + " (0)?");
		}
		if (a == 1)
			whiteToMove = true;
		else
			whiteToMove = false;
	}

	/**
	 * Fuehrt den Zug aus.
	 *
	 * @param move
	 *            der auszufuehrende Zug!
	 */
	private void applyMove(int move) {
		int spalte = move % 10;
		int reihe = (move - spalte) / 10;
		board[reihe - 1][spalte - 1] = true;
		// spielerwechsel
		if (whiteToMove == true) {
			whiteToMove = false;
		} else {
			whiteToMove = true;
		}
		printBoard();
		// spielerwechsel
		if (whiteToMove == true) {
			whiteToMove = false;
		} else {
			whiteToMove = true;
		}
	}

	private boolean goOnTest(int move) {
		boolean ergebnis = true;
		int anzahl = 0;
		// für den Test auf true, am ende wieder umdrehen
		board[(move - (move % 10)) / 10 - 1][(move % 10) - 1] = true;

		for (int i = 0; i < nrRows; i++) {
			anzahl = 0;
			for (int k = 0; k < nrColumns; k++) {
				if (board[k][i] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					board[(move - (move % 10)) / 10 - 1][(move % 10) - 1] = false;
					return false;
				}
			}
		}
		// Spalten doppelt abchecken
		for (int i = 0; i < nrColumns; i++) {
			anzahl = 0;
			for (int k = 0; k < nrRows; k++) {
				if (board[i][k] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					board[(move - (move % 10)) / 10 - 1][(move % 10) - 1] = false;
					return false;
				}
			}
		}
		// diagonal noch abchecken 11 => 22 richung
		anzahl = 0;
		int spalte = 0, reihe = 0;
		for (int i = 0; i < nrColumns; i++) {
			anzahl = 0;
			reihe = 0;
			spalte = i;
			while (spalte < nrColumns && reihe < nrRows) {
				if (board[spalte][reihe] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					board[(move - (move % 10)) / 10 - 1][(move % 10) - 1] = false;
					return false;
				}
				spalte++;
				reihe++;
			}
		}
		// diagonal noch abchecken 22 => 13 richtung
		for (int i = 0; i < nrColumns; i++) {
			anzahl = 0;
			reihe = 0;
			spalte = i;
			while (spalte < nrColumns && spalte >= 0 && reihe < nrRows && reihe >= 0) {
				if (board[spalte][reihe] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					board[(move - (move % 10)) / 10 - 1][(move % 10) - 1] = false;
					return false;
				}
				spalte--;
				reihe++;
			}
		}
		// wieder zurück auf false
		board[(move - (move % 10)) / 10 - 1][(move % 10) - 1] = false;
		return ergebnis;
	}

	private int endeTest(int move) {
		int anzahl = 0;
		// reihenabchecken
		for (int i = 0; i < nrRows; i++) {
			anzahl = 0;
			for (int k = 0; k < nrColumns; k++) {
				if (board[k][i] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					spielAbbruch = true;
					return -1;
				}
			}
		}
		// Spalten doppelt abchecken
		for (int i = 0; i < nrColumns; i++) {
			anzahl = 0;
			for (int k = 0; k < nrRows; k++) {
				if (board[i][k] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					spielAbbruch = true;
					return -1;
				}
			}
		}
		// diagonal noch abchecken 11 => 22 richung
		anzahl = 0;
		int spalte = 0, reihe = 0;
		for (int i = 0; i < nrColumns; i++) {
			anzahl = 0;
			reihe = 0;
			spalte = i;
			while (spalte < nrColumns && reihe < nrRows) {
				if (board[spalte][reihe] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					spielAbbruch = true;
					return -1;
				}
				spalte++;
				reihe++;
			}
		}
		// diagonal noch abchecken 22 => 13 richtung
		for (int i = 0; i < nrColumns; i++) {
			anzahl = 0;
			reihe = 0;
			spalte = i;
			while (spalte < nrColumns && spalte >= 0 && reihe < nrRows && reihe >= 0) {
				if (board[spalte][reihe] == true) {
					anzahl++;
				}
				if (anzahl > 1) {
					spielAbbruch = true;
					return -1;
				}
				spalte--;
				reihe++;
			}
		}

		return move;
	}

	/*
	 * soll das Spielfeld überprüfen, ob ein weiterer Spielzug möglich ist.
	 * Falls kein weiterer Spielzug möglich ist, ende und ausgabe des
	 * ergebnisses, sprich spielAbbruch auf true setzen
	 */
	private void goOnPossible() {
		int anzahl = 0;
		for (int i = 1; i <= nrColumns; i++) {
			for (int k = 1; k <= nrRows; k++) {
				if (board[i - 1][k - 1] == false) {
					if (goOnTest(i * 10 + k) == true) {
						anzahl++;
					}
				}
			}
		}
		// falls kein Zug mehr möglich ist, Spielabbruch
		if (anzahl == 0) {
			spielAbbruch = true;
			System.out.println("Es ist kein weiterer Zug möglich!");
		}
	}

	/**
	 * Startet die Hauptschleife des Spiels mit der Abfrage nach Zuegen. Die
	 * Schleife wird durch Eingabe von -1 beendet.
	 */
	private void mainLoop() {
		while (spielAbbruch == false) {
			goOnPossible();
			if (spielAbbruch == true)
				return;
			int move = 99;
			while (move != -1
					&& (move % 10 > nrRows || move % 10 == 0 || (move - move % 10) / 10 > nrColumns || move < 11)) {
				move = readInt((whiteToMove ? white : black) + ", geben Sie bitte Ihren Zug ein: ");
			}
			if (move != -1) {
				applyMove(move);
				move = endeTest(move);
			}
			if (move == -1) {
				spielAbbruch = true;
				return;
			}
			// spielerwechsel
			if (whiteToMove == true) {
				whiteToMove = false;
			} else {
				whiteToMove = true;
			}
		}
	}

	/**
	 * Informiert die Benutzerin ueber den Ausgang des Spiels. Speziell: Wer hat
	 * gewonnen (Weiss oder Schwarz)?
	 */
	private void reportWinner() {
		System.out.println("\n" + (whiteToMove ? black : white) + " hat gewonnen.");
	}

	/**
	 * Startet das Spiel.
	 */
	public void startGame() {
		determineBoardSize();
		initBoard();
		determineFirstPlayer();
		printBoard();
		mainLoop();
		reportWinner();
	}

	public static void main(String[] args) {
		DameSpiel ds = new DameSpiel("Weiß", "Schwarz");
		ds.startGame();
	}

}
