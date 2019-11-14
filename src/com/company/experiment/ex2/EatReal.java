package com.company.experiment.ex2;

/**
 * @PackgeName: com.company.experiment.ex2
 * @ClassName: EatReal
 * @Author: z8932
 * Date: 2019/11/14 21:37
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class EatReal implements IEat {
    @Override
    public void get() {
        System.out.println("我是厨师，做出香喷喷的烤鸡");
    }
}
