# 集合概述
Java 集合，也叫作容器，主要是由两大接口派生而来：一个是 `Collection`接口，主要用于存放单一元素；另一个是 `Map` 接口，主要用于存放键值对。对于`Collection` 接口，下面又有三个主要的子接口：`List`、`Set` 、`Queue`。

Java 集合框架如下图所示：

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741864674835-4306dfb4-df9b-4f7a-8ee8-382514cc84b5.png)

注：图中只列举了主要的继承派生关系，并没有列举所有关系。比方省略了`AbstractList`, `NavigableSet`等抽象类以及其他的一些辅助类

## 说说 List, Set, Queue, Map 四者的区别？
+ `List`(对付顺序的好帮手): 存储的元素是有序的、可重复的。
+ `Set`(注重独一无二的性质): 存储的元素不可重复的。
+ `Queue`(实现排队功能的叫号机): 按特定的排队规则来确定先后顺序，存储的元素是有序的、可重复的。
+ `Map`(用 key 来搜索的专家): 使用键值对（key-value）存储，类似于数学上的函数 y=f(x)，"x" 代表 key，"y" 代表 value，key 是无序的、不可重复的，value 是无序的、可重复的，每个键最多映射到一个值。

## 集合框架底层数据结构总结
先来看一下 `Collection` 接口下面的集合。

1. List
+ `ArrayList`：`Object[]` 数组。
+ `Vector`：`Object[]` 数组。
+ `LinkedList`：双向链表(JDK1.6 之前为循环链表，JDK1.7 取消了循环)。
2. Set
+ `HashSet`(无序，唯一): 基于 `HashMap` 实现的，底层采用 `HashMap` 来保存元素。
+ `LinkedHashSet`: `LinkedHashSet` 是 `HashSet` 的子类，并且其内部是通过 `LinkedHashMap` 来实现的。
+ `TreeSet`(有序，唯一): 红黑树(自平衡的排序二叉树)。
3. Queue
+ `PriorityQueue`: `Object[]` 数组来实现小顶堆。
+ `DelayQueue`:`PriorityQueue`。
+ `ArrayDeque`: 可扩容动态双向数组。

再来看看 `Map` 接口下面的集合。

1. `HashMap`：JDK1.8 之前 `HashMap` 由数组+链表组成的，数组是 `HashMap` 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间。
2. `LinkedHashMap`：`LinkedHashMap` 继承自 `HashMap`，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。另外，`LinkedHashMap` 在上面结构的基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序。同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。
3. `Hashtable`：数组+链表组成的，数组是 `Hashtable` 的主体，链表则是主要为了解决哈希冲突而存在的。
4. `TreeMap`：红黑树（自平衡的排序二叉树）。

## 如何选用集合?
+ 我们需要根据键值获取到元素值时就选用 `Map` 接口下的集合，需要排序时选择 `TreeMap`,不需要排序时就选择 `HashMap`,需要保证线程安全就选用 `ConcurrentHashMap`。
+ 我们只需要存放元素值时，就选择实现`Collection` 接口的集合，需要保证元素唯一时选择实现 `Set` 接口的集合比如 `TreeSet` 或 `HashSet`，不需要就选择实现 `List` 接口的比如 `ArrayList` 或 `LinkedList`，然后再根据实现这些接口的集合的特点来选用。

## 为什么要使用集合？
+ 存储的数据类型多种多样且数量不确定：与数组相比，Java 集合提供了更灵活、更有效的方法来存储多个数据对象。
+ Java 集合框架中的各种集合类和接口可以存储不同类型和数量的对象，同时还具有多样化的操作方式。
+ Java 集合的优势在于它们的大小可变、支持泛型、具有内建算法等。
+ 总的来说，Java 集合提高了数据的存储和处理灵活性，可以更好地适应现代软件开发中多样化的数据需求，并支持高质量的代码编写。

# List
## ArrayList 和 Array（数组）的区别？
`ArrayList` 内部基于动态数组实现，比 `Array`（静态数组） 使用起来更加灵活：

+ `ArrayList`会根据实际存储的元素动态地**扩容或缩容**，而 `Array` 被创建之后就不能改变它的长度了。
+ `ArrayList` 允许你使用**泛型**来确保类型安全，`Array` 则不可以。
+ `ArrayList` 中**只能存储对象**。对于基本类型数据，需要使用其对应的包装类（如 Integer、Double 等）。`Array` 可以直接存储基本类型数据，也可以存储对象。
+ `ArrayList` 支持**插入、删除、遍历**等常见操作，并且提供了丰富的 API 操作方法，比如 `add()`、`remove()`等。`Array` 只是一个固定长度的数组，只能按照下标访问其中的元素，不具备动态添加、删除元素的能力。
+ `ArrayList`创建时**不需要指定大小**，而`Array`创建时必须指定大小。

下面是二者使用的简单对比：

Array：

```java
 // 初始化一个 String 类型的数组
 String[] stringArr = new String[]{"hello", "world", "!"};
 // 修改数组元素的值
 stringArr[0] = "goodbye";
 System.out.println(Arrays.toString(stringArr));// [goodbye, world, !]
 // 删除数组中的元素，需要手动移动后面的元素
 for (int i = 0; i < stringArr.length - 1; i++) {
     stringArr[i] = stringArr[i + 1];
 }
 stringArr[stringArr.length - 1] = null;
 System.out.println(Arrays.toString(stringArr));// [world, !, null]
```

ArrayList：

```java
// 初始化一个 String 类型的 ArrayList
 ArrayList<String> stringList = new ArrayList<>(Arrays.asList("hello", "world", "!"));
// 添加元素到 ArrayList 中
 stringList.add("goodbye");
 System.out.println(stringList);// [hello, world, !, goodbye]
 // 修改 ArrayList 中的元素
 stringList.set(0, "hi");
 System.out.println(stringList);// [hi, world, !, goodbye]
 // 删除 ArrayList 中的元素
 stringList.remove(0);
 System.out.println(stringList); // [world, !, goodbye]
```

## ArrayList 可以添加 null 值吗？
`ArrayList` 中可以存储任何类型的对象，包括 `null` 值。不过，不建议向`ArrayList` 中添加 `null` 值， `null` 值无意义，会让代码难以维护比如忘记做判空处理就会导致空指针异常。

## ArrayList 插入和删除元素的时间复杂度？
对于插入：

+ 头部插入：由于需要将所有元素都依次向后移动一个位置，因此时间复杂度是 O(n)。
+ 尾部插入：当 `ArrayList` 的容量未达到极限时，往列表末尾插入元素的时间复杂度是 O(1)，因为它只需要在数组末尾添加一个元素即可；当容量已达到极限并且需要扩容时，则需要执行一次 O(n) 的操作将原数组复制到新的更大的数组中，然后再执行 O(1) 的操作添加元素。
+ 指定位置插入：需要将目标位置之后的所有元素都向后移动一个位置，然后再把新元素放入指定位置。这个过程需要移动平均 n/2 个元素，因此时间复杂度为 O(n)。

对于删除：

+ 头部删除：由于需要将所有元素依次向前移动一个位置，因此时间复杂度是 O(n)。
+ 尾部删除：当删除的元素位于列表末尾时，时间复杂度为 O(1)。
+ 指定位置删除：需要将目标元素之后的所有元素向前移动一个位置以填补被删除的空白位置，因此需要移动平均 n/2 个元素，时间复杂度为 O(n)。

## LinkedList 插入和删除元素的时间复杂度？
+ 头部插入/删除：只需要修改头结点的指针即可完成插入/删除操作，因此时间复杂度为 O(1)。
+ 尾部插入/删除：只需要修改尾结点的指针即可完成插入/删除操作，因此时间复杂度为 O(1)。
+ 指定位置插入/删除：需要先移动到指定位置，再修改指定节点的指针完成插入/删除，不过由于有头尾指针，可以从较近的指针出发，因此需要遍历平均 n/4 个元素，时间复杂度为 O(n)。

## LinkedList 为什么不能实现 RandomAccess 接口？
`RandomAccess` 是一个标记接口，用来表明实现该接口的类支持随机访问（即可以通过索引快速访问元素）。由于 `LinkedList` 底层数据结构是链表，内存地址不连续，只能通过指针来定位，不支持随机快速访问，所以不能实现 `RandomAccess` 接口。

## ArrayList 与 LinkedList 区别?
+ **是否保证线程安全：**`ArrayList` 和 `LinkedList` 都是不同步的，也就是**不保证线程安全**；
+ **底层数据结构：**`ArrayList` 底层使用的是 `**Object**`** 数组**；`LinkedList` 底层使用的是 **双向链表** 数据结构（JDK1.6 之前为循环链表，JDK1.7 取消了循环。注意双向链表和双向循环链表的区别）
+ **插入和删除是否受元素位置的影响：**
    - `ArrayList` 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。 比如：执行`add(E e)`方法的时候， `ArrayList` 会默认在将指定的元素追加到此列表的末尾，这种情况时间复杂度就是 O(1)。但是如果要在指定位置 i 插入和删除元素的话（`add(int index, E element)`），时间复杂度就为 O(n)。因为在进行上述操作的时候集合中第 i 和第 i 个元素之后的(n-i)个元素都要执行向后位/向前移一位的操作。
    - `LinkedList` 采用链表存储，所以在头尾插入或者删除元素不受元素位置的影响（`add(E e)`、`addFirst(E e)`、`addLast(E e)`、`removeFirst()`、 `removeLast()`），时间复杂度为 O(1)，如果是要在指定位置 `i` 插入和删除元素的话（`add(int index, E element)`，`remove(Object o)`,`remove(int index)`）， 时间复杂度为 O(n) ，因为需要先移动到指定位置再插入和删除。
+ **是否支持快速随机访问：**`LinkedList` 不支持高效的随机元素访问，而 `ArrayList`（实现了 `RandomAccess` 接口） 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于`get(int index)`方法)。
+ **内存空间占用：**`ArrayList` 的空间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。

我们在项目中一般是不会使用到 `LinkedList` 的，需要用到 `LinkedList` 的场景几乎都可以使用 `ArrayList` 来代替，并且，性能通常会更好。 另外，不要下意识地认为 `LinkedList` 作为链表就最适合元素增删的场景。`LinkedList` 仅仅在头尾插入或者删除元素的时候时间复杂度近似 O(1)，其他情况增删元素的平均时间复杂度都是 O(n) 。

## 说说集合中的 fail-fast 和 fail-safe 是什么
快速失败的思想即针对可能发生的异常进行提前表明故障并停止运行，通过尽早的发现和停止错误，降低故障系统级联的风险。

在`java.util`包下的大部分集合是不支持线程安全的，为了能够提前发现并发操作导致线程安全风险，提出通过维护一个`modCount`记录修改的次数，迭代期间通过比对预期修改次数`expectedModCount`和`modCount`是否一致来判断是否存在并发操作，从而实现快速失败，由此保证在避免在异常时执行非必要的复杂代码。

对应的我们给出下面这样一段在示例，我们首先插入`100`个操作元素，一个线程迭代元素，一个线程删除元素，最终输出结果如愿抛出`ConcurrentModificationException`：

```java
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
```

 我们在初始化时插入了`100`个元素，此时对应的修改`modCount`次数为`100`，随后线程 2 在线程 1 迭代期间进行元素删除操作，此时对应的`modCount`就变为`101`。  
线程 1 在随后`foreach`第 2 轮循环发现`modCount` 为`101`，与预期的`expectedModCount(值为100因为初始化插入了元素100个)`不等，判定为并发操作异常，于是便快速失败，抛出`ConcurrentModificationException`：

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741868307686-0e041fde-c157-44bb-80fc-f175e36482d9.png)

而`fail-safe`也就是安全失败的含义，它旨在即使面对意外情况也能恢复并继续运行，这使得它特别适用于不确定或者不稳定的环境：

该思想常运用于并发容器，最经典的实现就是`CopyOnWriteArrayList`的实现，通过写时复制的思想保证在进行修改操作时复制出一份快照，基于这份快照完成添加或者删除操作后，将`CopyOnWriteArrayList`底层的数组引用指向这个新的数组空间，由此避免迭代时被并发修改所干扰所导致并发操作安全问题，当然这种做法也存缺点即进行遍历操作时无法获得实时结果：

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741868517009-f99c6d3c-7430-47de-9cd0-cb9cf455093c.png)

