package com.company.experiment.ex1.main;

import com.company.homwork.chapter2.collection.Student;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: XMLSourceReader
 * @Author: z8932
 * Date: 2019/11/13 16:04
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 * XML注册读取器
 * 该类继承了注册读取器接口，并模拟实现了读取注册对象信息的方法
 * 使用DOM4j读取
 */
public class XMLSourceReader implements SourceReader {
    /**
     * 实现读取注册对象信息方法
     * @param filePath 读取路径
     * @return
     */
    @Override
    public Map<String, BeanInfo> loadBeans(String filePath) {
        Map<String, BeanInfo> container = new HashMap<>();
        // 创建reader对象
        SAXReader reader = new SAXReader();
        // 加载xml
        try {
            Document document = reader.read(new File(filePath));
            // 获取根节点
            Element rootElement = document.getRootElement();
            // 遍历获取bean们
            Element element;
            for (Iterator it = rootElement.elementIterator("bean"); it.hasNext(); ) {
                // 获取bean
                BeanInfo beanInfo = new BeanInfo();
                element = (Element) it.next();
                // 标识
                beanInfo.setId(element.attributeValue("id"));
                // 类型
                beanInfo.setType(element.attributeValue("class"));
                // 遍历bean的属性
                Element property;
                Map<String, Object> properties = new HashMap<>();
                for (Iterator iterator = element.elementIterator("property"); iterator.hasNext(); ) {
                    property = (Element) iterator.next();
                    // 属性的类型
                    Class clazz = Class.forName(property.attributeValue("type"));
                    // 不能用newInstance，因为Long或者Integer这种是没有无参构造方法的
//                    Object object = clazz.newInstance();
                    // 用有参构造方法
                    Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
                    Object object = constructor.newInstance(property.attributeValue("value"));
                    properties.put(property.attributeValue("name"), object);
                }
                beanInfo.setProperties(properties);
                container.put(beanInfo.getId(), beanInfo);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return container;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        XMLSourceReader reader = new XMLSourceReader();
        Map<String, BeanInfo> container =  reader.loadBeans("D:\\programming\\JAVA\\idea_workspace\\JAVA_ADVANCED\\src\\com\\company\\experiment\\ex1\\main\\beanfile\\applicationContext.xml");
        System.out.println(container);
    }
}
