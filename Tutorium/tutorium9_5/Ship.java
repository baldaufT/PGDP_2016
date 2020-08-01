package tutorium9_5_sol;

import java.util.LinkedList;

public class Ship {
   private boolean sunk = false; // Schiff gesunken?

   private LinkedList<Coordinate> coordinates; // vom Schiff belegte Felder
   private LinkedList<Coordinate> hit; // vom Schiff belegte Felder, die geraten wurden

   public boolean isSunk() { return sunk; }

   // Konstruktor.
   // Laenge, Startfeld und Ausrichtung (horizontal / vertikal) werden uebergeben.
   public Ship(Coordinate start, int length, boolean vertical) {
      coordinates = new LinkedList<Coordinate>();
      hit = new LinkedList<Coordinate>();

      // Offset je nach Ausrichtung.
      int xOffset = 0, yOffset = 1;
      if (!vertical) {
         xOffset = 1; yOffset = 0;
      }

      // Felder explizit machen und abspeichern.
      for (int i = 0; i < length; i++) {
         Coordinate newC = new Coordinate(start.getX()+i*xOffset,start.getY()+i*yOffset);
         coordinates.add(newC);
      }
   }

   // Vom Schiff belegte Felder.
   public LinkedList<Coordinate> getCoordinates() {
      return coordinates;
   }

   // Wird c vom Schiff belegt?
   public boolean contains(Coordinate c) {
      return coordinates.contains(c);
   }

   // Wurde c getroffen? (vom Schiff belegt und bereits geraten)
   public boolean isHit(Coordinate c) {
      return hit.contains(c);
   }

   // c wurde geraten => Zustand aktualisieren.
   public void hit(Coordinate c) {
      if(coordinates.contains(c) && !isHit(c)) {
         // Treffer
         hit.add(c);
         // Wenn Anzahl Treffer = Anzahl Felder des Schiffs,
         // dann gilt das Schiff als gesunken, ansonsten nicht.
         sunk = (hit.size()==coordinates.size());
      }
   }

   // Grenzt c an ein Feld des aktuellen Schiffs (this) oder
   // ueberschneidet sich mit einem solchen?
   public boolean adjoining(Coordinate c) {
      for (Coordinate d : coordinates) {
         if (Math.abs(c.getX()-d.getX()) <= 1
          && Math.abs(c.getY()-d.getY()) <= 1) {
            return true;
         }
      }
      return false;
   }
}
