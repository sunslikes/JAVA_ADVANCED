package com.company.experiment.ex1.main;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: BeanFactory
 * @Author: z8932
 * Date: 2019/11/13 15:07
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description: IOC容器的顶层接口
 */
public interface BeanFactory {
    /**
     * 根据对象的名称标识获取对象实例
     * @param name 对象名称 对象描述信息中的对象标识
     * @return 指定名称的对象实例
     */
    public Object getBean(String name);
}
