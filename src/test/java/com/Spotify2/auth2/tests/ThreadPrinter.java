package com.Spotify2.auth2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class ThreadPrinter {

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("METHOD NAME:" +m.getName());
        System.out.println("THREAD ID:"+ Thread.currentThread().threadId());
    }
}
