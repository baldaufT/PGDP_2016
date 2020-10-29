package hausaufgabe11;

public class Password {

	private final int nrUpperShould, nrLowerShould, nrSpecialShould, nrNumbersShould, lengthShould;
	private final char[] illegalChars;

	public Password(int nrUpperShould, int nrLowerShould, int nrSpecialShould, int nrNumbersShould, int lengthShould,
			char[] illegalChars) {
		this.illegalChars = illegalChars;
		this.nrUpperShould = nrUpperShould;
		this.nrLowerShould = nrLowerShould;
		this.nrSpecialShould = nrSpecialShould;
		this.nrNumbersShould = nrNumbersShould;
		this.lengthShould = lengthShould;
	}

	public static void main(String[] args) {
		char[] ch = new char[5];
		ch[0] = '\n';
		String passwort = "\nPasswort§!A234";
		Password pw1 = new Password(2, 7, 2, 3, 13, ch);
		try {
			pw1.checkFormat(passwort);
		} catch (IllegalCharExc e) {
			System.out.println(e);
		} catch (NotEnoughExc e) {
			System.out.println(e);
		} catch (NotLongEnoughExc e) {
			System.out.println(e);
		}
	}

	public void checkFormat(String pwd) throws IllegalCharExc, NotEnoughExc, NotLongEnoughExc {
		char[] pwdc = new char[pwd.length()];
		for (int i = 0; i < pwd.length(); i++) {
			pwdc[i] = pwd.charAt(i);
		}

		if (!(nrIlleChar(pwdc) == '2'))
			throw new IllegalCharExc((char) (nrIlleChar(pwdc)));
		if (nrLow(pwdc) < nrLowerShould)
			throw new NotEnoughLower(nrLowerShould, nrLow(pwdc));
		if (nrUpper(pwdc) < nrUpperShould)
			throw new NotEnoughUpper(nrUpperShould, nrUpper(pwdc));
		if (nrNumber(pwdc) < nrNumbersShould)
			throw new NotEnoughNumber(nrNumbersShould, nrNumber(pwdc));
		if (nrSpecial(pwdc) < nrSpecialShould)
			throw new NotEnoughSpecial(nrSpecialShould, nrSpecial(pwdc));
		if (pwd.length() < lengthShould)
			throw new NotLongEnoughExc(lengthShould, pwd.length());
	}

	private int nrLow(char[] str) {
		int anzahl = 0;
		for (char s : str) {
			if (s <= 'z' && s >= 'a')
				anzahl++;
		}
		return anzahl;
	}

	private int nrUpper(char[] str) {
		int anzahl = 0;
		for (char s : str) {
			if (s <= 'Z' && s >= 'A')
				anzahl++;
		}
		return anzahl;
	}

	private int nrNumber(char[] str) {
		int anzahl = 0;
		for (char s : str) {
			if (s == '0' || s == '1' || s == '2' || s == '3' || s == '4' || s == '5' || s == '6' || s == '7' || s == '8'
					|| s == '9')
				anzahl++;
		}
		return anzahl;
	}

	private int nrSpecial(char[] str) {
		int anzahl = 0;
		for (char s : str) {
			if (!(s == '0' || s == '1' || s == '2' || s == '3' || s == '4' || s == '5' || s == '6' || s == '7'
					|| s == '8' || s == '9' || (s <= 'Z' && s >= 'A') || (s <= 'z' && s >= 'a')))
				anzahl++;
		}
		return anzahl;
	}

	private char nrIlleChar(char[] str) {
		for (char s : str) {
			for (char ch : illegalChars) {
				if (s == ch) {
					return s;
				}
			}
		}
		return '2';
	}
}
