package tutorium6;

public class Wurzel extends MiniJava {

	public static void main(String[] args) {
		double a = readInt("Die Wurzel welcher Zahl wollen Sie berechnen?");
		System.out.println(start(a, 1));
	}

	public static double start(double a, double x0) {
		double x = x0;
		if (ende(a, x) == false) {
			x = start(a, (0.5 * (x + a / x)));
		}
		return x;

	}

	private static boolean ende(double a, double x) {
		if (a - (x * x) < 0.001 && a - (x * x) > -0.001) {
			return true;
		} else {
			return false;
		}
	}
}
