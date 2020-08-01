package tutorium2;

import tutorium1.MiniJava;

public class Maexle {

	static int zahl1 = MiniJava.dice();
	static int zahl2 = MiniJava.dice();
	static int zahl1PC = MiniJava.dice();
	static int zahl2PC = MiniJava.dice();
	static int gesamtzahlIch;
	static int gesamtzahlPC;
	static boolean beendet = false;
	static int letzteZahlPC;
	static int letzteZahlIch;

	public static void main(String[] args) {
		System.out.println("Sie starten, somit verliert der PC bei Punktegleichstand! \n");
		neueRunde();
	}

	public static void neueRunde() {
		while (beendet == false) {
			getNewNumbers();
			zahlenOrdnen(zahl1, zahl2, zahl1PC, zahl2PC);
			zahlenAusgeben();
			GewonnenFrage();
			letzteZahlIch = gesamtzahlIch;
			letzteZahlPC = gesamtzahlPC;
		}
	}

	private static void GewonnenFrage() {
		if (zahl1 == zahl2) {
			gesamtzahlIch = gesamtzahlIch * 10;
		}
		if (zahl1PC == zahl2PC) {
			gesamtzahlPC = gesamtzahlPC * 10;
		}
		if(gesamtzahlIch < letzteZahlPC){
			beendet = true;
			System.out.println("Sie haben leider verloren!");
			
		}
		if(gesamtzahlPC < gesamtzahlIch){
			beendet = true;
			System.out.println("Ich: "+gesamtzahlIch+"\n"+"Sie haben gewonnen! Herzlichen Glückwunsch!");
		}

	}

	private static void getNewNumbers() {
		zahl1 = MiniJava.dice();
		zahl2 = MiniJava.dice();
		zahl1PC = MiniJava.dice();
		zahl2PC = MiniJava.dice();
	}

	private static void zahlenOrdnen(int a, int b, int c, int d) {
		if (a > b) {
			gesamtzahlIch = zahl1 * 10 + zahl2;
		}
		if (a < b) {
			gesamtzahlIch = zahl2 * 10 + zahl1;
		}
		if (a == b) {
			gesamtzahlIch = zahl1 * 10 + zahl2;
		}
		if (c > d) {
			gesamtzahlPC = zahl1PC * 10 + zahl2PC;
		}
		if (c < d) {
			gesamtzahlPC = zahl2PC * 10 + zahl1PC;
		}
		if (c == d) {
			gesamtzahlPC = zahl1PC * 10 + zahl2PC;
		}
	}

	private static void zahlenAusgeben() {
		System.out.println("Ich: " + gesamtzahlIch + "\n" + "PC: " + gesamtzahlPC);
		if (gesamtzahlIch == 21) {
			System.out.println("Sie haben mit einem Meier gewonnen! Herzlichen Glückwunsch!");
			beendet = true;
		}
		if (gesamtzahlPC == 21 && gesamtzahlIch != 21) {
			System.out.println("Sie haben leider verloren! Der PC hatte einen Meier.");
			beendet = true;
		}
	}
}
