package tutorium3;

public class Leiterspiel_a_Sol extends Spielfeld {
   public static void main(String[] args){
        // Positionen der Spielsteine
        int piece1,piece2;
        // Startpositionen
        piece1 = 0;
        piece2 = 0;

        // Wer ist dran?
        int player;
        player = 1;

        // Wuerfelergebnis , Zielfeld
        int dice, field;

        while(piece1 < 35 && piece2 < 35){
           paintField(piece1,piece2);
           if(player==1){
               field = piece1;
           }
           else{
               field = piece2;
           }

           // Wuerfeln
           dice=dice();
           field = field +dice;
           write("Der Wuerfel zeigt "+dice+". Du kommst auf Feld "+field+".");

           // Schlangen- und Leiterfelder
           if(field<35){
               while(field%5==0 || field%7==0 ){
                   if (player==1)
                       paintField(field,piece2);
                   else
                       paintField(piece1,field);

                   if(field%5==0){
                       field = field + 3;
                       write("Leiterfeld! Du kommst 3 Felder vor auf Feld "+field+".");
                   }
                   if(field%7==0){
                       field = field - 4;
                       if(field<0){
                           field = 0;
                       }
                       write("Schlangenfeld! Du musst leider 4 Felder zurueck auf Feld "+field+".");
                   }
               }
           }
           // Ende des Spielzugs, naechsten Spieler auswaehlen
           if(player==1){
               piece1 = field;
               paintField(piece1,piece2);
               write("Spieler 1, du beendest deinen Zug auf Feld "+piece1+".");
               field =0;
               player = 2;
           }
           else{
               piece2 = field;
               paintField(piece1,piece2);
               write("Spieler 2, du beendest deinen Zug auf Feld "+piece2+".");
               field =0;
               player = 1;
           }
        }
        // Ende des Spiels
        if(piece1 >34){
            write("Spieler 1 gewinnt!");
        }
        else{
            write("Spieler 2 gewinnt!");
        }
    }
}
