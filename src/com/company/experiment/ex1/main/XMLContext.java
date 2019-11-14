package com.company.experiment.ex1.main;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: XMLContext
 * @Author: z8932
 * Date: 2019/11/14 13:24
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class XMLContext extends AbstractBeanFactory{
    /**
     *  上下文的构造方法
     *  指明注册器
     *  并在构造该方法时一次性加载注册对象
     * @param filePath
     */
    public XMLContext(String filePath) {
        super(filePath);
        this.setSourceReader(new XMLSourceReader());
        this.registerBeans();
    }

    @Override
    protected void setSourceReader(SourceReader reader) {
        this.reader = reader;
    }

}
