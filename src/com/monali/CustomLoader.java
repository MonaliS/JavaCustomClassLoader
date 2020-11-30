package com.monali;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomLoader extends ClassLoader {

    /**
     *Parent ClassLoader is set through the constructor
     */
    public CustomLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Loads the class from the file system.
     * @param name
     * Fully Classified name of the class, for example, com.monali.Foo
     */
    private Class getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] b = null;
        try {
            // This loads the byte code data from the file
            b = loadClassFileData(file);
            // defineClass is inherited from the ClassLoader class, it converts byte array into a Class.
            Class c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * If the class is in com.monali package, we will use CustomLoader classloader otherwise delegate the
     * request to parent classloader.
     * @param name
     * Full class name
     */
    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading Class '" + name + "'");
        if (name.startsWith("com.monali")) {
            System.out.println("Loading Class using CustomLoader");
            return getClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * Reads the file (.class) into a byte array.
     * @param name Filename
     * @return Byte array read from the file
     * @throws IOException if an exception comes in reading the file
     */
    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(
                name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
