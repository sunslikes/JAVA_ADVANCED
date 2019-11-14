package com.sunslikes.homwork.chapter5.Thread;

import java.util.Scanner;

/**
 *  利用多线程计算一个范围内的素数
 */
public class PrimeNumberThread implements Runnable {
    public static void main(String[] args) {
        System.out.println("请输入起始值：");
        Scanner scanner = new Scanner(System.in);
        int begin = scanner.nextInt();
        System.out.println("请输入结束值：");
        int end = scanner.nextInt();

    }

    @Override
    public void run() {

    }
}
