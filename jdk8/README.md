> # JDK1.8特性

1. [Lambdab表达式](#lambdab表达式)

    1.1 [初始Lambda](#初始lambda)
    
    1.2 [Lambda表达式基础语法](#lambda表达式基础语法)
    
2. [函数式接口](#函数式接口)

    2.1 [什么是函数式接口](#什么是函数式接口)
    
    2.2 [自定义函数式接口](#自定义函数式接口)
    
    2.3 [内置四大核心函数式接口](#内置四大核心函数式接口)
    
3. [方法引用与构造器引用](#方法引用与构造器引用)  
  
    3.1 [方法引用](#方法引用)
    
    3.2 [构造器引用](#构造器引用)
   
4. [Stream-api](#stream-api)   
 
    4.1 [什么是 Stream](#什么是-stream)
    
    4.2 [Stream 操作三步走](#stream-操作三步走)
    
    4.3 [创建 Stream](创建-stream)
    
    4.3.1 [Collection 创建流](#collection-创建流)
    
    4.3.2 [Arrays 创建流](#arrays-创建流)
    
    4.3.3 [由值创建流](#由值创建流)
    
    4.3.4 [由函数创建流](#由函数创建流)
    
    4.4 [Stream 的中间操作](#stream-的中间操作)
    
> # Lambdab表达式

Lambda是一个 **匿名的函数** ，我们可以把Lambda表达式理解为是**一段可以传递的代码**（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到提升

## 初始Lambda

原来的匿名内部类,下面使用jdk提供的Comparator接口对TreeSet中的元素进行排序 这样看起来似乎没有什么问题，但是仔细观察发现实际有用的
只有  return Integer.compare(o1,o2);这一句，写了一推的代码发现只有这么一句有用的

```java
@Test
public void test1() {
    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };

    TreeSet<Integer> treeSet = new TreeSet<>(comparator);
}
```

JDK1.8的Lambada表达式

```java
@Test
public void test2() {
    Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
    TreeSet<Integer> treeSet = new TreeSet<>(comparator);
}
```

使用Lambda表达式使代码变得更加简洁，值得注意的是在JDK1.8以前如果在匿名内部类中使用局部变量必须申明为final，JDK1.8后可以省略显示申明final,如果在匿名内部类中使用到局部变量则会默认隐试添加final

## Lambda表达式基础语法

java8中引入了一个新的操作符“->” 该操作符称为箭头操作符或Lambda操作符；箭头操作符将Lambda表达式拆分为两部分：
 
左侧：Lambda表达式的参数列表
 
右侧：Lambda表达式所需执行的功能，即Lambda体

注意：Lambda表达所支持的接口必须是函数式接口，**也就是一个接口中只能存在一个带实现的方法**；

[自定义函数式接口](#自定义函数式接口)

* 语法1：无参数，无返回值

```java
() -> System.out.println("hello");
```

*  语法2：有参数，有返回值

```java
(param1,param2...)-> return result;
```
*  语法3：如果只有一个参数，则参数小括号可以省略

```java
 param -> System.out.println("hello");
```
*  语法4：有多个参数，有返回值；存在复杂逻辑

```java
(param1,param2...)->{

     if (param1 == param2){
        return 0;
    }

     if (param1 > param2){
        return 1;
    }

     if (param1 < param2){
        return 2;
    }
};
```
*  语法5：若 Lambda 体中只有一条语句，return 和 大括号都可以省略

```java
Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
```

> # 函数式接口

## 什么是函数式接口

* 只包含一个抽象方法的接口，称为函数式接口。 

* 你可以通过 Lambda 表达式来创建该接口的对象。（若 Lambda 
  表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方
  法上进行声明）。
  
* 我们可以在任意函数式接口上使用 `@FunctionalInterface` 注解，
这样做可以检查它是否是一个函数式接口，同时 javadoc 也会包
含一条声明，说明这个接口是一个函数式接口


## 自定义函数式接口

```java
@FunctionalInterface
public interface Query<T> {
    boolean test(T t);
}
```

`@FunctionalInterface` 注解是JDK1.8中提供的函数式接口标记，用来约束一个接口中只能申明一个方法

## 内置四大核心函数式接口


* Consume<T>  消费型接口

```java
@Test
public void consumeTests() {
    accept(100, (m) -> System.out.println("消费：" + m));
}

private void accept(int money, Consumer<Integer> consumer) {
    consumer.accept(money);
}
```

* Supplier<T> 供给型接口

```java
@Test
public void supplierTests() {
    List<Integer> random = getRandom(10, () -> (int) (Math.random() * 1000));
    random.forEach(System.out::println);
}

private List<Integer> getRandom(int num, Supplier<Integer> supplier) {
    List<Integer> randoms = new ArrayList<>();
    for (int i = 0; i < num; i++) {
        int randomNumber = supplier.get();
        randoms.add(randomNumber);
    }
    return randoms;
}
```
* Function<T> 函数型接口

```java
@Test
public void functionTests() {
    int length = handlerStr("function", (s) -> s == null ? 0 : s.length());
    Assert.assertEquals(length, 8);

}

private Integer handlerStr(String str, Function<String, Integer> function) {
    return function.apply(str);
}
```
* Predicate<T> 断言行接口

```java
@Test
public void predicateTests() {
    List<String> list = Arrays.asList("4","1","2","a");
  filterStr(list,(s)-> "a".equals(s)).forEach(System.out::println);
}

private List<String> filterStr(List<String> strs, Predicate<String> predicate) {
    List<String> list = new ArrayList<>();
    for (String str : strs) {
        if (predicate.test(str)) {
            list.add(str);
        }
    }
    return list;
}
```

> # 方法引用与构造器引用

## 方法引用

当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！

（实现抽象方法的参数列表，必须与方法引用方法的参数列表保持一致！）

方法引用：使用操作符 `::` 将方法名和对象或类的名字分隔开来。 如下三种主要使用情况：

* 对象::实例方法
* 类::静态方法
* 类::实例方法

**示例1：**

```java
System.out.println(x);
//等同
System.out::println; 
```
**示例2：**

```java
BinaryOperator<Double> bo = (x,y)-> Math.pow(x,y);
//等同
BinaryOperator<Double> bo2 = Math::pow;

Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
//等同
Comparator<Integer> comparator2 = Integer::compare;
```

## 构造器引用

**格式：** `ClassName::new`

与函数式接口相结合，自动与函数式接口中方法兼容。 可以把构造器引用赋值给定义的方法，与构造器参数 列表要与接口中抽象方法的参数列表一致！

```java
// Function<Integer,MyClass> fun = (n) -> new MyClass(n);
   Function<Integer, MyClass> fun = MyClass::new;
```

> # Stream API

Java8中有两大最为重要的改变。第一个是 Lambda 表达式；另外一 个则是 `Stream API(java.util.stream.*)`。

Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对

集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。

使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数

据库查询。也可以使用 Stream API 来并行执行操作。简而言之，

Stream API 提供了一种高效且易于使用的处理数据的方式

## 什么是 Stream

流(Stream) 到底是什么呢？

是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。

“集合讲的是数据，流讲的是计算！”

注意：
* Stream 自己不会存储元素。
* Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
* Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行

## Stream 操作三步走

* 创建 Stream
一个数据源（如：集合、数组），获取一个流 

* 中间操作
一个中间操作链，对数据源的数据进行处理 

* 终止操作(终端操作)
一个终止操作，执行中间操作链，并产生结果

```text

   --------                                --------       
  | 数据源 |  ->  filter -> map -> ... -> |  终止  |
   --------                                --------
```
## 创建 Stream

### Collection 创建流

Java8 中的 Collection 接口被扩展，提供了 两个获取流的方法：

* default Stream<E> stream() : 返回一个顺序流 

* default Stream<E> parallelStream() : 返回一个并行流

```java
 List<String> names = new ArrayList<>();
 names.stream();
 names.parallelStream();
```

###  Arrays 创建流

Java8 中的 Arrays 的静态方法 stream() 可 以获取数组流：

提供了多个重载来支持各种数据类型

```java
String[] strs = {"one","two","three"};
Arrays.stream(strs);
int[] ints = {1,2,3};
Arrays.stream(ints);
double[] doubles = {1,2,3};
Arrays.stream(doubles);
long[] longs = {1,2,3};
Arrays.stream(longs);
//等等...
```
###   由值创建流

可以使用静态方法 Stream.of(), 通过显示值 创建一个流。它可以接收任意数量的参数

```java
Stream.of(1,2,3,4,5).forEach(System.out::println);
```
### 由函数创建流

可以使用静态方法 Stream.iterate() 和 Stream.generate(), 创建无限流。

* 迭代
`public static<T> Stream<T> iterate(final T seed, final 
UnaryOperator<T> f) `

* 生成
`public static<T> Stream<T> generate(Supplier<T> s)`

# Stream 的中间操作

多个中间操作可以连接起来形成一个流水线，除非流水 线上触发终止操作，否则中间操作不会执行任何的处理！ 而在终止操作时一次性全部处理，称为“惰性求值”。

### 筛选与切片

| 方法 | 描述|
|:---|:---|
|filter(Predicate p) |接收 Lambda ， 从流中排除某些元素。|
|distinct() |筛选，通过流所生成元素的 hashCode() 和 equals() 去 除重复元素 |
|limit(long maxSize) |截断流，使其元素不超过给定数量。|
|skip(long n) |跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素 不足 n 个，则返回一个空流。与 limit(n) 互补|

### 映射

| 方法 | 描述|
|:---|:---|
|map(Function f) |接收一个函数作为参数，该函数会被应用到每个元 素上，并将其映射成一个新的元素。|
|mapToDouble(ToDoubleFunction f) |接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 DoubleStream。 mapToInt(ToIntFunction f) 接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 IntStream。 mapToLong(ToLongFunction f) 接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 LongStream。|
|flatMap(Function f)| 接收一个函数作为参数，将流中的每个值都换成另 一个流，然后把所有流连接成一个流|

### 排序

| 方法 | 描述|
|:---|:---|
|sorted()| 产生一个新流，其中按自然顺序排序|
|sorted(Comparator comp)| 产生一个新流，其中按比较器顺序排序|

# Stream 的终止操作

终端操作会从流的流水线生成结果。其结果可以是任何不是流的 值，例如：List、Integer，甚至是 void 。


> # 接口默认方法与静态方法

> # 新时间日期API

> # 其他新特性

## Optional 类

`Optional<T>` 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在，
原来用 null 表示一个值不存在，现在 Optional 可以更好的表达这个概念。并且
可以避免空指针异常。

**常用方法：**

|方法|描述|
|:---|:---|
|Optional.of(T t) | 创建一个 Optional 实例|
|Optional.empty() |创建一个空的 Optional 实例|
|Optional.ofNullable(T t)|若 t 不为 null,创建 Optional 实例,否则创建空实例|
|isPresent() | 判断是否包含值|
|ifPresent(Consumer<? super T> consumer)|判断不为空，调用消费函数|
|orElse(T t) | 如果调用对象包含值，返回该值，否则返回t|
|orElseGet(Supplier s) |如果调用对象包含值，返回该值，否则返回 s 获取的值|
|map(Function f)| 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()|
|flatMap(Function mapper)|与 map 类似，要求返回值必须是Optional|


=======
# 方法引用与构造器引用

# stream API

# 接口默认方法与静态方法

# 新时间日期API

# 其他新特性
>>>>>>> 142e0f6abdcb72e3aa34c023812604e199f4d63f
