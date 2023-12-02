package com.company.memoization;

import java.util.*;

/**
 * Write a function availableMoves(position) which returns possibly moves of grid.
 */
public class GridTraveler {

    public static int move(int row, int column, Map<String, Integer> memo){
        var key = String.valueOf(row).concat(" ").concat(String.valueOf(column));
        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        if(row == 0 || column == 0){
            return 0;
        }
        if(row == 1 && column == 1){
            return 1;
        }
        memo.put(key, move(row - 1, column, memo) + move(row, column - 1, memo));
        return memo.get(key);
    }

}

