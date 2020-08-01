package pgdp8;

public class Test {

	public static void main(String[] args) {
		Bank b = new Bank();
		b.newAccount("Tobias", "Baldauf");
		b.newAccount("Niklas", "Baier");
		System.out.println("1\n"+b.toString());
		b.removeAccount(1002);
		System.out.println("2\n"+b.toString());
		b.newAccount("Philipp", "Barth");
		b.newAccount("Niklas", "Baier");
		System.out.println("3\n"+b.toString());
	}
}
