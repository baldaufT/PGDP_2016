package tutorium9;

public class Grundflaeche {

	private int laenge;

	public Grundflaeche() {
		laenge = 0;
	}

	public Grundflaeche(int laenge) {
		this.laenge = laenge;
	}

	public double umfang() {
		return 0;
	}

	public int getLaenge() {
		return laenge;
	}

	public Quadrat zuQuadrat() {
		if (isQuadrat() == true) {
			return new Quadrat(laenge);
		} else
			return null;
	}

	public boolean isQuadrat() {
		return false;
	}

	public double flaeche() {
		return 0;
	}

	public String toString() {
		return "Flaeche: " + flaeche() + ", Umfang: " + umfang();
	}
}
