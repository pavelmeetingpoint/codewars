package com.company.tasks;

import java.math.BigDecimal;

public class CanSum {
    public static boolean canSum(int n, int[] array){
        if(n == 0){
            return true;
        }
        if(n < 0){
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if(true == canSum(n - array[i], array)){
                return true;
            }
        }
        return false;
    }

    public static boolean canSumTabulation(int n, int[] array){
        boolean[] table = new boolean[n];
        table[0] = true;
        for (int i = 0; i < table.length; i++) {
            if(table[i]){
                for (int j = 0; i < table.length; i++) {
                    if(i + j < table.length){
                        table[i+j] = true;
                    }
                }
            }
        }
        return table[n - 1];
    }

}
