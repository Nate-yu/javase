# 找单独的数（❌）
## <font style="color:rgb(0, 0, 0);">问题描述</font>
<font style="color:rgb(0, 0, 0);">在一个班级中，每位同学都拿到了一张卡片，上面有一个整数。有趣的是，除了一个数字之外，所有的数字都恰好出现了两次。现在需要你帮助班长小C快速找到那个拿了独特数字卡片的同学手上的数字是什么。</font>

<font style="color:rgb(0, 0, 0);">要求：</font>

1. <font style="color:rgb(0, 0, 0);">设计一个算法，使其时间复杂度为 </font>**<font style="color:rgb(0, 0, 0);">O(n)</font>**<font style="color:rgb(0, 0, 0);">，其中 n 是班级的人数。</font>
2. <font style="color:rgb(0, 0, 0);">尽量减少额外空间的使用，以体现你的算法优化能力。</font>

## <font style="color:rgb(0, 0, 0);">测试样例</font>
<font style="color:rgb(0, 0, 0);">样例1：</font>

<font style="color:rgb(0, 0, 0);">输入：</font>`cards = [1, 1, 2, 2, 3, 3, 4, 5, 5]`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">输出：</font>`4`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">解释：拿到数字 4 的同学是唯一一个没有配对的。</font>

<font style="color:rgb(0, 0, 0);">样例2：</font>

<font style="color:rgb(0, 0, 0);">输入：</font>`cards = [0, 1, 0, 1, 2]`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">输出：</font>`2`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">解释：数字 2 只出现一次，是独特的卡片。</font>

<font style="color:rgb(0, 0, 0);">样例3：</font>

<font style="color:rgb(0, 0, 0);">输入：</font>`cards = [7, 3, 3, 7, 10]`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">输出：</font>`10`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">解释：10 是班级中唯一一个不重复的数字卡片。</font>

## <font style="color:rgb(0, 0, 0);">约束条件</font>
+ <font style="color:rgb(0, 0, 0);">1 ≤ cards.length ≤ 1001</font>
+ <font style="color:rgb(0, 0, 0);">0 ≤ cards[i] ≤ 1000</font>
+ <font style="color:rgb(0, 0, 0);">班级人数为奇数</font>
+ <font style="color:rgb(0, 0, 0);">除了一个数字卡片只出现一次外，其余每个数字卡片都恰好出现两次</font>

## <font style="color:rgb(0, 0, 0);">方法</font>
### 异或运算（官方）
异或运算的性质：`a^0=a`、`a^a=0`

```java
public class Main {
    public static int solution(int[] cards) {
        int n = cards.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= cards[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5}) == 4);
        System.out.println(solution(new int[]{0, 1, 0, 1, 2}) == 2);
    }
}

```

### 数学公式
使用数学公式：

单独的数字 = 2$ \times $全部无重复的数字的和 - 所有数字的和

```java
import java.util.HashSet;
public class Main {
    public static int solution(int[] cards) {
        HashSet<Integer> set = new HashSet<Integer>();
        int UniqueSum = 0;
        int Sum = 0;
        for (int card : cards) {
            if (set.add(card)) {
                UniqueSum += card;
            }
            Sum += card;
        }
        return 2 * UniqueSum - Sum;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5}) == 4);
        System.out.println(solution(new int[]{0, 1, 0, 1, 2}) == 2);
    }
}
```

### 排序后遍历
```java
import java.util.Arrays;
public class Main {
    public static int solution(int[] cards) {
        int n = cards.length;
        Arrays.sort(cards);
        for(int i = 0; i < n-1; i += 2) {
            if(cards[i] != cards[i+1]) {
                return cards[i];
            }
        }
        return cards[n-1];
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5}) == 4);
        System.out.println(solution(new int[]{0, 1, 0, 1, 2}) == 2);
    }
}

```



# 数字字符串格式化（✅）
## <font style="color:rgb(0, 0, 0);">问题描述</font>
<font style="color:rgb(0, 0, 0);">小M在工作时遇到了一个问题，他需要将用户输入的不带千分位逗号的数字字符串转换为带千分位逗号的格式，并且保留小数部分。小M还发现，有时候输入的数字字符串前面会有无用的 </font>`0`<font style="color:rgb(0, 0, 0);">，这些也需要精简掉。请你帮助小M编写程序，完成这个任务。</font>

## <font style="color:rgb(0, 0, 0);">测试样例</font>
_**<font style="color:rgb(0, 0, 0);">样例1：</font>**_

<font style="color:rgb(0, 0, 0);">输入：</font>`s = "1294512.12412"`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">输出：</font>`'1,294,512.12412'`

_**<font style="color:rgb(0, 0, 0);">样例2：</font>**_

<font style="color:rgb(0, 0, 0);">输入：</font>`s = "0000123456789.99"`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">输出：</font>`'123,456,789.99'`

_**<font style="color:rgb(0, 0, 0);">样例3：</font>**_

<font style="color:rgb(0, 0, 0);">输入：</font>`s = "987654321"`<font style="color:rgb(0, 0, 0);">  
</font><font style="color:rgb(0, 0, 0);">输出：</font>`'987,654,321'`

## 方法
### 自己的方法
1. 分割整数部分和小数部分
2. 去除前导零
3. 加入逗号
4. 拼接字符串

```java
public class Main {
    public static String solution(String s) {
        String after_point="";
        String before_point="";
        int i = 0;
        for (i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '.') {
                before_point += c;
            } else if (c == '.') {
                i++;
                break;
            }
        }
        after_point += s.substring(i);
        
        String pureString = "";
        for (int j = 0; j < before_point.length(); j++) {
            if (before_point.charAt(j) == '0') {
                continue;
            } else {
                pureString += before_point.substring(j);
                break;
            }
        }

        StringBuilder edString = new StringBuilder(pureString);
        if (pureString.length() > 3) {
            for (int k =pureString.length()-3; k >= 0; k-=3) {
                edString.insert(k,',');
            }
        }

        String to_before_point = edString.toString();
        if (to_before_point.charAt(0) == ',') {
            to_before_point = to_before_point.substring(1);
        }
        String reString = "";
        if (after_point .equals("") ) {
            reString = to_before_point;
        } else {
            reString += to_before_point+'.'+after_point;
        }
        return reString;
    }

    public static void main(String[] args) {
        System.out.println(solution("1294512.12412").equals("1,294,512.12412"));
        System.out.println(solution("0000123456789.99").equals("123,456,789.99"));
        System.out.println(solution("987654321").equals("987,654,321"));
    }
}
```

### AI优化后的方法
1. `String.split("\\.")`可以分割整数与小数
2. ^0+(?!$)含义：
+ `**^**`<font style="color:rgb(51, 57, 64);"> 表示字符串的开始。</font>
+ `**0+**`<font style="color:rgb(51, 57, 64);"> </font><font style="color:rgb(51, 57, 64);">表示一个或多个</font><font style="color:rgb(51, 57, 64);"> </font>`**0**`<font style="color:rgb(51, 57, 64);">。</font>
+ `**(?!$)**`<font style="color:rgb(51, 57, 64);"> 是一个负向前瞻，表示 </font>`**0**`<font style="color:rgb(51, 57, 64);"> 后面不能是字符串的结尾。</font>

```java
public class Main {
    public static String solution(String s) {
        // 直接使用 String.split("\\.") 来分割整数部分和小数部分
        String[] parts = s.split("\\.");
        // 直接使用 before_point.replaceFirst("^0+(?!$)", "") 来去除前导零
        String before_point = parts[0].replaceFirst("^0+(?!$)", "");
        String after_point = parts.length > 1 ? parts[1] : "";

        StringBuilder edString = new StringBuilder(before_point);
        for (int k = before_point.length() - 3; k > 0; k -= 3) {
            edString.insert(k, ',');
        }

        String to_before_point = edString.toString();
        if (to_before_point.charAt(0) == ',') {
            to_before_point = to_before_point.substring(1);
        }

        return after_point.isEmpty() ? to_before_point : to_before_point + '.' + after_point;
    }

    public static void main(String[] args) {
        System.out.println(solution("1294512.12412").equals("1,294,512.12412"));
        System.out.println(solution("0000123456789.99").equals("123,456,789.99"));
        System.out.println(solution("987654321").equals("987,654,321"));
    }
}
```

