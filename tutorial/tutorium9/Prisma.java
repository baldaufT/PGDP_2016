package tutorium9;

public class Prisma {
	private int hoehe;
	private Grundflaeche g;

	public Prisma(int hoehe) {
		g = new Grundflaeche();
		this.hoehe = hoehe;
	}

	public Prisma(short typ, int hoehe) {
		this.hoehe = hoehe;
		switch (typ) {
		case 1:
			g = new Kreis();
		case 2:
			g = new Rechteck();
		case 3:
			g = new NEck();
		default:
			g = new Grundflaeche();
		}
	}

	public Prisma() {
		g = new Grundflaeche();
		hoehe = 0;
	}

	public boolean isQuadrat() {
		if (g.isQuadrat() == true) {
			g = (Quadrat) g.zuQuadrat();
			return true;
		}
		return false;
	}

	public Quadrat zuQuadrat() {
		if (g.isQuadrat() == true) {
			return new Quadrat(g.getLaenge());
		} else
			return null;
	}

	public boolean isWuerfel() {
		if (!(g instanceof Kreis) && g.isQuadrat() == true) {
			if (g instanceof Rechteck && (((Rechteck) g).getLaenge()) == hoehe) {
				return true;
			}
			if (g instanceof NEck && (((NEck) g).getLaenge()) == hoehe) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		String out = "";

		out = this.isWuerfel() ? "Würfel"
				: g instanceof Kreis ? "Zylinder"
						: g instanceof Rechteck ? "Quader"
								: g instanceof NEck ? (((NEck) g).getAnzahlEcken() + "Prisma") : "Grundflaeche/Prisma";
		out = out + " - " + "Hoehe: " + hoehe + ", " + g.toString();
		return out;
	}

	public int getHoehe() {
		return this.hoehe;
	}

	public boolean setHoehe(int hoehe) {
		if (hoehe < 0)
			return false;
		this.hoehe = hoehe;
		return true;
	}

	public double volumen() {
		return hoehe * g.flaeche();
	}

	public double oberflaeche() {
		return hoehe * g.umfang() + 2 * g.flaeche();
	}

	// return true if kovertierung möglich, sonst false
	public boolean setKreis(int radius) {
		if (g instanceof NEck || g instanceof Rechteck)
			return false;
		g = (Kreis) new Kreis(radius);
		return true;
	}

	// return true if kovertierung möglich, sonst false
	public boolean setRechteck(int breite, int laenge) {
		if (g instanceof NEck || g instanceof Kreis)
			return false;
		g = (Rechteck) new Rechteck(breite, laenge);
		return true;
	}

	// return true if kovertierung möglich, sonst false
	public boolean setNEck(int anzahlEcken, int seitenlaenge) {
		if (g instanceof Kreis || g instanceof Rechteck)
			return false;
		g = (NEck) new NEck(anzahlEcken, seitenlaenge);
		return true;
	}
}
