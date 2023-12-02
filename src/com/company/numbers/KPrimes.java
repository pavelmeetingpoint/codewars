package com.company.numbers;

import java.util.*;

/**
 * A natural number is called k-prime if it has exactly k prime factors, counted with multiplicity. For example:
 *
 * k = 2  -->  4, 6, 9, 10, 14, 15, 21, 22, ...
 * k = 3  -->  8, 12, 18, 20, 27, 28, 30, ...
 * k = 5  -->  32, 48, 72, 80, 108, 112, ...
 * A natural number is thus prime if and only if it is 1-prime.
 *
 * Task:
 * Complete the function count_Kprimes (or countKprimes, count-K-primes, kPrimes) which is given parameters k,
 * start, end (or nd) and returns an array (or a list or a string depending on the language - see "Solution"
 * and "Sample Tests") of the k-primes between start (inclusive) and end (inclusive).
 */
public class KPrimes {

    private static int FIRST_PRIME = 2;
    private static final Map<Integer, long[]> memo = new HashMap<>();
    public static long[] countKprimes(int k, long start, long end) {
        int size = (int) (end - start + 1);
        long[] kPrimes = new long[size];
        int index = 0;
        for (long i = start; i <= end; i++) {
            if (countPrimeFactors(i) == k) {
                kPrimes[index] = i;
                index++;
            }
        }
        return Arrays.copyOf(kPrimes, index);
    }

    private static int countPrimeFactors(long number) {
        int count = 0;
        for (int i = 2; i * i <= number; i++) {
            while (number % i == 0) {
                number /= i;
                count++;
            }
        }
        if (number > 1) {
            count++;
        }
        return count;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int puzzle(int s) {
        long[] onePrimes = countKprimes(1, 2, s);
        long[] threePrimes = countKprimes(3, 2, s);
        long[] sevenPrimes = countKprimes(7, 2, s);

        int count = 0;
        for (long a : onePrimes) {
            for (long b : threePrimes) {
                for (long c : sevenPrimes) {
                    if (a + b + c == s) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}

