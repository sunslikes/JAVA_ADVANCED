package com.company.experiment.ex4.thread;

import java.util.ArrayList;

public class MaxnumberClient {
    public static final int LEN = 10000000;
    public static final int THREAD = 3;

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.println("开始随机生成数组，长度为" + LEN);
        for (int i = 0; i < LEN; i++) {
            numbers.add((int)(Math.random() * 10000000));
        }
        System.out.print("生成数组：");
//        for (int number: numbers) {
//            System.out.print(number + ", ");
//        }
        System.out.println();
        int LENGTH = LEN/THREAD;
        int begin = 0;
        ArrayList<Integer> bignumbers = new ArrayList<>();
        for (int i = 0; i < THREAD; i++) {
            BiggestNumberThread thread = new BiggestNumberThread(numbers, begin, begin + LENGTH);
            begin = begin + LENGTH + 1;
            bignumbers.add(thread.call());
        }
        System.out.println("进行最后整合：");
        BiggestNumberThread thread = new BiggestNumberThread(bignumbers, 0, bignumbers.size() - 1);

        thread.call();
    }
}
