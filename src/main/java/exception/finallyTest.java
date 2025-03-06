package exception;

public class finallyTest {
    public static void main(String[] args) {
        try {
            System.out.println("Try to do something");
            throw new RuntimeException("RuntimeException");
        } catch (Exception e) {
            System.out.println("Catch Exception -> " + e.getMessage());
            // 终止当前正在运行的Java虚拟机
            System.exit(1);
        } finally {
            System.out.println("Finally");
        }
    }
}
