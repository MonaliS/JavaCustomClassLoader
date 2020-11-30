package com.monali.classes;

public class Test2 {

    public Test2(String a, String b) {
        System.out.println("Test2 Constructor >>> " + a + " " + b);
    }

    public void printCL() {
        System.out.println("Test2 ClassLoader: "+Test2.class.getClassLoader());
    }
}
