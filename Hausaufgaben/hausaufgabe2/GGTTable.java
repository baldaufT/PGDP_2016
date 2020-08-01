package hausaufgabe2;

public class GGTTable {

	static int anzahl = 1;
	static int xkoord = 1;
	static int ykoord = 1;
	static int aktggt = 1;

	public static void main(String[] args) {
		anzahl = MiniJava.readInt("Bis zu welcher Zahl soll die ggt-Tabelle berechnet werden?");
		tabelleErzeugen();
	}

	private static void tabelleErzeugen() {
		int x = 1;
		int y = 1;
		boolean ytrue = true;
		
		while(x <= anzahl){
			System.out.print("\t"+x);
			x++;
		}
		System.out.println("\n");
		while (ykoord <= anzahl) {
			while (xkoord <= anzahl) {
				ggt(xkoord, ykoord);
				if(ytrue == true){
					System.out.print(y);
				}
				ytrue = false;
				System.out.print("\t"+aktggt);
				xkoord++;
			}
			System.out.println("\t");
			y++;
			ytrue = true;
			xkoord = 1;
			ykoord++;
		}
	}

	public static void ggt(int a, int b) {
		while (a != b) {
			if (a < b) {
				b = b - a;
			} else {
				a = a - b;
			}
		}
		aktggt = a;
	}
}
