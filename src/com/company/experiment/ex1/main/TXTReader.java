package com.company.experiment.ex1.main;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: TXTReader
 * @Author: z8932
 * Date: 2019/11/14 14:04
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class TXTReader implements SourceReader {
    /**
     * 实现读取注册对象信息方法
     * @param filePath 读取路径
     * @return
     */
    @Override
    public Map<String, BeanInfo> loadBeans(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            Map<String, BeanInfo> beanInfoMap = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                // 说明读取到一个bean信息,以///结束
                if (line.equals("###")) {
                    // 获取bean
                    BeanInfo beanInfo = new BeanInfo();
                    line = reader.readLine();
                    if (line == null) {
                        System.out.println("txt文件错误");
                        break;
                    }
                    String[] elements = line.split(" ");
                    Map<String, String> elementsMap = new HashMap<>();
                    // 将bean的标识和类型保存
                    for (String propersity: elements) {
                        elementsMap.put(propersity.split("=")[0], propersity.split("=")[1]);
                    }
                    // 设置标志
                    beanInfo.setId(elementsMap.get("id"));
                    // 设置类型
                    beanInfo.setType(elementsMap.get("class"));
                    // 初始化属性及值的集合
                    Map<String, Object> propersitiesMap = new HashMap<>();
                    line = reader.readLine();
                    while (!"///".equals(line)&& line != null) {
                        elements = line.split(" ");
                        // 属性的名称、值、类型
                        for (String propersity: elements) {
                            elementsMap.put(propersity.split("=")[0], propersity.split("=")[1]);
                        }
                        // 通过类名称实例化属性的值
                        Class clazz = Class.forName(elementsMap.get("type"));
                        Constructor constructor = clazz.getConstructor(String.class);
                        Object object = constructor.newInstance(elementsMap.get("value"));
                        // 保存一个属性及值
                        propersitiesMap.put(elementsMap.get("name"), object);
                        // 读取下一个属性的信息
                        line = reader.readLine();
                    }
                    beanInfo.setProperties(propersitiesMap);
                    beanInfoMap.put(beanInfo.getId(), beanInfo);
                }
            }
            return beanInfoMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        TXTReader txtReader = new TXTReader();
        System.out.println(txtReader.loadBeans("D:\\programming\\JAVA\\idea_workspace\\JAVA_ADVANCED\\src\\com\\company\\experiment\\ex1\\main\\beanfile\\Person.txt"));
    }
}
