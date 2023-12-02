package com.company.backtracking;

import java.util.*;

/**
 * Write a function availableMoves(position) which returns possibly moves of chess queen.
 * Returned value should be an array with possible moves sorted alphabetically, excluded starting position.
 */
public class ChessQueen {

    private static final Map<String, Integer> LETTERS = Map.of("A", 1, "B", 2, "C", 3, "D", 4,"E",5, "F", 6, "G",7, "H",8);
    private static final Map<Integer, String> REVERSE_LETTERS = Map.of(1,"A", 2,"B", 3,"C", 4,"D", 5,"E", 6,"F", 7,"G", 8,"H");
    private static final List<Integer> DIGITS = List.of(1, 2, 3, 4, 5, 6, 7, 8);
    private static final int MIN = 0;
    private static final int MAX = 8;
    public static final String[] EMPTY_ARRAY = new String[0];

    public static void solve(String inputPosition){
        var positionArray = inputPosition.split("");
        var letter = positionArray[0];
        var digit = Integer.valueOf(positionArray[1]);
        if(!LETTERS.containsKey(letter) || !DIGITS.contains(digit)){
            System.out.println(Arrays.toString(EMPTY_ARRAY));
        }
        var solutions = new TreeSet<String>();
        search(solutions, letter, digit);
        solutions.stream().toList();
        System.out.println(solutions);
    }

    private static boolean isValid(String inputPosition){
        if(Objects.isNull(inputPosition) || inputPosition.length() > 2){
            return false;
        }
        return true;
    }

   private static void search(Set<String> solutions, String letter, Integer digit){
        solutions.addAll(getHorizontal(letter, digit));
        solutions.addAll(getVertical(letter, digit));
        solutions.addAll(getDiagonal(letter, digit));
    }

    private static List<String> getHorizontal(String letter, Integer digit){
        var result = new ArrayList<String>();
        LETTERS.keySet().forEach(currentLetter -> {
            if(!currentLetter.equals(letter)) {
                result.add(currentLetter + digit);
            }
        });
        return result;
    }

    private static List<String> getVertical(String letter, Integer digit){
        var result = new ArrayList<String>();
        DIGITS.forEach(currentDigit -> {
            if(!Objects.equals(currentDigit, digit)) {
                result.add(letter + currentDigit);
            }
        });
        return result;
    }

    private static Set<String> getDiagonal(String letter, Integer digit){
        var result = new TreeSet<String>();
        result.addAll(getCandidates(letter, digit, false, false));
        result.addAll(getCandidates(letter, digit, true, false));
        result.addAll(getCandidates(letter, digit, false, true));
        result.addAll(getCandidates(letter, digit, true, true));
        return result;
    }

    private static Set<String> getCandidates(String letter, Integer digit, boolean isDown, boolean isRight){
        var column = LETTERS.get(letter) + (isRight ? 1 : -1);
        var row = digit + (isDown ? 1 : -1);
        var result = new HashSet<String>();
        while ((isRight ? MAX >= column : MIN < column)
                && (isDown ? MAX >= row : MIN < row)) {
            result.add(REVERSE_LETTERS.get(column) + row);
            column = isRight ? column + 1 : column - 1;
            row = isDown ? row + 1 : row - 1;
        }
        return result;
    }

}

