package collections.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class FailFast {
    public static void main(String[] args) throws InterruptedException {
        // 使用线程安全的 CopyOnWriteArrayList 避免 ConcurrentModificationException
        List<Integer> list = new CopyOnWriteArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 添加元素
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Thread t1 = new Thread(() -> {
            // 迭代元素 (注意：Integer 是不可变的，这里的 i++ 不会修改 list 中的值)
            for (Integer i : list) {
                i++; // 这行代码实际上没有修改list中的元素
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            System.out.println("删除元素1");
            list.remove(Integer.valueOf(1)); // 使用 Integer.valueOf(1) 删除指定值的对象
            countDownLatch.countDown();
        });

        t1.start();
        t2.start();
        countDownLatch.await();
    }
}
