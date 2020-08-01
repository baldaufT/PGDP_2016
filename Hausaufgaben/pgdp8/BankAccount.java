package pgdp8;

public class BankAccount {

	private int bankaccount;
	private String firstname;
	private String surname;
	private Money balance;

	public BankAccount(int accountnumber, String fname, String sname) {
		firstname = fname;
		surname = sname;
		bankaccount = accountnumber;
		balance = new Money();
	}

	public int getAccountnumber() {
		return bankaccount;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public Money getBalance() {
		return balance;
	}

	public void addMoney(Money m) {
		balance = balance.addMoney(m);
	}

	public String toString() {
		return surname + ", " + firstname + "\nBankaccount: " + bankaccount + "\nGuthaben: " + balance;
	}
}
