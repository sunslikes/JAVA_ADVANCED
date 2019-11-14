package com.company.homwork.chapter2.collection;

public class Student {
    private Long studentId;
    private String name;
    private String sex;
    private Integer classNumber;

    private Student() {}

    Student(Long studentId, String name, String sex, Integer classNumber){
        this.setStudentId(studentId);
        this.setName(name);
        this.setSex(sex);
        this.setClassNumber(classNumber);
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append("[");
        stringBuilder.append("studentId=" + studentId);
        stringBuilder.append(", name=" + name);
        stringBuilder.append(", sex=" + sex);
        stringBuilder.append(", classNumber=" + classNumber);
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }
}
