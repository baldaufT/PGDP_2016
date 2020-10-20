package tutorium9;

public class Rechteck extends Grundflaeche {
	private int breite;

	public Rechteck() {
		super();
		breite = 0;
	}

	public boolean isQuadrat() {
		if (breite == super.getLaenge())
			return true;
		return false;
	}

	public Rechteck(int breite, int laenge) {
		super(laenge);
		this.breite = breite;
	}

	public int getBreite() {
		return breite;
	}

	public double umfang() {
		return breite * 2 + super.getLaenge() * 2;
	}

	public double flaeche() {
		return breite * super.getLaenge();
	}

	public String toString() {
		return "Breite x Laenge: " + breite + " x " + super.getLaenge() + ", " + super.toString();
	}
}
