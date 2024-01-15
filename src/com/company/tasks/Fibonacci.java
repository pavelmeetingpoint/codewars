package com.company.tasks;

import java.util.HashMap;

public class Fibonacci {
    private static int fibonnachi(int n, HashMap<Integer, Integer> map){
        if(map.containsKey(n)){
            return map.get(n);
        }
        if(n == 1){
            return 0;
        }
        if(n == 2){
            return 1;
        }
        var result = fibonnachi(n - 1, map) + fibonnachi(n - 2, map);
        map.put(n, result);
        return map.get(n);
    }

    public static int fibonnachi(int n){
        int[] number = new int[n + 1];
        number[1] = 1;
        for(int i = 1; i < n; i++){
            number[i + 1] += number[i];
            if(i + 2 < n + 1) {
                number[i + 2] += number[i];
            }
        }
        return number[n];
    }
}
