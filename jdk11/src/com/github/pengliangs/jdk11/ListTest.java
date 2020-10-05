package com.github.pengliangs.jdk11;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author pengliang
 * @since 2020/10/5 20:42
 */
public class ListTest {

    @Test
    public void test_list_of() {
        var list = List.of("A", "B", "C", "D", "E", "F");
        System.out.println(list);
        // java.lang.UnsupportedOperationException
        // list.add("G");
        var arraysList = Arrays.asList("A", "B", "C", "D", "E", "F");
        System.out.println(arraysList);

        // java.lang.UnsupportedOperationException
        // arraysList.add("G");

        // java.lang.IllegalArgumentException: duplicate element: A
        // var set = Set.of("A","B","A");
        var set = Set.of("A", "B", "C");
        System.out.println(set);
    }

    /**
     * 新增流api
     */
    @Test
    public void test_list_stream() {
        // 会出现空指针，return stream(array, 0, array.length);
        //  Stream<Object> stream = Stream.of(null);
        Stream<Object> stream = Stream.ofNullable(null);
        stream.forEach(System.out::println);
    }

    @Test
    public void test_list_stream2() {
        var stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 10, 23);
        /**
         * 新增 takeWhile，dropWhile
         * takeWhile: 从流中获取判定为真的元素，一旦判断为假结束处理
         * dropWhile：从流中判定为真的丢弃，一旦判断为假结束处理
         */
        stream.takeWhile(num -> num % 2 != 0).forEach(System.out::println);
        System.out.println("=============================================");

        var stream2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 10, 23);
        stream2.dropWhile(num -> num % 2 != 0).forEach(System.out::println);
    }

    @Test
    public void test_list_stream3() {
        // 流迭代器, 无限迭代
        var stream = Stream.iterate(1, t -> 2 * t + 1);
        stream.limit(10).forEach(System.out::println);
        System.out.println("================================");
        // 有限迭代
        Stream.iterate(1, t -> t < 10, t -> 2 * t + 1)
                .forEach(System.out::println);
    }

}
