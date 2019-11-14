package com.company.experiment.ex4.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BiggestNumberThread implements Callable<Integer> {

    // 数组
    private List<Integer> numbers;
    // 该线程处理的数组开始位置
    private Integer begin;
    // 该线程处理的数组结束位置
    private Integer end;

    private BiggestNumberThread() {} // 禁止无参构造

    BiggestNumberThread (ArrayList<Integer> numbers, int begin, int end) { // 初始化起始位置
        this.numbers = numbers;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        // 数组为空
        if (numbers == null) {
            System.out.println("无数据");
            return null;
        }
        if (begin < 0) {
            System.out.println("开始位置错误");
            return null;
        }
        if (end >= numbers.size()) {
            end = numbers.size() - 1;
        }
        System.out.println(this.toString() + "开始处理");
        // 初始化最大数
        int max = numbers.get(begin);
        for (int i = begin + 1; i <= end; i++) {
            if (max < numbers.get(i)) {
                max = numbers.get(i);
            }
        }
        System.out.println(this.toString() + "的最大值为：" + max);
        // 自动装箱
        return max;
    }

    @Override
    public String toString() {
        return "线程[" + begin + ", " + end + "]";
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
