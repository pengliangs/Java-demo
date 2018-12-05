package com.github.pengliangs;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author pengliang on 2018-12-05 11:47
 */
public class StreamTest {

    /**
     * JDK9 对stream api进行增强新增4个方法
     * 1.takeWhile
     * 2.dropWhile
     * 3.ofNullable
     * 4.iterate重载
     */
    @Test
    public void jdk9StreamTakeWhileTest() {
        /**
         * takeWhile 尽可能的从前向后获取匹配元素 遇到一个不满足结束
         */
        List<Integer> list = Arrays.asList(12, 32, 37, 44, 55, 78, 96, 68, 29);
        list.stream().takeWhile(x -> x < 40).forEach(System.out::println);
    }

    @Test
    public void jdk9StreamDropWhile() {
        /**
         * dropWhile 从头开始匹配成功的去除，否则结束
         */
        List<Integer> list = Arrays.asList(12, 32, 37, 44, 55, 78, 96, 68, 29);
        list.stream().dropWhile(x -> x < 40).forEach(System.out::println);
    }

    @Test
    public void jdk9StreamOfNullable() {
        //JDK8
        Stream<Integer> stream = Stream.of(null, 1, 2, 3, null);
        stream.forEach(System.out::println);
        System.out.println("------------------------------");
        /**
         * 如果只有单个元素不能是null否则空指针异常，多个元素的情况下允许null
         */
        // Stream<Integer> stream2 = Stream.of(null);
        Stream<Integer> stream2 = Stream.of(null, null);
        stream2.forEach(System.out::println);
        System.out.println("------------------------------");
        //JDK9 新增ofNullable 允许Stream存放单个元素为null
        Stream<Integer> stream3 = Stream.ofNullable(null);
        stream3.forEach(System.out::println);
        Stream<String> stream4 = Stream.ofNullable("hellow");
        stream4.forEach(System.out::println);
    }

    @Test
    public void jdk9StreamIterate() {
        /**
         * 创建stream实例的方式
         * 1.通过集合的stream
         * 2.通过工具类Arrays
         * 3.Stream中静态方法of
         * 4.iterate
         */
        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);

        //JDK9 iterate 重载 Predicate 判断语句
        Stream.iterate(0, x -> x < 10, x -> x + 1).forEach(System.out::println);
    }
}
