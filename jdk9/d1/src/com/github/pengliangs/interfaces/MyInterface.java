package com.github.pengliangs.interfaces;

/**
 * JDK7：只能声明全局常量(public static final)和抽象方法(public abstract)
 * JDK8：接口中可以声明静态方法和默认方法
 */
interface MyInterfaceService {

    /**
     * JDK7
     */
    void test1();

    /**
     * JDK8
     */
    static void test2() {
        System.out.println("test2");
    }

    default void test3() {
        System.out.println("test3");
        test4();
    }

    /**
     * JDK9
     */
    private void test4() {
        System.out.println("test4");
    }
}

class MyInterfaceServiceImpl implements MyInterfaceService {

    @Override
    public void test1() {
        System.out.println("test1");
    }
}

/**
 * @author pengliang on 2018-12-04 18:14
 */
public class MyInterface {
    public static void main(String[] args) {
        MyInterfaceService myInterface = new MyInterfaceServiceImpl();
        myInterface.test1();
        myInterface.test3();
    }
}