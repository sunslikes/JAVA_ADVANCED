package com.company.experiment.ex2;

/**
 * @PackgeName: com.company.experiment.ex2
 * @ClassName: EatProxy
 * @Author: z8932
 * Date: 2019/11/14 21:38
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class EatProxy implements IEat {
    private IEat iEat;
    public EatProxy (IEat iEat) {
        this.iEat = iEat;
    }

    @Override
    public void get() {
        System.out.println("我是饭店");
        iEat.get();
        System.out.println("送餐结账");
    }
}
