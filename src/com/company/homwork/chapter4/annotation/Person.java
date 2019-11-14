package com.company.homwork.chapter4.annotation;

import com.company.homwork.chapter4.annotation.annotation.Column;
import com.company.homwork.chapter4.annotation.annotation.Entity;
import com.company.homwork.chapter4.annotation.annotation.ID;

/**
 * @PackgeName: com.company.homwork.chapter4.annotation
 * @ClassName: Person
 * @Author: z8932
 * Date: 2019/11/6 1:00
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
@Entity("People")
public class Person {
    @ID
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 16)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
