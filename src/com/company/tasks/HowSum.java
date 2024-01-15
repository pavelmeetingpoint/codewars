package com.company.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HowSum {

    public static List<Integer> howSum(int n, int[] array, Map<Integer, List<Integer>> memoization) {
        if(memoization.containsKey(n)){
            return memoization.get(n);
        }
        if(n == 0){
            return new ArrayList<>();
        }
        if(n < 0){
            return null;
        }

        for (int i = 0; i < array.length; i++) {
            int reminder = n - array[i];
            var how= howSum(reminder, array, memoization);
            if(how != null){
                how.add(array[i]);
                memoization.put(n, how);
                return memoization.get(n);
            }
        }
        memoization.put(n, null);
        return null;
    }
}
