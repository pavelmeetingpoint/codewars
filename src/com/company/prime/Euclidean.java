package com.company.prime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Euclidean {

    public static Set<Integer> primeDivisors(int n) {
        Set<Integer> primeDivisorsList = new HashSet<>();

        while (n % 2 == 0) {
            primeDivisorsList.add(2);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                primeDivisorsList.add(i);
                n /= i;
            }
        }

        if (n > 2) {
            primeDivisorsList.add(n);
        }

        return primeDivisorsList;
    }

}
