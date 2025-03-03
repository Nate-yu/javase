package basicDataType;

import java.math.BigDecimal;
import java.util.Objects;

public class bigDecimal {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("1.00");
        BigDecimal c = new BigDecimal("0.8");

        BigDecimal x = a.subtract(c);
        BigDecimal y = b.subtract(c);

        System.out.println(x); /* 0.2 */
        System.out.println(y); /* 0.20 */
        // 比较内容，不是比较值
        System.out.println(Objects.equals(x, y)); /* false */
        // 比较值相等用相等compareTo，相等返回0
        System.out.println(0 == x.compareTo(y)); /* true */
    }
}
