package com.company.tasks;

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
}
