package com.github.pengliangs.jdk8;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author pengliang  2019-02-27 19:45
 */
public class MethodAndConstructorReferenceTest {

    @Test
    public void methodReferenceTest() {
        // Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        Comparator<Integer> comparator = Integer::compare;
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(10);
        treeSet.forEach(System.out::println);

        //BinaryOperator<Double> bo = (x, y)-> Math.pow(x,y);
        BinaryOperator<Double> bo = Math::pow;
        System.out.println(bo.apply(1.2, 1.2));
    }


    @Test
    public void constructorReferenceTest() {
        // Function<Integer,MyClass> fun = (n) -> new MyClass(n);
        Function<Integer, MyClass> fun = MyClass::new;
    }
}

class MyClass {

    public MyClass(Integer i) {
    }
}