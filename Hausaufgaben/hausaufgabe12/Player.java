package hausaufgabe12;

import java.util.Random;

public class Player implements Runnable {

	private int nextZahl = 3;
	private boolean neueZahlistda = false, schongelesen = true;
	Random r = new Random();

	@Override
	public void run() {
		while (true) {
			try {
				synchronized (this) {
					while (!schongelesen)
						wait();
					neueZahlistda = true;
					nextZahl = r.nextInt(3);
					schongelesen = false;
					notifyAll();
				}
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public synchronized int getChoice() throws InterruptedException {
		while (!neueZahlistda)
			wait();
		int nextZahl1 = nextZahl;
		schongelesen = true;
		neueZahlistda = false;
		notifyAll();
		return nextZahl1;
	}
}
