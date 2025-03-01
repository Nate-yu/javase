package basicGrammar;

public class increasesDecreases {
    public static void main(String[] args) {
        int a = 9;
        int b = a++; // b = 9, a = 10
        int c = ++a; // c = 11, a = 11
        int d = c--; // d = 11, c = 10
        int e = --d; // e = 10, d = 10
        // a = 11, b = 9, c = 10, d = 10, e = 10
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

    }
}
