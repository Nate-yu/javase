package collections.list;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest {
    public static void main(String[] args) {
        // 初始化一个String类型的ArrayList
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("hello", "world", "!"));
        // 添加元素到ArrayList中
        stringList.add("goodbye");
        System.out.println(stringList);
        // 修改ArrayList中的元素
        stringList.set(0,"hi");
        System.out.println(stringList);
        // 删除ArrayList中的元素
        stringList.remove(0);
        System.out.println(stringList);
    }
}
