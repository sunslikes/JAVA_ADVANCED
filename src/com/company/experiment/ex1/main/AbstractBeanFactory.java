package com.company.experiment.ex1.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: AbstractBeanFactory
 * @Author: z8932
 * Date: 2019/11/13 15:10
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 * 最顶层的IOC实现
 * 该类负责从注册器中取出注册对象
 * 实现从对象描述信息转换为对象实例的过程
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    // 注册文件路径
    private String filePath;
    // 注册对象信息Map
    private Map<String, BeanInfo> container;
    // 对象注册读取器
    protected SourceReader reader;

    public AbstractBeanFactory(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 该方法为抽象方法，需要由子类实现，用于指定使用什么样的注册读取器
     * @param reader 指定的注册读取器
     */
    protected abstract void setSourceReader(SourceReader reader);

    /**
     * 从注册读取器中读取注册对象的信息Map
     */
    public void registerBeans() {
        this.container = this.reader.loadBeans(filePath);
    }

    /**
     * 实现BeanFactory定义的根据名称获取指定对象的方法
     * @param name 对象名称 对象描述信息中的对象标识
     * @return
     */
    @Override
    public Object getBean(String name) {
        //根据对象名称获取该对象的描述信息
        BeanInfo beanInfo = this.container.get(name);
        //如果容器中不存在该对象的描述信息，则返回null，此处可以抛开一个异常
        if (beanInfo == null) {
            return null;
        } else {
            //根据对象信息，解析并生成指定对象实例，返回给用户
            return this.parseBean(beanInfo);
        }
    }

    /**
     * 解析并生成对象实例
     * 该方法主要通过反射实现，步骤如下：
     * 1、根据类名，加载制定类，并获取该类的Class对象clazz
     * 2、使用Class对象clazz实例化该类，获取一个对象，注意，这里实例化对象时，采用的是无参构造方法，因此要求注册的对象必须含有无参构造方法
     * 3、逐个设置对象字段的值，这里采用setterMethod方式，而不是直接使用Field对象的原因是，用户有可能在setter对象中，对注入的值进行额外处理，如格式化等
     * 4、返回对象实例
     * @param beanInfo 指定对象的描述信息
     * @return 对象实例
     */
    protected Object parseBean(BeanInfo beanInfo) {
        Class<?> clazz;
        try {
            // 根据对象的全类名，指定类名
            clazz = Class.forName(beanInfo.getType());
            // 使用注册对象的无参构造方法，实例化对象实例
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object bean = constructor.newInstance();
//            Object bean = clazz.newInstance();
            // 获取该类声明的所有公共方法，其实Spring获取的是所有方法，包括非公有方法
            Method[] methods = clazz.getMethods();
            // 便利对象的所有属性进行赋值
            for (String property: beanInfo.getProperties().keySet()) {
                // 获取属性的setter方法名称
                char[] chars = new char[1];
                chars[0] = property.charAt(0);
                String temp = new String(chars);
                String setter = "set" + property.replaceFirst(temp, temp.toUpperCase());
                // 遍历该类的所有公有方法
                for (Method method: methods) {
                    // 获取方法的名称
                    String methodName = method.getName();
                    // 比较方法名与属性的setter方法名是否相同，如果相同则进行赋值
                    if (methodName.equals(setter)) {
                        // 从对象描述信息中获取该属性的值
                        Object value = beanInfo.getProperties().get(property);
                        // 通过反射对属性进行赋值
                        method.invoke(bean, value);
                        // 对下一属性赋值
                        continue;
                    }
                }
            }
            // 返回指定的对象
            return bean;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
