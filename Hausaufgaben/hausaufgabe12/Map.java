package hausaufgabe12;

public class Map {

	public static <T, R> void map(Fun<T, R> f, T[] a, R[] b, int n) throws InterruptedException {
		if (a.length != b.length || n > a.length || n < 1)
			throw new IllegalArgumentException();
		int groeseGroese = 1 + (a.length / n);
		int anzahlTeileGroeseGroese = a.length % n, kleineGroese = a.length / n;
		boolean[] bool = new boolean[n];

		Thread[] t = new Thread[n];

		for (int k = 0; k < t.length; k++) {
			int counter = ((((k + 1) - anzahlTeileGroeseGroese) < (0)) ? (0) : ((k + 1) - anzahlTeileGroeseGroese))
					* kleineGroese
					+ (((k + 1) < anzahlTeileGroeseGroese) ? ((k + 1) * groeseGroese)
							: (groeseGroese * anzahlTeileGroeseGroese));
			int laenge = (k < anzahlTeileGroeseGroese) ? (groeseGroese) : (kleineGroese);
			int khelp = k;

			t[k] = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = (counter - laenge); i < counter; i++) {
						b[i] = f.apply(a[i]);
					}
					bool[khelp] = true;
				}
			});
			t[k].start();
		}
		for (int k = 0; k < t.length; k++) {
			t[k].join();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		Integer[] a = new Integer[] { 1, 2, 3, 4, 5 };
		String[] b = new String[5];
		Map.map(new IntToString(), a, b, 3);
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
	}
}
