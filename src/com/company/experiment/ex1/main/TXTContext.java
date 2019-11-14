package com.company.experiment.ex1.main;

import com.company.experiment.ex1.main.AbstractBeanFactory;
import com.company.experiment.ex1.main.SourceReader;
import com.company.experiment.ex1.main.TXTReader;

/**
 * @PackgeName: com.company.experiment.ex1.main.beanfile
 * @ClassName: TXTContext
 * @Author: z8932
 * Date: 2019/11/14 17:19
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class TXTContext extends AbstractBeanFactory {
    /**
     *  上下文的构造方法
     *  指明注册器
     *  并在构造该方法时一次性加载注册对象
     * @param filePath
     */
    public TXTContext(String filePath) {
        super(filePath);
        setSourceReader(new TXTReader());
        this.registerBeans();
    }

    @Override
    protected void setSourceReader(SourceReader reader) {
        this.reader = reader;
    }
}
