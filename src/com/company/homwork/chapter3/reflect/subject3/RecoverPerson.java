package com.company.homwork.chapter3.reflect.subject3;

import com.company.homwork.chapter3.reflect.subject2.Person;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackgeName: com.company.homwork.chapter3.reflect.subject3
 * @ClassName: RecoverPerson
 * @Author: z8932
 * Date: 2019/10/31 22:55
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class RecoverPerson {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException {
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        FileInputStream fileInputStream = new FileInputStream("D:\\programming\\JAVA\\idea_workspace\\JAVA_ADVANCED\\src\\com\\company\\homwork\\chapter3\\reflect\\subject2\\PersonFields.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Map<String,String> fieldValueMap = new HashMap<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split("=");
            fieldValueMap.put(strings[0].toLowerCase(), strings[1]);
        }
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        for (Field field: fields) {
            field.setAccessible(true);
            String valueString = fieldValueMap.get(field.getName().toLowerCase());
            Object value = null;
            if ("age".equals(field.getName().toLowerCase())) {
                value = Integer.valueOf(valueString);
            } else if ("ismerried".equals(field.getName().toLowerCase())) {
                value = Boolean.valueOf(valueString);
            } else {
                value = valueString;
            }
            field.set(person, value);
        }
        System.out.println("idNo:"+person.getIdNo()+",name:"+person.getName()+",age:"
                +person.getAge()+",sex:"+person.getSex()+",isMerried:"
                +person.getIsMerried());
    }

}
