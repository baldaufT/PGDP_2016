package tutorium9;

public class NEck extends Grundflaeche {

	private int anzahlEcken;

	public NEck() {
		super();
		anzahlEcken = 0;
	}

	public NEck(int anzahlEcken, int seitenlaenge) {
		super(seitenlaenge);
		this.anzahlEcken = anzahlEcken;
	}

	public double umfang() {
		return anzahlEcken * super.getLaenge();
	}

	public double flaeche() {
		return ((anzahlEcken * super.getLaenge() * super.getLaenge()) / (4 * Math.tan(Math.PI / anzahlEcken)));
	}

	public boolean isQuadrat() {
		if (anzahlEcken == 4)
			return true;
		return false;
	}

	public int getAnzahlEcken() {
		return anzahlEcken;
	}

	public String toString() {
		return "AnzahlEcken: " + getAnzahlEcken() + ", " + super.toString();
	}
}
