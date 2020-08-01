package tutorium9;

public class Kreis extends Grundflaeche {
	private int radius;

	public Kreis() {
		super();
		radius = 0;
	}

	public Kreis(int radius) {
		super();
		this.radius = radius;
	}

	public double umfang() {
		return Math.PI * 2 * getRadius();
	}

	public double flaeche() {
		return radius * radius * Math.PI;
	}

	public int getRadius() {
		return radius;
	}

	public String toString() {
		return "Radius: " + radius + ", " + super.toString();
	}
}
