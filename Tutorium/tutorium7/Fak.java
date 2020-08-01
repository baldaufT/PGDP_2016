package tutorium7;

public class Fak {

	public Fak() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//System.out.println(facRec(5));
		System.out.println(facTailRec(5));
		//System.out.println(facIt(5));
	}

	public static int facRec(int n) {
		if (n <= 0)
			return 1;
		else
			return n * facRec(n - 1);
	}

	public static int facTailRec(int n) {
		return facTailRecHelper(n, 1);
	}

	private static int facTailRecHelper(int n, int k) {
		if (n > 0) {
			return facTailRecHelper(n - 1, k * n);
		}
		return k;
	}

	public static int facIt(int n) {
		int k = 1;
		while (n > 0) {
			k = k * n;
			n = n - 1;
		}
		return k;
	}
}
