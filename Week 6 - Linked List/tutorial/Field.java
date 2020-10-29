package tutorium9_5_sol;

import java.util.LinkedList;

public class Field {

   // Liste mit allen Schiffen
   private LinkedList<Ship> shipList;

   // Liste mit allen geratenen Feldern.
   // Wird fuer die Spielfeldausgabe verwendet.
   private LinkedList<Coordinate> guess;

   // c wurde geraten
   private void hit(Coordinate c) {
      guess.add(c);
      // Den Schiffen Bescheid geben, dass c geraten wurde.
      for(Ship ship : shipList) {
         ship.hit(c);
      }
   }

   // Gibt zurueck, ob alle Schiffe gesunken sind.
   private boolean won() {
      for(Ship ship : shipList) {
         if (!ship.isSunk()) {
            return false;
         }
      }
      return true;
   }

   // Gibt zurueck, ob c erlaubt ist, d.h. ob c
   // an kein bereits platziertes Schiff angrenzt
   // (bzw. sich damit ueberschneidet)
   // und innerhalb der Grenzen des Spielfelds liegt
   private boolean positionOK(Coordinate c) {
      if (!c.isValid()) {
         // c liegt ausserhalb des Spielfelds.
         return false;
      }
      for(Ship ship : shipList) {
         if (ship.adjoining(c)) {
            // c grenzt an ship oder ship belegt c.
            return false;
         }
      }
      return true;
   }

   // Gibt zurueck, ob alle von ship belegten Felder konfliktfrei
   // sind in Bezug zu den bereits platzierten Schiffen.
   // Jedes von ship belegte Feld wird einzeln ueberprueft, ob
   // es einen Konflikt verursacht oder verwendet werden darf.
   private boolean positionOK(Ship ship) {
      for(Coordinate c : ship.getCoordinates()) {
         if (!positionOK(c)) {
            return false;
         }
      }
      return true;
   }

   // Gibt msg aus und liest eine Zahl zwischen low und high ein.
   private int readInt(String msg, int low, int high) {
      System.out.print(msg);
      int input = new java.util.Scanner(System.in).nextInt();
      while (input < low || input > high) {
         System.out.print("Bitte geben Sie eine Zahl zwischen "
             + low + " und " + high + " ein! ");
         input = new java.util.Scanner(System.in).nextInt();
      }
      return input;
   }

   // Liest eine Koordinate ein. Ruft printFields mit Parameter true auf,
   // falls eine -1 eingegeben wird.
   private Coordinate readCoordinate() {
      int input;
      while (true) {
         input = readInt("Koordinate in der Form \"XY\" "
            + "eingeben (X Felder nach rechts, Y nach oben): ", -1, 99);
         if (input!=-1) break;
         printFields(true);
      }
      return new Coordinate(input/10,input%10);
   }

   // Aufruf mit true zeigt alle Schiffe an,
   // Aufruf mit false nur die schon geratenen Felder.
   public void printFields(boolean show) {
      System.out.println("\n  --------------------");
      for (int up = 9; up >= 0; up--) {
         for (int right = 1; right <= 24; right++) {
            int x=(right-3)/2;
            Coordinate current = new Coordinate(x,up);
            char next = ' ';
            if (right == 1) next = (""+up).charAt(0); else
            if (right == 2 || right == 22) next = '|';
            for (int shipNr = 0; shipNr < shipList.size(); shipNr++) {
               if (right%2==0) break;
               Ship ship = shipList.get(shipNr);
               if (show && ship.contains(current)) {
                  next='X'; break;
               }
               for (int i = 0; i < guess.size(); i++) {
                  if (!show && guess.get(i).equals(current)) {
                     next='W'; // Wasser
                  }
               }
               if (!show && ship.isHit(current)) {
                  next='T'; // Treffer
                  if (ship.isSunk()) next='V'; // Versenkt
                  break;
               }
            }
            System.out.print(next);
         }
         System.out.println();
      }
      System.out.println("  - - - - - - - - - -\n  0 1 2 3 4 5 6 7 8 9\n");
   }

   // init legt von den Laengen 2 bis 4 je ein Schiff an und platziert
   // diese regelkonform auf dem Spielfeld.
   private void init() {
      final int min = 2, max = 4;

      shipList = new LinkedList<Ship>();
      guess = new LinkedList<Coordinate>();

      System.out.println("\n\nInitialisierung...\n");
      for (int shipNr = min; shipNr <= max; shipNr++) {
         // Lege Schiff mit Laenge shipNr an.
         while (true) {
            // Zufaelliges Feld waehlen.
            Coordinate start = new Coordinate(myRandom(0,10),myRandom(0,10));
            // Zufaellige Ausrichtung (vertikal / horizontal) waehlen.
            int test = myRandom(0,2);

            // Schiff provisorisch anlegen.
            Ship ship = new Ship(start, shipNr, (test==0));
            if (positionOK(ship)) {
               // Regelkonform platziertes Schiff annehmen.
               shipList.add(ship);
               // Weiter mit naechstem Schiff.
               break;
            }
         }
      }
   }

   public void play() {
      init();

      // Spielen, bis der Spieler gewonnen hat.
      while (true) {
         printFields(false);
         System.out.println("\nNaechster Zug:\n");
         Coordinate coordinate = readCoordinate();
         hit(coordinate);
         if (won()) {
            System.out.println("Gewonnen!");
            break;
         }
      }
      printFields(false);
   }

   // Zufallszahl zwischen low und high-1.
   private static int myRandom(int low, int high) {
      return (int) (Math.random() * (high - low) + low);
   }
}
