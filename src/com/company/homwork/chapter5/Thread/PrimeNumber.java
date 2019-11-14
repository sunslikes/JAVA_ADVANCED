package com.sunslikes.homwork.chapter5.Thread;

public class PrimeNumber {
    public static boolean isPrimeNumber(Integer integer) {
        if (integer < 2) {
            return false;
        }
        boolean isPrimeNumber = true;
        for (int index = 2; index <= Math.sqrt(integer); index++) {
            if (integer % index == 0) {
                isPrimeNumber = false;
                break;
            }
        }
        return isPrimeNumber;
    }
}
