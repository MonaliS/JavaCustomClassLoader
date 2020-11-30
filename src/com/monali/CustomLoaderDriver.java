package com.monali;

import java.lang.reflect.Method;


public class CustomLoaderDriver {
    public static void main(String args[]) throws Exception {
        String progClass = args[0];
        String progArgs[] = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);

        CustomLoader customLoader = new CustomLoader(CustomLoaderDriver.class.getClassLoader());
        Class clas = customLoader.loadClass(progClass);
        Class mainArgType[] = { (new String[0]).getClass() };
        Method main = clas.getMethod("main", mainArgType);
        Object argsArray[] = { progArgs };
        main.invoke(null, argsArray);

        // Below method is used to check that the Test is getting loaded by our custom class loader
        Method printCL = clas.getMethod("printCL", null);
        printCL.invoke(null, new Object[0]);
    }
}
