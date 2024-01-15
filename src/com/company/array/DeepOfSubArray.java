package com.company.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeepOfSubArray {

    public static int findMaximumDeepArray(int[] array, int low, int high){
        boolean left = false;
        int max_left = 0;
        int deep = 0;
        int deepIndex = 0;
        List<Integer> results = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            if(i == 0){
                if(array[i] > array[i+1]) {
                    max_left = array[i];
                    left = true;
                }
            }
            else if(array[i] > array[i-1] && !left){
                max_left = array[i];
                left = true;
            }
            else if(i + 1 > array.length - 1){
                deep = array[i] - array[deepIndex];
                results.add(deep);
            }
            else if(array[i] > max_left && left){
                results.add(deep);
                left = false;
                max_left = array[i];
                deep = 0;
            }
            else{
                if(max_left - array[i] > deep){
                    deep = max_left - array[i];
                    deepIndex = i;
                }
            }
        }
        return deep;
    }

}
