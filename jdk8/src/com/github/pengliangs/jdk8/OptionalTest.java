package com.github.pengliangs.jdk8;

import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author pengliang  2019-05-18 11:30
 */
public class OptionalTest {
    @Test
    public void mapTest() {
        Optional<Person> person = Optional.ofNullable(new Person("张三", 18));
        System.out.println(person.map(p -> p.getName()).get());
    }

    /**
     * 若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 是empty和of的综合
     * value == null ? empty() : of(value);
     */
    @Test
    public void ofNullableTest() {
        Optional<Person> person = Optional.ofNullable(new Person("张三", 18));
        System.out.println(person.get());
        Optional<Person> person2 = Optional.ofNullable(null);
        System.out.println(person2.get());
    }

    /**
     * 创建一个空实例，不可直接获取
     */
    @Test
    public void empty() {
        Optional<Person> person = Optional.empty();
        System.out.println(person.get());
    }

    /**
     * 创建一个Optional实例，不可创建空实例
     */
    @Test
    public void ofTest() {
        Optional<Person> person = Optional.of(new Person());
        System.out.println(person.get());

        Optional<Person> person2 = Optional.of(null);
        person2.get();
    }
}

class Person {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}