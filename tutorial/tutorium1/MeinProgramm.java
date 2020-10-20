package tutorium1;

public class MeinProgramm extends MiniJava{

	public static void main(String[] args) {
		MiniJavaTeil();
	}
	private static void MiniJavaTeil(){
		// 2 Zahlen einlesen, addieren und ausgeben und dann das Programm wieder schlieﬂen
		int x = read();
		int y = read();
		int z = x + y;
		write(z);
	}
}
