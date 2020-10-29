package tutorium6;

public class Scoping {
    static int a = 19;
    static int b = -3;
    public static int fred(){
        return b + 42;
    }
    public static int georg(int b){
        int a;
        a = 42;
        return b + 14;
    }
    public static int hans(int b){
        b = georg(b);
        return b;
    }
    public static int ian(int [] pin){
        int c = 8;
        {
            for (int a = 0; a < 42; a = a + 42){
                int b = 42;
                pin[a] = hans(42);
                b = b + a;
            }
            c = a + b;
        }
        return 42;
    }
    public static void josef(int [] zahlenkolonne){
        zahlenkolonne[0] = hans(georg(zahlenkolonne[0]));
    }

    public static void main(String []args){
        b = 11;
        int a = 0;
        int b = 0;
        System.out.println(a);
        a = fred();
        a = georg(42);
        b = hans(a);
        a = hans(b);
        int [] pinnummer = {0, 0, 0, 0};
        a = ian(new int[]{4});
        josef(pinnummer);
    }
}
