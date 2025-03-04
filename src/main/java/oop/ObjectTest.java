package oop;

public class ObjectTest {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = new String("hello");
        String s3 = "hello";

        // 使用 == 比较字符串的引用相等
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true

        // 使用 equals 方法比较字符串相等
        System.out.println(s1.equals(s2)); // true
        System.out.println(s1.equals(s3)); // true
    }
}
