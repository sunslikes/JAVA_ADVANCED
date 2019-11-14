package com.company.experiment.ex2;

/**
 * @PackgeName: com.company.experiment.ex2
 * @ClassName: Speakable
 * @Author: z8932
 * Date: 2019/11/14 20:10
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class Speakable {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("fuck you");
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
