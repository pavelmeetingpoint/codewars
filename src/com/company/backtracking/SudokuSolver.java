package com.company.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Write a function that solve sudoku puzzle
 */
public class SudokuSolver {

    private static final int SHAPE = 9;
    private static final int GRID = 3;
    private static final int EMPTY = 0;
    private static final Set<Integer> DIGITS = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final int[] DIGITS2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static int[][] solve(int[][] board){
        validate(board);
        List<int[][]> solutions = new ArrayList<>();
        search(board, solutions);
        if(solutions.size() == 1){
            return solutions.get(0);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public static void validate(int[][] board){
        if (board.length != SHAPE) {
            throw new IllegalArgumentException();
        }
        int countOfUnknown = 0;
        for(int i = 0; SHAPE > i; i++) {
            if (board[i].length != SHAPE) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; SHAPE > j; j++) {
                if(Arrays.binarySearch(DIGITS2, board[i][j]) < 0 && board[i][j] != 0){
                    throw new IllegalArgumentException();
                }
                if(board[i][j] == 0){
                    countOfUnknown++;
                }
            }
        }
        if(countOfUnknown == 0){
            throw new IllegalArgumentException();
        }
    }

    private static void search(int[][] board, List<int[][]> solutions){
        if(isStateValid(board)){
            solutions.add(copyBoard(board));
            if(solutions.size() > 1){
                throw new IllegalArgumentException();
            }
            return;
        }
        for(int i = 0; SHAPE > i; i++){
            for(int j = 0; SHAPE > j; j++){
                if(board[i][j] == EMPTY){
                    for(int candidate : getCandidates(board, i, j)){
                        board[i][j] = candidate;
                        search(board, solutions);
                        board[i][j] = EMPTY;
                    }
                    return;
                }
            }
        }
    }

    private static boolean isStateValid(int[][] board){
        return validateRows(board) && validateColums(board) && validateBoxes(board);
    }

    private static int[] getCandidates(int[][] board, int row, int column){
        boolean[] set = new boolean[SHAPE+1];
        addDigitsToSet(set, getRowDigits(board, row));
        addDigitsToSet(set, getColDigits(board, column));
        addDigitsToSet(set, getBoxDigits(board, row, column));
        set[EMPTY] = true;
        return getNotFoundFromSet(set);
    }

    private static Set<Integer> getCandidatesOld(int[][] board, int row, int column){
        var set = new HashSet<Integer>();
        set.addAll(Arrays.stream(getRowDigits(board, row)).boxed().collect(Collectors.toSet()));
        set.addAll(Arrays.stream(getColDigits(board, column)).boxed().collect(Collectors.toSet()));
        set.addAll(Arrays.stream(getBoxDigits(board, row, column)).boxed().collect(Collectors.toSet()));
        set.remove(EMPTY);
        Set<Integer> difference = new HashSet<>(DIGITS);
        difference.removeAll(set);
        return difference;
    }

    private static void addDigitsToSet(boolean[] set, int[] digits) {
        for (int digit : digits) {
            if(!set[digit]) {
                set[digit] = true;
            }
        }
    }

    private static int[] getNotFoundFromSet(boolean[] set) {
        int count = 0;
        for (boolean isNotPresent : set) {
            if (!isNotPresent) {
                count++;
            }
        }
        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < set.length; i++) {
            if (!set[i]) {
                result[index++] = i;
            }
        }
        return result;
    }

    private static int[] getRowDigits(int[][] board, int row){
        var set = new int[SHAPE];
        for(int col = 0; board[row].length > col; col++){
            set[col] = board[row][col];
        }
        return set;
    }

    private static int[] getColDigits(int[][] board, int col){
        var set = new int[SHAPE];
        for(int row = 0; board.length > row; row++){
            set[row] = board[row][col];
        }
        return set;
    }

    private static int[] getBoxDigits(int[][] board, int row, int col){
        var set = new int[SHAPE];
        var index = 0;
        for(int boxIndexRow = 0; SHAPE > boxIndexRow; boxIndexRow += GRID) {
            for (int boxIndexColumn = 0; SHAPE > boxIndexColumn; boxIndexColumn += GRID) {
                if(boxIndexRow <= row && row < GRID + boxIndexRow && boxIndexColumn <= col && col < GRID + boxIndexColumn){
                    for (int i = boxIndexRow; GRID + boxIndexRow > i; i++) {
                        for (int j = boxIndexColumn; GRID + boxIndexColumn > j; j++) {
                            set[index] = board[i][j];
                            index++;
                        }
                    }
                }
            }
        }
        return set;
    }

    private static boolean validateRows(int[][] board){
        for (int row = 0; SHAPE > row; row++) {
            var digits = new int[SHAPE];
            for (int col = 0; SHAPE > col; col++) {
                digits[col] = board[row][col];
            }
            if(!isValid(digits)){
                return false;
            }
        }
        return true;
    }
    private static boolean validateColums(int[][] board){
        for (int col = 0; SHAPE > col; col++) {
            var digits = new int[SHAPE];
            for (int row = 0; SHAPE > row; row++) {
                digits[row] = board[row][col];
            }
            if(!isValid(digits)){
                return false;
            }
        }
        return true;
    }

    private static boolean validateBoxes(int[][] board){
        for(int boxIndexRow = 0; SHAPE > boxIndexRow; boxIndexRow += GRID) {
            for (int boxIndexColumn = 0; SHAPE > boxIndexColumn; boxIndexColumn += GRID) {
                var digits = new int[SHAPE];
                var index = 0;
                for (int row = boxIndexRow; GRID+boxIndexRow > row; row++) {
                    for (int col = boxIndexColumn; GRID + boxIndexColumn > col; col++) {
                        digits[index] = board[row][col];
                        index++;
                    }
                }
                if(!isValid(digits)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[] array) {
        Arrays.sort(array);
        return Arrays.equals(array, DIGITS2);
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[SHAPE][SHAPE];
        for (int i = 0; SHAPE > i; i++) {
            for (int j = 0; SHAPE > j; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

}

