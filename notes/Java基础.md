# 基础概念与常识
## Java语言特点
1. 简单
2. 面向对象（封装、多态、继承）
3. Java虚拟机实现平台无关性
4. 支持多线程
5. 具备异常处理和自动内存管理机制
6. 提供了多重安全防护机制：访问权限修饰符、限制程序直接访问操作系统资源
7. 高效
8. 支持网络编程
9. 编译与解释并存

<font style="background-color:rgb(255,233,40);">Java强大的生态是其最大优势</font>

## JavaSE & JavaEE
+ Java SE（Java Platform，Standard Edition）: Java 平台标准版，Java 编程语言的基础，它包含了支持 Java 应用程序开发和运行的核心类库以及虚拟机等核心组件。Java SE 可以用于构建桌面应用程序或简单的服务器应用程序。
+ Java EE（Java Platform, Enterprise Edition ）：Java 平台企业版，建立在 Java SE 的基础上，包含了支持企业级应用程序开发和部署的标准和规范（比如 Servlet、JSP、EJB、JDBC、JPA、JTA、JavaMail、JMS）。 Java EE 可以用于构建分布式、可移植、健壮、可伸缩和安全的服务端 Java 应用程序，例如 Web 应用程序。

简单来说，Java SE 是 Java 的基础版本，Java EE 是 Java 的高级版本。Java SE 更适合开发桌面应用程序或简单的服务器应用程序，Java EE 更适合开发复杂的企业级应用程序或 Web 应用程序。

## JVM & JDK & JRE
### JVM
Java 虚拟机（Java Virtual Machine, JVM）是运行 Java 字节码的虚拟机。JVM 有针对不同系统的特定实现，目的是使用相同的字节码，它们都会给出相同的结果。字节码和不同系统的 JVM 实现是 Java 语言“一次编译，随处可以运行”的关键所在。

如下图所示，不同编程语言（Java、Groovy、Kotlin、JRuby、Clojure ...）通过各自的编译器编译成 `.class` 文件，并最终通过 JVM 在不同平台上运行，即`程序-->编译器-->.class文件-->JVM-->系统平台`。

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1740663961983-cf02340f-173e-47a7-b77a-a026e9223c51.png)

JVM并不是只有一种，可以自由开发属于自己的JVM。

### JDK & JRE
JDK（Java Development Kit）是一个功能齐全的 Java 开发工具包，供开发者使用，用于创建和编译 Java 程序。它包含了 JRE（Java Runtime Environment），以及编译器 javac 和其他工具，如 javadoc（文档生成器）、jdb（调试器）、jconsole（监控工具）、javap（反编译工具）等。

JRE 是运行已编译 Java 程序所需的环境，主要包含以下两个部分：

1. JVM
2. Java 基础类库（Class Library）：一组标准的类库，提供常用的功能和 API（如 I/O 操作、网络通信、数据结构等）。

JRE只包含Java所需环境和类库，JDK包含JRE与用于开发和调试工具。

除了编译Java程序或使用JavaAPI文档需要安装JDK外，某些需要Java特性的应用程序（如jsp转换为servlet或使用反射）也需要JDK来编译和运行Java。

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1740663962128-d29b6aea-5a11-4fb4-a5f4-4d274999d8f3.png)

不过，从 JDK 9 开始，就不需要区分 JDK 和 JRE 的关系了，取而代之的是模块系统（JDK 被重新组织成 94 个模块）+ jlink 工具 (随 Java 9 一起发布的新命令行工具，用于生成自定义 Java 运行时映像，该映像仅包含给定应用程序所需的模块) 。并且，从 JDK 11 开始，Oracle 不再提供单独的 JRE 下载。

## 什么是字节码?采用字节码的好处是什么?
在 Java 中，JVM 可以理解的代码就叫做字节码（即扩展名为 `.class` 的文件），它不面向任何特定的处理器，只面向虚拟机。Java 语言通过字节码的方式，在一定程度上解决了传统解释型语言执行效率低的问题，同时又保留了解释型语言可移植的特点。所以， Java 程序运行时相对来说还是高效的（不过，和 C、 C++，Rust，Go 等语言还是有一定差距的），而且，由于字节码并不针对一种特定的机器，因此，Java 程序无须重新编译便可在多种不同操作系统的计算机上运行。

Java 程序从源代码到运行的过程如下图所示：

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1740663961979-72676101-3442-430d-b60a-daeed76c5621.png)

我们需要格外注意的是 .class->机器码 这一步。在这一步 JVM 类加载器首先加载字节码文件，然后通过解释器逐行解释执行，这种方式的执行速度会相对比较慢。而且，有些方法和代码块是经常需要被调用的(也就是所谓的热点代码)，所以后面引进了 JIT（Just in Time Compilation） 编译器，而 JIT 属于运行时编译。当 JIT 编译器完成第一次编译后，其会将字节码对应的机器码保存下来，下次可以直接使用。而我们知道，机器码的运行效率肯定是高于 Java 解释器的。这也解释了我们为什么经常会说 Java 是编译与解释共存的语言 。

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1740663962018-967ee367-5981-42c1-825d-7c31124478d5.png)

HotSpot 采用了惰性评估(Lazy Evaluation)的做法，根据二八定律，消耗大部分系统资源的只有那一小部分的代码（热点代码），而这也就是 JIT 所需要编译的部分。JVM 会根据代码每次被执行的情况收集信息并相应地做出一些优化，因此执行的次数越多，它的速度就越快。

JDK、JRE、JVM、JIT 这四者的关系如下：

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1740663962264-b9373040-4854-44d7-b2ab-f5d2bab8e523.png)

## 为什么说 Java 语言“编译与解释并存”？
这是因为 Java 语言既具有编译型语言的特征，也具有解释型语言的特征。因为 Java 程序要经过先编译，后解释两个步骤，由 Java 编写的程序需要先经过编译步骤，生成字节码（.class 文件），这种字节码必须由 Java 解释器来解释执行。

## AOT 有什么优点？为什么不全部使用 AOT 呢？
JDK 9 引入了一种新的编译模式 AOT(Ahead of Time Compilation) 。和 JIT 不同的是，这种编译模式会在程序被执行前就将其编译成机器码，属于静态编译（C、 C++，Rust，Go 等语言就是静态编译）。AOT 避免了 JIT 预热等各方面的开销，可以提高 Java 程序的启动速度，避免预热时间长。并且，AOT 还能减少内存占用和增强 Java 程序的安全性（AOT 编译后的代码不容易被反编译和修改），特别适合云原生场景。

 我们前面也对比过 JIT 与 AOT，两者各有优点，只能说 AOT 更适合当下的云原生场景，对微服务架构的支持也比较友好。除此之外，AOT 编译无法支持 Java 的一些动态特性，如反射、动态代理、动态加载、JNI（Java Native Interface）等。然而，很多框架和库（如 Spring、CGLIB）都用到了这些特性。如果只使用 AOT 编译，那就没办法使用这些框架和库了，或者说需要针对性地去做适配和优化。举个例子，CGLIB 动态代理使用的是 ASM 技术，而这种技术大致原理是运行时直接在内存中生成并加载修改后的字节码文件也就是 `.class` 文件，如果全部使用 AOT 提前编译，也就不能使用 ASM 技术了。为了支持类似的动态特性，所以选择使用 JIT 即时编译器。

## Oracle JDK vs OpenJDK
1. **是否开源：**OpenJDK 是一个参考模型并且是完全开源的，而 Oracle JDK 是基于 OpenJDK 实现的，并不是完全开源的（个人观点：众所周知，JDK 原来是 SUN 公司开发的，后来 SUN 公司又卖给了 Oracle 公司，Oracle 公司以 Oracle 数据库而著名，而 Oracle 数据库又是闭源的，这个时候 Oracle 公司就不想完全开源了，但是原来的 SUN 公司又把 JDK 给开源了，如果这个时候 Oracle 收购回来之后就把他给闭源，必然会引起很多 Java 开发者的不满，导致大家对 Java 失去信心，那 Oracle 公司收购回来不就把 Java 烂在手里了吗！然后，Oracle 公司就想了个骚操作，这样吧，我把一部分核心代码开源出来给你们玩，并且我要和你们自己搞的 JDK 区分下，你们叫 OpenJDK，我叫 Oracle JDK，我发布我的，你们继续玩你们的，要是你们搞出来什么好玩的东西，我后续发布 Oracle JDK 也会拿来用一下，一举两得！）OpenJDK 开源项目：[https://github.com/openjdk/jdk](https://github.com/openjdk/jdk) 。
2. **是否免费：**Oracle JDK 会提供免费版本，但一般有时间限制。JDK17 之后的版本可以免费分发和商用，但是仅有 3 年时间，3 年后无法免费商用。不过，JDK8u221 之前只要不升级可以无限期免费。OpenJDK 是完全免费的。
3. **功能性：**Oracle JDK 在 OpenJDK 的基础上添加了一些特有的功能和工具，比如 Java Flight Recorder（JFR，一种监控工具）、Java Mission Control（JMC，一种监控工具）等工具。不过，在 Java 11 之后，OracleJDK 和 OpenJDK 的功能基本一致，之前 OracleJDK 中的私有组件大多数也已经被捐赠给开源组织。
4. **稳定性：**OpenJDK 不提供 LTS 服务，而 OracleJDK 大概每三年都会推出一个 LTS 版进行长期支持。不过，很多公司都基于 OpenJDK 提供了对应的和 OracleJDK 周期相同的 LTS 版。因此，两者稳定性其实也是差不多的。
5. **协议：**Oracle JDK 使用 BCL/OTN 协议获得许可，而 OpenJDK 根据 GPL v2 许可获得许可。

## Java 和 C++ 的区别?
虽然，Java 和 C++ 都是面向对象的语言，都支持封装、继承和多态，但是，它们还是有挺多不相同的地方：

+ Java 不提供指针来直接访问内存，程序内存更加安全
+ Java 的类是单继承的，C++ 支持多重继承；虽然 Java 的类不可以多继承，但是接口可以多继承。
+ Java 有自动内存管理垃圾回收机制(GC)，不需要程序员手动释放无用内存。
+ C ++同时支持方法重载和操作符重载，但是 Java 只支持方法重载（操作符重载增加了复杂性，这与 Java 最初的设计思想不符）。



# 基本语法
## 标识符和关键字的区别是什么？
标识符就是一个名字。关键字是被赋予特殊含义的标识符。

## 自增自减运算符
`++`和`--`运算符可以放变量之前也可以之后：

+ 前缀形式（`++a`或`--a`）：先自增/自减变量的值，然后再使用该变量：`b = ++a` 是先将`a`增加1，然后把增加后的值赋值给`b`。
+ 后缀形式（`a++`或`a--`）：先使用变量的当前值，然后再自增/自减变量的值：`b = a++` 是先将`a`的当前值赋给`b`，然后再将`a`增加1。

> 符号在前先加减，符号在后后加减
>

```java
int a = 9;
int b = a++; // b = 9, a = 10
int c = ++a; // c = 11, a = 11
int d = c--; // d = 11, c = 10
int e = --d; // e = 10, d = 10
// a = 11, b = 9, c = 10, d = 10, e = 10
```

## 移位运算符
移位操作中，被操作的数据被视为二进制数，移位就是将其向左或向右移动若干位的运算。

使用移位运算符的主要原因：

1. **高效**：移位运算符直接对应于处理器的移位指令。现代处理器具有专门的硬件指令来执行这些移位操作，这些指令通常在一个时钟周期内完成。相比之下，乘法和除法等算术运算在硬件层面上需要更多的时钟周期来完成。
2. **节省内存**：通过移位操作，可以使用一个整数（如 `int` 或 `long`）来存储多个布尔值或标志位，从而节省内存。

移位运算符最常用于快速乘以或除以 2 的幂次方。除此之外，它还在以下方面发挥着重要作用：

+ **位字段管理**：例如存储和操作多个布尔值。
+ **哈希算法和加密解密**：通过移位和与、或等操作来混淆数据。
+ **数据压缩**：例如霍夫曼编码通过移位运算符可以快速处理和操作二进制数据，以生成紧凑的压缩格式。
+ **数据校验**：例如 CRC（循环冗余校验）通过移位和多项式除法生成和校验数据完整性。
+ **内存对齐**：通过移位操作，可以轻松计算和调整数据的对齐地址。

Java 中有三种移位运算符：

+ `<<`：左移运算符，向左移动若干位，高位丢弃，低位补0。`x<<n`，相当于$ x\times2^n $。
+ `>>`：带符号右移，向右移动若干位，高位补符号位，低位丢弃。正数高位补0，负数高位补1。`x>>n`相当于$ x/2^n $
+ `>>>`：无符号右移，忽略符号位，空位都以0补齐。

实际应用中，右移操作需要考虑符号位的处理方式。由于 `double`，`float` 在二进制中的表现比较特殊，因此不能来进行移位操作。移位操作符实际上支持的类型只有`int`和`long`，编译器在对`short`、`byte`、`char`类型进行移位前，都会将其转换为int类型再操作。

**如果移位的位数超过数值所占有的位数会怎样？**

当 int 类型左移/右移位数大于等于 32 位操作时，会先求余（%）后再进行左移/右移操作。也就是说左移/右移 32 位相当于不进行移位操作（32%32=0），左移/右移 42 位相当于左移/右移 10 位（42%32=10）。当 long 类型进行左移/右移操作时，由于 long 对应的二进制是 64 位，因此求余操作的基数也变成了 64。

也就是说：`x<<42`等同于`x<<10`，`x>>42`等同于`x>>10`，`x >>>42`等同于`x >>> 10`。

左移：

```java
int i = -1;
System.out.println("初始数据：" + i);
System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(i));
i <<= 10;
System.out.println("左移 10 位后的数据 " + i);
System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(i));
```

输出：

```plain
初始数据：-1
初始数据对应的二进制字符串：11111111111111111111111111111111
左移 10 位后的数据 -1024
左移 10 位后的数据对应的二进制字符 11111111111111111111110000000000
```

由于左移位数大于等于 32 位操作时，会先求余（%）后再进行左移操作，所以下面的代码左移 42 位相当于左移 10 位（42%32=10），输出结果和前面的代码一样。

```java
int i = -1;
System.out.println("初始数据：" + i);
System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(i));
i <<= 42;
System.out.println("左移 10 位后的数据 " + i);
System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(i));
```

## continue、break 和 return 的区别是什么？
1. `continue`：跳出当前这一次循环，继续下一循环
2. `break`：跳出整个循环，继续执行循环下面的语句
3. `return`：跳出所在方法，结束该方法的运行。两种用法：
    1. `return;`：直接使用 return 结束方法执行，用于没有返回值函数的方法
    2. `return value;`：return 一个特定值，用于有返回值函数的方法

```java
public static void main(String[] args) {
    boolean flag = false;
    for (int i = 0; i <= 3; i++) {
        if(i == 0) {
            System.out.println("0");
        } else if (i == 1) {
            System.out.println("1");
            continue;
        } else if (i == 2) {
            System.out.println("2");
            flag = true;
        } else if (i == 3) {
            System.out.println("3");
            break;
        } else if (i == 4) {
            System.out.println("4");
        }
        System.out.println("xixi");
    }
    if (flag) {
        System.out.println("haha");
        return;
    }
    System.out.println("heihei");
}
```

输出：

```latex
0
xixi
1
2
xixi
3
haha
```

# 基本数据类型
## Java 中的几种基本数据类型
8种基本数据类型：

+ 6种数字类型：
    - 4种整数型：`byte`、`short`、`int`、`long`
    - 2种浮点型：`float`、`double`
+ 1种字符类型：`char`
+ 1种布尔型：`boolean`

8 种基本数据类型的默认值以及所占空间的大小如下：

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1740921975708-e5c5a56c-9106-47f4-9327-c4e3836eb230.png)

可以看到，像 `byte`、`short`、`int`、`long`能表示的最大正数都减 1 了。这是为什么呢？

这是因为在二进制补码表示法中，最高位是用来表示符号的（0 表示正数，1 表示负数），其余位表示数值部分。所以，如果我们要表示最大的正数，我们需要把除了最高位之外的所有位都设为 1。如果我们再加 1，就会导致溢出，变成一个负数。

对于 `boolean`，官方文档未明确定义，它依赖于 JVM 厂商的具体实现。逻辑上理解是占用 1 位，但是实际中会考虑计算机高效存储因素。

另外，Java 的每种基本类型所占存储空间的大小不会像其他大多数语言那样随机器硬件架构的变化而变化。这种所占存储空间大小的不变性是 Java 程序比用其他大多数语言编写的程序更具可移植性的原因之一。

**注意：**

1. Java 里使用 `long` 类型的数据一定要在数值后面加上 **L**，否则将作为整型解析。
2. Java 里使用 `float` 类型的数据一定要在数值后面加上 **f 或 F**，否则将无法通过编译。
3. `char a = 'h'`char :单引号，`String a = "hello"` :双引号。

这八种基本类型都有对应的包装类分别为：`Byte`、`Short`、`Integer`、`Long`、`Float`、`Double`、`Character`、`Boolean` 。

## 基本类型和包装类型的区别？
1. 用途：基本类型一般用于定义常量和局部变量。包装类型定义方法参数、对象属性等，且包装类型可以用于泛型，而基本类型不可以
2. 存储方式：基本数据类型的局部变量存放在JVM栈中的局部变量表中，基本数据类型的成员变量（未被`static`修饰）存放在JVM堆中。包装类型属于对象类型，所有对象实例都存在于堆中。
3. 占用空间：基本数据类型占用空间非常小
4. 默认值：包装类型默认值均为`null`，基本类型有默认值
5. 比较方式：对于基本类型来说，`==`比较的是值，对于包装类型来说，`==`比较的是对象的内存地址。所有整型包装类对象值之间的比较，全部使用`equals()`方法。

**为什么说是几乎所有对象实例都存在于堆中呢？** 

这是因为 HotSpot 虚拟机引入了 JIT 优化之后，会对对象进行逃逸分析，如果发现某一个对象并没有逃逸到方法外部，那么就可能通过标量替换来实现栈上分配，而避免堆上分配内存

 ⚠️ 注意：**基本数据类型存放在栈中是一个常见的误区！** 基本数据类型的存储位置取决于它们的作用域和声明方式。如果它们是局部变量，那么它们会存放在栈中；如果它们是成员变量，那么它们会存放在堆/方法区/元空间中。

```java
public class Test {
    // 成员变量，存放在堆中
    int a = 10;
    // 被 static 修饰的成员变量，JDK 1.7 及之前位于方法区，1.8 后存放于元空间，均不存放于堆中。
    // 变量属于类，不属于对象。
    static int b = 20;

    public void method() {
        // 局部变量，存放在栈中
        int c = 30;
        static int d = 40; // 编译错误，不能在方法中使用 static 修饰局部变量
    }
}
```

## 包装类型的缓存机制了解么？
Java 基本数据类型的包装类型的大部分都用到了缓存机制来提升性能。

`Byte`,`Short`,`Integer`,`Long` 这 4 种包装类默认创建了数值 **[-128，127]** 的相应类型的缓存数据，`Character` 创建了数值在 **[0,127]** 范围的缓存数据，`Boolean` 直接返回 `True` or `False`。

`Integer`缓存源码：

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static {
        // high value may be configured by property
        int h = 127;
    }
}
```

`Character`缓存源码：

```java
public static Character valueOf(char c) {
    if (c <= 127) { // must cache
      return CharacterCache.cache[(int)c];
    }
    return new Character(c);
}

private static class CharacterCache {
    private CharacterCache(){}
    static final Character cache[] = new Character[127 + 1];
    static {
        for (int i = 0; i < cache.length; i++)
            cache[i] = new Character((char)i);
    }

}
```

`Boolean`缓存源码：

```java
public static Boolean valueOf(boolean b) {
    return (b ? TRUE : FALSE);
}
```

如果超出对应范围仍然会去创建新的对象，缓存的范围区间的大小只是在性能和资源之间的权衡。

两种浮点数类型的包装类 `Float`,`Double` 并没有实现缓存机制。

```java
Integer i1 = 33;
Integer i2 = 33;
System.out.println(i1 == i2);// 输出 true

Float i11 = 333f;
Float i22 = 333f;
System.out.println(i11 == i22);// 输出 false

Double i3 = 1.2;
Double i4 = 1.2;
System.out.println(i3 == i4);// 输出 false
```

## 自动装箱与拆箱了解吗？原理是什么？
什么是自动拆装箱？

+ 装箱：将基本类型用它们对应的引用类型包装起来；
+ 拆箱：将包装类型转换为基本数据类型；

```java
Integer i = 10;  //装箱
int n = i;   //拆箱
```

`Integer i = 10;` 等价于 `Integer i = Integer.valueOf(10);`

`int n = i;` 等价于`int n = i.intValue();`

注意：如果频繁拆装箱的话，也会严重影响系统的性能。我们应该尽量避免不必要的拆装箱操作。

## 为什么浮点数运算的时候会有精度丢失的风险？
```java
float a = 2.0f - 1.9f;
float b = 1.8f - 1.7f;
System.out.printf("%.9f",a);// 0.100000024
System.out.println(b);// 0.099999905
System.out.println(a == b);// false
```

为什么会出现这个问题呢？

这个和计算机保存浮点数的机制有很大关系。我们知道计算机是二进制的，而且计算机在表示一个数字时，宽度是有限的，**无限循环的小数存储在计算机时，只能被截断，所以就会导致小数精度发生损失的情况**。这也就是解释了为什么浮点数没有办法用二进制精确表示。

就比如说十进制下的 0.2 就没办法精确转换成二进制小数：

```java
// 0.2 转换为二进制数的过程为，不断乘以 2，直到不存在小数为止，
// 在这个计算过程中，得到的整数部分从上到下排列就是二进制的结果。
0.2 * 2 = 0.4 -> 0
0.4 * 2 = 0.8 -> 0
0.8 * 2 = 1.6 -> 1
0.6 * 2 = 1.2 -> 1
0.2 * 2 = 0.4 -> 0（发生循环）
...
```

## 如何解决浮点数运算的精度丢失问题？
`BigDecimal` 可以实现对浮点数的运算，不会造成精度丢失。通常情况下，大部分需要浮点数精确运算结果的业务场景（比如涉及到钱的场景）都是通过 `BigDecimal` 来做的。

```java
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
```

## 超过 long 整型的数据应该如何表示？
基本数值类型都有一个表达范围，如果超过这个范围就会有数值溢出的风险。

在 Java 中，64 位 long 整型是最大的整数类型。

```java
long l = Long.MAX_VALUE;
System.out.println(l + 1); // -9223372036854775808
System.out.println(l + 1 == Long.MIN_VALUE); // true
```

`BigInteger` 内部使用 `int[]` 数组来存储任意大小的整形数据。

相对于常规整数类型的运算来说，`BigInteger` 运算的效率会相对较低。

# 变量
## 成员变量与局部变量的区别
+ 语法形式：成员变量属于类，局部变量是在代码块或方法中定义的变量或者是方法的参数；成员变量可以被`public`、`private`、`static`等修饰符所修饰，而局部变量不能被修饰；局部变量与成员变量均可被`final`所修饰
+ 存储方式：若成员变量被`static`修饰，则属于类，否则属于实例，存在于堆内存，局部变量存在于栈内存中
+ 生存时间：成员变量随着对象的创建而存在，局部变量随着方法的调用自动生成，调用结束消亡
+ 默认值：没有被赋初始值的成员变量自动以类型的默认值而赋值（被`final`修饰的成员变量必须显示地被赋值），局部变量不会自动赋值

**为什么成员变量有默认值？**

1. 没有默认值就会导致变量是任意随机值，程序读取该值运行会出现意外
2. 成员变量在运行时可以借助反射等方式手动赋值，局部变量不行
3. 局部变量没赋值可以直接报错，而成员变量可能是运行时赋值，无法判断

成员变量与局部变量代码示例：

```java
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
```

## 静态变量有什么作用？
被`static`修饰的变量为静态变量，可以被类的所有实例共享，只会被分配一次内存。静态变量可以通过类名来访问。

```java
public class StaticVariableExample {
    // 静态变量
    public static int staticVar = 0;
}
```

通常情况下，静态变量会被 `final` 关键字修饰成为常量。

```java
public class ConstantVariableExample {
    // 常量
    public static final int constantVar = 0;
}
```

## 字符型常量和字符串常量的区别?
+ 形式 : 字符常量是单引号引起的一个字符，字符串常量是双引号引起的 0 个或若干个字符
+ 含义 : 字符常量相当于一个整型值( ASCII 值)，可以参加表达式运算；字符串常量代表一个地址值(该字符串在内存中存放位置)
+ 占内存大小：字符常量只占 2 个字节; 字符串常量占若干个字节。

注意：`char` 在 Java 中占两个字节。字符型常量和字符串常量代码示例：

```java
public class StringExample {
    // 字符型常量
    public static final char LETTER_A = 'A';

    // 字符串常量
    public static final String GREETING_MESSAGE = "Hello, world!";
    public static void main(String[] args) {
        System.out.println("字符型常量占用的字节数为："+Character.BYTES);
        System.out.println("字符串常量占用的字节数为："+GREETING_MESSAGE.getBytes().length);
    }
}
```

输出：



