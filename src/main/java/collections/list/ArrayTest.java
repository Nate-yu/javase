package collections.list;

import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        // 初始化一个String类型的数组
        String[] stringArr = {"hello", "world", "!"};
        // 修改数组元素的值
        stringArr[0] = "goodbye";
        System.out.println(Arrays.toString(stringArr));
        // 删除数组中的元素，需要手动移动后面的元素
        for (int i = 0; i < stringArr.length - 1; i++) {
            stringArr[i] = stringArr[i+1];
        }
        stringArr[stringArr.length - 1] = null;
        System.out.println(Arrays.toString(stringArr));
    }
}
