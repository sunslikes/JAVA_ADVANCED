package com.company.homwork.chapter4.annotation.annotation;

import java.lang.annotation.*;

/**
 * @PackgeName: com.company.homwork.chapter4.annotation.annotation
 * @ClassName: Column
 * @Author: z8932
 * Date: 2019/11/5 23:10
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description: 指定属性需要映射成数据库中的字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    /**
     * 字段名
     * @return
     */
    public String value() default "";

    /**
     * 字段可不可以为空
     * @return
     */
    public boolean nullable() default true;

    /**
     * 字段最大长度
     * @return
     */
    public int length() default -1;
}
