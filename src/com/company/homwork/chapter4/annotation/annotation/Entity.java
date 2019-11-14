package com.company.homwork.chapter4.annotation.annotation;

import java.lang.annotation.*;

/**
 * @PackgeName: com.company.homwork.chapter4.annotation.annotation
 * @ClassName: Entity
 * @Author: z8932
 * Date: 2019/11/5 23:08
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description: 该类需要映射成数据库总的表
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Entity {
    /**
     * 用于映射成数据库表中的名字
     * @return 表名
     */
    public String value() default "";
}
