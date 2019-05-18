package com.github.pengliangs.jdk8;

import com.github.pengliangs.jdk8.bean.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author pengliang  2019-02-27 20:08
 */
public class StreamTest {

    List<User> users;

    @Before
    public void before() {
        users = new ArrayList<User>() {{
            add(new User(1L, "张三", 18));
            add(new User(2L, "李四", 20));
            add(new User(3L, "王五", 21));
            add(new User(4L, "中间操", 43));
            add(new User(5L, "全部处理", 16));
            add(new User(1L, "张三", 36));
            add(new User(6L, "作不会", 34));
            add(new User(7L, "间操作", 56));
            add(new User(8L, "张三", 15));
            add(new User(9L, "stream", 23));
            add(new User(10L, "一个函数作", 21));
        }};

    }

    @Test
    public void collectionCreateStreamTest() {
        List<String> names = new ArrayList<>();
        names.stream();
        names.parallelStream();
    }

    @Test
    public void arrayCreateStreamTest() {
        String[] strs = {"one", "two", "three"};
        Arrays.stream(strs);
        int[] ints = {1, 2, 3};
        Arrays.stream(ints);
        double[] doubles = {1, 2, 3};
        Arrays.stream(doubles);
        long[] longs = {1, 2, 3};
        Arrays.stream(longs);
    }

    @Test
    public void valueCreateStreamTest() {
        Stream.of(1, 2, 3, 4, 5).forEach(System.out::println);
    }

    @Test
    public void filter() {

    }
}
