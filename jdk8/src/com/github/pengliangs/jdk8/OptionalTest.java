package com.github.pengliangs.jdk8;

import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author pengliang  2019-05-18 11:30
 */
public class OptionalTest {


    /**
     * 若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 是empty和of的综合
     * value == null ? empty() : of(value);
     */
    @Test
    public void ofNullable() {
        Optional<Person> person = Optional.ofNullable(new Person());
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
    private String age;
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}