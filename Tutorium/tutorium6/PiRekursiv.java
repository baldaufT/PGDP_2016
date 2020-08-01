package tutorium6;

public class PiRekursiv extends MiniJava {

	public static void main(String[] args) {
		int n = readInt();
		System.out.println(start(n));

	}

	private static double start(int n) {
		double pi = 4;
		if(n <= 0)
			return pi;
		if (n % 2 == 0)
			return pi = (4.0 / (2.0 * n + 1)) + start(n - 1);
		else
			return pi = ((-4.0) / ((2.0 * n) + 1)) + start(n - 1);
			
	}
}
