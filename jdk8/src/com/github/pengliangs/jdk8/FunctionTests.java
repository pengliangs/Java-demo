package com.github.pengliangs.jdk8;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口
 *
 * @author pengliang  2019-01-14 22:31
 */
public class FunctionTests {

    @Test
    public void consumeTests() {
        accept(100, (m) -> System.out.println("消费：" + m));
    }

    private void accept(int money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

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

    @Test
    public void functionTests() {
        int length = handlerStr("function", (s) -> s == null ? 0 : s.length());
        Assert.assertEquals(length, 8);

    }

    private Integer handlerStr(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

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
}
