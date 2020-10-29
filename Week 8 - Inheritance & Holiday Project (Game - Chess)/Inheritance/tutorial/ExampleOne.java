package tutorium9;

class M {
	public void someMethod() {
		System.out.println("M.someMethod()");
	}

	public void someMethod(M m) {
		System.out.println("M.someMethod(M m)");
	}

	public void someMethod(N n) {
		System.out.println("M.someMethod(N n)");
	}

	public M anotherMethod() {
		return this;
	}

	public static void someStaticMethod() {
		System.out.println("M.someStaticMethod()");
	}
}

class N extends M {
	public void someMethod() {
		System.out.println("N.someMethod()");
	}

	public void someMethod(M m) {
		System.out.println("N.someMethod(M m)");
	}

	public N anotherMethod() {
		return this;
	}

	public static void someStaticMethod() {
		System.out.println("N.someStaticMethod()");
	}
}

class P extends M {
	public void foo(N n) {
		((M) n).someMethod(super.anotherMethod());
	}
}

public class ExampleOne {
	public static void main(String[] args) {
		M m = new M();
		N n = new N();

		m.someStaticMethod(); // 1
		n.someStaticMethod(); // 2
		((M) n).someStaticMethod(); // 3
		M.someStaticMethod(); // 4
		N.someStaticMethod(); // 5
		System.out.println();

		m.someMethod(); // 6
		n.someMethod(); // 7
		((M) n).someMethod(); // 8
		System.out.println();

		m.someMethod(m); // 9
		m.someMethod(n); // 10
		n.someMethod(m); // 11
		n.someMethod(n); // 12
		System.out.println();

		m.someMethod((M) n); // 13
		n.someMethod((M) n); // 14
		System.out.println();

		System.out.println(m.anotherMethod()); // 15
		System.out.println(n.anotherMethod()); // 16
		System.out.println(((M) n).anotherMethod()); // 17
		System.out.println();

		P p = new P();
		p.foo(n); // 18

	}
}
