package hausaufgabe12;

public class RockPaperScissors implements Runnable {
	private int wieoft;
	private int gewonnen1 = 0, gewonnen2 = 0, unentschieden = 0, helpwert = 3, wert = 3;

	public RockPaperScissors() {
		wieoft = 1000;
	}

	public RockPaperScissors(int wieoft) {
		this.wieoft = wieoft;
	}

	@Override
	public void run() {
		Player p1 = new Player();
		Player p2 = new Player();
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		t1.start();
		t2.start();
		int i = 0;
		try {
			while (i < wieoft) {
				wert = p1.getChoice();
				helpwert = p2.getChoice();
				getNumber();
				i++;
			}
			t1.interrupt();
			t2.interrupt();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Thread-1 hat " + gewonnen1 + " mal gewonnen, Thread-2 hat " + gewonnen2
				+ " mal gewonnen und " + unentschieden + " mal haben sie unentschieden gespielt. \nIn Summe "
				+ (unentschieden + gewonnen1 + gewonnen2) + " Spiele.");
	}

	private synchronized void getNumber() {
		if (wert == helpwert)
			unentschieden++;
		else if (wert == 0 && helpwert == 1)
			gewonnen2++;
		else if (wert == 0 && helpwert == 2)
			gewonnen1++;
		else if (wert == 1 && helpwert == 0)
			gewonnen1++;
		else if (wert == 1 && helpwert == 2)
			gewonnen2++;
		else if (wert == 2 && helpwert == 0)
			gewonnen2++;
		else if (wert == 2 && helpwert == 1)
			gewonnen1++;
	}

	public static void main(String[] args) {
		new RockPaperScissors().run();
	}
}
