package com.company.experiment.ex1.main;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: TXTBootStrap
 * @Author: z8932
 * Date: 2019/11/14 17:22
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class TXTBootStrap {
    public static final String FILEPATH = "D:\\programming\\JAVA\\idea_workspace\\JAVA_ADVANCED\\src\\com\\company\\experiment\\ex1\\main\\beanfile\\Person.txt";
    public static void main(String[] args) {
        BeanFactory beanFactory = new TXTContext(FILEPATH);
        Object object = beanFactory.getBean("student");
        System.out.println(object);
        System.out.println(object.getClass().getName());
    }
}
