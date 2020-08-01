package hausaufgabe11;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author zinu
 */
public class TestSet {

	// num of loops for random tests
	public static final int NUM = 100;
	// print exception stack trace
	public static final boolean PRINT = true;

	// enable tests
	public static final boolean ADD = true;
	public static final boolean REMOVE = true;
	public static final boolean CONTAINS = true;
	public static final boolean SIZE = true;
	public static final boolean EQUALS = true;
	public static final boolean TOSTRING = true;
	public static final boolean ITERATOR = true;

	static Set<Integer> s, s2, s3, s4;
	static int zs[];
	static boolean failed;
	static Random r;

	public static void main(String[] args) {

		r = new Random();
		failed = true;
		zs = new int[NUM];
		for (int i = 0; i < NUM; i++) {
			while (true) {
				int k = r.nextInt();
				boolean found = false;
				for (int j = 0; j < i; j++) {
					if (zs[j] == k) {
						found = true;
					}
				}
				if (!found && k != -1) {
					zs[i] = k;
					break;
				}
			}
		}

		if (ADD) {
			test_add();
		}
		if (REMOVE) {
			test_remove();
		}
		if (CONTAINS) {
			test_contains();
		}
		if (SIZE) {
			test_size();
		}
		if (EQUALS) {
			test_equals();
		}
		if (TOSTRING) {
			test_toString();
		}
		if (ITERATOR) {
			test_iterator();
		}

		System.out.println("");

		// test with small set for debugging
		s = new Set();

		s = s.add(5);
		s = s.add(5);
		s = s.add(6);
		s = s.add(7);
		System.out.println("" + s + " size: " + s.size());
		// s2 = s.remove(null);

		System.out.println(s2.equals(s));
		s2 = s2.add(6);
		System.out.println(s2.equals(s));
		System.out.println(s2.equals(null));
		System.out.println("" + s2);

	}

	public static void test_add() {
		System.out.println("----------------------------");
		System.out.println("add:");
		try {
			System.out.print("\tadd2: ");
			s = new Set();
			for (int i = 0; i < NUM; i++) {
				s2 = s.add(zs[i]);
				if (s2 == s) {
					System.out.print("same");
					break;
				}
				if (!s2.contains(zs[i])) {
					System.out.print("doesnt contain e" + " " + i);
					break;
				}
				s = s2;
			}
		} catch (Exception e) {
			System.out.print("Error (uncomment contains?)" + e.toString());
			if (PRINT) {
				e.printStackTrace();
			}
		}

		System.out.println("");

		try {
			System.out.print("\tadd1: ");
			for (int i = 0; i < NUM; i++) {
				s2 = s.add(zs[i]);
				if (s2 != s) {
					System.out.print("not the same");
					break;
				}
				s = s2;
			}
		} catch (Exception e) {
			System.out.print("Error");
			if (PRINT) {
				e.printStackTrace();
			}
		}

		System.out.println("");

		try {
			System.out.print("\tadd3: ");
			s.add(null);
			System.out.print("Error1");
		} catch (NullPointerException e) {
			try {
				s2 = new Set();
				s2.add(null);
				System.out.print("Error2");
			} catch (NullPointerException e2) {
			} catch (Exception e2) {
				System.out.print("Error (different Exception:" + e2.getClass() + ")");
				if (PRINT) {
					e2.printStackTrace();
				}
			}
		} catch (Exception e2) {
			System.out.print("Error (different Exception:" + e2.getClass() + ")");
			if (PRINT) {
				e2.printStackTrace();
			}
		}
	}

	public static void test_remove() {
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("remove: (add needed!)");
		try {
			System.out.print("\tremove1: ");
			failed = false;
			int index = NUM / 2;
			for (int i = 0; i < NUM; i++) {
				s2 = s.remove(zs[index]);
				if (s2 == s) {
					System.out.print("same; ");
					failed = true;
					break;
				}
				if (s2.contains(zs[index])) {
					System.out.print("contains e; ");
					failed = true;
					break;
				}
				s = s2;
				index++;
				if (index >= NUM) {
					index = 0;
				}
			}
			try {
				if (!failed && s.size() != 0) {
					System.out.print("size after remove not 0");
				}
			} catch (Exception e2) {
				System.out.print("Error (only after calling size)");
				if (PRINT) {
					e2.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.print("Error (contains wrong?)");
			if (PRINT) {
				e.printStackTrace();
			}
		}

		try {
			// test removing elements that arent in the set
			s = new Set();
			s = s.add(10);
			s = s.add(15);
			s = s.add(11);
			for (int i = 0; i < NUM; i++) {
				s2 = s.remove(r.nextInt(1000) + 20);// ->at least 20
				if (s2.equals(s)) {
					System.out.print("(not same when not removing)");
					break;
				}
				s = s2;
			}
		} catch (Exception e) {
			System.out.print("Error (removing elements that dont exist)");
			if (PRINT) {
				e.printStackTrace();
			}
		}

		System.out.println("");

		try {
			System.out.print("\tremove2: ");
			s2 = s.remove(null);
			System.out.print("Error");
		} catch (NullPointerException e) {
		} catch (Exception e2) {
			System.out.print("Error (different Exception:" + e2.getClass() + ")");
			if (PRINT) {
				e2.printStackTrace();
			}
		}
	}

	public static void test_contains() {
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("contains: (add needed!)");
		try {
			System.out.print("\tcontains1: ");
			s = new Set();
			for (int i = 0; i < NUM; i++) {
				s = s.add(zs[i]);
			}
			for (int i = 0; i < NUM; i++) {
				if (!s.contains(zs[i])) {
					System.out.print("doesnt contain e");
					break;
				}
			}
		} catch (Exception e) {
			System.out.print("Error (add wrong?)");
			if (PRINT) {
				e.printStackTrace();
			}
		}
		System.out.println("");

		try {
			System.out.print("\tcontains2: ");
			s = new Set();
			s2 = new Set();
			s = s.add(15);
			for (int i = 0; i < NUM; i++) {
				if (s2.contains(r.nextInt())) {
					System.out.print("contains e");
					break;
				}
				if (s.contains(r.nextInt(2000) + 20)) {
					System.out.print("contains e");
					break;
				}
			}
		} catch (Exception e) {
			System.out.print("Error (add wrong?)");
			if (PRINT) {
				e.printStackTrace();
			}
		}
	}

	public static void test_size() {
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("size: (add and remove needed!)");
		try {
			System.out.print("\tsize1: ");
			s = new Set();
			failed = false;
			if (s.size() != 0) {
				System.out.print("size not 0 at start");
				failed = true;
			}
			for (int i = 0; i < NUM && !failed; i++) {
				s = s.add(zs[i]);
				if (s.size() != i + 1) {
					System.out.print("size not " + (i + 1) + " after add");
					failed = true;
				}
			}
			for (int i = 0; i < NUM && !failed; i++) {
				s = s.remove(zs[i]);
				if (s.size() != NUM - i - 1) {
					System.out.print("size not " + (NUM - i - 1) + " after remove");
					failed = true;
				}
			}
			if (!failed && s.size() != 0) {
				System.out.print("size not 0 at end");
			}
		} catch (Exception e) {
			System.out.print("Error (add/remove wrong?)");
			if (PRINT) {
				e.printStackTrace();
			}
		}
	}

	public static void test_equals() {
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("equals: (add needed)");
		try {
			System.out.print("\tequals1: ");
			s = new Set();
			s2 = new Set();
			s3 = new Set();
			s4 = new Set();
			// negative numbers arent in s
			// s2 = s2.add(-1);s2 = s2.add(-11);s2 = s2.add(-111);
			int index = NUM / 2;
			for (int i = 0; i < NUM && !failed; i++) {
				s = s.add(zs[i]);
				s3 = s3.add(zs[NUM - i - 1]);
				s4 = s4.add(zs[index]);
				index++;
				if (index >= NUM) {
					index = 0;
				}
			}
			// reflexive
			if (!s.equals(s) || !s2.equals(s2)) {
				System.out.print("not reflexive; ");
			}
			// symmetric
			if (!(s.equals(s2) == s2.equals(s) && s2.equals(s3) == s3.equals(s2) && s.equals(s3) == s3.equals(s))) {
				System.out.print("not symmetric; ");
			}
			// transitiv
			// a=b & b=c -> a=c <=> !(a=b & b=c) | a=c
			if (!((!(s.equals(s3) && s3.equals(s4)) || s.equals(s4))
					&& (!(s.equals(s2) && s2.equals(s4)) || s.equals(s4)))) {
				System.out.print("not transitiv; ");
			}

			// consistent
			if (!(s.equals(s2) == s.equals(s2) && s.equals(s3) == s.equals(s3))) {
				System.out.print("not consistent; ");
			}

			// null or other objects
			if (s.equals(null)) {
				System.out.println("null wrong; ");
			}
			if (s.equals("asd") || s.equals(new Double(5.5))) {
				System.out.println("other object wrong; ");
			}

		} catch (Exception e) {
			System.out.print("Error (add wrong?)");
			if (PRINT) {
				e.printStackTrace();
			}
		}

		System.out.println("");

		try {
			System.out.print("\tequals2: ");
			s = new Set();
			s2 = new Set();
			s3 = new Set();
			s4 = new Set();
			// negative numbers arent in s
			s2.add(-1);
			s2.add(-11);
			s2.add(-111);
			int index = NUM / 2;
			for (int i = 0; i < NUM && !failed; i++) {
				s = s.add(zs[i]);
				s3 = s3.add(zs[NUM - i - 1]);
				s4 = s4.add(zs[index]);
				index++;
				if (index >= NUM) {
					index = 0;
				}
			}
			if (!s.equals(s3) || !s.equals(s4) || !s3.equals(s4)) {
				System.out.print("wrong false; ");
			}

			if (s.equals(s2) || s2.equals(s3) || s2.equals(s4)) {
				System.out.print("wrong true");
			}
		} catch (Exception e) {
			System.out.print("Error (add wrong?)");
			if (PRINT) {
				e.printStackTrace();
			}
		}
	}

	public static void test_toString() {
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("toString: (add needed)");
		try {
			s = new Set();
			s2 = new Set();
			String sts = "{";
			for (int i = 0; i < 10; i++) {
				sts += 10 - i - 1;
				s2 = s2.add(i);
				if (i != 9) {
					sts += ",";
				}
			}
			sts += "}";
			if (!(s.toString().equals("{}") || s.toString().equals("{ }"))) {
				System.out.println("empty different: \"" + s.toString() + "\"");
			}
			if (!s2.toString().equals(sts)) {
				System.out.println("Elements might be in different order!");
				System.out.println("full different: \"" + s2.toString() + "\"");
				System.out.println("instead of: \t\"" + sts + "\"");
			}

		} catch (Exception e) {
			System.out.print("Error (add wrong? toString of empty set wrong?)");
			if (PRINT) {
				e.printStackTrace();
			}
		}
	}

	public static void test_iterator() {
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("Iterator: (add needed)");
		s = new Set();
		if (s instanceof Iterable) {
			try {
				for (int i = 0; i < NUM; i++) {
					s = s.add(zs[i]);
				}
				Iterable sit = (Iterable) s;
				Iterator<Integer> it = sit.iterator();
				for (int i = 0; i < NUM; i++) {
					if (!it.hasNext()) {
						System.out.println("hasNext wrong at " + i);
						break;
					}
					boolean found = false;
					int k = it.next();
					for (int j = 0; j < NUM; j++) {
						if (zs[j] == k) {
							zs[j] = -1;
							found = true;
							break;
						}
					}
					if (!found) {
						System.out.println("next wrong at " + i + " for " + k);
					}
				}

			} catch (Exception e) {
				System.out.print("Error (add wrong?)");
				if (PRINT) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("No Iterable implemented");
		}
	}
}
