package tutorium12;

public class Sessellieferant implements Runnable {

    private Sesselfabrik sf;
    private int anzahl;

    public Sessellieferant(Sesselfabrik sf, int anzahl) {
        this.sf = sf;
        this.anzahl = anzahl;
    }

    @Override
    public void run() {
        // Warten, bis genuegend Sessel produziert wurden.
        for (int i = 0; i < anzahl; i++)
            sf.sesselBestellen();
        // Warten, bis Rampe frei.
        sf.rampeBelegen();
        // Zur Rampe fahren und Sessel aufladen.
        for (int i = 0; i < anzahl; i++)
            sf.sesselAbholen();
        System.out.println("Sessel abgeholt (" + anzahl + " Stueck)");
        // Rampe freigeben.
        sf.rampeFreigeben();
    }
}
