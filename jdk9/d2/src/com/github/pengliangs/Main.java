package com.github.pengliangs;

import com.github.pengliangs.bean.Person;

import java.util.logging.Logger;

/**
 * @author pengliang on 2018-12-04 17:19
 */
public class Main {
    private final static Logger LOGGER = Logger.getLogger("main");
    public static void main(String[] args){

        Person person = new Person("张三",18);
        LOGGER.info(person.toString());

        //User user = new User("李四",28);
    }
}
