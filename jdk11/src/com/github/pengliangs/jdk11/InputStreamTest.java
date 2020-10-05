package com.github.pengliangs.jdk11;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author pengliang
 * @since 2020/10/5 21:40
 */
public class InputStreamTest {


    @Test
    public void inputStream_test() {
        try (var in = this.getClass().getClassLoader().getResourceAsStream("file");
             var out = new FileOutputStream("copy_file")) {
            /**
             * @since jdk 9
             * 把输入流中的数据复制到输出流
             */
            in.transferTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
