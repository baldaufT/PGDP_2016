package tutorium12;

public class Sesselfabrik implements Runnable {

    private int sesselBestellbar = 0;
    private int lagerPlaetzeFrei = 20;
    private boolean rampeBelegt = false;
    // synchronize variable sesselBestellbar
    private final Object lockSessel = new Object();
    // synchronize variable lagerPlaetzeFrei
    private final Object lockLager = new Object();
    // synchronize variable rampeBelegt
    private final Object lockRampe = new Object();

    @Override
    public void run() {
        try {
            while (true) {
                sesselEinlagern();
            }
        } catch (Exception e) {
            if (!(e instanceof InterruptedException))
                System.out.println("Unexpected Exception in Sesselfabrik.run()");
        }
    }

    public void sesselEinlagern() throws InterruptedException {
        synchronized (lockLager) {
            while (lagerPlaetzeFrei <= 0) {
                lockLager.wait();
            }
            lagerPlaetzeFrei--;
            System.out.println("Ein neuer Sessel wurde eingelagert.");
        }
        // Evtl. wartende Lieferanten benachrichtigen.
        synchronized (lockSessel) {
            sesselBestellbar++;
            lockSessel.notify();
        }
    }

    public void sesselBestellen() {
        synchronized (lockSessel) {
            while (sesselBestellbar <= 0) {
                try {
                    lockSessel.wait();
                } catch (InterruptedException e) {
                }
            }
            sesselBestellbar--;
        }
    }

    public void sesselAbholen() {
        synchronized (lockLager) {
            lagerPlaetzeFrei++;
            lockLager.notify();
        }
    }

    public void rampeBelegen() {
        synchronized (lockRampe) {
            while (rampeBelegt) {
                try {
                    lockRampe.wait();
                } catch (InterruptedException e) {
                }
            }
            rampeBelegt = true;
        }
    }

    public void rampeFreigeben() {
        synchronized (lockRampe) {
            rampeBelegt = false;
            lockRampe.notify();
        }
    }

    public static void main(String[] args) {
        Sesselfabrik sf = new Sesselfabrik();
        Thread tsf = new Thread(sf);
        tsf.start();
        Thread[] lieferanten = new Thread[10];
        for (int i = 0; i < lieferanten.length; i++) {
            lieferanten[i] = new Thread(new Sessellieferant(sf, (int) (1 + 3 * Math.random())));
            lieferanten[i].start();
        }
        for (Thread sl : lieferanten)
            try {
                sl.join();
            } catch (InterruptedException e) {
            }
        tsf.interrupt();
    }
}
