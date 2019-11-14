package com.company.experiment.ex2;

/**
 * @PackgeName: com.company.experiment.ex2
 * @ClassName: JAVADemo
 * @Author: z8932
 * Date: 2019/11/14 21:42
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class JAVADemo {
    public static void main(String[] args) {
        System.out.println("准备吃饭");
        IEat iEat = new EatProxy(new EatReal());
        iEat.get();
    }
}
