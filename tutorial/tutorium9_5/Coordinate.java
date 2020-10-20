package tutorium9_5_sol;

public class Coordinate {

   private int x, y;

   public Coordinate(int x, int y) {
      this.x=x;
      this.y=y;
   }

   public int getX() { return x; }
   public int getY() { return y; }

   public boolean isValid() {
      return x <= 9 && x >= 0 && y <= 9 && y >= 0;
   }

   @Override
   public boolean equals(Object other){
      Coordinate otherAsCoordinate = (Coordinate) other;
      return x == otherAsCoordinate.x && y == otherAsCoordinate.y;
   }
}
