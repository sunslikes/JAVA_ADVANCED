package com.company.homwork.chapter4.annotation.annotation;

import java.lang.annotation.*;

/**
 * @PackgeName: com.company.homwork.chapter4.annotation.annotation
 * @ClassName: ID
 * @Author: z8932
 * Date: 2019/11/5 23:13
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description: 主键
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ID {
    /**
     * 主键名字
     * @return
     */
    public String value() default "";
}
