package com.github.pengliangs.jdk11;

import org.junit.Test;

/**
 * @author pengliang
 * @since 2020/10/5 21:17
 */
public class StringTest {

    @Test
    public void string_test() {
        // 判断是否空白字符
        System.out.println("   \n\t \r".isBlank());

        // 判断是否为空字符
        System.out.println("".isEmpty());
        System.out.println("   ".isEmpty());

        // 去除收尾空白字符
        var stripStr = " \t   abc \n\t \r ";
        // strip 去除所有语言中的空白字符
        System.out.println(stripStr.strip());
        // 去除首部空白
        System.out.println(stripStr.stripLeading());
        // 去除尾部空白
        System.out.println(stripStr.stripTrailing());
        System.out.println(stripStr.strip().length());
        // trim 去除码值小于32的空白字符
        System.out.println(stripStr.trim());
        System.out.println(stripStr.trim().length());
    }

    @Test
    public void string_test2() {
        var str = "java";
        // 复制字符串
        System.out.println(str.repeat(5));

        // 根据换行符分割为流
        var strLines = "java\njava";
        System.out.println(strLines.lines().count());

    }
}
