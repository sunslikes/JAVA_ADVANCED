package com.company.homwork.chapter2.collection;

import java.util.*;

public class SchoolSystem {
    private List<Student> studentList;
    private List<Score> scoreList;

    SchoolSystem () {
        /**
         * 初始化所有学生
         */
        studentList = new ArrayList<>();
        studentList.add(new Student(1L, "张一", "MALE", 1));
        studentList.add(new Student(2L, "李二", "FEMALE", 1));
        studentList.add(new Student(3L, "张三", "FEMALE", 2));
        studentList.add(new Student(4L, "李四", "MALE", 2));

        /**
         * 初始化所有成绩
         */
        scoreList = new ArrayList<>();
        scoreList.add(new Score(1L, "JAVA基础", 80.0));
        scoreList.add(new Score(1L, "JAVA中级", 90.0));
        scoreList.add(new Score(1L, "JAVA高级", 100.0));

        scoreList.add(new Score(2L, "JAVA基础", 50.0));
        scoreList.add(new Score(2L, "JAVA中级", 60.0));
        scoreList.add(new Score(2L, "JAVA高级", 70.0));

        scoreList.add(new Score(3L, "JAVA基础", 36.0));
        scoreList.add(new Score(3L, "JAVA中级", 90.0));
        scoreList.add(new Score(3L, "JAVA高级", 69.0));

        scoreList.add(new Score(4L, "JAVA基础", 78.0));
        scoreList.add(new Score(4L, "JAVA中级", 88.0));
        scoreList.add(new Score(4L, "JAVA高级", 89.0));
    }

    public void getAllClassAverageScore() {
        /**
         * map<课程名，总分数>
         */
        Map<String, Double> courseScoreMap = new Hashtable<>();
        /**
         * map<课程名, 总人数>
         */
        Map<String, Integer> courseStudentMap = new Hashtable<>();
        Iterator<Score> iterator = scoreList.listIterator();
        while (iterator.hasNext()) {
            Score score = iterator.next();
            Double averageScoreTemp = courseScoreMap.get(score.getClassName());
            Integer courseStudentNumber = courseStudentMap.get(score.getClassName());
            if (averageScoreTemp == null) {
                courseScoreMap.put(score.getClassName(), score.getGrade());
                courseStudentMap.put(score.getClassName(), 1);
                continue;
            }
            averageScoreTemp += score.getGrade();
            courseStudentNumber += 1;
            courseScoreMap.put(score.getClassName(), averageScoreTemp);
            courseStudentMap.put(score.getClassName(), courseStudentNumber);
        }
        for (Map.Entry<String, Integer> entry : courseStudentMap.entrySet()) {
            System.out.println(entry.getKey() + " 分数为: " + courseScoreMap.get(entry.getKey()) / entry.getValue());
        }
    }

    public void getAllCourseMaxOrMinScore() {
        Map<String, Double> maxCourseScoreMap = new Hashtable<>();
        Map<String, Double> minCourseScoreMap = new Hashtable<>();
        Iterator<Score> iterator = scoreList.listIterator();
        while (iterator.hasNext()) {
            Score score = iterator.next();
            Double maxTemp = maxCourseScoreMap.get(score.getClassName());
            Double minTemp = minCourseScoreMap.get(score.getClassName());
            if (maxTemp == null) {
                maxCourseScoreMap.put(score.getClassName(), score.getGrade());
                minCourseScoreMap.put(score.getClassName(), score.getGrade());
                continue;
            }
            if (score.getGrade() > maxTemp) {
                maxCourseScoreMap.put(score.getClassName(), score.getGrade());
            }
            if (score.getGrade() < minTemp) {
                minCourseScoreMap.put(score.getClassName(), score.getGrade());
            }
        }
        System.out.println("输出每门课程的最高成绩：");
        for (Map.Entry<String, Double> entry : maxCourseScoreMap.entrySet()) {
            System.out.println(entry.getKey() + "最高分数为：" + entry.getValue());
        }
        System.out.println("输出每门课程的最低成绩：");
        for (Map.Entry<String, Double> entry : minCourseScoreMap.entrySet()) {
            System.out.println(entry.getKey() + "最低分数为：" + entry.getValue());
        }
    }

    public Student selectSutdent() {
        int choose = 0;
        while (choose < 1 || choose > studentList.size()) {
            System.out.println("请选择学生，输入1-" + studentList.size() + ": ");
            try {
                Scanner scanner = new Scanner(System.in);
                choose = scanner.nextInt();
            }catch (Exception e) {
                System.out.println("错误输入！请重新输入！");
            }
        }
        return studentList.get(choose - 1);
    }

    public void getStudentAllCourse (Student student) {
        Iterator<Score> scoreIterator = scoreList.listIterator();
        System.out.println(student.getName() + "同学的成绩单：");
        while (scoreIterator.hasNext()) {
            Score score = scoreIterator.next();
            if (score.getStudentId().equals(student.getStudentId())) {
                System.out.println(score.getClassName() + "的成绩为：" + score.getGrade());
            }
        }
    }

    public static void main(String[] args) {
        SchoolSystem schoolSystem = new SchoolSystem();
        System.out.println("输出每门课程的平均成绩：");
        schoolSystem.getAllClassAverageScore();
        System.out.println();
        schoolSystem.getAllCourseMaxOrMinScore();
        Student student = schoolSystem.selectSutdent();
        schoolSystem.getStudentAllCourse(student);
    }



    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
