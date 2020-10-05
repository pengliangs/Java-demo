package com.github.pengliangs.jdk11;

import org.junit.Test;

import java.util.Optional;

/**
 * @author pengliang
 * @since 2020/10/5 21:29
 */
public class OptionalTest {


    @Test
    public void optional_test() {
        var optional = Optional.ofNullable(null);

        // jdk 9
        // optional.or()

        // jdk 10
        // optional.orElseThrow();
    }
}
