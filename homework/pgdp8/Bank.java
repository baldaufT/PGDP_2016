package pgdp8;

public class Bank {
	private BankAccountList accounts;

	public Bank() {
		// TODO Auto-generated constructor stub
	}

	public void removeAccount(int accountnumber) {
		BankAccountList tmp = accounts;
		if (accounts.info.getAccountnumber() == accountnumber) {
			accounts = accounts.next;
			return;
		}
		while (tmp.next != null && tmp.next.info.getAccountnumber() != accountnumber) {
			tmp = tmp.next;
		}
		if (tmp.next != null)
			tmp.next = tmp.next.next;

	}

	public int newAccount(String firstname, String lastname) {
		int kontonummer;
		if (accounts == null)
			kontonummer = 1001;
		else
			kontonummer = getNewAccountNumber();
		BankAccount b = new BankAccount(kontonummer, firstname, lastname);
		if (accounts == null) {
			accounts = new BankAccountList();
			accounts.info = b;
			return kontonummer;
		}
		BankAccountList tmp = accounts;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = new BankAccountList();
		tmp.next.info = b;
		return kontonummer;
	}

	public Money getBalance(int accountnumber) {
		BankAccountList tmp = accounts;
		while (tmp != null && tmp.info.getAccountnumber() != accountnumber) {
			tmp = tmp.next;
		}
		if (tmp == null)
			return null;
		return tmp.info.getBalance();
	}

	// Wieder entfernen ... nur für Testzwecke
	public String toString() {
		String str = "";
		BankAccountList tmp = accounts;
		while (tmp != null) {
			str = str + tmp.info.toString() + "\n\n";
			tmp = tmp.next;
		}
		return str;
	}

	public boolean transfer(int from, int to, Money m) {
		BankAccountList von = accounts;
		while (von != null && von.info.getAccountnumber() != from) {
			von = von.next;
		}
		BankAccountList nach = accounts;
		while (nach != null && nach.info.getAccountnumber() != to) {
			nach = nach.next;
		}
		if (nach == null || von == null)
			return false;
		depositOrWithdraw(from, new Money(-m.getCent()));
		depositOrWithdraw(to, m);
		return true;
	}

	public boolean depositOrWithdraw(int accountnumber, Money m) {
		BankAccountList tmp = accounts;
		while (tmp != null && tmp.info.getAccountnumber() != accountnumber) {
			tmp = tmp.next;
		}
		if (tmp == null)
			return false;
		tmp.info.addMoney(m);
		return true;
	}

	private int getNewAccountNumber() {
		BankAccountList tmp = accounts;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		return tmp.info.getAccountnumber() + 1;
	}

	private class BankAccountList {
		BankAccount info;
		BankAccountList next;
	}
}
