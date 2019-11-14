package com.company.homwork.chapter3.reflect.subject2;

public class Person {
    private String idNo;// 身份证号
    private String name;// 姓名
    private Integer age;// 年龄
    private String sex;// 性别
    private Boolean isMerried;// 是否已婚

    public Person() {

    }

    public Person(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getIsMerried() {
        return isMerried;
    }

    public void setIsMerried(Boolean isMerried) {
        this.isMerried = isMerried;
    }

}