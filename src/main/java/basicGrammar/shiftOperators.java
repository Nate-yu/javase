package basicGrammar;

public class shiftOperators {
    public static void main(String[] args) {
        left();
    }
    public static void test() {
        int x = 5;
        int x_1 = x << 2;
        int y = -20;
        int y_1 = y >> 2;
        int y_2 = y >>> 2;
        System.out.println(x_1);
        System.out.println(y_1);
        System.out.println(y_2);
    }
    public static void left() {
        int i = -1;
        System.out.println("初始数据：" + i);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(i));
        i <<= 10;
        System.out.println("左移 10 位后的数据 " + i);
        System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(i));

        //左移42位等于左移10位
        int i1 = -1;
        System.out.println("初始数据：" + i1);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(i1));
        i1 <<= 42;
        System.out.println("左移 10 位后的数据 " + i1);
        System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(i1));
    }
}
