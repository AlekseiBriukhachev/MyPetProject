package com.aleksei.app;

import java.util.Arrays;

public class EratosphenSieve {
    public static void main(String[] args) {
//        int[] primes = sieve(100);
//        for (int prime : primes) {
//            System.out.println(prime + " ");
//        }
    }

    private static void sieve(int n) {

        boolean[] mark = new boolean[n];
        Arrays.fill(mark, true);
        for (int i = 3; i * i < n; i += 2) {
            if (mark[i]) {
                for (int j = i * i; j < n; j += i) {
                    mark[j] = false;
                }
            }
        }
    }
}
