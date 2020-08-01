package tutorium9;

public class Test {

	public static void main(String[] args) {
		Prisma p = new Prisma();
		p.setHoehe(6);
		p.setRechteck(6, 6);
		System.out.println(p);
		p.zuQuadrat();
		System.out.println(p);
	}

}
