package com.company.homwork.chapter3.reflect.subject2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @PackgeName: com.company.homwork.chapter3.reflect.subject2
 * @ClassName: StorePersonField
 * @Author: z8932
 * Date: 2019/10/31 22:14
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class StorePersonField {
    public static void main(String[] args) throws IllegalAccessException, IOException {
        Person person = new Person();
        person.setAge(23);
        person.setIdNo("5122245566");
        person.setName("张小平");
        person.setSex("Mele");
        person.setIsMerried(true);
        Class<Person> personClass = (Class<Person>) person.getClass();
        Field[] fields = personClass.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field: fields) {
            field.setAccessible(true);
            stringBuilder.append(field.getName() + "=" + field.get(person) + "\n");
        }
        System.out.println(stringBuilder);
        String url = System.getProperty("user.dir") + "\\src\\" + personClass.getPackage().getName().replace(".", "\\") + "\\PersonFields.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(url);
        fileOutputStream.write(stringBuilder.toString().getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
