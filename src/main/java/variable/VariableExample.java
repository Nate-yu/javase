package variable;

public class VariableExample {
    // 成员变量
    private String name;
    private int age;

    // 方法中的局部变量
    public void method() {
        int num = 10; // 栈中分配的局部变量
        String str = "Hello!"; // 栈中分配的局部变量
        System.out.println(num);
        System.out.println(str);
    }

    // 带参数的方法中的局部变量
    public void method(int num) {
        int sum = num + 10; // 栈中分配的局部变量
        System.out.println(sum);
    }

    // 构造方法中的局部变量
    public VariableExample(String name, int age) {
        this.name = name; // 对成员变量进行赋值
        this.age = age; // 对成员变量进行赋值
        int num = 20; // 栈中分配的局部变量
        String str = "Hello, " + this.name + "!"; // 栈中分配的局部变量
        System.out.println(num);
        System.out.println(str);
    }
}
