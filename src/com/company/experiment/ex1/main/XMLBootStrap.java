package com.company.experiment.ex1.main;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: XMLBootStrap
 * @Author: z8932
 * Date: 2019/11/14 13:30
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 * XML测试类
 */
public class XMLBootStrap {
    public static final String FILEPATH = "D:\\programming\\JAVA\\idea_workspace\\JAVA_ADVANCED\\src\\com\\company\\experiment\\ex1\\main\\beanfile\\applicationContext.xml";
    public static void main(String[] args) {
        BeanFactory beanFactory = new XMLContext(FILEPATH);
        System.out.println(beanFactory.getBean("student"));
        System.out.println(beanFactory.getBean("score"));
    }
}
