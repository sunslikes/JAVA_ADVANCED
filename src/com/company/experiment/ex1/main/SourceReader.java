package com.company.experiment.ex1.main;

import java.util.Map;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: SourceReader
 * @Author: z8932
 * Date: 2019/11/13 15:18
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 * 注册读取器接口
 * 负责读取用户注册的对象
 * 继承该接口的类可以实现多种读取方式，如从配置文件中读取，根据标注读取，从网络中读取等
 */
public interface SourceReader {
    /**
     * 读取用户注册的对象信息
     * @param filePath 读取路径
     * @return 注册对象信息Map
     */
    Map<String, BeanInfo> loadBeans(String filePath);
}
