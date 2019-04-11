package com.es.stone.util;

import java.io.FileInputStream;

public class CustomizationClassLoader extends ClassLoader{
    public static String classPath;
    static {
        classPath = System.getProperty("user.dir") + "/target/classes";
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            name = name.replaceAll("src/main/java", "target/classes");
            name = name.replaceAll("java", "class");
            byte[] data = loadByte(name);

            name = name.replaceAll("\\.", "");
            name = name.replaceAll("/", ".");

            String[] sArray = name.split("classes.");
            name = sArray[1].replaceAll("class", "");

            System.out.println(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    /**
     * 加载class文件，转换为字节数组
     */
    private byte[] loadByte(String name) throws Exception {

        FileInputStream fis = new FileInputStream(name);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

}
