package hausaufgabe3;

public class SuV_Sol extends MiniJava {
    public static void main(String[] args) {
        int playerOne, playerTwo; // Score of 1st/2nd player

        write("Player one");
        playerOne = drawCard(); // start with 1 card
        // Exit when score exceeds 21 or user refuses to play.
        int cont = 1;
        while (cont == 1) {
            // Draw a card.
            playerOne = playerOne + drawCard();
            write("Player one: Your score is " + playerOne);
            if (playerOne > 21) {
                // Score > 17 + 4 :-)
                write("Player one lost!");
                return;
            }
            cont = read("Player one continue? (1=yes, 0=no)");
            while ((cont > 1) || (cont < 0)) {
                cont = read("Player one continue? (1=yes, 0=no)");
            }
        }

        write("It's your turn, player two!");
        playerTwo =  drawCard(); // start with 1 card
        // Second player.
        cont = 1;
        while (cont == 1) {
            // Draw a card.
            playerTwo = playerTwo + drawCard();
            write("Player two: Your score is " + playerTwo);
            if (playerTwo > 21) {
                // Score > 17 + 4 :-)
                write("Player two lost!");
                return;
            }
            cont = read("Player two continue? (1=yes, 0=no)");
            while ((cont > 1) || (cont < 0)) {
                cont = read("Player two continue? (1=yes, 0=no)");
            }
        }

        // Both players have less or equal 21 points
        // Inform about result:
        if (playerOne == playerTwo || playerOne > playerTwo) {
            write("Player one won!");
        } else {
            write("Player two won!");
        }
    }
}
