package com.company.homwork.chapter3.reflect.subject1;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URLClassLoader;

/**
 * @PackgeName: com.company.homwork.chapter3.reflect.subject1
 * @ClassName: MyClassLoader
 * @Author: z8932
 * Date: 2019/10/30 21:01
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description: 类加载器会先到loadClass，再通过findloadedclass查询是否有该类，然后给父类加载器处理，最后才给自己的findclass
 * 重写findclass就保留了双亲委派模型，重写loadClass就去掉了双亲委派模型
 */

public class MyClassLoader extends ClassLoader {
    private String classpath;

    // 设置自定义加载器的目录
    public MyClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = getData(name);

            if (classData == null) {
                throw new ClassNotFoundException();
            } else {
                // defineclass方法将字节码转化为类
                return defineClass(name, classData, 0, classData.length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getData(String className) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path = classpath + File.separatorChar +
                className.replace('.', File.separatorChar) + ".class";
        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
        return null;
    }
}
