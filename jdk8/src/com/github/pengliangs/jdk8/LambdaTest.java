package com.github.pengliangs.jdk8;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * lambda表达式
 *
 * @author pengliang  2019-01-14 22:24
 */
public class LambdaTest {

    /**
     * 匿名内部类
     */
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

    /**
     * JDK1.8的Lambada表达式
     */
    @Test
    public void test2() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }
}

