> # JDK1.8特性

1. [Lambdab表达式](#lambdab表达式)

    1.1 [初始Lambda](#初始lambda)
    
    1.2 [Lambda表达式基础语法](#lambda表达式基础语法)
    
2. [内置四大函数式接口](#内置四大函数式接口)


# Lambdab表达式

> Lambda是一个 **匿名的函数** ，我们可以把Lambda表达式理解为是**一段可以传递的代码**（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到提升

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

```java
@FunctionalInterface
public interface Query<T> {
    boolean test(T t);
}
```

@FunctionalInterface注解是JDK1.8中提供的函数式接口标记，用来约束一个接口中只能申明一个方法

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

# 内置四大函数式接口

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

 


# 方法引用与构造器引用

# stream API

# 接口默认方法与静态方法

# 新时间日期API

# 其他新特性
