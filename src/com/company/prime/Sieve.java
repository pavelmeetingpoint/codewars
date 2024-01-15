package com.company.prime;

import java.util.ArrayList;
import java.util.List;

public class Sieve {

    public static boolean[] sieve(int n) {
        boolean[] sieve = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            sieve[i] = true;
        }
        sieve[0] = sieve[1] = false;

        int i = 2;
        while (i * i <= n) {
            if (sieve[i]) {
                int k = i * i;
                while (k <= n) {
                    sieve[k] = false;
                    k += i;
                }
            }
            i++;
        }
        return sieve;
    }

    public static int[] factorization(int n) {
        int[] f = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            f[i] = 0;
        }
        int i = 2;
        while (i * i <= n) {
            if (f[i] == 0) {
                int k = i * i;
                while (k <= n) {
                    if(f[k] == 0){
                        f[k] = i;
                    }
                    k += i;
                }
            }
            i++;
        }
        return f;
    }

    public static List<Integer> factorization(int x, int[] f) {
        List<Integer> primeFactors = new ArrayList<>();
        while (f[x] > 0) {
            primeFactors.add(x);
            x /= f[x];
        }
        primeFactors.add(x);
        return primeFactors;
    }


}
