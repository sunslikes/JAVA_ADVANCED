package com.company.homwork.chapter2.collection;

public class Score {
    private Long studentId;
    private String className;
    private Double grade;

    private Score() {}

    Score(Long studentId, String className, Double grade) {
        this.setStudentId(studentId);
        this.setClassName(className);
        this.setGrade(grade);
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
