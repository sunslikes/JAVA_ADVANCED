package com.company.homwork.chapter3.reflect.subject1;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @PackgeName: com.company.homwork.chapter3.reflect.subject1
 * @ClassName: GenerateClass
 * @Author: z8932
 * Date: 2019/10/30 23:08
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description: 深刻理解了为什么boolean字段不能加is
 */
public class GenerateClass {
    /**
     * 类加载器的加载路径
     */
    private String path;
    private String name;
    private Class<?> clazz = null;
    private static final String BLANK = " ";
    private static final String TAB = "\t";
    private StringBuilder stringBuilder = new StringBuilder();

    public GenerateClass(String path,String name) throws ClassNotFoundException {
        this.path = path;
        this.name = name;
        this.clazz = this.setClazz();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        GenerateClass generateClass = new GenerateClass("D:\\programming\\JAVA\\idea_workspace\\JAVA_ADVANCED\\src\\com\\company\\homwork\\chapter3\\reflect\\subject1","com.company.homwork.chapter3.reflect.subject1.Person");
        generateClass.generate();
    }

    /**
     * 通过class文件获取对象
     * @return class对象
     * @throws ClassNotFoundException
     */
    private Class<?> setClazz() throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader(this.path);
        // 包名+类名  注意这里的包名是绝对不能变的，初始类是什么包的这个就要是什么包的，只是说加载路径不同了
        Class<?> clazz = myClassLoader.loadClass(this.name);
        return clazz;
    }

    public void generate() {
        this.getPackageDesc();
        this.getClassDesc();
        this.getFieldDesc();
        this.getConstructorDesc();
        this.getMethodDesc();
        stringBuilder.append("\n}");
        this.generateOutput();
    }

    private void getPackageDesc() {
        String packageName = clazz.getPackage().getName();
        stringBuilder.append("package " + packageName + ".generated;");
    }
    /**
     * Modifier类修饰符的对应情况
     * Modifier.PUBLIC //1
     * Modifier.PRIVATE//2
     * Modifier.PROTECTED//4
     * "default"//0
     * modifier值>=512即为interface，0-511是class
     */
    private void getClassDesc() {
        String classModifier = "";
        int modifier = clazz.getModifiers();
        if (modifier == 0) {
            classModifier = "class";
        } else if (modifier == 1) {
            classModifier = "public class";
        } else {
            if (Modifier.isPublic(modifier)) {
            } else {
                classModifier = "interface";
            }
        }
        String className = clazz.getSimpleName();
        stringBuilder.append("\n\n" + classModifier + BLANK + className + BLANK);
        Class parent = clazz.getSuperclass();
        // 说明有父类,且不是object
        if (parent != null && !Object.class.isInstance(parent)) {
            stringBuilder.append("extends " + parent.getSimpleName() + BLANK);
        }
        // 判断有没有实现接口
        Class[] interfaces = null;
        interfaces = clazz.getInterfaces();
        if (interfaces != null && interfaces.length != 0) {
            stringBuilder.append("implements" + BLANK);
            for (Class<?> inter: interfaces) {
                stringBuilder.append(inter.getSimpleName() + BLANK);
            }
        }
        stringBuilder.append("{");
    }

    /**
     * 字段
     */
    private void getFieldDesc() {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            String name = field.toString();
            name = name.replaceFirst("\\S(\\w*\\.*)*$", field.getName());
            stringBuilder.append("\n" + TAB + name + ";");
        }
    }
    /**
     * 构造方法
     */
    private void getConstructorDesc() {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor: constructors) {
            Class[] params = constructor.getParameterTypes();
            StringBuilder paramSB = new StringBuilder();
            // 有参数的方法， 要在类型后面添加变量名
            if (params.length > 0) {
                paramSB.append(clazz.getSimpleName() + "(");
                String arg = "arg";
                for (int i = 0; i < params.length; i++) {
                    paramSB.append(params[i].getCanonicalName() + BLANK + arg + i + ",");
                }
                paramSB = new StringBuilder(paramSB.substring(0, paramSB.length() - 1));
                paramSB.append(")");
                String name = constructor.toString();
                name = name.replaceFirst("\\S(\\w*\\.*)*\\((\\w*\\.*\\ *)*\\)", paramSB.toString());
                stringBuilder.append("\n" + TAB + name + "{\n\n" + TAB + "}\n");
                continue;
            }
            // 无参构造方法
            String name = constructor.toString();
            name = name.replaceFirst("\\S(\\w*\\.*)*\\(", clazz.getSimpleName() + "(");
            stringBuilder.append("\n" + TAB + name + "{\n\n" + TAB + "}\n");
        }
    }
    /**
     * 一般方法
     */
    private void getMethodDesc() {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method: methods) {
            Class[] params = method.getParameterTypes();
            StringBuilder paramSb = new StringBuilder();
            // 有参数的方法
            if (params.length > 0) {
                paramSb.append(method.getName() + "(");
                String arg = "arg";
                for (int i = 0; i < params.length; i++) {
                    paramSb.append(params[i].getCanonicalName() + BLANK + arg + i + ",");
                }
                paramSb = new StringBuilder(paramSb.substring(0,paramSb.length() - 1));
                paramSb.append(")");
                String name = method.toString();
                name = name.replaceFirst("\\S(\\w*\\.*)*\\((\\w*\\.*\\ *)*\\)", paramSb.toString());
                stringBuilder.append("\n"+TAB+name+"{\n\n"+TAB+"}\n");
                continue;
            }
            // 无参方法
            String name = method.toString();
            name = name.replaceFirst("\\S(\\w*\\.*)*\\(", method.getName() + "(");
            //获得return语句
            String attrName = method.getName().substring(3);
            char[] chars = new char[1];
            chars[0] = attrName.charAt(0);
            String temp = new String(chars);
            attrName = attrName.replaceFirst(temp, temp.toLowerCase());
            paramSb.append("\n"+TAB+TAB+"return "+attrName+";");

            stringBuilder.append("\n"+TAB+name+"{");
            stringBuilder.append(paramSb.toString()+"\n"+TAB+"}\n");
        }
    }
    /**
     * 输出
     */
    private void generateOutput() {
        String path = clazz.getPackage().getName();
        path = path.replace(".", "\\");
        String url = System.getProperty("user.dir") + "\\src\\" + path + "\\generated\\" +
                clazz.getSimpleName() + ".java";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(url);
            fileOutputStream.write(stringBuilder.toString().getBytes("UTF-8"));
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Class<?> getClazz() {
        return this.clazz;
    }
}
