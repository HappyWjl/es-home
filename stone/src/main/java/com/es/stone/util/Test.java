package com.es.stone.util;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.Arrays;

public class Test {

    /**
     * 编译java文件
     */
    public static void compilerJava(String outRoot, String namespace, File... file) throws Exception{
        String classPath = outRoot + "/" + namespace;
        // 取得当前系统的编译器
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        //获取一个文件管理器
        StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        try {
            //文件管理器与文件连接起来
            Iterable it = javaFileManager.getJavaFileObjects(file);
            File dir = new File(classPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            //创建编译任务
            JavaCompiler.CompilationTask  task = javaCompiler.getTask(null, javaFileManager, null, Arrays.asList("-d", classPath + "/target/classes/"), null, it);
            //执行编译
            task.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            javaFileManager.close();
        }
    }

}
