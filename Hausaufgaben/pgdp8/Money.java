package pgdp8;

public class Money {
	private int cent;

	public Money() {
		cent = 0;
	}

	public Money(int cent) {
		this.cent = cent;
	}

	public int getCent() {
		return this.cent;
	}

	public Money addMoney(Money m) {
		return new Money(this.cent + m.getCent());
	}

	public String toString() {
		String str = "";
		int ce = cent;
		if (ce < 0) {
			str = str + "-";
			ce = -ce;
		}

		str = str + ce / 100 + ",";
		ce = ce % 100;
		str = str + ce / 10 + ce % 10;
		return str + " Euro";
	}
}
