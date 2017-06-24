package com.Goldbach;

/**
 * Created by Ramtin on 2017-06-24.
 */
public class Goldbach2 {

    public Goldbach2(){

    }
    public void Goldbach(int x) {
        if (x % 2 != 0) {
            System.out.println("Not Even");
            return;
        }
        if (x <= 2) {
            System.out.println("Less than 2");
            return;
        }
        for (int i = 3; i < x / 2; i++) {
            if (isPrime(i) && isPrime(x - i)) {
                System.out.println("Prime Numbers are " + i + " " + (x - i));
            }
        }
    }

    public boolean isPrime(int x) {
        for (int i = 2; i < x / 2; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
