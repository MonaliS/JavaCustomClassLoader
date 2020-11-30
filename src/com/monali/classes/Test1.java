package com.monali.classes;

public class Test1 {
     public static void main(String args[]) throws Exception {
        System.out.println("Test1 Constructor >>> " + args[0] + " " + args[1]);
        Test2 test2 = new Test2(args[0], args[1]);
        test2.printCL();
    }

    public static void printCL() {
        System.out.println("Test1 ClassLoader: "+ Test1.class.getClassLoader());
    }
}