package com.github.pengliangs;

import com.github.pengliangs.bean.Person;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author pengliang on 2018-12-04 17:19
 */
public class Main {
    private final static Logger LOGGER = Logger.getLogger("main");

    public static void main(String[] args) {

        Person person = new Person("张三", 18);
        LOGGER.info(person.toString());

        //User user = new User("李四",28);
    }


    @Test
    public void listTest() {
        List<String> list1 = new ArrayList<>();
        list1.add("one");
        list1.add("two");
        list1.add("three");

        //Collections.unmodifiableCollection将集合变为只读的
        List<String> newList1 = Collections.unmodifiableList(list1);
        list1.add("four");
        newList1.add("five");
        newList1.forEach(System.out::println);
    }

    @Test
    public void setTest() {
        Set<Integer> set1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3)));
        set1.forEach(System.out::println);
        set1.add(4);
    }

    @Test
    public void mapTest() {
        Map<String, Integer> map1 = Collections.unmodifiableMap(new HashMap<>(3) {{
            put("one", 1);
            put("two", 2);
            put("three", 3);
        }});
        map1.forEach((k, v) -> System.out.println(k + ":" + v));
        map1.put("four", 4);
    }

    /**
     * JDK9中的集合
     */
    @Test
    public void finalListJDK9() {
        List<Integer> list = List.of(1, 2, 3, 4);
        //list.add(5);
        Set<Integer> set = Set.of(1, 2, 3, 4);
        //set.add(5);
        Map<String, Integer> map1 = Map.of("one", 1, "two", 2);
        // map.put("three",3);
        Map<String, Integer> map2 = Map.ofEntries(Map.entry("one", 1), Map.entry("two", 2));
        map2.put("three", 3);
    }



}

