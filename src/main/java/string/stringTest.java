package string;

public class stringTest {
    public static void stringConstant() {
        // 在字符串常量池中创建字符串对象 ”ab“
        // 将字符串对象 ”ab“ 的引用赋值给 aa
        String aa = "ab";
        // 直接返回字符串常量池中字符串对象 ”ab“，赋值给引用 bb
        String bb = "ab";
        System.out.println(aa == bb);; // true
    }

    public static void stringIntern() {
        // s1 指向字符串常量池中的 "Java" 对象
        String s1 = "Java";
        // s2 也指向字符串常量池中的 "Java" 对象，和 s1 是同一个对象
        String s2 = s1.intern();
        // 在堆中创建一个新的 "Java" 对象，s3 指向它
        String s3 = new String("Java");
        // s4 指向字符串常量池中的 "Java" 对象，和 s1 是同一个对象
        String s4 = s3.intern();
        // s1 和 s2 指向的是同一个常量池中的对象
        System.out.println(s1 == s2); // true
        // s3 指向堆中的对象，s4 指向常量池中的对象，所以不同
        System.out.println(s3 == s4); // false
        // s1 和 s4 都指向常量池中的同一个对象
        System.out.println(s1 == s4); // true
    }

    public static void stringConcat() {
        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        String str5 = "string";
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false
    }

    public static void stringFinalConcat() {
        final String str1 = "str";
        final String str2 = "ing";
        // 下面两个表达式其实是等价的
        String c = "str" + "ing";// 常量池中的对象
        String d = str1 + str2; // 常量池中的对象
        System.out.println(c == d);// true
    }

    public static String getStr() {
        return "ing";
    }
    public static void stringBuildFinalConcat() {
        final String str1 = "str";
        final String str2 = getStr();
        String c = "str" + "ing";// 常量池中的对象
        String d = str1 + str2; // 在堆上创建的新的对象
        System.out.println(c == d);// false

    }

    public static void main(String[] args) {
        System.out.println("stringConstant: ");
        stringConstant();
        System.out.println("stringIntern: ");
        stringIntern();
        System.out.println("stringConcat: ");
        stringConcat();
        System.out.println("stringFinalConcat(): ");
        stringFinalConcat();
        System.out.println("stringBuildFinalConcat(): ");
        stringBuildFinalConcat();
    }
}
