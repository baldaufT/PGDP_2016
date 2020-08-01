package hausaufgabe8;

public class Test {

	public static void main(String[] args) {
		SymmetricStack s = new SymmetricStack();
		for (int i = 0; i < 12; i++) {
			if (i % 2 == 1)
				s.append(i);
			s.prepend(i * 10);
			System.out.println(s);
		}
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 1)
				s.removeLast();
			s.removeFirst();
			System.out.println(s);
		}
	}

}
