
package tutorium2;

import tutorium1.MiniJava;

public class MeiernSolution extends MiniJava {
    public static void main(String[] args) {
        int number, lastNumber;     // Aktueller und letzter Wurf
        int firstDraw, secondDraw;  // Wuerfelaugen
        int playerNumber;           // Spieler (0), Computer (1)
        int output;

        lastNumber = playerNumber = 0;

        number = 1;
        while (number > lastNumber) {
            lastNumber = number;

            // Zweimal wuerfeln:
            firstDraw = dice();
            secondDraw = dice();

            //System.out.print("" + firstDraw + secondDraw);

            // Wuerfelaugen zu einer zweistelligen Zahl kombinieren:
            if (firstDraw > secondDraw) {
                number = firstDraw * 10 + secondDraw;
            } else if (secondDraw > firstDraw) {
                number = firstDraw + secondDraw * 10;
            } else {
                // Pasch ist mehr Wert als ein normaler Wurf
                number = firstDraw * 100 + secondDraw * 10;
            }

            //System.out.println(" " + number);

            if (number == 21) {
                // 21 => "Meier"
                number = number * 1000;
            }

            output = number;
            while (output > 100) {
                output = output / 10;
            }
            if (playerNumber == 0) {
                write("Du wuerfelst " + output);
            } else {
                write("Computer wuerfelt " + output);
            }

            // Naechster Spieler:
            playerNumber = 1 - playerNumber;
        }
        if (playerNumber == 0) {
            write("Du hast gewonnen");
        } else {
            write("Computer hat gewonnen");
        }
    }
}
