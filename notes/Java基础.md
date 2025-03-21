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

```java
字符型常量占用的字节数为：2
字符串常量占用的字节数为：13
```

# 方法
## 什么是方法的返回值?方法有哪几种类型？
方法的返回值 是指我们获取到的某个方法体中的代码执行后产生的结果（前提是该方法可能产生结果）。返回值的作用是接收出结果，使得它可以用于其他的操作！

我们可以按照方法的返回值和参数类型将方法分为下面这几种：

1. 无参数无返回值的方法

```java
public void f1() {
    //......
}
// 下面这个方法也没有返回值，虽然用到了 return
public void f(int a) {
    if (...) {
        // 表示结束方法的执行,下方的输出语句不会执行
        return;
    }
    System.out.println(a);
}
```

2. 有参数无返回值的方法

```java
public void f2(Parameter 1, ..., Parameter n) {
    //......
}
```

3. 有返回值有参数的方法

```java
public int f4(int a, int b) {
    return a * b;
}
```

## 静态方法为什么不能调用非静态成员?
这个需要结合 JVM 的相关知识，主要原因如下：

1. 静态方法是属于类的，在类加载时就会分配内存，可以通过类名直接访问。而非静态成员属于实例对象，只有在对象实例化之后才存在，需要通过类的实例对象去访问
2. 在类的非静态成员不存在的时候，静态方法就已经存在了，此时调用在内存中还不存在的非静态成员，属于非法操作

## 静态方法和实例方法有何不同？
1. 调用方式

在外部调用静态方法时，可以使用 `类名.方法名` 的方式，也可以使用 `对象.方法名` 的方式，而实例方法只有后面这种方式。也就是说，调用静态方法可以无需创建对象 。

不过，需要注意的是一般不建议使用 `对象.方法名` 的方式来调用**静态方法**。这种方式非常容易造成混淆，静态方法不属于类的某个对象而是属于这个类。

因此，一般建议使用 `类名.方法名` 的方式来调用**静态方法**。

```java
public class Person {
    public void method() {
      //......
    }

    public static void staicMethod(){
      //......
    }
    public static void main(String[] args) {
        Person person = new Person();
        // 调用实例方法
        person.method();
        // 调用静态方法
        Person.staicMethod()
    }
}
```

2. 访问类成员是否存在限制

静态方法在访问本类的成员时，只允许访问静态成员（即静态成员变量和静态方法），不允许访问实例成员（即实例成员变量和实例方法），而实例方法不存在这个限制。

## 重载和重写有什么区别？
**重载**就是同样的一个方法能够根据输入数据的不同，做出不同的处理

**重写**就是当子类继承自父类的相同方法，输入数据一样，但要做出有别于父类的响应时，你就要覆盖父类方法

### 重载
发生在同一个类中（或者父类和子类之间），方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同。

综上：重载就是同一个类中多个同名方法根据不同的传参来执行不同的逻辑处理。

### 重写
重写发生在运行期，是子类对父类的允许访问的方法的实现过程进行重新编写。

1. 方法名、参数列表必须相同，子类方法返回值类型应比父类方法返回值类型更小或相等，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类。
2. 如果父类方法访问修饰符为 `private/final/static` 则子类就不能重写该方法，但是被 static 修饰的方法能够被再次声明。
3. 构造方法无法被重写

综上：重写就是**子类对父类方法**的重新改造，外部样子不能改变，内部逻辑可以改变。

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741004910668-c4eea829-fc6e-475b-950b-94dfa3360bdd.png)

方法的重写要遵循“两同两小一大”：

+ “两同”即方法名相同、形参列表相同；
+ “两小”指的是子类方法返回值类型应比父类方法返回值类型更小或相等，子类方法声明抛出的异常类应比父类方法声明抛出的异常类更小或相等；
+ “一大”指的是子类方法的访问权限应比父类方法的访问权限更大或相等。

如果方法的返回类型是 void 和基本数据类型，则返回值重写时不可修改。但是如果方法的返回值是引用类型，重写时是可以返回该引用类型的子类的。

```java
public class Hero {
    public String name() {
        return "超级英雄";
    }
}
public class SuperMan extends Hero{
    @Override
    public String name() {
        return "超人";
    }
    public Hero hero() {
        return new Hero();
    }
}

public class SuperSuperMan extends SuperMan {
    @Override
    public String name() {
        return "超级超级英雄";
    }

    @Override
    public SuperMan hero() {
        return new SuperMan();
    }
}
```

## 什么是可变长参数？
可变长参数就是允许在调用方法时传入不定长度的参数。就比如下面这个方法就可以接受 0 个或者多个参数：

```java
public static void method1(String... args) {
   //......
}
```

另外，可变参数只能作为函数的最后一个参数，但其前面可以有也可以没有任何其他参数。

```java
public static void method2(String arg1, String... args) {
   //......
}
```

**遇到方法重载的情况怎么办呢？会优先匹配固定参数还是可变参数的方法呢？**

答：会优先匹配固定参数的方法，因为固定参数的方法匹配度更高。

```java
package method;

public class VariableLengthArgument {
    public static void printVariable(String... args) {
        for (String s : args) {
            System.out.println(s);
        }
    }

    public static void printVariable(String arg1, String arg2) {
        System.out.println(arg1 + arg2);
    }

    public static void main(String[] args) {
        printVariable("a", "b");
        printVariable("a","b","c","d");
    }
}
```

输出：

```plain
ab
a
b
c
d
```

另外，Java 的可变参数编译后实际会被转换成一个数组，我们看编译后生成的 class文件就可以看出来了。

```java
public class VariableLengthArgument {

    public static void printVariable(String... args) {
        String[] var1 = args;
        int var2 = args.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            System.out.println(s);
        }
        
    }
    // ......
}
```

# 面向对象基础
## 面向对象和面向过程的区别
+ **<font style="color:rgb(60, 60, 67);">面向过程编程（POP）</font>**<font style="color:rgb(60, 60, 67);">：面向过程把解决问题的过程拆成一个个方法，通过一个个方法的执行解决问题。</font>
+ **<font style="color:rgb(60, 60, 67);">面向对象编程（OOP）</font>**<font style="color:rgb(60, 60, 67);">：面向对象会先抽象出对象，然后用对象执行方法的方式解决问题。</font>

## <font style="color:rgb(60, 60, 67);">创建一个对象用什么运算符?对象实体与对象引用有何不同?</font>
new 运算符，new 创建对象实例（对象实例在堆内存中），对象引用指向对象实例（对象引用存放在栈内存中）。

+ 一个对象引用可以指向 0 个或 1 个对象（一根绳子可以不系气球，也可以系一个气球）；
+ 一个对象可以有 n 个引用指向它（可以用 n 条绳子系住一个气球）。

## 对象的相等和引用相等的区别
+ <font style="color:rgb(60, 60, 67);">对象的相等一般比较的是内存中存放的内容是否相等。</font>
+ <font style="color:rgb(60, 60, 67);">引用相等一般比较的是他们指向的内存地址是否相等。</font>

```java
public class ObjectTest {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = new String("hello");
        String s3 = "hello";

        // 使用 == 比较字符串的引用相等
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);

        // 使用 equals 方法比较字符串相等
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
    }
}
```

从上面的代码输出结果可以看出：

+ `str1` 和 `str2` 不相等，而 `str1` 和 `str3` 相等。这是因为 `==` 运算符比较的是字符串的引用是否相等。
+ `str1`、 `str2`、`str3` 三者的内容都相等。这是因为`equals` 方法比较的是字符串的内容，即使这些字符串的对象引用不同，只要它们的内容相等，就认为它们是相等的。

## 构造方法有哪些特点？是否可被 override?
构造方法具有以下特点：

+ **名称与类名相同**：构造方法的名称必须与类名完全一致。
+ **没有返回值**：构造方法没有返回类型，且不能使用 `void` 声明。
+ **自动执行**：在生成类的对象时，构造方法会自动执行，无需显式调用。

构造方法**不能被重写（override）**，但**可以被重载（overload）**。因此，一个类中可以有多个构造方法，这些构造方法可以具有不同的参数列表，以提供不同的对象初始化方式。

## 面向对象三大特征
### 封装
封装是指把一个对象的状态信息（属性）隐藏在对象内部，不允许外部对象直接访问对象内部信息。但可以提供一些可以被外界访问的方法来操作属性。

```java
public class Student {
    private int id;//id属性私有化
    private String name;//name属性私有化

    //获取id的方法
    public int getId() {
        return id;
    }

    //设置id的方法
    public void setId(int id) {
        this.id = id;
    }

    //获取name的方法
    public String getName() {
        return name;
    }

    //设置name的方法
    public void setName(String name) {
        this.name = name;
    }
}
```

### 继承
继承是使用已存在的类的定义作为基础建立新类的技术，新类的定义可以增加新的数据或新的功能，也可以使用父类的功能，但不能选择性地继承父类。

1. 子类拥有父类对象的所有属性和方法（包括私有属性和方法），但父类中私有属性和方法子类无法访问，只是拥有
2. 子类可以对父类进行扩展
3. 子类可以用自己的方式实现父类的方法

### 多态
表示一个对象具有多种状态，具体表现为父类的引用指向子类的实例。

多态特点：

+ 对象类型和引用类型之间具有继承（类）/实现（接口）的关系
+ 引用类型变量发出的方法调用的是哪个类中的方法，必须在程序运行期间才能确定
+ 多态不能调用“只在子类存在但在父类不存在”的方法
+ 如果子类重写了父类的方法，整整执行的是子类重写的方法，否则执行的就是父类的方法

## 接口和抽象类有什么共同点和区别？
### 接口和抽象类的共同点
+ **<font style="color:rgb(60, 60, 67);">实例化</font>**<font style="color:rgb(60, 60, 67);">：接口和抽象类都不能直接实例化，只能被实现（接口）或继承（抽象类）后才能创建具体的对象。</font>
+ **<font style="color:rgb(60, 60, 67);">抽象方法</font>**<font style="color:rgb(60, 60, 67);">：接口和抽象类都可以包含抽象方法。抽象方法没有方法体，必须在子类或实现类中实现。</font>

### <font style="color:rgb(60, 60, 67);">接口和抽象类的区别</font>
+ **设计目的**：接口主要用于对类的行为进行约束，你实现了某个接口就具有了对应的行为。抽象类主要用于代码复用，强调的是所属关系。
+ **继承和实现**：一个类只能继承一个类（包括抽象类），因为 Java 不支持多继承。但一个类可以实现多个接口，一个接口也可以继承多个其他接口。
+ **成员变量**：接口中的成员变量只能是 `public static final` 类型的，不能被修改且必须有初始值。抽象类的成员变量可以有任何修饰符（`private`, `protected`, `public`），可以在子类中被重新定义或赋值。
+ **方法**： 
    - Java 8 之前，接口中的方法默认是 `public abstract` ，也就是只能有方法声明。自 Java 8 起，可以在接口中定义 `default`（默认） 方法和 `static` （静态）方法。 自 Java 9 起，接口可以包含 `private` 方法。
    - 抽象类可以包含抽象方法和非抽象方法。抽象方法没有方法体，必须在子类中实现。非抽象方法有具体实现，可以直接在抽象类中使用或在子类中重写。

<font style="color:rgb(60, 60, 67);">Java 8 引入的</font>`<font style="color:rgb(60, 60, 67);">default</font>`<font style="color:rgb(60, 60, 67);"> 方法用于提供接口方法的默认实现，可以在实现类中被覆盖。这样就可以在不修改实现类的情况下向现有接口添加新功能，从而增强接口的扩展性和向后兼容性。</font>

```java
public interface MyInterface {
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }
}
```

 Java 8 引入的`static` 方法无法在实现类中被覆盖，只能通过接口名直接调用（ `MyInterface.staticMethod()`），类似于类中的静态方法。`static` 方法通常用于定义一些通用的、与接口相关的工具方法，一般很少用。

```java
public interface MyInterface {
    static void staticMethod() {
        System.out.println("This is a static method in the interface.");
    }
}
```

<font style="color:rgb(60, 60, 67);">Java 9 允许在接口中使用 </font>`<font style="color:rgb(60, 60, 67);">private</font>`<font style="color:rgb(60, 60, 67);"> 方法。</font>`<font style="color:rgb(60, 60, 67);">private</font>`<font style="color:rgb(60, 60, 67);">方法可以用于在接口内部共享代码，不对外暴露。</font>

```java
public interface MyInterface {
    // default 方法
    default void defaultMethod() {
        commonMethod();
    }

    // static 方法
    static void staticMethod() {
        commonMethod();
    }

    // 私有静态方法，可以被 static 和 default 方法调用
    private static void commonMethod() {
        System.out.println("This is a private method used internally.");
    }

      // 实例私有方法，只能被 default 方法调用。
    private void instanceCommonMethod() {
        System.out.println("This is a private instance method used internally.");
    }
}
```

## 深拷贝和浅拷贝区别了解吗？什么是引用拷贝？
+ **浅拷贝**：浅拷贝会在堆上创建一个新的对象（区别于引用拷贝的一点），不过，如果原对象内部的属性是引用类型的话，浅拷贝会直接复制内部对象的引用地址，也就是说拷贝对象和原对象共用同一个内部对象。
+ **深拷贝**：深拷贝会完全复制整个对象，包括这个对象所包含的内部对象。

### 浅拷贝
<font style="color:rgb(60, 60, 67);">浅拷贝的示例代码如下，我们这里实现了</font><font style="color:rgb(60, 60, 67);"> </font>`<font style="color:rgb(60, 60, 67);">Cloneable</font>`<font style="color:rgb(60, 60, 67);"> </font><font style="color:rgb(60, 60, 67);">接口，并重写了</font><font style="color:rgb(60, 60, 67);"> </font>`<font style="color:rgb(60, 60, 67);">clone()</font>`<font style="color:rgb(60, 60, 67);"> </font><font style="color:rgb(60, 60, 67);">方法。</font>

`<font style="color:rgb(60, 60, 67);">clone()</font>`<font style="color:rgb(60, 60, 67);"> 方法的实现很简单，直接调用的是父类 </font>`<font style="color:rgb(60, 60, 67);">Object</font>`<font style="color:rgb(60, 60, 67);"> 的 </font>`<font style="color:rgb(60, 60, 67);">clone()</font>`<font style="color:rgb(60, 60, 67);"> 方法。</font>

```java
public class Address implements Cloneable {
    private String name;
    
    // 构造方法
    public Address(String name) {
        this.name = name;
    }

    // Getter方法
    public String getName() {
        return name;
    }

    // Setter方法
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

public class Person implements Cloneable {
    private Address address;
    
    // 构造方法
    public Person(Address address) {
        this.address = address;
    }

    // Getter方法
    public Address getAddress() {
        return address;
    }

    // Setter方法
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Person clone() {
        try {
            Person person = (Person) super.clone();
            return person;  // 浅拷贝，不对address进行clone
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

<font style="color:rgb(60, 60, 67);">测试：</font>

```java
Person person1 = new Person(new Address("武汉"));
Person person1Copy = person1.clone();
// true
System.out.println(person1.getAddress() == person1Copy.getAddress());
```

<font style="color:rgb(60, 60, 67);">从输出结构就可以看出， </font>`<font style="color:rgb(60, 60, 67);">person1</font>`<font style="color:rgb(60, 60, 67);"> 的克隆对象和 </font>`<font style="color:rgb(60, 60, 67);">person1</font>`<font style="color:rgb(60, 60, 67);"> 使用的仍然是同一个 </font>`<font style="color:rgb(60, 60, 67);">Address</font>`<font style="color:rgb(60, 60, 67);"> 对象。</font>

### 深拷贝
<font style="color:rgb(60, 60, 67);">这里我们简单对 </font>`<font style="color:rgb(60, 60, 67);">Person</font>`<font style="color:rgb(60, 60, 67);"> 类的 </font>`<font style="color:rgb(60, 60, 67);">clone()</font>`<font style="color:rgb(60, 60, 67);"> 方法进行修改，连带着要把 </font>`<font style="color:rgb(60, 60, 67);">Person</font>`<font style="color:rgb(60, 60, 67);"> 对象内部的 </font>`<font style="color:rgb(60, 60, 67);">Address</font>`<font style="color:rgb(60, 60, 67);"> 对象一起复制。</font>

```java
public Person clone() {
    try {
        Person person = (Person) super.clone();
//            return person;  // 浅拷贝，不对address进行clone
        person.setAddress(person.getAddress().clone()); // 深拷贝
        return person;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

<font style="color:rgb(60, 60, 67);">测试：</font>

```java
Person person1 = new Person(new Address("武汉"));
Person person1Copy = person1.clone();
// true
System.out.println(person1.getAddress() == person1Copy.getAddress());
```

<font style="color:rgb(60, 60, 67);">从输出结构就可以看出，显然 </font>`<font style="color:rgb(60, 60, 67);">person1</font>`<font style="color:rgb(60, 60, 67);"> 的克隆对象和 </font>`<font style="color:rgb(60, 60, 67);">person1</font>`<font style="color:rgb(60, 60, 67);"> 包含的 </font>`<font style="color:rgb(60, 60, 67);">Address</font>`<font style="color:rgb(60, 60, 67);"> 对象已经是不同的了。</font>

**<font style="color:rgb(60, 60, 67);">那什么是引用拷贝呢？</font>**<font style="color:rgb(60, 60, 67);"> </font><font style="color:rgb(60, 60, 67);">简单来说，引用拷贝就是两个不同的引用指向同一个对象。</font>

<font style="color:rgb(60, 60, 67);">描述浅拷贝、深拷贝、引用拷贝：</font>

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741092952962-4596a3ce-0834-43c7-b683-74857a00fcd6.png)

# Object
## == 和 equals() 的区别
`**==**` 对于基本类型和引用类型的作用效果是不同的：

+ 对于基本数据类型来说，`==` 比较的是值。
+ 对于引用数据类型来说，`==` 比较的是对象的内存地址。

因为 Java 只有值传递，所以，对于 == 来说，不管是比较基本数据类型，还是引用数据类型的变量，其本质比较的都是值，只是引用类型变量存的值是对象的地址。

`**equals()**` 不能用于判断基本数据类型的变量，只能用来判断两个对象是否相等。`equals()`方法存在于`Object`类中，而`Object`类是所有类的直接或间接父类，因此所有的类都有`equals()`方法。

`Object` 类 `equals()` 方法：

```java
public boolean equals(Object obj) {
     return (this == obj);
}
```

`equals()` 方法存在两种使用情况：

+ **类没有重写 **`**equals()**`**方法**：通过`equals()`比较该类的两个对象时，等价于通过“==”比较这两个对象，使用的默认是 `Object`类`equals()`方法。
+ **类重写了 **`**equals()**`**方法**：一般我们都重写 `equals()`方法来比较两个对象中的属性是否相等；若它们的属性相等，则返回 true(即，认为这两个对象相等)。

`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 中的 </font>`<font style="color:rgb(60, 60, 67);">equals</font>`<font style="color:rgb(60, 60, 67);"> 方法是被重写过的，因为 </font>`<font style="color:rgb(60, 60, 67);">Object</font>`<font style="color:rgb(60, 60, 67);"> 的 </font>`<font style="color:rgb(60, 60, 67);">equals</font>`<font style="color:rgb(60, 60, 67);"> 方法是比较的对象的内存地址，而 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 的 </font>`<font style="color:rgb(60, 60, 67);">equals</font>`<font style="color:rgb(60, 60, 67);"> 方法比较的是对象的值。</font>

<font style="color:rgb(60, 60, 67);">当创建 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 对象。</font>

## <font style="color:rgb(60, 60, 67);">hashCode() 有什么用？</font>
`<font style="color:rgb(60, 60, 67);">hashCode()</font>`<font style="color:rgb(60, 60, 67);"> 的作用是获取哈希码（</font>`<font style="color:rgb(60, 60, 67);">int</font>`<font style="color:rgb(60, 60, 67);"> 整数），也称为散列码。这个哈希码的作用是确定该对象在哈希表中的索引位置。</font>

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741174772043-58b4a3b3-ea6f-4856-8d57-3add0e1ff109.png)

`hashCode()` 定义在 JDK 的 `Object` 类中，这就意味着 Java 中的任何类都包含有 `hashCode()` 函数。另外需要注意的是：`Object` 的 `hashCode()` 方法是本地方法，也就是用 C 语言或 C++ 实现的。

## 为什么要有 hashCode？
`<font style="color:rgb(60, 60, 67);">hashCode()</font>`<font style="color:rgb(60, 60, 67);"> 和 </font>`<font style="color:rgb(60, 60, 67);">equals()</font>`<font style="color:rgb(60, 60, 67);">都是用于比较两个对象是否相等。</font>

**那为什么 JDK 还要同时提供这两个方法呢？**

这是因为在一些容器（比如 `HashMap`、`HashSet`）中，有了 `hashCode()` 之后，判断元素是否在对应容器中的效率会更高（参考添加元素进`HashSet`的过程）！

我们在前面也提到了添加元素进`HashSet`的过程，如果 `HashSet` 在对比的时候，同样的 `hashCode` 有多个对象，它会继续使用 `equals()` 来判断是否真的相同。也就是说 `hashCode` 帮助我们大大缩小了查找成本。

**那为什么不只提供 **`**hashCode()**`** 方法呢？**

这是因为两个对象的`hashCode` 值相等并不代表两个对象就相等。

**那为什么两个对象有相同的 **`**hashCode**`** 值，它们也不一定是相等的？**

因为 `hashCode()` 所使用的哈希算法也许刚好会让多个对象传回相同的哈希值。越糟糕的哈希算法越容易碰撞，但这也与数据值域分布的特性有关（所谓哈希碰撞也就是指的是不同的对象得到相同的 `hashCode` )。

总结下来就是：

+ 如果两个对象的`hashCode` 值相等，那这两个对象不一定相等（哈希碰撞）。
+ 如果两个对象的`hashCode` 值相等并且`equals()`方法也返回 `true`，我们才认为这两个对象相等。
+ 如果两个对象的`hashCode` 值不相等，我们就可以直接认为这两个对象不相等。

## 为什么重写 equals() 时必须重写 hashCode() 方法？
因为两个相等的对象的 `hashCode` 值必须是相等。也就是说如果 `equals` 方法判断两个对象是相等的，那这两个对象的 `hashCode` 值也要相等。

如果重写 `equals()` 时没有重写 `hashCode()` 方法的话就可能会导致 `equals` 方法判断是相等的两个对象，`hashCode` 值却不相等。

**思考**：重写 `equals()` 时没有重写 `hashCode()` 方法的话，使用 `HashMap` 可能会出现什么问题。

**总结**：

+ `equals` 方法判断两个对象是相等的，那这两个对象的 `hashCode` 值也要相等。
+ 两个对象有相同的 `hashCode` 值，他们也不一定是相等的（哈希碰撞）。

# String
## String、StringBuffer、StringBuilder 的区别？
**可变性**

`String` 是不可变的。

`StringBuilder` 与 `StringBuffer` 都继承自 `AbstractStringBuilder` 类，在 `AbstractStringBuilder` 中也是使用字符数组保存字符串，不过没有使用 `final` 和 `private` 关键字修饰，最关键的是这个 `AbstractStringBuilder` 类还提供了很多修改字符串的方法比如 `append` 方法。

```java
abstract class AbstractStringBuilder implements Appendable, CharSequence {
    char[] value;
    public AbstractStringBuilder append(String str) {
        if (str == null)
            return appendNull();
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }
    //...
}
```

**线程安全性**

`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 中的对象是不可变的，也就可以理解为常量，线程安全。</font>`<font style="color:rgb(60, 60, 67);">AbstractStringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 是 </font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 与 </font>`<font style="color:rgb(60, 60, 67);">StringBuffer</font>`<font style="color:rgb(60, 60, 67);"> 的公共父类，定义了一些字符串的基本操作，如 </font>`<font style="color:rgb(60, 60, 67);">expandCapacity</font>`<font style="color:rgb(60, 60, 67);">、</font>`<font style="color:rgb(60, 60, 67);">append</font>`<font style="color:rgb(60, 60, 67);">、</font>`<font style="color:rgb(60, 60, 67);">insert</font>`<font style="color:rgb(60, 60, 67);">、</font>`<font style="color:rgb(60, 60, 67);">indexOf</font>`<font style="color:rgb(60, 60, 67);"> 等公共方法。</font>`<font style="color:rgb(60, 60, 67);">StringBuffer</font>`<font style="color:rgb(60, 60, 67);"> 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。</font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 并没有对方法进行加同步锁，所以是非线程安全的。</font>

**性能**

<font style="color:rgb(60, 60, 67);">每次对 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 类型进行改变的时候，都会生成一个新的 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 对象，然后将指针指向新的 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 对象。</font>`<font style="color:rgb(60, 60, 67);">StringBuffer</font>`<font style="color:rgb(60, 60, 67);"> 每次都会对 </font>`<font style="color:rgb(60, 60, 67);">StringBuffer</font>`<font style="color:rgb(60, 60, 67);"> 对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 </font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 相比使用 </font>`<font style="color:rgb(60, 60, 67);">StringBuffer</font>`<font style="color:rgb(60, 60, 67);"> 仅能获得 10%~15% 左右的性能提升，但却要冒多线程不安全的风险。</font>

**对于三者使用的总结：**

+ <font style="color:rgb(60, 60, 67);">操作少量的数据: 适用 </font>`<font style="color:rgb(60, 60, 67);">String</font>`
+ <font style="color:rgb(60, 60, 67);">单线程操作字符串缓冲区下操作大量数据: 适用 </font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`
+ <font style="color:rgb(60, 60, 67);">多线程操作字符串缓冲区下操作大量数据: 适用 </font>`<font style="color:rgb(60, 60, 67);">StringBuffer</font>`

## String 为什么是不可变的?
`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 类中使用 </font>`<font style="color:rgb(60, 60, 67);">final</font>`<font style="color:rgb(60, 60, 67);"> 关键字修饰字符数组来保存字符串</font>

我们知道被 `final` 关键字修饰的类不能被继承，修饰的方法不能被重写，修饰的变量是基本数据类型则值不能改变，修饰的变量是引用类型则不能再指向其他对象。因此，`final` 关键字修饰的数组保存字符串并不是 `String` 不可变的根本原因，因为这个数组保存的字符串是可变的（`final` 修饰引用类型变量的情况）。

`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 真正不可变有下面几点原因：</font>

1. <font style="color:rgb(60, 60, 67);">保存字符串的数组被 </font>`<font style="color:rgb(60, 60, 67);">final</font>`<font style="color:rgb(60, 60, 67);"> 修饰且为私有的，并且</font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 类没有提供/暴露修改这个字符串的方法。</font>
2. `<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 类被 </font>`<font style="color:rgb(60, 60, 67);">final</font>`<font style="color:rgb(60, 60, 67);"> 修饰导致其不能被继承，进而避免了子类破坏 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 不可变。</font>

## 字符串拼接用“+” 还是 StringBuilder?
<font style="color:rgb(60, 60, 67);">Java 语言本身并不支持运算符重载，“+”和“+=”是专门为 String 类重载过的运算符，也是 Java 中仅有的两个重载过的运算符。字符串对象通过“+”的字符串拼接方式，实际上是通过 </font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 调用 </font>`<font style="color:rgb(60, 60, 67);">append()</font>`<font style="color:rgb(60, 60, 67);"> 方法实现的，拼接完成之后调用 </font>`<font style="color:rgb(60, 60, 67);">toString()</font>`<font style="color:rgb(60, 60, 67);"> 得到一个 </font>`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 对象 。</font>

<font style="color:rgb(60, 60, 67);">不过，在循环内使用“+”进行字符串的拼接的话，存在比较明显的缺陷：</font>**<font style="color:rgb(60, 60, 67);">编译器不会创建单个 </font>**`**<font style="color:rgb(60, 60, 67);">StringBuilder</font>**`**<font style="color:rgb(60, 60, 67);"> 以复用，会导致创建过多的 </font>**`**<font style="color:rgb(60, 60, 67);">StringBuilder</font>**`**<font style="color:rgb(60, 60, 67);"> 对象</font>**<font style="color:rgb(60, 60, 67);">。</font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 对象是在循环内部被创建的，这意味着每循环一次就会创建一个 </font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 对象。如果直接使用 </font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 对象进行字符串拼接的话，就不会存在这个问题了。</font>

## <font style="color:rgb(60, 60, 67);">String#equals() 和 Object#equals() 有何区别？</font>
`<font style="color:rgb(60, 60, 67);">String</font>`<font style="color:rgb(60, 60, 67);"> 中的 </font>`<font style="color:rgb(60, 60, 67);">equals</font>`<font style="color:rgb(60, 60, 67);"> 方法是被重写过的，比较的是 String 字符串的值是否相等。 </font>`<font style="color:rgb(60, 60, 67);">Object</font>`<font style="color:rgb(60, 60, 67);"> 的 </font>`<font style="color:rgb(60, 60, 67);">equals</font>`<font style="color:rgb(60, 60, 67);"> 方法是比较的对象的内存地址。</font>

## <font style="color:rgb(60, 60, 67);">字符串常量池的作用了解吗？</font>
**<font style="color:rgb(60, 60, 67);">字符串常量池</font>**<font style="color:rgb(60, 60, 67);"> 是 JVM 为了提升性能和减少内存消耗针对字符串（String 类）专门开辟的一块区域，主要目的是为了避免字符串的重复创建。</font>

```java
// 在字符串常量池中创建字符串对象 ”ab“
// 将字符串对象 ”ab“ 的引用赋值给 aa
String aa = "ab";
// 直接返回字符串常量池中字符串对象 ”ab“，赋值给引用 bb
String bb = "ab";
System.out.println(aa==bb); // true
```

## String s1 = new String("abc");这句话创建了几个字符串对象？
会创建 1 或 2 个字符串对象。

1. 字符串常量池中不存在 "abc"：会创建 2 个 字符串对象。一个在字符串常量池中，由 `ldc` 指令触发创建。一个在堆中，由 `new String()` 创建，并使用常量池中的 "abc" 进行初始化。
2. 字符串常量池中已存在 "abc"：会创建 1 个 字符串对象。该对象在堆中，由 `new String()` 创建，并使用常量池中的 "abc" 进行初始化。

## String#intern 方法有什么作用?
`String.intern()` 是一个 `native` (本地) 方法，用来处理字符串常量池中的字符串对象引用。它的工作流程可以概括为以下两种情况：

1. **常量池中已有相同内容的字符串对象**：如果字符串常量池中已经有一个与调用 `intern()` 方法的字符串内容相同的 `String` 对象，`intern()` 方法会直接返回常量池中该对象的引用。
2. **常量池中没有相同内容的字符串对象**：如果字符串常量池中还没有一个与调用 `intern()` 方法的字符串内容相同的对象，`intern()` 方法会将当前字符串对象的引用添加到字符串常量池中，并返回该引用。

总结：

+ `intern()` 方法的主要作用是确保字符串引用在常量池中的唯一性。
+ 当调用 `intern()` 时，如果常量池中已经存在相同内容的字符串，则返回常量池中已有对象的引用；否则，将该字符串添加到常量池并返回其引用。

```java
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
```

## String 类型的变量和常量做“+”运算时发生了什么？
先来看字符串不加 `final`关键字拼接的情况：

```java
String str1 = "str";
String str2 = "ing";
String str3 = "str" + "ing";
String str4 = str1 + str2;
String str5 = "string";
System.out.println(str3 == str4);//false
System.out.println(str3 == str5);//true
System.out.println(str4 == str5);//false
```

**注意**：比较 String 字符串的值是否相等，可以使用 `equals()` 方法。 `String` 中的 `equals` 方法是被重写过的。 `Object` 的 `equals` 方法是比较的对象的内存地址，而 `String` 的 `equals` 方法比较的是字符串的值是否相等。如果你使用 `==` 比较两个字符串是否相等的话，IDEA 还是提示你使用 `equals()` 方法替换。

<font style="color:rgb(60, 60, 67);">我们在平时写代码的时候，尽量避免多个字符串对象拼接，因为这样会重新创建对象。如果需要改变字符串的话，可以使用 </font>`<font style="color:rgb(60, 60, 67);">StringBuilder</font>`<font style="color:rgb(60, 60, 67);"> 或者 </font>`<font style="color:rgb(60, 60, 67);">StringBuffer</font>`<font style="color:rgb(60, 60, 67);">。</font>

<font style="color:rgb(60, 60, 67);">不过，字符串使用 </font>`<font style="color:rgb(60, 60, 67);">final</font>`<font style="color:rgb(60, 60, 67);"> 关键字声明之后，可以让编译器当做常量来处理。</font>

```java
final String str1 = "str";
final String str2 = "ing";
// 下面两个表达式其实是等价的
String c = "str" + "ing";// 常量池中的对象
String d = str1 + str2; // 常量池中的对象
System.out.println(c == d);// true
```

被 `final` 关键字修饰之后的 `String` 会被编译器当做常量来处理，编译器在程序编译期就可以确定它的值，其效果就相当于访问常量。

如果 ，编译器在运行时才能知道其确切值的话，就无法对其优化。

示例代码（`str2` 在运行时才能确定其值）：

```java
final String str1 = "str";
final String str2 = getStr();
String c = "str" + "ing";// 常量池中的对象
String d = str1 + str2; // 在堆上创建的新的对象
System.out.println(c == d);// false
public static String getStr() {
      return "ing";
}
```

# 异常
![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741260189089-320e2d35-54fb-4902-85aa-b1e441e340a8.png)

## Exception 和 Error 有什么区别？
+ `Exception`：程序本身可以处理的异常，可以通过`catch`来进行捕获。可以分为必须处理的受检查异常和可以不处理的不受检查异常
+ `Error`：程序无法处理的错误，不建议通过`catch`捕获。错误发生时，JVM一般选择线程终止

## Checked Exception 和 Unchecked Exception 有什么区别？
**<font style="color:rgb(60, 60, 67);">Checked Exception</font>**<font style="color:rgb(60, 60, 67);"> 即 </font>**<font style="color:rgb(60, 60, 67);">受检查异常</font>**<font style="color:rgb(60, 60, 67);"> ，Java 代码在编译过程中，如果受检查异常没有被 </font>`<font style="color:rgb(60, 60, 67);">catch</font>`<font style="color:rgb(60, 60, 67);">或者</font>`<font style="color:rgb(60, 60, 67);">throws</font>`<font style="color:rgb(60, 60, 67);"> 关键字处理的话，就没办法通过编译。</font>

**<font style="color:rgb(60, 60, 67);">Unchecked Exception</font>**<font style="color:rgb(60, 60, 67);"> 即 </font>**<font style="color:rgb(60, 60, 67);">不受检查异常</font>**<font style="color:rgb(60, 60, 67);"> ，Java 代码在编译过程中 ，我们即使不处理不受检查异常也可以正常通过编译。</font>

`RuntimeException` 及其子类都统称为非受检查异常，常见的有（建议记下来，日常开发中会经常用到）：

+ `<font style="color:rgb(60, 60, 67);">NullPointerException</font>`<font style="color:rgb(60, 60, 67);">(空指针错误)</font>
+ `<font style="color:rgb(60, 60, 67);">IllegalArgumentException</font>`<font style="color:rgb(60, 60, 67);">(参数错误比如方法入参类型错误)</font>
+ `<font style="color:rgb(60, 60, 67);">NumberFormatException</font>`<font style="color:rgb(60, 60, 67);">（字符串转换为数字格式错误，</font>`<font style="color:rgb(60, 60, 67);">IllegalArgumentException</font>`<font style="color:rgb(60, 60, 67);">的子类）</font>
+ `<font style="color:rgb(60, 60, 67);">ArrayIndexOutOfBoundsException</font>`<font style="color:rgb(60, 60, 67);">（数组越界错误）</font>
+ `<font style="color:rgb(60, 60, 67);">ClassCastException</font>`<font style="color:rgb(60, 60, 67);">（类型转换错误）</font>
+ `<font style="color:rgb(60, 60, 67);">ArithmeticException</font>`<font style="color:rgb(60, 60, 67);">（算术错误）</font>
+ `<font style="color:rgb(60, 60, 67);">SecurityException</font>`<font style="color:rgb(60, 60, 67);"> （安全错误比如权限不够）</font>
+ `<font style="color:rgb(60, 60, 67);">UnsupportedOperationException</font>`<font style="color:rgb(60, 60, 67);">(不支持的操作错误比如重复创建同一用户)</font>

## Throwable 类常用方法有哪些？
+ `String getMessage()`: 返回异常发生时的详细信息
+ `String toString()`: 返回异常发生时的简要描述
+ `String getLocalizedMessage()`: 返回异常对象的本地化信息。使用 `Throwable` 的子类覆盖这个方法，可以生成本地化信息。如果子类没有覆盖该方法，则该方法返回的信息与 `getMessage()`返回的结果相同
+ `void printStackTrace()`: 在控制台上打印 `Throwable` 对象封装的异常信息

## try-catch-finally 如何使用？
+ `try`块：用于捕获异常。其后可以接0个或多个`catch`块，如果没有`catch`块，则必须跟一个`finally`块
+ `catch`块：用于处理try捕获到的异常
+ `finally`块：无论是否捕获或处理异常，`finally`块里的语句都会被执行。当中`try`块或`catch`块中遇到`return`语句时，`finally`语句块将在方法返回之前被执行。

```java
public class tryCatchFinally {
    public static void main(String[] args) {
        try {
            System.out.println("Try to do something");
            throw new RuntimeException("RuntimeException");
        } catch (Exception e) {
            System.out.println("Catch Exception -> " + e.getMessage());
        } finally {
            System.out.println("Finally");
        }
    }
}
```

输出：

```plain
Try to do something
Catch Exception -> RuntimeException
Finally
```

**注意：不要在 finally 语句块中使用 return!** 当 try 语句和 finally 语句中都有 return 语句时，try 语句块中的 return 语句会被忽略。这是因为 try 语句中的 return 返回值会先被暂存在一个本地变量中，当执行到 finally 语句中的 return 之后，这个本地变量的值就变为了 finally 语句中的 return 返回值。

示例：

```java
public static void main(String[] args) {
    System.out.println(f(2));
}

public static int f(int value) {
    try {
        return value * value;
    } finally {
        if (value == 2) {
            return 0;
        }
    }
}
```

输出：

```plain
0
```

## finally 中的代码一定会执行吗？
<font style="color:rgb(60, 60, 67);">不一定的！在某些情况下，finally 中的代码不会被执行。</font>

<font style="color:rgb(60, 60, 67);">就比如说 finally 之前虚拟机被终止运行的话，finally 中的代码就不会被执行。</font>

```java
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
```

输出：

```plain
Try to do something
Catch Exception -> RuntimeException
```

<font style="color:rgb(60, 60, 67);">另外，在以下 2 种特殊情况下，</font>`<font style="color:rgb(60, 60, 67);">finally</font>`<font style="color:rgb(60, 60, 67);"> </font><font style="color:rgb(60, 60, 67);">块的代码也不会被执行：</font>

1. <font style="color:rgb(60, 60, 67);">程序所在的线程死亡。</font>
2. <font style="color:rgb(60, 60, 67);">关闭 CPU。</font>

## <font style="color:rgb(60, 60, 67);">如何使用 try-with-resources 代替try-catch-finally？</font>
+ **适用范围（资源的定义）：** 任何实现 `java.lang.AutoCloseable`或者 `java.io.Closeable` 的对象
+ **关闭资源和 finally 块的执行顺序：** 在 `try-with-resources` 语句中，任何 catch 或 finally 块在声明的资源关闭后运行

 Java 中类似于`InputStream`、`OutputStream`、`Scanner`、`PrintWriter`等的资源都需要我们调用`close()`方法来手动关闭，一般情况下我们都是通过`try-catch-finally`语句来实现这个需求，如下：

```java
//读取文本文件的内容
Scanner scanner = null;
try {
    scanner = new Scanner(new File("D://read.txt"));
    while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
} finally {
    if (scanner != null) {
        scanner.close();
    }
}
```

<font style="color:rgb(60, 60, 67);">使用 Java 7 之后的 </font>`<font style="color:rgb(60, 60, 67);">try-with-resources</font>`<font style="color:rgb(60, 60, 67);"> 语句改造上面的代码:</font>

```java
try (Scanner scanner = new Scanner(new File("test.txt"))) {
    while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
    }
} catch (FileNotFoundException fnfe) {
    fnfe.printStackTrace();
}
```

<font style="color:rgb(60, 60, 67);">当然多个资源需要关闭的时候，使用 </font>`<font style="color:rgb(60, 60, 67);">try-with-resources</font>`<font style="color:rgb(60, 60, 67);"> 实现起来也非常简单，如果你还是用</font>`<font style="color:rgb(60, 60, 67);">try-catch-finally</font>`<font style="color:rgb(60, 60, 67);">可能会带来很多问题。</font>

<font style="color:rgb(60, 60, 67);">通过使用分号分隔，可以在</font>`<font style="color:rgb(60, 60, 67);">try-with-resources</font>`<font style="color:rgb(60, 60, 67);">块中声明多个资源。</font>

```java
try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
     BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
    int b;
    while ((b = bin.read()) != -1) {
        bout.write(b);
    }
}
catch (IOException e) {
    e.printStackTrace();
}
```

## 异常使用有哪些需要注意的地方？
+ **不要**把异常定义为**静态变量**，因为这样会导致异常栈信息错乱。每次手动抛出异常，我们都需要手动 new 一个异常对象抛出。
+ 抛出的异常信息一定要有意义。
+ 建议抛出更加具体的异常比如字符串转换为数字格式错误的时候应该抛出`NumberFormatException`而不是其父类`IllegalArgumentException`。
+ 避免重复记录日志：如果在捕获异常的地方已经记录了足够的信息（包括异常类型、错误信息和堆栈跟踪等），那么在业务代码中再次抛出这个异常时，就不应该再次记录相同的错误信息。重复记录日志会使得日志文件膨胀，并且可能会掩盖问题的实际原因，使得问题更难以追踪和解决。

# 泛型
## 什么是泛型？有什么作用？
<font style="color:rgb(60, 60, 67);">使用泛型参数，可以增强代码的可读性以及稳定性。</font>

<font style="color:rgb(60, 60, 67);">编译器可以对泛型参数进行检测，并且通过泛型参数可以指定传入的对象类型。比如 </font>`<font style="color:rgb(60, 60, 67);">ArrayList<Person> persons = new ArrayList<Person>()</font>`<font style="color:rgb(60, 60, 67);"> 这行代码就指明了该 </font>`<font style="color:rgb(60, 60, 67);">ArrayList</font>`<font style="color:rgb(60, 60, 67);"> 对象只能传入 </font>`<font style="color:rgb(60, 60, 67);">Person</font>`<font style="color:rgb(60, 60, 67);"> 对象，如果传入其他类型的对象就会报错。</font>

```java
ArrayList<E> extends AbstractList<E>
```

并且，原生 List 返回类型是 Object ，需要手动转换类型才能使用，使用泛型后编译器自动转换。

## 泛型的使用方式有哪几种？
<font style="color:rgb(60, 60, 67);">泛型一般有三种使用方式:</font>**<font style="color:rgb(60, 60, 67);">泛型类</font>**<font style="color:rgb(60, 60, 67);">、</font>**<font style="color:rgb(60, 60, 67);">泛型接口</font>**<font style="color:rgb(60, 60, 67);">、</font>**<font style="color:rgb(60, 60, 67);">泛型方法</font>**<font style="color:rgb(60, 60, 67);">。</font>

1. 泛型类

```java
//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
public class Generic<T>{

    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey(){
        return key;
    }
}
```

<font style="color:rgb(60, 60, 67);">如何实例化泛型类：</font>

```java
Generic<Integer> genericInteger = new Generic<Integer>(123456);
```

2. 泛型接口

```java
public interface Generator<T> {
    public T method();
}
```

<font style="color:rgb(60, 60, 67);">实现泛型接口，不指定类型：</font>

```java
class GeneratorImpl<T> implements Generator<T>{
    @Override
    public T method() {
        return null;
    }
}
```

<font style="color:rgb(60, 60, 67);">实现泛型接口，指定类型：</font>

```java
class GeneratorImpl implements Generator<String> {
    @Override
    public String method() {
        return "hello";
    }
}
```

3. 泛型方法

```java
public static < E > void printArray( E[] inputArray )
{
     for ( E element : inputArray ){
        System.out.printf( "%s ", element );
     }
     System.out.println();
}
```

使用：

```java
// 创建不同类型数组：Integer, Double 和 Character
Integer[] intArray = { 1, 2, 3 };
String[] stringArray = { "Hello", "World" };
printArray( intArray  );
printArray( stringArray  );
```

 注意: `public static < E > void printArray( E[] inputArray )` 一般被称为静态泛型方法；在 java 中泛型只是一个占位符，必须在传递类型后才能使用。类在实例化时才能真正的传递类型参数，由于静态方法的加载先于类的实例化，也就是说类中的泛型还没有传递真正的类型参数，静态的方法的加载就已经完成了，所以静态泛型方法是没有办法使用类上声明的泛型的。只能使用自己声明的 `<E>`。

## 项目中哪里用到了泛型？
+ 自定义接口通用返回结果 `CommonResult<T>` 通过参数 `T` 可根据具体的返回类型动态指定结果的数据类型
+ 定义 `Excel` 处理类 `ExcelUtil<T>` 用于动态指定 `Excel` 导出的数据类型
+ 构建集合工具类（参考 `Collections` 中的 `sort`, `binarySearch` 方法）。

# 反射
## 何为反射？
通过反射可以获取任意一个类的所有属性和方法

## 反射的应用场景
动态代理的实现，就是依赖反射

<font style="color:rgb(60, 60, 67);">比如下面是通过 JDK 实现动态代理的示例代码，其中就使用了反射类 </font>`<font style="color:rgb(60, 60, 67);">Method</font>`<font style="color:rgb(60, 60, 67);"> 来调用指定的方法。</font>

```java
public class DebugInvocationHandler implements InvocationHandler {
    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method " + method.getName());
        return result; 
    }
}
```

另外，像 Java 中的一大利器 **注解** 的实现也用到了反射。

为什么你使用 Spring 的时候 ，一个`@Component`注解就声明了一个类为 Spring Bean 呢？为什么你通过一个 `@Value`注解就读取到配置文件中的值呢？究竟是怎么起作用的呢？

这些都是因为你可以基于反射分析类，然后获取到类/属性/方法/方法的参数上的注解。你获取到注解之后，就可以做进一步的处理。

## 谈谈反射机制的优缺点
**优点**：可以让咱们的代码更加灵活、为各种框架提供开箱即用的功能提供了便利

**缺点**：让我们在运行时有了分析操作类的能力，这同样也增加了安全问题。比如可以无视泛型参数的安全检查（泛型参数的安全检查发生在编译时）。另外，反射的性能也要稍差点，不过，对于框架来说实际是影响不大的。

## 反射实战
### 获取 Class 对象的四种方式
<font style="color:rgb(60, 60, 67);">如果我们动态获取到这些信息，我们需要依靠 Class 对象。Class 类对象将一个类的方法、变量等信息告诉运行的程序。Java 提供了四种方式获取 Class 对象：</font>

1. 知道具体类的情况下可以使用：

```java
Class alunbarClass = TargetObject.class;
```

<font style="color:rgb(60, 60, 67);">但是我们一般是不知道具体类的，基本都是通过遍历包下面的类来获取 Class 对象，通过此方式获取 Class 对象不会进行初始化</font>

2. <font style="color:rgb(60, 60, 67);">通过 </font>`<font style="color:rgb(60, 60, 67);">Class.forName()</font>`<font style="color:rgb(60, 60, 67);">传入类的全路径获取：</font>

```java
Class alunbarClass1 = Class.forName("cn.javaguide.TargetObject");
```

3. <font style="color:rgb(60, 60, 67);">通过对象实例</font>`<font style="color:rgb(60, 60, 67);">instance.getClass()</font>`<font style="color:rgb(60, 60, 67);">获取：</font>

```java
TargetObject o = new TargetObject();
Class alunbarClass2 = o.getClass();
```

4. <font style="color:rgb(60, 60, 67);">通过类加载器</font>`<font style="color:rgb(60, 60, 67);">xxxClassLoader.loadClass()</font>`<font style="color:rgb(60, 60, 67);">传入类路径获取：</font>

```java
ClassLoader.getSystemClassLoader().loadClass("cn.javaguide.TargetObject");
```

<font style="color:rgb(60, 60, 67);">通过类加载器获取 Class 对象不会进行初始化，意味着不进行包括初始化等一系列步骤，静态代码块和静态对象不会得到执行</font>

### <font style="color:rgb(60, 60, 67);">反射的一些基本操作</font>
1. <font style="color:rgb(60, 60, 67);">创建一个我们要使用反射操作的类 </font>`<font style="color:rgb(60, 60, 67);">TargetObject</font>`<font style="color:rgb(60, 60, 67);">。</font>

```java
package reflect;

public class TargetObject {
    private String value;

    public TargetObject() {
        value = "javaguide";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}

```

2. <font style="color:rgb(60, 60, 67);">使用反射操作这个类的方法以及属性</font>

```java
package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException, NoSuchFieldException {
        /**
         * 获取 TargetObject 类的 Class 对象并且创建 TargetObject 类实例
         */
        Class<?> targetClass = Class.forName("reflect.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();
        /**
         * 获取 TargetObject 类中定义的所有方法
         */
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        /**
        * 获取指定方法并调用
        */
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "yubin");

        /**
         * 获取指定参数并对参数进行修改
         */
        Field field = targetClass.getDeclaredField("value");
        // 为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(targetObject,"javaguide");

        /**
         * 调用private方法
         */
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }

}
```

输出：

```plain
privateMethod
publicMethod
I love yubin
value is javaguide
```

# 注解
## 何谓注解
`Annotation` （注解） 是 Java5 开始引入的新特性，可以看作是一种特殊的注释，主要用于修饰类、方法或者变量，提供某些信息供程序在编译或者运行时使用。

注解本质是一个继承了`Annotation` 的特殊接口：

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {

}

public interface Override extends Annotation{

}
```

<font style="color:rgb(60, 60, 67);">JDK 提供了很多内置的注解（比如 </font>`<font style="color:rgb(60, 60, 67);">@Override</font>`<font style="color:rgb(60, 60, 67);">、</font>`<font style="color:rgb(60, 60, 67);">@Deprecated</font>`<font style="color:rgb(60, 60, 67);">），同时，我们还可以自定义注解。</font>

## <font style="color:rgb(60, 60, 67);">注解的解析方法有哪几种？</font>
注解只有被解析之后才会生效，常见的解析方法有两种：

+ **编译期直接扫描**：编译器在编译 Java 代码的时候扫描对应的注解并处理，比如某个方法使用`@Override` 注解，编译器在编译的时候就会检测当前的方法是否重写了父类对应的方法。
+ **运行期通过反射处理**：像框架中自带的注解(比如 Spring 框架的 `@Value`、`@Component`)都是通过反射来进行处理的。

# Java值传递
## 形参 & 实参
+ **<font style="color:rgb(60, 60, 67);">实参（实际参数，Arguments）</font>**<font style="color:rgb(60, 60, 67);">：用于传递给函数/方法的参数，必须有确定的值。</font>
+ **<font style="color:rgb(60, 60, 67);">形参（形式参数，Parameters）</font>**<font style="color:rgb(60, 60, 67);">：用于定义函数/方法，接收实参，不需要有确定的值。</font>

## <font style="color:rgb(60, 60, 67);">值传递 & 引用传递</font>
<font style="color:rgb(60, 60, 67);">将实参传递给方法（或函数）的方式分为两种：</font>

+ **<font style="color:rgb(60, 60, 67);">值传递</font>**<font style="color:rgb(60, 60, 67);">：方法接收的是实参值的拷贝，会创建副本。</font>
+ **<font style="color:rgb(60, 60, 67);">引用传递</font>**<font style="color:rgb(60, 60, 67);">：方法接收的直接是实参所引用的对象在堆中的地址，不会创建副本，对形参的修改将影响到实参。</font>

在java中，只有值传递！

## 为什么在 Java 中只有值传递
### 传递基本类型参数
```java
public static void main(String[] args) {
    int num1 = 10;
    int num2 = 20;
    swap(num1, num2);
    System.out.println("num1 = " + num1);
    System.out.println("num2 = " + num2);
}

public static void swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
    System.out.println("a = " + a);
    System.out.println("b = " + b);
}
```

输出：

```plain
a = 20
b = 10
num1 = 10
num2 = 20
```

解析：

 在 `swap()` 方法中，`a`、`b` 的值进行交换，并不会影响到 `num1`、`num2`。因为，`a`、`b` 的值，只是从 `num1`、`num2` 的复制过来的。也就是说，a、b 相当于 `num1`、`num2` 的副本，副本的内容无论怎么修改，都不会影响到原件本身。

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741694885802-ad8cce42-2317-49f7-9fbc-6ac79d801f20.png)

<font style="color:rgb(60, 60, 67);">通过上面例子，我们已经知道了一个方法不能修改一个基本数据类型的参数，而对象引用作为参数就不一样。</font>

### <font style="color:rgb(60, 60, 67);">传递引用类型参数1</font>
```java
  public static void main(String[] args) {
      int[] arr = { 1, 2, 3, 4, 5 };
      System.out.println(arr[0]);
      change(arr);
      System.out.println(arr[0]);
  }

  public static void change(int[] array) {
      // 将数组的第一个元素变为0
      array[0] = 0;
  }
```

输出：

```plain
1
0
```

解析：

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741695045216-12c36695-f314-4f9b-a9dd-f2bf037092a9.png)

这里传递的还是值，不过，这个值是实参的地址罢了！

也就是说 `change` 方法的参数拷贝的是 `arr` （实参）的地址，因此，它和 `arr` 指向的是同一个数组对象。这也就说明了为什么方法内部对形参的修改会影响到实参。

### 传递引用类型参数2
```java
public class Person {
    private String name;
   // 省略构造函数、Getter&Setter方法
}

public static void main(String[] args) {
    Person xiaoZhang = new Person("小张");
    Person xiaoLi = new Person("小李");
    swap(xiaoZhang, xiaoLi);
    System.out.println("xiaoZhang:" + xiaoZhang.getName());
    System.out.println("xiaoLi:" + xiaoLi.getName());
}

public static void swap(Person person1, Person person2) {
    Person temp = person1;
    person1 = person2;
    person2 = temp;
    System.out.println("person1:" + person1.getName());
    System.out.println("person2:" + person2.getName());
}
```

输出：

```plain
person1:小李
person2:小张
xiaoZhang:小张
xiaoLi:小李
```

解析：

`swap` 方法的参数 `person1` 和 `person2` 只是拷贝的实参 `xiaoZhang` 和 `xiaoLi` 的地址。因此， `person1` 和 `person2` 的互换只是拷贝的两个地址的互换罢了，并不会影响到实参 `xiaoZhang` 和 `xiaoLi` 。

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741695329689-03646919-bab1-4674-acd7-18ffe5f15f7d.png)

## 为什么 Java 不引入引用传递呢？
引用传递看似很好，能在方法内就直接把实参的值修改了，但是，为什么 Java 不引入引用传递呢？

1. 出于安全考虑，方法内部对值进行的操作，对于调用者都是未知的（把方法定义为接口，调用方不关心具体实现）。你也想象一下，如果拿着银行卡去取钱，取的是 100，扣的是 200，是不是很可怕。
2. Java 之父 James Gosling 在设计之初就看到了 C、C++ 的许多弊端，所以才想着去设计一门新的语言 Java。在他设计 Java 的时候就遵循了简单易用的原则，摒弃了许多开发者一不留意就会造成问题的“特性”，语言本身的东西少了，开发者要学习的东西也少了。

## 总结
Java 中将实参传递给方法（或函数）的方式是 **值传递**：

+ 如果参数是基本类型的话，很简单，传递的就是基本类型的字面量值的拷贝，会创建副本。
+ 如果参数是引用类型，传递的就是实参所引用的对象在堆中地址值的拷贝，同样也会创建副本。

# 序列化
## 什么是序列化和反序列化?
如果我们需要持久化 Java 对象比如将 Java 对象保存在文件中，或者在网络传输 Java 对象，这些场景都需要用到序列化。

简单来说：

+ **序列化**：将数据结构或对象转换成可以存储或传输的形式，通常是二进制字节流，也可以是 JSON, XML 等文本格式
+ **反序列化**：将在序列化过程中所生成的数据转换为原始数据结构或者对象的过程

对于 Java 这种面向对象编程语言来说，我们序列化的都是对象（Object）也就是实例化后的类(Class)，但是在 C++这种半面向对象的语言中，struct(结构体)定义的是数据结构类型，而 class 对应的是对象类型。

下面是序列化和反序列化常见应用场景：

+ 对象在进行网络传输（比如远程方法调用 RPC 的时候）之前需要先被序列化，接收到序列化的对象之后需要再进行反序列化；
+ 将对象存储到文件之前需要进行序列化，将对象从文件中读取出来需要进行反序列化；
+ 将对象存储到数据库（如 Redis）之前需要用到序列化，将对象从缓存数据库中读取出来需要反序列化；
+ 将对象存储到内存之前需要进行序列化，从内存中读取出来之后需要进行反序列化。

<font style="color:rgb(60, 60, 67);">综上：</font>**<font style="color:rgb(60, 60, 67);">序列化的主要目的是通过网络传输对象或者说是将对象存储到文件系统、数据库、内存中。</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741696020567-66a85400-3189-4f44-a411-843d4c189992.png)

**<font style="color:rgb(60, 60, 67);">序列化协议对应于 TCP/IP 4 层模型的哪一层？</font>**

<font style="color:rgb(60, 60, 67);">我们知道网络通信的双方必须要采用和遵守相同的协议。TCP/IP 四层模型是下面这样的，序列化协议属于哪一层呢？</font>

1. <font style="color:rgb(60, 60, 67);">应用层</font>
2. <font style="color:rgb(60, 60, 67);">传输层</font>
3. <font style="color:rgb(60, 60, 67);">网络层</font>
4. <font style="color:rgb(60, 60, 67);">网络接口层</font>

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741696048583-3a43518a-378d-4b41-b074-37551e9a14b6.png)

如上图所示，OSI 七层协议模型中，表示层做的事情主要就是对应用层的用户数据进行处理转换为二进制流。反过来的话，就是将二进制流转换成应用层的用户数据。这不就对应的是序列化和反序列化么？

因为，OSI 七层协议模型中的应用层、表示层和会话层对应的都是 TCP/IP 四层模型中的应用层，所以序列化协议属于 TCP/IP 协议应用层的一部分。

## 常见序列化协议有哪些？
JDK 自带的序列化方式一般不会用 ，因为序列化效率低并且存在安全问题。比较常用的序列化协议有 Hessian、Kryo、Protobuf、ProtoStuff，这些都是基于二进制的序列化协议。

像 JSON 和 XML 这种属于文本类序列化方式。虽然可读性比较好，但是性能较差，一般不会选择。

### JDK 自带的序列化方式
<font style="color:rgb(60, 60, 67);">JDK 自带的序列化，只需实现 </font>`<font style="color:rgb(60, 60, 67);">java.io.Serializable</font>`<font style="color:rgb(60, 60, 67);">接口即可。</font>

```java
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private RpcMessageTypeEnum rpcMessageTypeEnum;
}
```

**<font style="color:rgb(60, 60, 67);">serialVersionUID 有什么作用？</font>**

<font style="color:rgb(60, 60, 67);">序列化号 </font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 属于版本控制的作用。反序列化时，会检查 </font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 是否和当前类的 </font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 一致。如果 </font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 不一致则会抛出 </font>`<font style="color:rgb(60, 60, 67);">InvalidClassException</font>`<font style="color:rgb(60, 60, 67);"> 异常。强烈推荐每个序列化类都手动指定其 </font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);">，如果不手动指定，那么编译器会动态生成默认的 </font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);">。</font>

**<font style="color:rgb(60, 60, 67);">serialVersionUID 不是被 static 变量修饰了吗？为什么还会被“序列化”？</font>**

`<font style="color:rgb(60, 60, 67);">static</font>`<font style="color:rgb(60, 60, 67);"> 修饰的变量是静态变量，属于类而非类的实例，本身是不会被序列化的。然而，</font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 是一个特例，</font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 的序列化做了特殊处理。当一个对象被序列化时，</font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 会被写入到序列化的二进制流中；在反序列化时，也会解析它并做一致性判断，以此来验证序列化对象的版本一致性。如果两者不匹配，反序列化过程将抛出 </font>`<font style="color:rgb(60, 60, 67);">InvalidClassException</font>`<font style="color:rgb(60, 60, 67);">，因为这通常意味着序列化的类的定义已经发生了更改，可能不再兼容。</font>

<font style="color:rgb(60, 60, 67);">也就是说，</font>`<font style="color:rgb(60, 60, 67);">serialVersionUID</font>`<font style="color:rgb(60, 60, 67);"> 只是用来被 JVM 识别，实际并没有被序列化。</font>

**如果有些字段不想进行序列化怎么办？**

<font style="color:rgb(60, 60, 67);">对于不想进行序列化的变量，可以使用 </font>`<font style="color:rgb(60, 60, 67);">transient</font>`<font style="color:rgb(60, 60, 67);"> 关键字修饰。</font>

`<font style="color:rgb(60, 60, 67);">transient</font>`<font style="color:rgb(60, 60, 67);"> 关键字的作用是：阻止实例中那些用此关键字修饰的的变量序列化；当对象被反序列化时，被 </font>`<font style="color:rgb(60, 60, 67);">transient</font>`<font style="color:rgb(60, 60, 67);"> 修饰的变量值不会被持久化和恢复。</font>

<font style="color:rgb(60, 60, 67);">关于 </font>`<font style="color:rgb(60, 60, 67);">transient</font>`<font style="color:rgb(60, 60, 67);"> 还有几点注意：</font>

+ `<font style="color:rgb(60, 60, 67);">transient</font>`<font style="color:rgb(60, 60, 67);"> 只能修饰变量，不能修饰类和方法。</font>
+ `<font style="color:rgb(60, 60, 67);">transient</font>`<font style="color:rgb(60, 60, 67);"> 修饰的变量，在反序列化后变量值将会被置成类型的默认值。例如，如果是修饰 </font>`<font style="color:rgb(60, 60, 67);">int</font>`<font style="color:rgb(60, 60, 67);"> 类型，那么反序列后结果就是 </font>`<font style="color:rgb(60, 60, 67);">0</font>`<font style="color:rgb(60, 60, 67);">。</font>
+ `<font style="color:rgb(60, 60, 67);">static</font>`<font style="color:rgb(60, 60, 67);"> 变量因为不属于任何对象(Object)，所以无论有没有 </font>`<font style="color:rgb(60, 60, 67);">transient</font>`<font style="color:rgb(60, 60, 67);"> 关键字修饰，均不会被序列化。</font>

**为什么不推荐使用 JDK 自带的序列化？**

<font style="color:rgb(60, 60, 67);">我们很少或者说几乎不会直接使用 JDK 自带的序列化方式，主要原因有下面这些原因：</font>

+ **不支持跨语言调用**<font style="color:rgb(60, 60, 67);"> : 如果调用的是其他语言开发的服务的时候就不支持了。</font>
+ **性能差**<font style="color:rgb(60, 60, 67);">：相比于其他序列化框架性能更低，主要原因是序列化之后的字节数组体积较大，导致传输成本加大。</font>
+ **存在安全问题**<font style="color:rgb(60, 60, 67);">：序列化和反序列化本身并不存在问题。但当输入的反序列化的数据可被用户控制，那么攻击者即可通过构造恶意输入，让反序列化产生非预期的对象，在此过程中执行构造的任意代码。</font>

### Kryo
Kryo 是一个高性能的序列化/反序列化工具，由于其变长存储特性并使用了字节码生成机制，拥有较高的运行速度和较小的字节码体积。

另外，Kryo 已经是一种非常成熟的序列化实现了，已经在 Twitter、Groupon、Yahoo 以及多个著名开源项目（如 Hive、Storm）中广泛的使用。

### Protobuf
 Protobuf 出自于 Google，性能还比较优秀，也支持多种语言，同时还是跨平台的。就是在使用中过于繁琐，因为你需要自己定义 IDL 文件和生成对应的序列化代码。这样虽然不灵活，但是，另一方面导致 protobuf 没有序列化漏洞的风险。

### ProtoStuff
由于 Protobuf 的易用性较差，它的哥哥 Protostuff 诞生了。

protostuff 基于 Google protobuf，但是提供了更多的功能和更简易的用法。虽然更加易用，但是不代表 ProtoStuff 性能更差。

### Hessian
<font style="color:rgb(60, 60, 67);">Hessian 是一个轻量级的，自定义描述的二进制 RPC 协议。Hessian 是一个比较老的序列化实现了，并且同样也是跨语言的。</font>

![](https://cdn.nlark.com/yuque/0/2025/png/25941432/1741696608422-64647d65-18a8-458b-8bc6-52bde0c9183d.png)

# 代理模式
代理模式是一种比较好理解的设计模式。简单来说就是 **我们使用代理对象来代替对真实对象(real object)的访问，这样就可以在不修改原目标对象的前提下，提供额外的功能操作，扩展目标对象的功能。**

**代理模式的主要作用是扩展目标对象的功能，比如说在目标对象的某个方法执行前后你可以增加一些自定义的操作。**

## 静态代理
**静态代理中，我们对目标对象的每个方法的增强都是手动完成的，非常不灵活（**_**比如接口一旦新增加方法，目标对象和代理对象都要进行修改**_**）且麻烦(**_**需要对每个目标类都单独写一个代理类**_**）。** 实际应用场景非常非常少，日常开发几乎看不到使用静态代理的场景。

上面我们是从实现和应用角度来说的静态代理，从 JVM 层面来说， **静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。**

静态代理实现步骤:

1. 定义一个接口及其实现类；
2. 创建一个代理类同样实现这个接口
3. 将目标对象注入进代理类，然后在代理类的对应方法调用目标类中的对应方法。这样的话，我们就可以通过代理类屏蔽对目标对象的访问，并且可以在目标方法执行前后做一些自己想做的事情。

代码示例：

1. 定义发送短信的接口

```java
public interface SmsService {
    String send(String message);
}
```

2. 实现发送短信的接口

```java
public class SmsServiceImpl implements SmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
```

3. 创建代理类并同样实现发送短信的接口

```java
public class SmsProxy implements SmsService {

    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method send()");
        smsService.send(message);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method send()");
        return null;
    }
}
```

4. 实际使用

```java
public class Main {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
```

运行上述代码之后，控制台打印出：

```java
before method send()
send message:java
after method send()
```

可以输出结果看出，我们已经增加了 `SmsServiceImpl` 的`send()`方法。

## 动态代理
相比于静态代理来说，动态代理更加灵活。我们不需要针对每个目标类都单独创建一个代理类，并且也不需要我们必须实现接口，我们可以直接代理实现类（CGLIB 动态代理机制）。

**从 JVM 角度来说，动态代理是在运行时动态生成类字节码，并加载到 JVM 中的。**

说到动态代理，Spring AOP、RPC 框架应该是两个不得不提的，它们的实现都依赖了动态代理。

**动态代理在我们日常开发中使用的相对较少，但是在框架中的几乎是必用的一门技术。学会了动态代理之后，对于我们理解和学习各种框架的原理也非常有帮助。**

就 Java 来说，动态代理的实现方式有很多种，比如 **JDK 动态代理**、**CGLIB 动态代理**等等。

### JDK动态代理机制
**在 Java 动态代理机制中 **`**InvocationHandler**`** 接口和 **`**Proxy**`** 类是核心。**

`Proxy` 类中使用频率最高的方法是：`newProxyInstance()` ，这个方法主要用来生成一个代理对象。

```java
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
throws IllegalArgumentException
{
......
}
```

这个方法一共有 3 个参数：

1. **loader** :类加载器，用于加载代理对象。
2. **interfaces** : 被代理类实现的一些接口；
3. **h** : 实现了 `InvocationHandler` 接口的对象；

要实现动态代理的话，还必须需要实现`InvocationHandler` 来自定义处理逻辑。 当我们的动态代理对象调用一个方法时，这个方法的调用就会被转发到实现`InvocationHandler` 接口类的 `invoke` 方法来调用。

```java
public interface InvocationHandler {

    /**
     * 当你使用代理对象调用方法的时候实际会调用到这个方法
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable;
}
```

`invoke()` 方法有下面三个参数：

1. **proxy** :动态生成的代理类
2. **method** : 与代理类对象调用的方法相对应
3. **args** : 当前 method 方法的参数

也就是说：**你通过**`**Proxy**`** 类的 **`**newProxyInstance()**`** 创建的代理对象在调用方法的时候，实际会调用到实现**`**InvocationHandler**`** 接口的类的 **`**invoke()**`**方法。** 你可以在 `invoke()` 方法中自定义处理逻辑，比如在方法执行前后做什么事情。

JDK 动态代理类使用步骤：

1. 定义一个接口及其实现类；
2. 自定义 `InvocationHandler` 并重写`invoke`方法，在 `invoke` 方法中我们会调用原生方法（被代理类的方法）并自定义一些处理逻辑；
3. 通过 `Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)` 方法创建代理对象；

代码示例：

1. 定义发送短信的接口

```java
public interface SmsService {
    String send(String message);
}
```

2. 实现发送短信的接口

```java
public class SmsServiceImpl implements SmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
```

3. 定义一个 JDK 动态代理类

```java
package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 定义一个 JDK 动态代理类
public class DebugInvocationHandler implements InvocationHandler {

    // 代理类中的真实对象
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return result;
    }
}

```

`invoke()` 方法: 当我们的动态代理对象调用原生方法的时候，最终实际上调用到的是`invoke()`方法，然后`invoke()` 方法代替我们去调用了被代理对象的原生方法。

4. 获取代理对象的工厂类

```java
public class JdkProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载器
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }
}
```

`getProxy()`：主要通过`Proxy.newProxyInstance()`方法获取某个类的代理对象

5. 实际使用

```java
SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
smsService.send("java");
```

运行上述代码之后，控制台打印出：

```plain
before method send
send message:java
after method send
```

### CGLIB 动态代理机制
**JDK 动态代理有一个最致命的问题是其只能代理实现了接口的类。**

**为了解决这个问题，我们可以用 CGLIB 动态代理机制来避免。**

[CGLIB](https://github.com/cglib/cglib)(_Code Generation Library_)是一个基于[ASM](http://www.baeldung.com/java-asm)的字节码生成库，它允许我们在运行时对字节码进行修改和动态生成。CGLIB 通过继承方式实现代理。很多知名的开源框架都使用到了[CGLIB](https://github.com/cglib/cglib)， 例如 Spring 中的 AOP 模块中：如果目标对象实现了接口，则默认采用 JDK 动态代理，否则采用 CGLIB 动态代理。

**在 CGLIB 动态代理机制中 **`**MethodInterceptor**`** 接口和 **`**Enhancer**`** 类是核心。**

你需要自定义 `MethodInterceptor` 并重写 `intercept` 方法，`intercept` 用于拦截增强被代理类的方法。

```java
public interface MethodInterceptor extends Callback{
    // 拦截被代理类中的方法
    public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args,MethodProxy proxy) throws Throwable;
}
```

1. obj : 被代理的对象（需要增强的对象）
2. method : 被拦截的方法（需要增强的方法）
3. args : 方法入参
4. proxy : 用于调用原始方法

你可以通过 `Enhancer`类来动态获取被代理类，当代理类调用方法的时候，实际调用的是 `MethodInterceptor` 中的 `intercept` 方法。

CGLIB动态代理类使用步骤：

1. 定义一个类；
2. 自定义 `MethodInterceptor` 并重写 `intercept` 方法，`intercept` 用于拦截增强被代理类的方法，和 JDK 动态代理中的 `invoke` 方法类似；
3. 通过 `Enhancer` 类的 `create()`创建代理类；

代码示例：

不同于 JDK 动态代理不需要额外的依赖。CGLIB(Code Generation Library) 实际是属于一个开源项目，如果你要使用它的话，需要手动添加相关依赖。并且，由于在CGLIB 3.x版本中，包名已经改为org.springframework.cglib，所以还要加入spring-core依赖。

```xml
<dependency>
  <groupId>cglib</groupId>
  <artifactId>cglib</artifactId>
  <version>3.3.0</version>
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-core</artifactId>
  <version>6.1.3</version>
</dependency>
```

1. 实现一个使用阿里云发送短信的类

```java
package proxy.cglibProxy;
// 实现一个使用阿里云发送短信的类
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
```

2. 自定义 MethodInterceptor（方法拦截器）

```java
package proxy.cglibProxy;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//自定义 MethodInterceptor（方法拦截器）
public class DebugMethodInterceptor implements MethodInterceptor {
    /**
     * @param o           被代理的对象（需要增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object object = methodProxy.invokeSuper(o, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return object;
    }
}

```

3. 获取代理类

```java
package proxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;

// 获取代理类
public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}
```

**4.实际使用**

```java
AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
aliSmsService.send("java");
```

运行上述代码之后，控制台打印出：

```plain
before method send
send message:java
after method send
```

### JDK 动态代理和 CGLIB 动态代理对比


